package com.user.service;

import com.user.model.User;

public interface UserService {

	boolean login(String username,String password);
	
	boolean signIn(User user);
	
	 
}
