package com.Item.serviceImpl;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.Item.model.Item;
import com.Item.model.ItemDetails;
import com.Item.service.ItemService;
//import java.time.LocalDate;


public class ItemServiceImpl implements ItemService{
	
	private DataSource datasource;
	
	public ItemServiceImpl(DataSource datasource){
		this.datasource=datasource;	
	}
	
	
	@Override
	public boolean addItem(Item item) {
		 Connection connection =null;
		 Statement statement =null;
		
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
			String sql ="INSERT INTO item (ID,name,price,total_number) values("+
					 item.getId()+",'"+ 
					 item.getName()+"',"+ 
					 item.getPrice()+","+
					 item.getTotalNumber()+")";
			 
			statement.execute(sql);
             return true;
			   } 	
		 catch (Exception e) {
		
			 System.out.println(e.getMessage());
		}
		 finally {
			
				 try {
					 
					 if(connection !=null) connection.close();
					 if(statement!=null) statement.close();
					 
				     }
				 catch (Exception e) {
					  System.out.println(e.getMessage());
				 
				}
			   }
				 return false;		 
	 
	}

	@Override
	public boolean updateItem(Item item) {
		
		 Connection connection =null;
		 PreparedStatement statement =null;
		
		 try {
			connection=datasource.getConnection();
	 
			
			String sql = "UPDATE ITEM SET NAME = ?, PRICE = ?, TOTAL_NUMBER = ? WHERE id = ?";
		    statement = connection.prepareStatement(sql);
			statement .setString(1, item.getName());
			statement .setDouble(2, item.getPrice());
			statement .setInt(3, item.getTotalNumber());
			statement .setLong(4, item.getId());

			 statement .executeUpdate();
			  
			return true;

			   } 	
		 catch (Exception e) {
		
			 System.out.println(e.getMessage());
		}
		 finally {
			
				 try {
					 
					 if(connection !=null) connection.close();
					 if(statement!=null) statement.close();
					 
				     }
				 catch (Exception e) {
					  System.out.println(e.getMessage());
				 
				}
			   }
				 return false;		 
	 
	}

	 
	 
	@Override
	public Item getItemById(Long id) {
	    Connection connection = null;
	    Statement statement = null;
	   
 

	    try {
	        connection = datasource.getConnection();
	        statement = connection.createStatement();

	
	        String sql = "SELECT * FROM item WHERE id = " + id;

	        ResultSet resultSet = statement.executeQuery(sql);
	          Item itemData = new Item();
	           
	        if (resultSet.next()) { 
	            itemData.setId(resultSet.getLong("id"));
	            itemData.setName(resultSet.getString("name"));
	            itemData.setPrice(resultSet.getDouble("price"));
	            itemData.setTotalNumber(resultSet.getInt("total_number"));
	        }

	        return itemData;

	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        try {
	            
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            System.out.println("Closing error: " + e.getMessage());
	        }
	    }

	    return null;
	}


	@Override
	public List<Item> getItems() {
		 Connection connection =null;
		 Statement statement =null;
		
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
		    String sql = "SELECT i.id, i.name, i.price, i.total_number, " +
	                 "CASE WHEN EXISTS (SELECT 1 FROM item_details d WHERE d.item_id = i.id) " +
	                 "THEN 1 ELSE 0 END AS hasDetails " +
	                 "FROM item i";


			ResultSet resultSet =statement.executeQuery(sql);
			
			List<Item> items= new ArrayList();
			
			while(resultSet.next())
			{
				
				Item itemData=new Item();
				itemData.setId( resultSet.getLong("id"));
				itemData.setName( resultSet.getString("name"));
				itemData.setPrice( resultSet.getDouble("price"));
				itemData.setTotalNumber( resultSet.getInt("total_number"));
				itemData.setHasDetails(resultSet.getInt("hasDetails") == 1);
				items.add(itemData);
			}
			
			 return items;
			
		 } 	
		 catch (Exception e) {
		
			 System.out.println(e.getMessage());
		}
		 finally {
			
				 try {
					 
					 if(connection !=null) connection.close();
					 if(statement!=null) statement.close();
					 
				     }
				 catch (Exception e) {
					  System.out.println(e.getMessage());
				 
				}
			   }
				 return new ArrayList(); // it will return empty list if the connection not conect
		 }		 

	@Override
	public boolean deleteItemById(Long id) {
		 Connection connection =null;
		 Statement statement =null;
		
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
			String sql ="DELETE FROM ITEM WHERE ID= "+id;
			 
			statement.execute(sql);
             return true;
			   } 	
		 catch (Exception e) {
		
			 System.out.println(e.getMessage());
		}
		 finally {
			
				 try {
					 
					 if(connection !=null) connection.close();
					 if(statement!=null) statement.close();
					 
				     }
				 catch (Exception e) {
					  System.out.println(e.getMessage());
				 
				}
			   }
				 return false;		 
	 
	}

	@Override
	public boolean saveItemDetails(ItemDetails itemdetails) {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = datasource.getConnection();

	        String sql = "INSERT INTO ITEM_DETAILS (description, category, Added_date, brand, item_id) VALUES (?, ?, ?, ?, ?)";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, itemdetails.getDescription());
	        statement.setString(2, itemdetails.getCategory());
	        statement.setDate(3, java.sql.Date.valueOf(itemdetails.getDate())); // LocalDate → SQL Date
	        statement.setString(4, itemdetails.getBrand());
	        statement.setLong(5, itemdetails.getItem_id());

	        statement.executeUpdate();
	        return true;

	    } catch (Exception e) {
	        System.out.println("Error in showItemDetails: " + e.getMessage());
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    return false;
	}


 
	@Override
	 
	public boolean removeItemDetails(Long item_id) {
		Connection connection =null;
		 Statement statement =null;
		 ItemDetails itemdetail=new ItemDetails();
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
			String sql ="DELETE FROM ITEM_DETAILS WHERE item_id= "+ item_id;
			 
			statement.execute(sql);
            return true;
			   } 	
		 catch (Exception e) {
		
			 System.out.println(e.getMessage());
		}
		 finally {
			
				 try {
					 
					 if(connection !=null) connection.close();
					 if(statement!=null) statement.close();
					 
				     }
				 catch (Exception e) {
					  System.out.println(e.getMessage());
				 
				}
			   }
		return false;
	}


	@Override
	public List<ItemDetails> showDetailsById(Long item_id) {
	    Connection connection = null;
	    Statement statement = null;

	    try {
	        connection = datasource.getConnection();
	        statement = connection.createStatement();

	        List<ItemDetails> list = new ArrayList<>();
	        String sql = "SELECT description, category, brand, added_date, item_id " +
	                     "FROM item_details WHERE item_id = " + item_id;

	        ResultSet resultSet = statement.executeQuery(sql);

	        while (resultSet.next()) { 
	            ItemDetails itemDetails = new ItemDetails();

	            itemDetails.setDescription(resultSet.getString("description"));
	            itemDetails.setCategory(resultSet.getString("category"));
	            itemDetails.setBrand(resultSet.getString("brand"));

	            java.sql.Date sqlDate = resultSet.getDate("added_date");
	            if (sqlDate != null) {
	                itemDetails.setDate(sqlDate.toLocalDate());
	            }

	            itemDetails.setItem_id(resultSet.getLong("item_id"));
	            list.add(itemDetails);
	        }

	        return list;

	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            System.out.println("Closing error: " + e.getMessage());
	        }
	    }

	    return null;
	}


}
