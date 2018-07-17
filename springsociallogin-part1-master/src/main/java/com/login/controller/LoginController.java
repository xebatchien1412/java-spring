package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.dao.UserDao;
import com.login.entites.Settings;
import com.login.entites.User;
import com.login.model.ResponseMessage;
import com.login.social.providers.FacebookProvider;
import com.login.social.providers.GoogleProvider;
import com.login.social.providers.LinkedInProvider;

@Controller
public class LoginController {
	
	@Autowired
	UserDao userDao;
	
	Settings setting_default;
	
	@Autowired 
	FacebookProvider facebookProvider;
	
	@Autowired 
	GoogleProvider googleProvider;

	@Autowired 
	LinkedInProvider linkedInProvider;

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String loginToFacebook(Model model) {
		User userExist;
		User facebookUser = facebookProvider.getFacebookUser();
		if(userDao.checkUserExist(facebookUser)==true) {
		       facebookUser.setSettings(setting_default);
		       userDao.add(facebookUser);
		       return facebookProvider.getFacebookUserData(model, new User());
		} 
		else {
		  userExist = userDao.findUserByEmail(facebookUser);
		  return facebookProvider.getFacebookUserData(model, userExist);
		}
		
		
	}
    
	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String loginToGoogle(Model model) {
		User userExist;
		User googleUser = googleProvider.getGoogleUser();
		if(userDao.checkUserExist(googleUser)==true) {
		       googleUser.setSettings(setting_default);
		       userDao.add(googleUser);
		       return googleProvider.getGoogleUserData(model, new User());
		} 
		else {
		  userExist = userDao.findUserByEmail(googleUser);
		  return googleProvider.getGoogleUserData(model, userExist);
		}
	}
	
	@RequestMapping(value = { "/","/login" })
	public String login() {
		return "login";
	}
	@RequestMapping(value = {"/{id}/settings"})
	public String settings(@PathVariable String id) {
		return "settings";
	}
	@RequestMapping(value = {"/signup"})
	public String signup() {
		return "signup";
	}
    
	@PostMapping("/facebook/accounts/settings_default")
	public ResponseEntity<ResponseMessage> setSettingsDefault(@RequestBody Settings settings) {
		ResponseMessage resMessage = new ResponseMessage();
		if(settings.getVersion().equals("")|| settings.getVersion()==null||settings.getAuto_play_video().equals("") || settings.getRecept_notifies().equals("") || settings.getLanguage().equals("")||
			  settings.getAuto_play_video()==null||settings.getRecept_notifies()==null || settings.getLanguage()==null ) {
			  resMessage.setMessage("Settings default không hợp lệ!");
			  resMessage.setStatus(0);
			  return ResponseEntity.ok(resMessage);
		}
		setting_default = new Settings(settings.getVersion(), settings.getAuto_play_video(), settings.getRecept_notifies(), settings.getLanguage());
		resMessage.setMessage("Set settings default thành công!");
		resMessage.setStatus(1);
		resMessage.setResults(settings);
		return ResponseEntity.ok(resMessage);	
	}
}
