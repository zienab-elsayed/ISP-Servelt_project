package com.Item.model;

 
import java.time.LocalDate;

public class ItemDetails {
	
	private String description;
	private String category;
	private String brand;
	private LocalDate date;
    private Long item_id ;
    
    
    public ItemDetails() {}
    
    public ItemDetails(String description,String category,String brand ,LocalDate date,Long item_id) {
    	
    	 this. description=description;
    	 this.category=category;
    	 this.brand=brand;
    	 this.date=date;
         this.item_id=item_id ;
   	  	
    }
    
    
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

   
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

 
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

 
    public Long getItem_id() {
        return item_id;
    }
    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }
}
