<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Form</title>
</head>
<body>
<h1>Registration Form</h1>
<form action="Registration" method="get">
    <table style="with: 50%">
        <tr>
            <td>Full Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td><input type="email" name="login"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="Register"/>
    <input type="reset" value="Reset">
</form>
</body>
</html>