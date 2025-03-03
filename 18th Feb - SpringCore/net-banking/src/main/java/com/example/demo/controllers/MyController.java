package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class MyController {
    @Autowired
    private UserService userService;
    
	@RequestMapping("/")
	public String getFirstPage() {
		return "home.html";
	}
	
	@RequestMapping("/netbanking")
	public String getNetbankingHome() {
		return "netbanking_home.html";
	}
	@RequestMapping("/login")
	public String getLogin() {
		return "login.html";
	}
	@RequestMapping("/signup")
	public String getSignup() {
		return "signup.html";
	}
	
	@RequestMapping("verify")
	public String verifyUser(int id,String pwd,HttpSession session) {
		boolean isValidUser = userService.validateUserLogin(id, pwd);
		if(isValidUser) {
			String username = userService.getUserNameById(id);
			session.setAttribute("user", username);
			return "welcome.jsp";
		}
		else {
			return "failure.jsp";
		}
	}
	@RequestMapping("create")
	public String CreateUser(int id,String name,String pwd,String pwdconfirm,HttpSession session) {
        if (!pwd.equals(pwdconfirm)) {
            return "passdontmatch.jsp";
        }
		boolean isUserExist = userService.UserSignup(id, name, pwd);
		if(isUserExist) {
			String username = userService.getUserNameById(id);
			session.setAttribute("user", username);
			return "successSignup.jsp";
		}
		else {
			return "signupfailure.jsp";
		}
	}
}
