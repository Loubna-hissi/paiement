package com.abonnement.bean;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.abonnement.util.JPAUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;


@SuppressWarnings("unused")
public class LoginDao {
	private EntityManager 
	entityManager=JPAUtil.getEntityManager("AppLoginJPA");
	
	//validation User
	public boolean validate(LoginBean loginBean) {
		boolean status=false;
		    TypedQuery<LoginBean> query = entityManager.createQuery("SELECT c FROM LoginBean c WHERE c.login = :name AND c.password = :pass", LoginBean.class);
		    query.setParameter("name", loginBean.getLogin());
		    query.setParameter("pass", loginBean.getPassword());
		    try {
		    	LoginBean c = (LoginBean) query.getSingleResult();
		    	System.out.print(c.toString());
		        status= true;
		    }catch(Exception e){};
		        
		    return status;
	}
	public boolean ValidateInscription(LoginBean loginBean) {
		boolean status=false;
		TypedQuery<LoginBean> query = entityManager.createQuery("SELECT c FROM LoginBean c WHERE c.login = :name AND c.email = :email", LoginBean.class);
	    query.setParameter("name", loginBean.getLogin());
	    query.setParameter("email", loginBean.getEmail());
	    try {
	    	LoginBean c = (LoginBean) query.getSingleResult();
	        status= true;
	    }catch(Exception e){};
		return status;
	}
	public boolean validatpasswords(String password,String passwordconf) {
		if(!(password.equals(passwordconf))){
			return false;
		}
		return true;
	}
	public void ajouterUtilisateur(LoginBean loginBean)
	{
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(loginBean);
	tx.commit();
	 
	}

	public boolean validateAgent(LoginAgent loginAgent) {
		boolean status=false;
		  TypedQuery<LoginAgent> query = entityManager.createQuery("SELECT c FROM LoginAgent c WHERE c.loginAgent = :name AND c.passwordAgent = :pass", LoginAgent.class);
		    query.setParameter("name", loginAgent.getLogin());
		    query.setParameter("pass", loginAgent.getPassword());
		    try {
		    	LoginAgent c = (LoginAgent) query.getSingleResult();
		        status= true;
		    }catch(Exception e){};
		        
		    return status;
	}
	
	public List<LoginBean> consulter(LoginBean loginBean) {
		List<LoginBean> client =entityManager.createQuery("SELECT c FROM LoginBean c WHERE c.login = :name")
	.setParameter("name",loginBean.getLogin()).getResultList();
	return client;
    }
	public boolean validateCard(String nuCarte,String dernierChiff) {
		boolean status=false;
		  TypedQuery<CardBean> query = entityManager.createQuery("SELECT c FROM CardBean c WHERE c.numeroCarte = :name AND c.DernierChiff = :pass", CardBean.class);
		    query.setParameter("name",nuCarte);
		    query.setParameter("pass",dernierChiff);
		    try {
		    	CardBean c = (CardBean) query.getSingleResult();
		        status= true;
		    }catch(Exception e){};
		        
		    return status;
	}
	public List<CardBean> ValidateMontant(String nuCarte,String dernierChiff) {
		List<CardBean> card =entityManager.createQuery("SELECT c FROM CardBean c WHERE c.numeroCarte = :name AND c.DernierChiff = :pass")
	.setParameter("name",nuCarte).setParameter("pass",dernierChiff).getResultList();
	return card;
    }
	@SuppressWarnings("unlikely-arg-type")
	public int paiementValidation(LoginBean loginBean,String nuCarte,String dernierChiff,LocalDate daeExpi) {
		  if(!(nuCarte.chars().allMatch( Character::isDigit )))
		  {
	    	  return 1;
	      }
		  else if(nuCarte.length()!=16) 
		  {
			  return 2;
		  }
		 /* else if(!(loginBean.getNumeroCarte().equals(nuCarte))){
			   return "Numéro de carte est incorrect";
		   }*/
		  else if(dernierChiff.length()!=3) 
		  {
			  return 3;
		  }
		  /*else if(!(loginBean.getDernierChiff().equals(dernierChiff))) {
			  return 4;
		  }*/
		  else if(daeExpi.isBefore(LocalDate.now()))
		  {
			  return 5;
		  }
		  else if(!validateCard(nuCarte,dernierChiff)) 
		  {
			  return 6;
		  }
		  else if(ValidateMontant(nuCarte,dernierChiff).get(0).getSolde()<loginBean.getMontant()) 
		  {
			  return 7;
		  }
		
		   return 0;
	}
	
