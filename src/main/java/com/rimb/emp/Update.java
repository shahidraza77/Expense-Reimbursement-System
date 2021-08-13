package com.rimb.emp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jvnet.hk2.internal.SystemDescriptor;

import com.rim.emp.utils.HibernateUtils;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
          
	    HttpSession se=request.getSession();  
          String email=null;
          String address=null;
          long phone=0;
           
            
           int val=  (Integer)se.getAttribute("name");
          email=request.getParameter("email");
          address=request.getParameter("address");
          System.out.println(email+" "+address);
          //phone=Long.parseLong(request.getParameter("phone"));
//          String hql = "UPDATE Employee E set " +
//                  "STORNO = :Storno," +
//                  "NAME = :Name " +
//                   ......  
//                  "where ID = :BuchungID";

//        Query qr = session.createSQLQuery(hql);
//
//        qr.setParameter("Storno","sto_value");
//
//        qr.setParameter("Name","name_value");

    
         System.out.println(val);
//         if(email!=null && address!=null)
//         {
//	         String hql = "Update Employee E set E.email=:email,E.address=:address WHERE E.id =:val";
//	 	     Query query = session.createQuery(hql);
//	 	    query.setParameter("val",val);
//	 	   query.setParameter("email",email);
//	 	  query.setParameter("address",email);
//	 	    query.executeUpdate();
//         }
          if(email.length()>0 && address.length()>0)
         {
        	 String hql = "Update Employee E set E.email=email,E.address=address WHERE E.id =:val";
	 	     Query query = session.createQuery(hql);
	 	    query.setParameter("val",val);
	 	    query.executeUpdate(); 
	 	     tx.commit();
	 	    session.close();
         }
//         else if(email!=null && phone!=0)
//         {
//        	 String hql = "Update Employee E set E.email=email,E.phone=phone WHERE E.id =:val";
//	 	     Query query = session.createQuery(hql);
//	 	    query.setParameter("val",val);
//	 	    query.executeUpdate();  
//         }
//         else if(email!=null && address!=null)
//         {
//        	 String hql = "Update Employee E set E.email=email,E.address=address WHERE E.id =:val";
//	 	     Query query = session.createQuery(hql);
//	 	    query.setParameter("val",val);
//	 	    query.executeUpdate();  
//         }
         else if(email.length()>0)
         {
        	 String hql = "Update Employee E set E.email=:email WHERE E.id =:val";
	 	     Query query = session.createQuery(hql);
	 	    query.setParameter("val",val);
	 	   query.setParameter("email",email);
	 	    query.executeUpdate();
           tx.commit();
	 	    session.close();
        }
         else if(address.length()>0)
         {
        	 String hql = "Update Employee E set E.address=:address WHERE E.id =:val";
	 	     Query query = session.createQuery(hql);
	 	    query.setParameter("val",val);
	 	    query.setParameter("address",address);
	 	    query.executeUpdate();
	 	    tx.commit();
	 	    session.close();
         }
//         else if(phone!=0)
//         {
//        	 String hql = "Update Employee E set E.phone=phone WHERE E.EMP_ID =:val";
//	 	     Query query = session.createQuery(hql);
//	 	    query.setParameter("val",val);
//	 	    query.executeUpdate();  
//         }
         request.getRequestDispatcher("profile.html").include(request, response);  
         out.print("<h2>Updated Sucessfully!<h2>");     
         out.close(); 
	}

}
