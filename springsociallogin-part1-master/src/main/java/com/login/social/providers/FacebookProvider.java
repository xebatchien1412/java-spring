package com.login.social.providers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FacebookProvider  {

	private static final String FACEBOOK = "facebook";
	private static final String REDIRECT_LOGIN = "redirect:/login";
	
    	@Autowired
    	BaseProvider baseProvider ;
    	

	public String getFacebookUserData(Model model, com.login.entites.User userForm) {

		ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return REDIRECT_LOGIN;
		}
		populateUserDetailsFromFacebook(userForm);
		
		model.addAttribute("loggedInUser",userForm);
		return "settings";
	}
	public com.login.entites.User getFacebookUser() {
		Facebook facebook = baseProvider.getFacebook();
		User user = facebook.userOperations().getUserProfile();
		com.login.entites.User userForm = new com.login.entites.User();
		userForm.setFacebookId(facebook.userOperations().getUserProfile().getId());
		userForm.setEmail(user.getEmail());
		userForm.setName(user.getName());
		userForm.setProvider(FACEBOOK);
		return userForm;
	}

	protected void populateUserDetailsFromFacebook(com.login.entites.User userForm) {
		Facebook facebook = baseProvider.getFacebook();
		User user = facebook.userOperations().getUserProfile();
		userForm.setFacebookId(facebook.userOperations().getUserProfile().getId());
		userForm.setEmail(user.getEmail());
		userForm.setName(user.getName());
		userForm.setProvider(FACEBOOK);
		if(userForm.getId()==null) {
		userForm.setId(UUID.randomUUID().toString());
		}
	}

	 

}
