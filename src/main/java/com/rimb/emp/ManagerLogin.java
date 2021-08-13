package com.rimb.emp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rim.emp.model.Employee;
import com.rim.emp.model.Manager;
import com.rim.emp.utils.HibernateUtils;

/**
 * Servlet implementation class ManagerLogin
 */
public class ManagerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();  
		Manager e1=null;
		Session session=HibernateUtils.getSessionFactory().openSession();
	    Transaction tx=session.beginTransaction();
	   // int val=Integer.parseInt(request.getParameter("uid"));
//	     e1 = (Manager)session.createQuery("FROM Manager E WHERE E.id = :val")
//        		.setParameter("val", val).uniqueResult();
	      //e1 =session.get(Manager.class,val);
	    int val=Integer.parseInt(request.getParameter("uid"));
	      e1 =session.get(Manager.class,val);
	      if(session==null)
	        System.out.println("session not created");   

	       
	       if(e1==null)
	       {
	    	   System.out.println("wrong data");
	    	   out.print("<h2 style=text-align:center;color:red>Wrong user id or Password</h2>");  
	           RequestDispatcher rd=request.getRequestDispatcher("/login.html");  
	           rd.include(request, response); 

	       }
	       else if(e1.getId()==val)
	       {
	    	   HttpSession ses=request.getSession();  
	           ses.setAttribute("name",val);  
		    System.out.println("loged sucessfully");
		      response.sendRedirect("manager.html");
	
	       }
	       else
	       {
	    	    
	    	   System.out.println("opps!!");
	       }
	       tx.commit();
	       session.close();

	}

}
