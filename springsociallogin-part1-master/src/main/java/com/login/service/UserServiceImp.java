package com.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entites.Settings;
import com.login.entites.User;
import com.login.repository.UserRepository;

	

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void update(User userCheck) {
		userRepository.save(userCheck);
	}

	@Override
	public void updateSettings_default(Settings newSettings) {
       List<User> listUser = userRepository.findAll();
       for(int i = 0 ; i < listUser.size(); i++) {
    	         listUser.get(i).getSettings().setVersion(newSettings.getVersion());
    	         if(!(listUser.get(i).getSettings().isAuto_play_video_is_changed())) {
    	        	 listUser.get(i).getSettings().setAuto_play_video(newSettings.getAuto_play_video());
    	         }
    	         if(!(listUser.get(i).getSettings().isRecept_notifies_is_changed())) {
    	        	 listUser.get(i).getSettings().setRecept_notifies(newSettings.getRecept_notifies());;
    	         } 
    	         if(!(listUser.get(i).getSettings().isLanguage_is_changed())) {
    	        	 listUser.get(i).getSettings().setLanguage(newSettings.getLanguage());
    	         } 
    	        userRepository.save(listUser.get(i));
       }
	}

}
