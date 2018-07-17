package com.login.service;

import com.login.entites.Settings;
import com.login.entites.User;

public interface UserService {

	void update(User userCheck);
	void updateSettings_default(Settings newSettings);

}
