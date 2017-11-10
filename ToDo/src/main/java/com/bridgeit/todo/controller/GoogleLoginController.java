package com.bridgeit.todo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.UserService;
import com.bridgeit.todo.util.GoogleUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GoogleLoginController {

	private Logger logger = (Logger) LoggerFactory.getLogger(GoogleLoginController.class);
	
	@Autowired
	 UserService userService;
	
	@Autowired
	ErrorMessage errorMessage;
	
	@RequestMapping(value="/googleLogin")
	public void googleLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String googleUrl=GoogleUtil.generateGoogleUrl();
		logger.info("checking google url"+googleUrl);
		try {
			response.sendRedirect(googleUrl);
		} catch (IOException e) {
			logger.info("exception while generating google url");
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getGoogleLogin")
	public ResponseEntity<ErrorMessage> getGoogleLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		String code = (String)request.getParameter("code");
		logger.info("code"+code);
		String accessToken = GoogleUtil.getAccessToken(code);
		logger.info("accessToken"+accessToken);
		String googleProfileInfo = GoogleUtil.getProfileData(accessToken);
		logger.info("google profile info"+googleProfileInfo);
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String email = objectMapper.readTree(googleProfileInfo).get("email").asText();
			logger.info("email:-"+email);
			User user = userService.getUserByEmail(email);
			if(user==null) {
				
				User googleUser = new User();
				googleUser.setEmail(email);
				
				String firstName = objectMapper.readTree(googleProfileInfo).get("given_name").asText();
				googleUser.setFirstName(firstName);
			
				String lastName = objectMapper.readTree(googleProfileInfo).get("family_name").asText();
				googleUser.setLastName(lastName);
				
				/*String contact = objectMapper.readTree(googleProfileInfo).get("contact").asText();
				googleUser.setContact(contact);*/
				
				googleUser.setActivated(true);
				int  userId= userService.saveUser(googleUser); 
				
				if(userId == 0){
					response.sendRedirect("http://localhost:8080/ToDoApp/#!/login");
				}
				else {
					String myaccessToken = TokenGenerate.generate(userId);
					session.setAttribute("todoAppAccessToken", myaccessToken);
				}
					
			} else {	
				String myaccessToken = TokenGenerate.generate(user.getId());
				logger.info("token geneted by jwt" + myaccessToken);
				session.setAttribute("AccessToken", myaccessToken);
			}
			
		} catch (IOException e) {
			logger.info("exception occured during registering user from fb:");
			e.printStackTrace();
		}
		return null;
	}
}
