package com.ashwin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ashwin.dao.Dao;
import com.ashwin.dao.EmployeeImpl;
import com.ashwin.model.Employee;
import com.sun.jersey.multipart.FormDataParam;

@Path("/myservice")
public class AshwinController {
	
	 
	
	@GET
	@Path("/getAllEmps")
	@Produces("text/html")
	public String getEmps() throws Exception{
	Dao employeeImpl = new EmployeeImpl();
		List<Employee> emps = employeeImpl.getAllRecords();
		JSONArray arr = new JSONArray();
		
		for(Employee e: emps)
		{
			//System.out.println(e.getId()+"   "+e.getName()+"   "+e.getEmail());
			JSONObject obj = new JSONObject();
			obj.append("Id",e.getId());
			obj.append("Name", e.getName());
			obj.append("Email", e.getEmail());
			arr.put(obj);
		}
		return arr.toString();
	}
	
	@GET
	@Path("/welcome")
	@Produces("application/json")
	public String welcomeMsg() {
		return "---->Welcome to Jersey RESTFul Web Services";
	}
	
	@GET
	@Path("/welcome1")
	@Produces("text/html")
	public String welcomeMsg1(
			@QueryParam("qName") String name,
			@QueryParam("qEmail") String email,
			@QueryParam("qAge") int age
			) 
	
	{
		return "Welcome :"+name+" Your email is :"+email+"  Age is :"+(age-1);
	}
	
	@GET
	@Path("/welcome2/{qName}/{qEmail}/{qAge}")
	@Produces("text/html")
	public String welcomeMsg2(
			@PathParam("qName") String name,
			@PathParam("qEmail") String email,
			@PathParam("qAge") int age
			) 
	
	{
		return "Welcome :"+name+" Your email is :"+email+"  Age is :"+(age-1);
	}
	
	@GET
	@Path("/welcome3")
	@Produces("text/html")
	public String welcomeMsg3(
			@MatrixParam("qName") String name,
			@MatrixParam("qEmail") String email,
			@MatrixParam("qAge") int age
			) 
	
	{
		return "Welcome :"+name+" Your email is :"+email+"  Age is :"+(age-1);
	}
	
	@POST
	@Path("/upload")
	@Produces("text/html")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String fileUpload(
			@FormDataParam("file") InputStream is
			) throws Exception
	{
		
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, nRead);
		}

		buffer.flush();
		System.out.println(buffer.toByteArray().length);
		return "File uploaded Sucessfully";
	}
	

}
