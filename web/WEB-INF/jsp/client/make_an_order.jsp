<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make an Order</title>
</head>
<body>
<h1>Make an Order</h1>
<form action="MakeAnOrder" method="get">
    <table style="with: 50%">
        <tr>
            <td>Departure Date</td>
            <td><input type="date" name="departureDate"/></td>
        </tr>
        <tr>
            <td>Type:</td>
            <br>
            <td>Soft</td>
            <td><input type="radio" name="type" value="typeSoft"/></td>
            <td>Hard</td>
            <td><input type="radio" name="type" value="typeHard"/></td>
        </tr>
        <tr>
            <td>Weight</td>
            <td><input type="text" name="weight"/></td>
        </tr>
        <tr>
            <td>Volume</td>
            <br>
            <td>Length</td>
            <td><input type="text" name="volumeLength"/></td>
            <td>Width</td>
            <td><input type="text" name="volumeWidth"/></td>
            <td>High</td>
            <td><input type="text" name="volumeHigh"/></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" name="city"/></td>
        </tr>
    </table>
    <input type="submit" value="Order"/>
    <input type="reset" value="Reset">
</form>
</body>
</html>
