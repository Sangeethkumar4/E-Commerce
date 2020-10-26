package com.example.demo.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.UserInfo;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<UserInfo> getUserInfo(String emailId){
		return userRepository.findByEmail(emailId);
	}
	
	public UserInfo insertUser(String name, String email, String persona) {
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail(email);
		userInfo.setName(name);
		userInfo.setRole(persona);
		return userRepository.save(userInfo);
	}
	
	
	@Transactional
	public int UpdatePersona(String email, String persona) {		
		return  userRepository.updatePersona(email, persona);		
	}
}
