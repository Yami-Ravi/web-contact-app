package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	List<Contact> contactlist=new ArrayList<Contact>();
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RequestMapping(value="/getdata",method=RequestMethod.GET)
	public ResponseEntity<Object> getData()
	{
		Collections.sort(contactlist);
		return new ResponseEntity<>(contactlist,HttpStatus.OK);
	}
	
	@RequestMapping("/validatenumber")
	public @ResponseBody String checkvalidity(HttpServletRequest req)
	{
		String mn=req.getParameter("phonenumber");
		for(Contact c:contactlist)
		{
			if(c.getPhonenumber().equals(mn))
			{
				System.out.println("Success");
				return "Success";
			}
		}
		System.out.println("Fail"+mn);
		return "Fail";
	}
	@RequestMapping(value="/searchdata/{name}",method=RequestMethod.GET)
	public ResponseEntity<Object> searchData(@PathVariable String name)
	{
		System.out.println("Inside");
		Contact r=null;
		for(Contact c:contactlist)
		{
			if(c.getName().equals(name))
			{
				
				r=c;
				break;
			}
		}
		return new ResponseEntity<>(r,HttpStatus.OK);
		
	}
	@RequestMapping(value="/postdata",method=RequestMethod.POST)
	public ResponseEntity<Object> postData(@RequestBody Contact contact)
	{
		Contact c =new Contact();
		c.setName(contact.getName());
		c.setPhonenumber(contact.getPhonenumber());
		c.setEmail(contact.getEmail());
		contactlist.add(c);
		return new ResponseEntity<>("Success",HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deletedata/{name}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteData(@PathVariable String name)
	{
		
		for(Contact c:contactlist)
		{
			if(c.getName().equals(name))
			{
				
				contactlist.remove(c);
				break;
			}
		}
		return new ResponseEntity<>("Success",HttpStatus.OK);
			
	}
	
	@RequestMapping(value="/editdata",method=RequestMethod.POST)
	public ResponseEntity<Object> editData(@RequestBody Contact contact)
	{
		for(Contact c:contactlist)
		{
			if(c.getName().equals(contact.getName()))
			{
				
				c.setEmail(contact.getEmail());
				c.setPhonenumber(contact.getPhonenumber());
				break;
			}
		}
		return new ResponseEntity<>("Success",HttpStatus.OK);
		
	}
	

}
