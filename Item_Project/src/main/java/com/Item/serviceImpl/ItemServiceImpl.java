package com.Item.serviceImpl;

 
import java.sql.Connection;
import java.sql.ResultSet; 
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.Item.model.Item;
import com.Item.service.ItemService;

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
			
			String sql ="INSERT INTO item (name,price,total_number) values('"+
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
		 Statement statement =null;
		
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
			String sql ="UPDATE ITEM SET NAME = '"+ item.getName()+"',"
			        +"PRICE = "+ item.getPrice()+","
					+"TOTAL_NUMBER = "+ item.getTotalNumber()+")";
					
			 
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
	public Item getItemById(Item item) {
		 Connection connection =null;
		 Statement statement =null;
		
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
			String sql ="select from item (id,name,price,total_number where id = " +item.getId()+") ";
			ResultSet resultSet =statement.executeQuery(sql);
			while(resultSet.next())
			{
				
				Item itemData=new Item();
				itemData.setId( resultSet.getLong("id"));
				itemData.setName( resultSet.getString("name"));
				itemData.setPrice( resultSet.getDouble("price"));
				itemData.setTotalNumber( resultSet.getInt("total_number"));
				
			 
			}
			 
             return item;
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
						 
	 
		return null;
	}

	@Override
	public List<Item> getItems() {
		 Connection connection =null;
		 Statement statement =null;
		
		 try {
			connection=datasource.getConnection();
			statement =connection.createStatement();
			
			String sql ="select * from Item";
			ResultSet resultSet =statement.executeQuery(sql);
			
			List<Item> items= new ArrayList();
			
			while(resultSet.next())
			{
				
				Item itemData=new Item();
				itemData.setId( resultSet.getLong("id"));
				itemData.setName( resultSet.getString("name"));
				itemData.setPrice( resultSet.getDouble("price"));
				itemData.setTotalNumber( resultSet.getInt("total_number"));
				
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

}
