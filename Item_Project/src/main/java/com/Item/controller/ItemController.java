package com.Item.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import com.Item.model.ItemDetails;
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
	
     
     private ItemService itemservice;

     @Override
     public void init() throws ServletException {
         super.init();
         itemservice = new ItemServiceImpl(datasource);
     }
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
	   case "UpdateItem":
		   updateItem(request,response);
		   break;
	   case "ShowItem":
		   showItem(request,response);
		   break;
	   case "ShowAllItems":
		   showAllItem(request,response);
		   break;
	   
	   case "AddItemDetails":
		   AdditemDetails(request,response);
		   break;
		   
	   case "SaveItemDetails": 
		   saveItemDetails(request,response);
		   break;
		   
	   case "ShowItemDetails":
		   showItemDetails(request,response);
		   break;
		   
	   case "RemoveItemDetails":
		   removeDetails(request,response);
		   break;
	   default:
		  addItem(request,response);   
	     
	   }
	}
 
	private void showItemDetails(HttpServletRequest request, HttpServletResponse response) {
		   
		try {
		  Long item_id = Long.parseLong(request.getParameter("item_id"));
		  List<ItemDetails> itemDetailsList = itemservice.showDetailsById(item_id);

	        request.setAttribute("itemDetailsList", itemDetailsList);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/Show-Item-details.jsp");
	        dispatcher.forward(request, response);

		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
			
		
	}
	private void saveItemDetails(HttpServletRequest request, HttpServletResponse response) {
		
	try {	
		   String description = request.getParameter("description");
	       String category = request.getParameter("category");
	       String brand = request.getParameter("brand");
	       LocalDate date= LocalDate.parse(request.getParameter("date"));
           Long item_id= Long.parseLong(request.getParameter("item_id"));
	       ItemDetails itemdetails=new ItemDetails(description,category,brand,date,item_id);		        
	       
	       boolean SavedDetails= itemservice.saveItemDetails(itemdetails);
	        
	       if(SavedDetails) {
	    	   showAllItem(request,response);
	       }
	}
	 catch (Exception e) {
		 
		 System.out.println(e.getMessage());
		 
	 }
		
	}
	private void removeDetails(HttpServletRequest request, HttpServletResponse response) {
		Long item_id= Long.parseLong(request.getParameter("item_id"));
		boolean itemDetailsDeleted =itemservice.removeItemDetails(item_id);
		if(itemDetailsDeleted) {
			showAllItem(request,response);
		}
		 
		
	}
	
	private void AdditemDetails(HttpServletRequest request, HttpServletResponse response) {
		try {
			
		
			Long item_id = Long.parseLong(request.getParameter("item_id"));
		    request.setAttribute("item_id", item_id);
		    request.getRequestDispatcher("Add-Item-details.jsp").forward(request, response);
		}catch(Exception e) {
			 System.out.println(e.getMessage());
			
			
		}
		
	}
	private void showAllItem(HttpServletRequest request, HttpServletResponse response) {
		 
	    
		 
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
		
		try {	
			
		 
		    Long id = Long.parseLong(request.getParameter("id"));
			Item item =itemservice.getItemById(id);
				
			request.setAttribute("item", item);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Update-Item.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		
		
		
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		     
	    
		try {
			
			  Long id = Long.parseLong(request.getParameter("id"));
		      String name = request.getParameter("name");
		      Double price = Double.parseDouble(request.getParameter("price"));
		      Integer totalNumber = Integer.parseInt(request.getParameter("total_number"));

		        
		        Item item = new Item(id,name,price,totalNumber);  
		        boolean updated = itemservice.updateItem(item);

		        if(updated) {
		            showAllItem(request, response);
		        } else {
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
		            dispatcher.forward(request, response);
		        }

		    
		} catch (Exception e) {
			 
			 System.out.println(e.getMessage());
			 
		}
		
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		 
		Long id= Long.parseLong(request.getParameter("id"));
		boolean itemDeleted =itemservice.deleteItemById(id);
		if(itemDeleted) {
			showAllItem(request,response);
		}
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		    
			
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
				 
			} catch (Exception e) {
				 
				 System.out.println(e.getMessage());
				 
			}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
