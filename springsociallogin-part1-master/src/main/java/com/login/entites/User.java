package com.login.entites;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
     @Id
	 private String id;
     
	 private String facebookId;
     private String googleId;
     private String provider;
	 public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	private String name;
	 private String email;
	 private String password;
	 private Settings settings;
	 
	 public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
     
	public User() {
		
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	
     public User(String name, String email, String password, Settings settings) 
	   
       {    
		    this.setName(name);
		    this.setEmail(email);
		    this.setPassword(password);
		    this.setSettings(settings);
		   
	   }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() 
	{
	    return "Person [id=" + id + ", name=" + name + "]";
	}
	public Settings getSettings() {
		return settings;
	}
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}
