<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>parsers</title>
</head>
<body>

<div class="shop">
    <ul>
        <li>Shop
            <s:iterator value="shop.category" var="category">
                <ul>
                    <li><s:property value="name"/>
                        <ul>
                            <s:iterator value="#category.subcategory" var="subcategory">
                                <li><s:property value="name"/>
                                    <s:iterator value="#subcategory.product" var="product" id="testProduct">
                                        <s:set value="priceOrNotInStock" var="testPriceOrNotInStock"/>
                                        <ul>
                                            <li>Product ID: <s:property value="id"/>
                                                <ul>
                                                    <li>Name: <s:property value="name"/></li>
                                                    <li>Provider: <s:property value="provider"/></li>
                                                    <li>Model: <s:property value="model"/></li>
                                                    <li>Date of issue: <s:property value="date"/></li>
                                                    <li>Color: <s:property value="color"/></li>
                                                    <s:if test="#testPriceOrNotInStock == true">
                                                        <li>Not in stock.</li>
                                                    </s:if>
                                                    <s:else>
                                                        <li>Price: <s:property value="priceOrNotInStock"/></li>
                                                    </s:else>
                                                </ul>
                                            </li>
                                        </ul>
                                    </s:iterator>
                                </li>
                            </s:iterator>
                        </ul>
                    </li>
                </ul>
            </s:iterator>
        </li>
    </ul>
</div>
</body>
</html>
