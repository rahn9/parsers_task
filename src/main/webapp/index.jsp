<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>
        parsers
    </title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/result">
    <input type="submit" name="parserType" value="DOM" />
    <a href="/sax">SAX</a>
    <a href="/stax">StAX</a>
</form>
</body>
</html>
