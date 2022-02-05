package com.abonnement.web;

import java.util.List;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=request.getParameter("login");
		String password=request.getParameter("password");
		String DernierChiff=request.getParameter("dernierChiff");
		String Ncarte=request.getParameter("Ncarte");	
		String DateExpi=request.getParameter("dateExpiration");
		HttpSession session1 = request.getSession( true );
		HttpSession session2 = request.getSession( true );
		LoginBean loginBean = new LoginBean();
		loginBean.setLogin(username);
		loginBean.setPassword(password);
		
		LoginDao loginDao = new LoginDao();
		if(loginDao.validate(loginBean)) 
		{
		    List<LoginBean> c=loginDao.consulter(loginBean);
		   
		    if(c.get(0).getStatus()!=0) {
		    	response.sendRedirect("Accueil.jsp");
		    }else {
		    	 HttpSession session = request.getSession( true );
				 session.setAttribute( "connectedUser", c.get(0));
				 session2.setAttribute("DernierChiff","");
				 session2.setAttribute("Ncarte","");
				 session2.setAttribute("DateExpi","");
				 session2.setAttribute("ErreurPaiement",null);
		    	 response.sendRedirect("LoginSuccess.jsp"); 
		    }	
		}
		else {
			if(username!=null && password!=null && request.getParameter("btn")!=null) {
				
				session1.setAttribute( "messageEreur",1);
				session1.setAttribute( "login",username);
				session1.setAttribute( "password",password);
				response.sendRedirect("Login.jsp");
			}
			else if(username==null && password==null && request.getParameter("btn")==null) {
				session1.setAttribute( "messageEreur",0);
				response.sendRedirect("Login.jsp");
	    	}else {
			HttpSession session = request.getSession( true );
			session1.setAttribute( "messageEreur",1);
			session1.setAttribute( "login",username);
			session1.setAttribute( "password",password);
			response.sendRedirect("Login.jsp");
		  }
			
	}

	}
}