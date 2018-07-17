package com.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.dao.UserDao;
import com.login.entites.Settings;
import com.login.entites.User;
import com.login.model.ResponseMessage;
import com.login.service.UserService;




@RestController
@RequestMapping("/api/users")
public class UserController {
	
    @Autowired
    UserDao userDao;
    Settings setting_default;
    
    @Autowired
    UserService userService;

	@GetMapping("/all")
	public ResponseEntity<ResponseMessage> getAll(){
		 ResponseMessage resMessage = new ResponseMessage();
		 List<User> stationBTS = userDao.listUser();
		 if(stationBTS.isEmpty()) {
			 resMessage.setMessage("No records");
			 resMessage.setStatus(0);
		   return ResponseEntity.ok(resMessage);
		 }
		 resMessage.setMessage("OK");
		 resMessage.setStatus(1);
		 resMessage.setResults(stationBTS);
		
		return ResponseEntity.ok(resMessage);
	}
    

	@GetMapping("/{id}")
	public ResponseEntity<ResponseMessage> getUserById(@PathVariable String id){
		 ResponseMessage resMessage = new ResponseMessage();
		 User user = userDao.findUserById(id);
		 if(user==null) {
			 resMessage.setMessage("No records");
			 resMessage.setStatus(0);
		   return ResponseEntity.ok(resMessage);
		 }
		 resMessage.setMessage("OK");
		 resMessage.setStatus(1);
		 resMessage.setResults(user);
		
		return ResponseEntity.ok(resMessage);
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseMessage> createUser(@RequestBody User user) {
		ResponseMessage resMessage = new ResponseMessage();
		if(userDao.checkUserExist(user)==false) {
			resMessage.setMessage("Tài khoản đã tồn tại!");
			 resMessage.setStatus(0);
		   return ResponseEntity.ok(resMessage);
		}
		user.setSettings(setting_default);
		userDao.add(user);
		resMessage.setMessage("Đăng ký thành công!");
		resMessage.setStatus(1);	
		resMessage.setResults(user);
		return ResponseEntity.ok(resMessage);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseMessage> loginUser(@RequestBody User user) {
		ResponseMessage resMessage = new ResponseMessage();
		
		if(userDao.checkUserExist(user)) {
			resMessage.setMessage("Tài khoản không tồn tại!");
			resMessage.setStatus(0);
		    return ResponseEntity.ok(resMessage);
		}
		else {
			if(!userDao.loginUser(user)) {
				
				resMessage.setMessage("Mật khẩu không chính xác!");
				resMessage.setStatus(0);
			    return ResponseEntity.ok(resMessage);
			}
			else {
				resMessage.setMessage("Đăng nhập thành công!");
				resMessage.setStatus(1);
				User userExist = userDao.findUserByEmail(user);
				resMessage.setResults(userExist);
				return ResponseEntity.ok(resMessage);
			}
		}		
	}

	@PostMapping("/settings_default")
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
	@PostMapping("/update/settings_default")
	public ResponseEntity<ResponseMessage> updateSettingsDefault(@RequestBody Settings settings){
		ResponseMessage resMessage = new ResponseMessage();
		userService.updateSettings_default(settings);
		setting_default = new Settings(settings.getVersion(), settings.getAuto_play_video(), settings.getRecept_notifies(), settings.getLanguage());
		resMessage.setMessage("Update settings default thành công!");
		resMessage.setStatus(1);
		resMessage.setResults(settings);
		return ResponseEntity.ok(resMessage);
	}
	

	@PostMapping("/update/settings/{id}")
	public ResponseEntity<ResponseMessage> updateSettingsUser(@PathVariable("id") String id , @RequestBody Settings settings){
		ResponseMessage resMessage = new ResponseMessage();
		User userUpdate = userDao.findUserById(id);
		if(userUpdate==null) {
			resMessage.setMessage("Tài khoản không tồn tại");
			resMessage.setStatus(0);
			return ResponseEntity.ok(resMessage);
		}
		userUpdate.setSettings(settings);
		userService.update(userUpdate);
		resMessage.setMessage("Cập nhật thành công!");
		resMessage.setStatus(1);
		resMessage.setResults(settings);
		return ResponseEntity.ok(resMessage);
		
	}
}
