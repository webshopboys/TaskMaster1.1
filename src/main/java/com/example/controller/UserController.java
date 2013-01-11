package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.form.UserForm;
import com.example.model.User;
import com.example.service.UserService;

/**
 * 
 * @author anirudh
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showCreateUserPage(Model model){
		UserForm user = new UserForm();
		model.addAttribute("user",user);
		return "createUser";
	}
	
	/**
	 * POST request to create User
	 * @param modell
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createUser(
			@ModelAttribute("user") UserForm user,HttpSession session){
		User newUser = populateUserFromForm(user);
		userService.createUser(newUser);
		session.setAttribute("user", newUser);
		return "home";
	}
	
	private User populateUserFromForm(UserForm user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		return newUser;
	}

}