 	public void recuCreatin(LoginBean loginBean,String masterPath) {
		Document dc = new Document();
		try {
			Font blue =FontFactory.getFont(FontFactory.COURIER,14,Font.BOLD,new CMYKColor(255,0,0,0));
			PdfWriter.getInstance(dc, new FileOutputStream(masterPath));
			dc.open();
			dc.add(new Paragraph("Reçu De Paiement Téléphonique",blue));
			dc.add(new Paragraph("--------------------------------------"));
			dc.add(new Paragraph("A Mr/M :"+loginBean.getLogin()));
			dc.add(new Paragraph("Bienvenu Mr/M : "+loginBean.getLogin()+" votre paiement a été effectuer avec succès"));
			dc.add(new Paragraph("vous avez payé un montant de :"+loginBean.getMontant()+"DH"));
			dc.add(new Paragraph("--------------------------------------"));
			dc.close();
			
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
 	public static void Email(LoginBean loginBean) {
		try{
			
			final String fromEmail = "testjava665@gmail.com";
            final String password = "testJava...!"; //fromEmail password 
            System.out.println("Email configuration code start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host set by default this
            props.put("mail.smtp.port", "587"); //TLS Port you can use 465 insted of 587
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() 
            {
            
                protected PasswordAuthentication getPasswordAuthentication() 
                {
                        return new PasswordAuthentication(fromEmail, password);
                }
            };
			            Session session = Session.getInstance(props, auth);
			
			            MimeMessage message = new MimeMessage(session);
			            message.setFrom(new InternetAddress(fromEmail));
			            message.addRecipient(Message.RecipientType.TO, new InternetAddress(loginBean.getEmail()));
			            message.setSubject("Paiement échoué");
			 
			                    
			            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
			         
			            Multipart multipartstp = new MimeMultipart();
			            
			            messageBodyPart1.setText("Salut Mr/M : "+ loginBean.getLogin()+" Votre Paiement est échoué  puisque votre solde est insuffisant ! \n Abientôt");//here you can write a msg what you want to send... just remove String parameter in alertByEmail method oherwise call parameter
			        
			            multipartstp.addBodyPart(messageBodyPart1);        
			            message.setContent(multipartstp);
			            Transport.send(message);//here mail sending process start.
			            System.out.println("Mail Sent Successfully");
        }
		catch(Exception ex)
		{
			            System.out.println("Mail fail");
			            System.out.println(ex);
        }
		
}
	public static void Email1(LoginBean loginBean,String file) {
			try{
				
				final String fromEmail = "testjava665@gmail.com";
	            final String password = "testJava...!"; //fromEmail password 
	            System.out.println("Email configuration code start");
	            Properties props = new Properties();
	            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host set by default this
	            props.put("mail.smtp.port", "587"); //TLS Port you can use 465 insted of 587
	            props.put("mail.smtp.auth", "true"); //enable authentication
	            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
	            //create Authenticator object to pass in Session.getInstance argument
	            Authenticator auth = new Authenticator() 
	            {
	            
	                protected PasswordAuthentication getPasswordAuthentication() 
	                {
	                        return new PasswordAuthentication(fromEmail, password);
	                }
	            };
				            Session session = Session.getInstance(props, auth);
				
				            MimeMessage message = new MimeMessage(session);
				            message.setFrom(new InternetAddress(fromEmail));
				            message.addRecipient(Message.RecipientType.TO, new InternetAddress(loginBean.getEmail()));
				            message.setSubject("Paiement");
				            /*Pdf*/
				            
				            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
				            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				            Multipart multipartstp = new MimeMultipart();
				            String fileName = "reçu.pdf";
				            DataSource source = new FileDataSource(file);
				            
				            messageBodyPart1.setText("Salut Mr/M :" +loginBean.getLogin()+ " votre paiement a été effectué avec succès !");//here you can write a msg what you want to send... just remove String parameter in alertByEmail method oherwise call parameter
				            messageBodyPart2.setDataHandler(new DataHandler(source));
				            messageBodyPart2.setFileName(fileName);
				             
				            multipartstp.addBodyPart(messageBodyPart1);
				            multipartstp.addBodyPart(messageBodyPart2);

				            message.setContent(multipartstp);
				            Transport.send(message);//here mail sending process start.
				            System.out.println("Mail Sent Successfully");
	        }
			catch(Exception ex)
			{
				            System.out.println("Mail fail");
				            System.out.println(ex);
	        }
			
	}
	public void modifierUser(LoginBean c)
	{
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
       	entityManager.merge(c);
    	tx.commit();
	 
	}

	public List<LoginBean> listTous() {
		List<LoginBean> clients =entityManager.createQuery("SELECT c FROM LoginBean c").getResultList();
	return clients;
    } 
	
}