package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.UserInfo;
import com.example.demo.Service.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		Map<String, Object> userInformation = new HashMap<String, Object>();
		List<UserInfo> userInfo = userService.getUserInfo(principal.getAttribute("email"));
		if(userInfo.size() == 0) {
			userInformation.put("userExists", false);
		}else {
			userInformation.put("userExists", true);
			userInformation.put("role", userInfo.get(0).getRole());
		}
		userInformation.put("name", principal.getAttribute("name"));		
		return userInformation;
	}
	
	@GetMapping("/signup")
	public UserInfo signup(@AuthenticationPrincipal OAuth2User principal, @RequestParam String persona) {
		return userService.insertUser(principal.getAttribute("name"), principal.getAttribute("email"), persona);		
	}
	
	@GetMapping("/update")
	public UserInfo update(@AuthenticationPrincipal OAuth2User principal, @RequestParam String persona) {
		 int rowsAffected = userService.UpdatePersona(principal.getAttribute("email"), persona);	
		 UserInfo userInfo = new UserInfo();
		 userInfo.setName(principal.getAttribute("name"));
		 if(rowsAffected > 0)
			 userInfo.setEmail(principal.getAttribute("email"));
		 userInfo.setRole(persona);
		 return userInfo;
	}
}
