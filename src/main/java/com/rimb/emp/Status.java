package com.rimb.emp;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rim.emp.model.Employee;
import com.rim.emp.model.Request;
import com.rim.emp.utils.HibernateUtils;

import antlr.collections.List;

/**
 * Servlet implementation class Status
 */
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Status() {
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
	
		Session session=HibernateUtils.getSessionFactory().openSession();
	    Transaction tx=session.beginTransaction();
	    int val=Integer.parseInt(request.getParameter("emid"));
//	    Request r = (Request)session.createQuery("FROM Request E WHERE E.empid = :val")
//        		.setParameter("val",val);
//	    String st=r.getStatus();
	    //System.out.println(st);
	    String hql = "FROM Request E WHERE E.empid =:val";
	    Query query = session.createQuery(hql);
	    query.setParameter("val",val);
	     ArrayList<Request> list = new ArrayList<Request>();
	    for(final Object o : query.list()) {
	        list.add((Request)o);
	    }
	    
	    
        if (list.size()>=1) {
        	
        	RequestDispatcher rd=request.getRequestDispatcher("/profile.html");  
	           rd.include(request, response);
            
            
	           out.println("<table border=1 width=50% height=50% >");  
	           out.println("<tr><th>EmpId</th><th>EmpName</th><th>Status</th><th>Retype</th><th>Amount</th><tr>");
            
            for(Request li: list)
            {
            	//System.out.println(rs.getStatus());
            	int cust_id=li.getEmpid();
        		String cust_name=li.getName();
        		String status=li.getStatus();
        		String retype=li.getRetype();
        		long phone=li.getPhone();
        		out.println("<tr><td>" + cust_id + "</td><td>" + cust_name + "</td><td>" + status + "</td><td>" + retype + "</td><td>"+ phone+ "</td></tr>"); 
            
            
            }
            
	   

	       }
	 
	       else
	       {
	    	    
	    	   System.out.println("opps!!");
	       }
	       tx.commit();
	       session.close();
	}
}

