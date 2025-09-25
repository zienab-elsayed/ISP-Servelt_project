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

    a {
        text-decoration: none;
        padding: 6px 14px;
        border-radius: 8px;
        margin: 2px;
        font-size: 0.9rem;
        display: inline-block;
    }

    a[href*="Update"] {
        background: #ffca28;
        color: #333;
    }

    a[href*="Delete"] {
        background: #e53935;
        color: white;
    }

    a[href*="Update"]:hover {
        background: #ffc107;
    }

    a[href*="Delete"]:hover {
        background: #c62828;
    }

    button {
        display: block;
        margin: 30px auto;
        padding: 12px 20px;
        background: #009688;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        transition: 0.3s;
    }

    button a {
        color: white;
        font-weight: bold;
        text-decoration: none;
    }

    button:hover {
        background: #00796b;
        transform: scale(1.05);
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
	                <a href="/Item_Project/Update-Item.jsp">Update</a>
	                <a href="/Item_Project/ItemController?action=DeleteItem&id=<%= item.getId() %>">Delete</a>
	            </td>
	        </tr>
         <%
                }
            }
          %>
        
        </tbody>
    </table>

    <button><a href="/Item_Project/Add-item.jsp">+ Add New Item</a></button>

</body>
</html>
