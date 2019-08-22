 package com.ctevs.common.exception;

import com.ctevs.common.beans.ExceptionBean;



 
 
 public class BaseException extends Exception
 {
   private static final long serialVersionUID = -4586124005804833645L;
   private ExceptionBean bean;
 
   public BaseException()
   {
      this.bean = new ExceptionBean();
   }
 
   public BaseException(String message)
   {
     super(message);
      this.bean = new ExceptionBean(message);
   }
 
   public BaseException(String message, Throwable cause)
   {
      super(message, cause);
      this.bean = new ExceptionBean(message, cause);
   }
 
   public BaseException(Throwable cause)
   {
     super(cause);
      this.bean = new ExceptionBean(cause);
   }
 
   public ExceptionBean getBean()
   {
      return this.bean;
   }
 }
 