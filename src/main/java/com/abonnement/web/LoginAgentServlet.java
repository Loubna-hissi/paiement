package com.abonnement.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abonnement.bean.LoginAgent;
import com.abonnement.bean.LoginBean;
import com.abonnement.bean.LoginDao;


/**
 * Servlet implementation class LoginAgentServlet
 */
@WebServlet("/loginAgent")
public class LoginAgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("LoginAgent.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=request.getParameter("loginAgent");
		String password=request.getParameter("passwordAgent");
		
		LoginAgent  loginAgent= new LoginAgent();
		loginAgent.setLogin(username);
		loginAgent.setPassword(password);
		
		LoginDao loginDao = new LoginDao();
		if(loginDao.validateAgent(loginAgent)) 
		{
			List<LoginBean> c=loginDao.listTous();
		    HttpSession session = request.getSession( true );
            session.setAttribute( "consultUser", c);
			response.sendRedirect("LoginSuccessAgent.jsp");
		}
		else {
			response.sendRedirect("LoginAgent.jsp");
		}
	}

}
