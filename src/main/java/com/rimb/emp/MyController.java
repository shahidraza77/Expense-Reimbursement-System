package com.rimb.emp;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rim.emp.dao.EmployeeDAO;
import com.rim.emp.dao.EmployeeDAOImp;
import com.rim.emp.model.Employee;
import com.rim.emp.model.Request;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("employee")
public class MyController {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    //@Path("/login/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	//EmployeeDAO dao=new EmployeeDAOImp();
    	//ObjectMapper mapper = new ObjectMapper();
    	//Employee e=dao.getEmployee(getIt())
        return "Got it!";
    }
    @POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String addPerson(String req) throws JsonProcessingException{
    	ObjectMapper mapper = new ObjectMapper();
		Employee r =null;
		String result=null;
		Authentication a=new Authentication();
		EmployeeDAO personDao= new EmployeeDAOImp();
		try {
		    r=mapper.readValue(req,Employee.class);
	        System.out.println(r.getName());
		    result=personDao.addEmployee(r);
		    }
		 catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(r==null)
			a.setStatus("false");
		else
		a.setStatus("true");
		
		return mapper.writeValueAsString(a);  
  }
    
    @POST
	@Path("/request")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
	public String addEmpReq(@FormParam("id") int id,@FormParam("name") String name, @FormParam("email") String email, @FormParam("phone") Long phone, @FormParam("rtype") String retype, @FormParam("amount") long amount) throws JsonProcessingException{
		Request r =new Request();
		r.setName(name);
		r.setAmount(amount);
		r.setEmail(email);
		r.setRetype(retype);
		r.setEmpid(id);
		r.setPhone(amount);
		r.setStatus("pending");
		String result=null;
		Authentication a=new Authentication();
		EmployeeDAO personDao= new EmployeeDAOImp();
		    
	        System.out.println(r.getName());
		    result=personDao.addRequest(r);
		
		return result;  
  }
}
