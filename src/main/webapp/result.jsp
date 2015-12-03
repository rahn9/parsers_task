<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya_Doroshenko
  Date: 11/13/2015
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="dist/themes/default/style.min.css"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title></title>
</head>
<body>

<div id="jstree" class="demo plugin-demo">
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

<!-- 4 include the jQuery library -->
<script src="dist/libs/jquery-1.11.3.js"></script>
<!-- 5 include the minified jstree source -->
<script src="dist/jstree.min.js"></script>
<script>
    $(function () {
        // 6 create an instance when the DOM is ready
        $('#jstree').jstree({
            "plugins": ["types"],
            "types": {
                "default": {
                    "icon": "glyphicon glyphicon-flash"
                },
                "demo": {
                    "icon": "glyphicon glyphicon-ok"
                }
            }
        });
    });
</script>
</body>
</html>
