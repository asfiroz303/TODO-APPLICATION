package com.bridgeit.todo.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.Token.VerifyJwt;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.MailService;
import com.bridgeit.todo.service.UserService;
import com.bridgeit.todo.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class UserController {

private  Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	MailService mailservice;

	@Autowired
	ErrorMessage errorMessage;

	@Autowired
	Validator validator;

	@Autowired
	TokenGenerate tokenGenerate;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> saveUser(@RequestBody User user, HttpSession session,
		HttpServletRequest request) {

		String isValid = validator.validateUserRegistration(user);

		if (isValid.equals("true")) {
			int isValidate = userService.saveUser(user);
         
		if (isValidate !=0) {
			
			logger.info("cheaking information is valid or not");
			String accessToken = TokenGenerate.generate(user.getId());
			String url = request.getRequestURL().toString();
			url = url.substring(0, url.lastIndexOf("/")) + "/" + "verifyMail" + "/" + accessToken;

			mailservice.sendMail(user.getEmail(), "mdfirozahmad2222@gmail.com", "emailVerification", url);
            logger.info("sending the mail for registration verification");
			errorMessage.setResponseMessage("registered Successfully....");
			//logger.info("registration successful");
			// return new ResponseEntity<String>(isValid, HttpStatus.OK);
			return ResponseEntity.ok(errorMessage);

			}
		} else {
			errorMessage.setResponseMessage(isValid);
			logger.error(isValid);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@RequestMapping(value = "/verifyMail/{accessToken:.+}", method = RequestMethod.GET)
	public ResponseEntity<ErrorMessage> verifyUser(@PathVariable("accessToken") String accessToken, HttpServletResponse response)
		    throws IOException {
		
		    User user = null;
		    System.out.println(user);
		    int id = VerifyJwt.verify(accessToken);
		    logger.debug("verifying accessToken");

		try {
			user = userService.getUserById(id);
			logger.debug("User verifing by id");

		}catch (Exception e) {
			logger.error("catching exception e");
			e.printStackTrace();
		}
		    user.setActivated(true);
		    logger.debug("accessToken Activated");
		try {
			userService.updateUser(user);
			logger.info("registration successful");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		    errorMessage.setResponseMessage("user Email verified successfully ");
		    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> userLogin(@RequestBody User user, HttpSession session) {
		
		     //User userLogin = userService.userLogin(user.getEmail(), user.getPassword());
		     User userLogin = userService.getUserByEmail(user.getEmail());
		     logger.info("cheaking information is valid or not");
		
		if (userLogin == null) {
			 logger.info("user entering invalid information");
			 errorMessage.setResponseMessage("user with this email not exist");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
		boolean match = BCrypt.checkpw(user.getPassword(), userLogin.getPassword());
		if (!match) {
			logger.warn("Wrong password");
			 errorMessage.setResponseMessage("wrong password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		} 
		     session.setAttribute("user", userLogin);
		     //String accessToken = TokenGenerate.generate(userLogin.getId());
		     logger.debug("user successfully login");
		     errorMessage.setResponseMessage("Login Successfully....");
		     return ResponseEntity.ok(errorMessage);
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> forgaotPassword(@RequestBody User user, HttpServletRequest request,
			 HttpSession session) {

		     String url = request.getRequestURL().toString();
		     url = url.substring(0, url.lastIndexOf("/")) + "/" + "setPassword";
		     logger.info("sending the mail for registration verification");
		     User email = userService.getUserByEmail(user.getEmail());

		if (email == null) {
			 logger.info("user entered invalid email");
			 errorMessage.setResponseMessage("Enter valid  email...");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

		} else {
			 String accessToken = TokenGenerate.generate(user.getId());
			 System.out.println("token" + accessToken);
			 // session.setAttribute("Token", token);
			 mailservice.sendMail(user.getEmail(), "mdfirozahmad2222@gmail.com", "accessToken is :", url);
			 errorMessage.setResponseMessage("success");
			 return ResponseEntity.ok(errorMessage);
		}
	}

	@RequestMapping(value = "/setPassword", method = RequestMethod.PUT)
	public ResponseEntity<ErrorMessage> setPassword(@RequestBody User user, HttpSession session) {
		  
		     //String email = user.getEmail();
		     //String password = user.getPassword();
		     user = userService.getUserByEmail(user.getEmail());
		    
		if (user == null) {
			 logger.info("No user Found at this email");
			 errorMessage.setResponseMessage("No user Found at this email");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		else {
			 String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
			user.setPassword(hashedPassword);
			// user.setPassword(password);
			 logger.debug("set password");

		if (userService.setPassword(user)) {
			 logger.info("checking user is valid or not for update password");
			 errorMessage.setResponseMessage("password updated");
			 logger.debug("password updated successfully");;
			 return ResponseEntity.ok(errorMessage);
			}

			 logger.info("password not updated");
			 errorMessage.setResponseMessage("password not updated");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}
/*
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception e) {
		return "Exception";
		*/
}
