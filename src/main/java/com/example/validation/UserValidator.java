package com.example.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.entities.User;
import com.example.service.UserService;

@Component
public class UserValidator implements Validator{

	private UserService userService;
	
	@Autowired
	public UserValidator(UserService userService) {
		this.userService=userService;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		 return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user=(User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Not empty");
		if(user.getUsername().length() < 6 || user.getUsername().length() > 32){
			errors.rejectValue("username", "Size.userForm.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Taken");
		if (userService.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email","Duplicate.userForm.email");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Not empty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		
	}

}
