package com.Item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.Item.serviceImpl.ItemServiceImpl;
import com.Item.model.Item;
import com.Item.service.ItemService;

//http://localhost:8080/Item_Project/ItemController/AddItem
//http://localhost:8080/Item_Project/ItemController/DeleteItem
//http://localhost:8080/Item_Project/ItemController/UpdateItem
//http://localhost:8080/Item_Project/ItemController/ShowItem
//http://localhost:8080/Item_Project/ItemController/ShowAllItems
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	 
     @Resource(name ="jdbc/itemConnection")
	private DataSource datasource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		
		String action= request.getParameter("action"); // this the action that the user we make it , it will sent here as a request
		if(Objects.isNull(action)) {
			 action= "ShowAllItems";
		}
	   
	   switch(action) {
	   
	   case "AddItem":
		    addItem(request,response);
		   break;
	   
	   case "DeleteItem":
		   deleteItem(request,response);
		   break;
	   case "UpdeteItem":
		   updateItem(request,response);
		   break;
	   case "ShowItem":
		   showItem(request,response);
		   break;
	   case "ShowAllItems":
		   showAllItem(request,response);
		   break;
	   
	   
	   default:
		  addItem(request,response);   
	     
	   }
	}
 
	private void showAllItem(HttpServletRequest request, HttpServletResponse response) {
		ItemService itemservice = new ItemServiceImpl(datasource);
		 
		try {	
			List<Item> items = itemservice.getItems();
	        		
			request.setAttribute("items", items);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowItems.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		
		
		
	}

	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		ItemService itemservice = new ItemServiceImpl(datasource);
		Item item =itemservice.getItemById(null);
		
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		ItemService itemservice = new ItemServiceImpl(datasource);
	    
		try {
			
			 Long id = Long.parseLong(request.getParameter("id"));
		      String name = request.getParameter("name");
		      Double price = Double.parseDouble(request.getParameter("price"));
		      Integer totalNumber = Integer.parseInt(request.getParameter("total_number"));

		        
		        Item item = new Item(id,name,price,totalNumber);  
		        boolean itemUpdated= itemservice.updateItem(item);
		        if(itemUpdated) {
					showAllItem(request,response);
					}
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowItems.jsp");
			dispatcher.forward(request, response);
		 
		} catch (Exception e) {
			 
			 System.out.println(e.getMessage());
			 
		}
		
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		ItemService itemservice = new ItemServiceImpl(datasource);
		Long id= Long.parseLong(request.getParameter("id"));
		boolean itemDeleted =itemservice.deleteItemById(id);
		if(itemDeleted) {
			showAllItem(request,response);
		}
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		   ItemService itemservice = new ItemServiceImpl(datasource);
		   
			
			try {
				
				 Long id = Long.parseLong(request.getParameter("id"));
			      String name = request.getParameter("name");
			      Double price = Double.parseDouble(request.getParameter("price"));
			      Integer totalNumber = Integer.parseInt(request.getParameter("total_number"));

			        
			        Item item = new Item(id,name,price,totalNumber);  
			        boolean itemAdded= itemservice.addItem(item);
			        if(itemAdded) {
						showAllItem(request,response);
						}
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowItems.jsp");
				dispatcher.forward(request, response);
			 
			} catch (Exception e) {
				 
				 System.out.println(e.getMessage());
				 
			}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
