<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivery</title>
</head>

<body>
<table id="main-container">
    <tr>
        <td class="content center">
            <form id="login_form" action="controller" method="post">
                <input type="hidden" name="command" value="login"/>
                <fieldset>
                    <legend>
                        <fmt:message key="login_jsp.label.login"/>
                    </legend>
                    <input name="login"/><br/>
                </fieldset>
                <br/>
                <fieldset>
                    <legend>
                        <fmt:message key="login_jsp.label.password"/>
                    </legend>
                    <input type="password" name="password"/>
                </fieldset>
                <br/>
                <input type="submit" value='Login'>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
