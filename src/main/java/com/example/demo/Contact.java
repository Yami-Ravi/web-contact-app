package com.example.demo;

public class Contact implements Comparable<Contact>{
	private String name;
	private String phonenumber;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	  public int compareTo(Contact c) {
	    if (this.getName() == null || c.getName() == null) {
	      return 0;
	    }
	    return this.getName().compareTo(c.getName());
	  }


	
	

}

