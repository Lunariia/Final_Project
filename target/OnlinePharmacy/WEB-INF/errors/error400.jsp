<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<html>
<head>
    <title><fmt:message key="page.error.400.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>
<body>
<div class="backdrop"></div>
<%-- <jsp:include page="../templates/header.jsp"/> --%>
<header class="main-header">
    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value="controller?command=home"/>">Medical Garden</a>
    </div>

</header>

<main>
    <section class="main-section">
        <h2><fmt:message key="page.error.400.header"/></h2>
        <p><fmt:message key="page.error.400.description"/></p>
    </section>
</main>
</body>
</html>
