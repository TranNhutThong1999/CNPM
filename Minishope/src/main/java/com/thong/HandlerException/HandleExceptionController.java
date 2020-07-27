package com.thong.HandlerException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleExceptionController   {

//	@ExceptionHandler(Exception.class)
//    public ModelAndView handleError()   {
//        return new ModelAndView("404error");
//    }
//
    @ExceptionHandler(value =NoHandlerFoundException.class)
    public String handleError404(NoHandlerFoundException  e )   {
    	System.out.println("rrrr");
        return"Error";
    }
}
