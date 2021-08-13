package com.rimb.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rim.emp.model.Request;
import com.rim.emp.utils.HibernateUtils;

/**
 * Servlet implementation class Pending
 */
public class Pending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pending() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();  
	
		Session session=HibernateUtils.getSessionFactory().openSession();
	    Transaction tx=session.beginTransaction();
	    String hql = "FROM Request E WHERE E.status =:val";
	    Query query = session.createQuery(hql);
	    query.setParameter("val","pending");
	     ArrayList<Request> li = new ArrayList<Request>();
	    for(final Object o : query.list()) {
	        li.add((Request)o);
	    }
	    
if (li.size()>=1) {
        	
	//PrintWriter out= response.getWriter();
	//out.println ("<form ACTION = 'ProcessButtons' method='Post'>" );
	out.println("<table border=1 width=50% height=50% >");  
    out.println("<tr><th>EmpId</th><th>EmpName</th><th>Status</th><th>Retype</th><th>Amount</th><tr>");  
//    <td>" + "<button type='submit' name='Submit1' >Approve</button>" + "</td><td>" + "<button type='submit' name='Submit2' >Reject</button>"+"
	for(int i=0;i<li.size();i++) {
		int cust_id=li.get(i).getEmpid();
		String cust_name=li.get(i).getName();
		String cust_contact=li.get(i).getStatus();
		String cust_Pan=li.get(i).getRetype();
		long phone=li.get(i).getPhone();
		out.println("<tr><td>" + cust_id + "</td><td>" + cust_name + "</td><td>" + cust_contact + "</td><td>" + cust_Pan + "</td><td>"+ phone+ "</td></tr>"); 
		
	} 
	     
           
	           RequestDispatcher rd=request.getRequestDispatcher("/manager.html");  
	           rd.include(request, response);

	   

	       }
		else
		{
					    
			RequestDispatcher rd=request.getRequestDispatcher("/manager.html");  
	        rd.include(request, response); 
	        out.println("<h2 style = color:green>There is no pending request <h2>");
		}
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
