package com.crsms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.crsms.exception.RestAjaxInternalServerException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handlingNoHandlerFound(HttpServletRequest request, NoHandlerFoundException ex) {
        return "404";
    }
    
    @ExceptionHandler(RestAjaxInternalServerException.class)
	public void handleRestAjaxInternalServerException(RestAjaxInternalServerException e, 
			HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    	response.getWriter().write(e.getMessage());
		response.flushBuffer();
	}
    
}