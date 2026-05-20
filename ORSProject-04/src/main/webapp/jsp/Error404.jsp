<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>404 - Page Not Found</title>

<style>
    *{
        margin:0;
        padding:0;
        box-sizing:border-box;
        font-family: Arial, sans-serif;
    }

    body{
        height:100vh;
        display:flex;
        justify-content:center;
        align-items:center;
        background:#0f172a;
        color:white;
        overflow:hidden;
    }

    .container{
        text-align:center;
        z-index:2;
    }

    .error-code{
        font-size:120px;
        font-weight:bold;
        color:#38bdf8;
        text-shadow:0 0 20px rgba(56,189,248,0.7);
    }

    .title{
        font-size:32px;
        margin-top:10px;
    }

    .message{
        margin-top:15px;
        font-size:18px;
        color:#cbd5e1;
    }

    .btn{
        display:inline-block;
        margin-top:30px;
        padding:12px 25px;
        background:#38bdf8;
        color:#0f172a;
        text-decoration:none;
        border-radius:8px;
        font-weight:bold;
        transition:0.3s;
    }

    .btn:hover{
        background:#0ea5e9;
        transform:translateY(-2px);
    }

    .circle{
        position:absolute;
        border-radius:50%;
        background:rgba(56,189,248,0.08);
        animation:float 6s ease-in-out infinite;
    }

    .circle1{
        width:300px;
        height:300px;
        top:-100px;
        left:-100px;
    }

    .circle2{
        width:400px;
        height:400px;
        bottom:-150px;
        right:-150px;
        animation-delay:2s;
    }

    @keyframes float{
        0%,100%{
            transform:translateY(0px);
        }
        50%{
            transform:translateY(20px);
        }
    }
</style>
</head>

<body>

<div class="circle circle1"></div>
<div class="circle circle2"></div>

<div class="container">

    <div class="error-code">404</div>

    <div class="title">
        Page Not Found
    </div>

    <div class="message">
        Sorry, the page you are looking for does not exist.
    </div>

    <a href="<%=request.getContextPath()%>/" class="btn">
        Back to Home
    </a>

</div>

</body>
</html>