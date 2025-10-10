<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<style>
    body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #e0f7fa, #fce4ec);
        margin: 0;
        padding: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container {
        background: #fff;
        padding: 40px 50px;
        border-radius: 16px;
        box-shadow: 0px 6px 15px rgba(0,0,0,0.15);
        width: 380px;
        animation: fadeIn 1s ease-in-out;
        text-align: center;
    }

    h2 {
        margin-bottom: 25px;
        color: #00796b;
        font-size: 2rem;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 18px;
    }

    label {
        text-align: left;
        font-size: 14px;
        color: #333;
        margin-bottom: 5px;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"] {
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 15px;
        transition: all 0.3s ease;
    }

    input:focus {
        border-color: #009688;
        box-shadow: 0 0 5px rgba(0,150,136,0.3);
        outline: none;
    }

    /* نسق زر الـ button اللي فيه لينك */
    button.btn {
        background: #009688;
        border: none;
        padding: 12px;
        border-radius: 10px;
        font-size: 15px;
        font-weight: bold;
        cursor: pointer;
        transition: 0.3s;
    }

    button.btn:hover {
        background: #00796b;
        transform: scale(1.05);
    }

    button.btn a {
        color: #fff;
        text-decoration: none;
        display: block;
        width: 100%;
        height: 100%;
    }

    .link {
        margin-top: 15px;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-20px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Sign Up</h2>
        <form action="/Item_Project/UserController" methos="post">
            <input type="hidden" name="action" value="SignIn">

            <label>Email</label>
            <input type="email" name="email" required>

            <label>User Name</label>
            <input type="text" name="username" required>

            <label>Password</label>
            <input type="password" name="password" required>

            <button class="btn">SignIn</button>
        </form>
    </div>
</body>
</html>
