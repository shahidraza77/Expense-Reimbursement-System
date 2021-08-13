package com.rimb.emp;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rim.emp.utils.HibernateUtils;

/**
 * Servlet implementation class ApproveReject
 */
public class ApproveReject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveReject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();  
	
		Session session=HibernateUtils.getSessionFactory().openSession();
	    Transaction tx=session.beginTransaction();
	    String s1="Approve",s2="Rejected";
	    //Query q=session.createQuery("update Request set status=s1 where amount>10000"); 
	    //Query q2=session.createQuery("update Request set status=s2 where amount <=10000");
	    String qryString = "update Request s set s.status='Rejected' where s.amount>10000";
        Query query = session.createQuery(qryString);
        int count = query.executeUpdate();
        
        String qryString2 = "update Request s set s.status='Approved' where s.amount <=10000";
        Query query1 = session.createQuery(qryString2);
        int count2 = query1.executeUpdate();
	    //int status=q.executeUpdate();
	    //int status2=q2.executeUpdate(); 
	    //System.out.println(status); 
	    
	    RequestDispatcher rd=request.getRequestDispatcher("/manager.html");  
        rd.include(request, response); 
        out.println("<h2 style = color:green>Action performed sucessfully <h2>");
	    tx.commit();
	    session.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
