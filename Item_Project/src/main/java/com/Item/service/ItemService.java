package com.Item.service;

import java.util.List;

import com.Item.model.Item;

public interface ItemService {

	boolean addItem(Item item);
	boolean updateItem(Item item);
	Item getItemById(Item item); 
	List<Item> getItems();
	boolean deleteItemById(Long id);
	
	   
	
	 
}
