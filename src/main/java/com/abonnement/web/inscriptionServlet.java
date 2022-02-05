package com.abonnement.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abonnement.bean.LoginBean;
import com.abonnement.bean.LoginDao;

/**
 * Servlet implementation class inscriptionServlet
 */
@WebServlet("/inscription")
public class inscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("inscription.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("name");
		String password1=request.getParameter("password1");
		String passwordconf=request.getParameter("passwordconf");
		String mail=request.getParameter("mail");	
		String numero=request.getParameter("numero");
		 int random_int = (int)Math.floor(Math.random()*(500-199+1)+199);
		 HttpSession session4 = request.getSession( true );
		LoginBean loginBean= new LoginBean();
		loginBean.setLogin(username);
		loginBean.setEmail(mail);
		loginBean.setNumero(numero);
		loginBean.setPassword(password1);
		loginBean.setMontant(random_int);
		LoginDao loginDao = new LoginDao();
		if(!loginDao.ValidateInscription(loginBean) && loginDao.validatpasswords(password1, passwordconf))
		{
			session4.setAttribute( "messageInscription", 0);
			loginDao.ajouterUtilisateur(loginBean);
			response.sendRedirect("inscription.jsp");
			
		}
		else if(loginDao.ValidateInscription(loginBean) )
		{	 
			session4.setAttribute( "messageInscription", 1);
			response.sendRedirect("inscription.jsp");
		}
		else if(!loginDao.validatpasswords(password1, passwordconf)) 
		{
			session4.setAttribute( "messageInscription", 2);
			response.sendRedirect("inscription.jsp");
		}
	}

}
