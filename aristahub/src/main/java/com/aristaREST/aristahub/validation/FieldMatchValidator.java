package com.aristaREST.aristahub.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> 
{
	/*
	 * -------------------------------------------------------	
	 * 					MEMBER VARIABLES
	 * -------------------------------------------------------
	 */
	private String firstFieldName;
    private String secondFieldName;
    private String message;

	/*
	 * -------------------------------------------------------	
	 * 					MEMBER METHODS
	 * -------------------------------------------------------
	 */
    @Override
    public void initialize(final FieldMatch constraintAnnotation) 
    {
    	// initializer field math for matching values
	    firstFieldName = constraintAnnotation.first();
	    secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }
    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) 
    {
    	//check if its valid the input
        boolean valid = true;
        try
        {
        	//final wrapper of properties
            final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
            //boolean valid
            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
            // we can ignore
        }
        //when valid is false send message and name already exists
        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        //return result....
        return valid;
    }
}