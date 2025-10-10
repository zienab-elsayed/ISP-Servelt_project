package com.Item.service;

import java.util.List;

import com.Item.model.Item;
import com.Item.model.ItemDetails;

public interface ItemService {

	boolean addItem(Item item);
	boolean updateItem(Item item);
	Item getItemById(Long id); 
	List<Item> getItems();
	boolean deleteItemById(Long id);
	boolean saveItemDetails(ItemDetails itemdetails);
	List<ItemDetails> showDetailsById(Long item_id);
	boolean removeItemDetails(Long item_id);
	 // boolean hasDetails(Long itemId);
}
