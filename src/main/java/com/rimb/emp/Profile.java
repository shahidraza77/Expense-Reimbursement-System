package com.rimb.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.rim.emp.model.Employee;
import com.rim.emp.model.Request;
import com.rim.emp.utils.HibernateUtils;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
          
	    HttpSession se=request.getSession();  
          
         int val=  (Integer)se.getAttribute("name");
         System.out.println(val);
         String hql = "FROM Employee E WHERE E.id =:val";
 	    Query query = session.createQuery(hql);
 	    query.setParameter("val",val);
 	     ArrayList<Employee> list = new ArrayList<Employee>();
 	    for(final Object o : query.list()) {
 	        list.add((Employee)o);
 	    }
 	    
 	    
         if (list.size()>=1) {
         	
         	
        	 RequestDispatcher rd=request.getRequestDispatcher("/profile.html");  
	           rd.include(request, response);
             
 	           out.println("<table border=1 >");  
 	           out.println("<tr><th>EmpId</th><th>EmpName</th><th>Email</th><th>Address</th><th>Gender</th><th>Phone</th><tr>");
             
             for(Employee li: list)
             {
             	//System.out.println(rs.getStatus());
             	int cust_id=li.getId();
         		String cust_name=li.getName();
         		String gender=li.getGender();
         		String email=li.getEmail();
         		long phone=li.getPhone();
         		String address=li.getAddress();
         		int age=li.getAge();
         		out.println("<tr><td>" + cust_id + "</td><td>" + cust_name + "</td><td>" + email + "</td><td>" + address + "</td><td>"+ gender+ "</td><td>"+ phone+"</td</tr>"); 
             
             
             }
             
             

 	       }
 	 
 	       else
 	       {
 	    	    
 	    	   System.out.println("opps!!");
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
