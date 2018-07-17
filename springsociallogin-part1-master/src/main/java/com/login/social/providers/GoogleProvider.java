package com.login.social.providers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.login.entites.User;

@Service
public class GoogleProvider   {

	private static final String REDIRECT_CONNECT_GOOGLE = "redirect:/login";
	private static final String GOOGLE = "google";

   	@Autowired
    	BaseProvider socialLoginBean ;


	public String getGoogleUserData(Model model, User user) {

		ConnectionRepository connectionRepository = socialLoginBean.getConnectionRepository();
		if (connectionRepository.findPrimaryConnection(Google.class) == null) {
			return REDIRECT_CONNECT_GOOGLE;
		}

		populateUserDetailsFromGoogle(user);
		model.addAttribute("loggedInUser",user);
		return "settings";
	}
	public com.login.entites.User getGoogleUser() {
		Google google = socialLoginBean.getGoogle();
		Person googleUser = google.plusOperations().getGoogleProfile();
		com.login.entites.User userForm = new com.login.entites.User();
		userForm.setGoogleId(googleUser.getId());
		userForm.setEmail(googleUser.getAccountEmail());
		userForm.setName(googleUser.getDisplayName());
		userForm.setProvider(GOOGLE);
		return userForm;
	}
	
	protected void populateUserDetailsFromGoogle(User user) {
		Google google = socialLoginBean.getGoogle();
		Person googleUser = google.plusOperations().getGoogleProfile();
		user.setEmail(googleUser.getAccountEmail());
		user.setName(googleUser.getDisplayName());
		user.setGoogleId(googleUser.getId());
		user.setProvider(GOOGLE);
		if(user.getId()==null) {
			user.setId(UUID.randomUUID().toString());
			}
	}

}
