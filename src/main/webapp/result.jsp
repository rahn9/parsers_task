<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>
        ${parserName}
    </title>
</head>
<body>
<table>
    <tr>
        <td>
            Shop
        </td>
    </tr>
    <c:if test="${not empty shop.categories}">
        <c:forEach var="category" items="${shop.categories}">
            <tr>
                <td><c:out value="${category.name}"/></td>
            </tr>
            <c:if test="${not empty category.subcategories}">
                <c:forEach var="subcategory" items="${category.subcategories}">
            <tr>
                <td><c:out value="${subcategory.name}"/></td>
            </tr>
                    <c:if test="${not empty subcategory.products}">
                        <c:forEach var="product" items="${subcategory.products}">
                                <td><c:out value="${product.name}"/></td>
                                <td><c:out value="${product.model}"/></td>
                                <td><c:out value="${product.dateOfIssue}"/></td>
                                <td><c:out value="${product.color}"/></td>
                                <td><c:out value="${product.producer}"/></td>
                                <c:if test="${product.price} != 0">
                                <td><c:out value="${product.price}"/></td>
                                </c:if>
                            <c:if test="${product.notInStock}">
                                <td>Not in stock</td>
                            </c:if>
                            </tr>
                </c:forEach>
            </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
