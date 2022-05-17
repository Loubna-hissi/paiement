package com.abonnement.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abonnement.bean.CardBean;
import com.abonnement.bean.LoginAgent;
import com.abonnement.bean.LoginBean;
import com.abonnement.bean.LoginDao;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class paiementServlet
 */
@WebServlet("/paiement")
public class paiementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paiementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect( "LoginSuccess.jsp" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DernierChiff=request.getParameter("dernierChiff");
		String Ncarte=request.getParameter("Ncarte");	
		String DateExpi=request.getParameter("dateExpiration");
	   	HttpSession session = request.getSession( true );
		LoginBean loginBean=(LoginBean) session.getAttribute( "connectedUser" );
		LocalDate date = LocalDate.parse(DateExpi);
		if(loginBean!=null) {
			LoginDao loginDao = new LoginDao();
			int test=loginDao.paiementValidation(loginBean,Ncarte,DernierChiff,date);
			if(test!=0 && test!=7 && request.getParameter("validerbtn")!=null){
				
				  HttpSession session1 = request.getSession( true );
				  session1.setAttribute("DernierChiff",DernierChiff);
				  session1.setAttribute("Ncarte",Ncarte);
				  session1.setAttribute("DateExpi",date);
				  session1.setAttribute("ErreurPaiement",test);
				  response.sendRedirect( "LoginSuccess.jsp" );
				
			}else if(test==7) {
				  HttpSession session1 = request.getSession( true );
				  session1.setAttribute("DernierChiff",DernierChiff);
				  session1.setAttribute("Ncarte",Ncarte);
				  session1.setAttribute("DateExpi",date);
				  session1.setAttribute("ErreurPaiement",test);
				  loginDao.Email(loginBean);
				  response.sendRedirect( "LoginSuccess.jsp" );
			
			}else if(test==0 && request.getParameter("validerbtn")!=null){
				String masterPath = request.getServletContext().getRealPath("/WEB-INF/recu.pdf" );
		        response.setContentType( "application/pdf" );
		        loginBean.setStatus(1);
		        loginDao.modifierUser(loginBean);
				loginDao.recuCreatin(loginBean,masterPath);
				loginDao.Email1(loginBean,masterPath);
				response.sendRedirect( "PaiementSuccess.jsp" );
				
			}
		}else{
			  request.getSession().setAttribute("connectedUser", null);
		      session.invalidate();
		      response.sendRedirect("Login.jsp");
		      return;
		}
		
	}

}
