<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.Item.model.ItemDetails" %>
    <%@ page import="java.util.List" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Details</title>
<style>
    body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e0f7fa, #fce4ec);
        margin: 0;
        padding: 30px;
    }

    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 25px;
        font-size: 2rem;
        text-transform: uppercase;
        letter-spacing: 1.5px;
    }

    table {
        width: 70%;
        margin: auto;
        border-collapse: collapse;
        background: #fff;
        box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
        border-radius: 12px;
        overflow: hidden;
    }

    th {
        background: #009688;
        color: white;
        padding: 15px;
        font-size: 1rem;
        text-transform: uppercase;
    }

    td {
        padding: 14px;
        text-align: center;
        border-bottom: 1px solid #ddd;
        font-size: 1rem;
        color: #444;
    }

    tr:nth-child(even) td {
        background: #f9f9f9;
    }

    tr:hover td {
        background: #e0f2f1;
        transition: 0.3s;
    }
</style>
</head>
<body>
   <h1>Item Details</h1>
   <%
      List<ItemDetails> itemDetails = (List<ItemDetails>) request.getAttribute("itemDetailsList");
   
   %>

   <table border="1">
      <tr>
         <th>Description</th>
         <th>Category</th>
         <th>Brand</th>
         <th>Date</th>
      </tr>

       <%
          if(itemDetails != null){
           for(ItemDetails itemDetail : itemDetails){
       
       %>
         <tr>
              <td><%= itemDetail.getDescription() %></td>
              <td><%=itemDetail.getCategory() %></td>
              <td><%= itemDetail.getBrand() %></td>
              <td><%= itemDetail.getDate() %></td>
	            
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
      
   </table>
</body>

</html>
