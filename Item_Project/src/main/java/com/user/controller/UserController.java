package com.user.controller;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.user.model.User;
import com.user.service.UserService;
import com.user.serviceimpl.UserServiceImpl;

 
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	@Resource(name ="jdbc/itemConnection")
	private DataSource datasource;
	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String action= request.getParameter("action"); // this the action that the user we make it , it will sent here as a request
		if(Objects.isNull(action)) {
			 action= "/WelcomePage.html";
		}
	   
	   switch(action) {
	   
	   case "Login":
		    logIn(request,response);
		   break;
	   
	   case "SignIn":
		   singIn(request,response);
		   break;
	   
	   
	   default:
		   logIn(request,response);
		   break;
	      
	   }	
	
	}
 
	
	private void singIn(HttpServletRequest request, HttpServletResponse response) {
		 UserService userservice = new UserServiceImpl(datasource);
		  
		try {
			  String email =request.getParameter("email");
		      String username = request.getParameter("username");
		      String password = request.getParameter("password");
		     
		        
		        User  user = new User(email,username,password);  
			    boolean isSingIn= userservice.signIn(user);
	
			  if(isSingIn) { 
			  		    	    		    	    			
		    	   RequestDispatcher dispatcher = request.getRequestDispatcher("/Log-in.jsp");
				   dispatcher.forward(request, response);
		    	   
			  }
			   
			  
				    
		} catch (Exception e) {
			
			System.out.println("Error : "+e.getMessage());
			 
		}
		
	}

	
	
	private void logIn(HttpServletRequest request, HttpServletResponse response) {
          UserService userservice = new UserServiceImpl(datasource);
		   
		try { 
			
			  
		      String username = request.getParameter("username");
		      String password = request.getParameter("password");
		     
		        
		      boolean isLogin= userservice.login(username, password);
		     
		      if(isLogin) {
		    	  
		    	  Cookie cookie = new Cookie("username", username);
		    	   cookie.setMaxAge(5*24*60*60);  // 5 day
		    	    response.addCookie(cookie);
		      	    		
		    	  HttpSession session=request.getSession();
		    	  session.setAttribute("username",username);
		    	  
		    	   
		    	   RequestDispatcher dispatcher = request.getRequestDispatcher("/StartPage.html");
				   dispatcher.forward(request, response);
		    	  
		      }
		      else {
		    	    request.setAttribute("errorMessage", "Invalid username or password!");
		    	    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		    	    dispatcher.forward(request, response);
		    	}

		          	        
		    } catch (Exception e) {
			 
			 System.out.println(e.getMessage());
			 
		  }
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
