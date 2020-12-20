<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

        <h1><fmt:message key="project.name"/></h1>
        <p><fmt:message key="project.description"/></p>

<header class="main-header">
        <button class="mobile-btn"></button>
        <h1 class="main-header__title">Medical Garden</h1>
        <form method = "GET" action = "/pharmacy/controller?command=signIn">
        <button class="main-header__sign" type="button">Sign in</button>
        <form>
        <div class="dropdown">
            <button class="button" type="button"><i class=" fa fa-caret-down">Language</i></button>
            <div class="dropdown-content">
                <a href="<c:url value="/controller?command=locale&lang=EN"/>">
                    <fmt:message key="navbar.link.locale.en"/>
                </a>
                <a href="<c:url value="/controller?command=locale&lang=BE"/>">
                    <fmt:message key="navbar.link.locale.be.by"/>
                </a>
                    <fmt:message key="navbar.link.locale.ru.by"/>
                </a>
            </div>
        </div>
    </header>