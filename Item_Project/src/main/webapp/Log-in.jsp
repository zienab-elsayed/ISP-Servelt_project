<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
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
        width: 350px;
        animation: fadeIn 1s ease-in-out;
        text-align: center;
    }

    h2 {
        margin-bottom: 25px;
        color: #00796b;
        font-size: 1.8rem;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }

    label {
        text-align: left;
        font-size: 14px;
        color: #444;
        margin-bottom: 5px;
    }

    input[type="text"],
    input[type="password"] {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 14px;
        transition: border 0.3s;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
        border-color: #009688;
        outline: none;
    }

    /* نسق زر الـ button اللي جواه لينك */
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

    /* خلي اللينك يبان كزر أبيض جوه الزر */
    button.btn a {
        color: #fff;
        text-decoration: none;
        display: block;
        width: 100%;
        height: 100%;
    }

    .link {
        margin-top: 15px;
        font-size: 14px;
    }

    .link a {
        color: #009688;
        text-decoration: none;
        font-weight: bold;
    }

    .link a:hover {
        text-decoration: underline;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-20px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="/Item_Project/UserController" method="post">
            <input type="hidden" name="action" value="Login" >

            <label>User Name</label>
            <input type="text" name="username" required>

            <label>Password</label>
            <input type="password" name="password" required>

            <button class="btn">LogIn</button>
        </form>
    </div>
</body>
</html>
