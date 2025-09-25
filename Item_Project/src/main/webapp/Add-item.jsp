<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Item</title>

<style>
    body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e0f7fa, #fce4ec);
        margin: 0;
        padding: 0;
    }

    .container {
        width: 400px;
        margin: 60px auto;
        padding: 30px;
        background: #fff;
        box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
        border-radius: 12px;
    }

    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 25px;
        font-size: 1.8rem;
    }

    label {
        display: block;
        margin: 12px 0 6px;
        color: #555;
        font-weight: bold;
    }

    input[type="number"],
    input[type="text"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 1rem;
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        background: #009688;
        color: white;
        border: none;
        border-radius: 8px;
        margin-top: 20px;
        font-size: 1rem;
        font-weight: bold;
        cursor: pointer;
        transition: 0.3s;
    }

    input[type="submit"]:hover {
        background: #00796b;
        transform: scale(1.05);
    }
</style>
</head>
<body>

<div class="container">
    <h1>Add New Item</h1>
    <form action="/Item_Project/ItemController" method="get">
        <input type="hidden" name="action" value="AddItem">

        <label>ID</label>
        <input type="number" name="id" required>

        <label>Name</label>
        <input type="text" name="name" required>

        <label>Price</label>
        <input type="number" name="price" step="0.01" required>

        <label>Total Number</label>
        <input type="number" name="total_number" required>

        <input type="submit" value="Add Item">
    </form>
</div>

</body>
</html>
