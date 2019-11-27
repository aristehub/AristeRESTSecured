package com.aristaREST.aristahub.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> 
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private Pattern pattern;
	private Matcher matcher;
	//email pattern acceptance
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) 
	{
		//hide pattern compiling it
		pattern = Pattern.compile(EMAIL_PATTERN);
		//email empty return false for validation
		if (email == null) 
		{
			return false;
		}
		//get pattern
		matcher = pattern.matcher(email);
		//match true
		return matcher.matches();
	}
}