package com.bridgeit.todo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.UserService;
import com.bridgeit.todo.validation.Validator;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ErrorMessage errorMessage;

	@Autowired
	Validator validator;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> saveUser(@RequestBody User user, HttpSession session) {
		
		String isValid = validator.validateUserRegistration(user);

		if (isValid.equals("true")) {
			userService.saveUser(user);
			errorMessage.setResponseMessage("registered Successfully....");
			return ResponseEntity.ok(errorMessage);
		} else {
			errorMessage.setResponseMessage(isValid);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> userLogin(@RequestBody User user, HttpSession session) {
		User userLogin = userService.userLogin(user.getEmail(), user.getPassword());
		
		if(userLogin==null){
			
			errorMessage.setResponseMessage("insert valid information");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
			//return ResponseEntity.ok(user);
			
		}
		session.setAttribute("user", userLogin);
		user.setPassword("");
		errorMessage.setResponseMessage("Login Successfully....");
		return ResponseEntity.ok(errorMessage);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception e) {
		return "Exception";

	}
}
