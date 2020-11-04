<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <c:choose>
                <c:when test="${fn:length(userOrderBeanList) == 0}">No such orders</c:when>

                <c:otherwise>
                    <table id="list_order_table">
                        <thead>
                        <tr>
                            <td>№</td>
                            <td><fmt:message key="list_orders_jsp.table.header.client"/></td>
                            <td><fmt:message key="list_orders_jsp.table.header.order"/></td>
                            <td><fmt:message key="list_orders_jsp.table.header.payment"/></td>
                        </tr>
                        </thead>

                        <c:forEach var="bean" items="${userOrderBeanList}">

                            <tr>
                                <td>${bean.id}</td>
                                <td>${bean.userName}</td>
                                <td>${bean.orderPrice}</td>
                                <td>${bean.orderPayment}</td>
                            </tr>

                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>
