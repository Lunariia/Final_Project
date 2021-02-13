<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<html lang="<fmt:message key="html.lang" />">
<head>
    <title><fmt:message key="project.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>

<body>

<div class="backdrop"></div>
<%-- <jsp:include page="../templates/header.jsp"/> --%>
<header class="main-header">
    <button class="mobile-btn"></button>

    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value="controller?command=homePage"/>">Medical Garden</a>
    </div>

    <form method="POST" action="controller?command=logout">
        <button class="main-header__sign" type="submit">
            <fmt:message key="header.form.signOut"/>
        </button>
    </form>

    <div class="dropdown">
        <button class="button" type="button"><i class=" fa fa-caret-down"><fmt:message key="header.form.language"/></i></button>
        <div class="dropdown-content">
            <a href="<c:url value="/controller?command=locale&lang=EN"/>">
                <fmt:message key="navbar.link.locale.en"/>
            </a>
            <a href="<c:url value="/controller?command=locale&lang=RU"/>">
                <fmt:message key="navbar.link.locale.ru.by"/>
            </a>
            <a href="<c:url value="/controller?command=locale&lang=BE"/>">
                <fmt:message key="navbar.link.locale.be.by"/>
            </a>
        </div>
    </div>
</header>

<main>
    <section class="main-section">
        <h2 class="main-section__title" style="color: red">${requestScope.message}</h2>
    </section>
</main>
</body>
</html>
