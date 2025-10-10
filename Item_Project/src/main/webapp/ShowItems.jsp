<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Item.model.Item" %>
<%@ page import="java.util.List" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Items List</title>

<style>
    body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e0f7fa, #fce4ec);
        margin: 0;
        padding: 20px;
    }

    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 30px;
        font-size: 2.2rem;
        text-transform: uppercase;
        letter-spacing: 2px;
    }

    table {
        width: 90%;
        margin: auto;
        border-collapse: collapse;
        background: #fff;
        box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
        border-radius: 12px;
        overflow: hidden;
    }

    thead {
        background: #009688;
        color: white;
    }

    th, td {
        padding: 14px;
        text-align: center;
        font-size: 1rem;
    }

    tr:nth-child(even) {
        background: #f9f9f9;
    }

    tr:hover {
        background: #e0f2f1;
        transition: 0.3s;
    }

    /* Base Button Style */
    a.action-btn {
        text-decoration: none;
        display: inline-block;
        padding: 10px 18px;
        border-radius: 25px; /* pill shape */
        margin: 4px;
        font-size: 0.9rem;
        font-weight: 600;
        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        transition: all 0.2s ease;
        cursor: pointer;
        color: white;
    }

    /* Update = Blue */
    a.action-btn.update { background: #42a5f5; }
    a.action-btn.update:hover {
        background: #1e88e5;
        transform: scale(1.05);
    }

    /* Delete = Red */
    a.action-btn.delete { background: #ef5350; }
    a.action-btn.delete:hover {
        background: #e53935;
        transform: scale(1.05);
    }

    /* Add Details = Green */
    a.action-btn.addDetails, a.btn-add { background: #66bb6a; }
    a.action-btn.addDetails:hover, a.btn-add:hover {
        background: #43a047;
        transform: scale(1.05);
    }

    /* Show Details = Purple */
    a.action-btn.showDetails { background: #7e57c2; }
    a.action-btn.showDetails:hover {
        background: #5e35b1;
        transform: scale(1.05);
    }

    /* Remove Details = Grey */
    a.action-btn.removeDetails { background: #9e9e9e; }
    a.action-btn.removeDetails:hover {
        background: #757575;
        transform: scale(1.05);
    }

    /* Add New Item */
    a.btn-add {
        display: inline-block;
        padding: 12px 24px;
        border-radius: 25px;
        text-decoration: none;
        font-weight: 700;
        font-size: 1rem;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        margin-top: 20px;
    }
</style>
</head>
<body>

   <%
     List<Item> items = (List<Item>) request.getAttribute("items");
   %>

   <h1>Items</h1>
   <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        
         <% 
            if(items != null){
                for(Item item : items){
         %>
	        <tr>
	            <td><%= item.getId() %></td>
                <td><%= item.getName() %></td>
                <td>$<%= item.getPrice() %></td>
                <td><%= item.getTotalNumber() %></td>
	            <td>
                    <a class="action-btn update" href="/Item_Project/ItemController?action=ShowItem&id=<%= item.getId() %>">Update</a>
                    <a class="action-btn delete" href="/Item_Project/ItemController?action=DeleteItem&id=<%= item.getId() %>">Delete</a>
                    <a class="action-btn addDetails" href="/Item_Project/Add-Item-details.jsp?item_id=<%= item.getId() %>">Add Details</a>

                    <% if(item.isHasDetails()) { %>
                        <a class="action-btn showDetails" href="/Item_Project/ItemController?action=ShowItemDetails&item_id=<%= item.getId() %>">Show Details</a>  
                        <a class="action-btn removeDetails"  href="/Item_Project/ItemController?action=RemoveItemDetails&item_id=<%= item.getId() %>">Remove Details</a>
                    <% } %>
                </td>
	        </tr>
         <%
                }
            } else {
         %>
            <tr>
                <td colspan="5">No items found.</td>
            </tr>
         <%
            }
          %>
        
        </tbody>
    </table>

    <div style="text-align:center;">
        <a class="btn-add" href="/Item_Project/Add-item.jsp">+ Add New Item</a>
    </div>

</body>
</html>
