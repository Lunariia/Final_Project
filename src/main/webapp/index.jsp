<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.title"/></title>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/LoginStyle.css">
</head>

<body>
    <div class="backdrop"></div>
    <%-- <jsp:include page="../templates/header.jsp"/> --%>
    <header class="main-header">
        <button class="mobile-btn"></button>
        <h1 class="main-header__title">Medical Garden</h1>
        <form method = "GET" action = "/pharmacy/controller?command=signIn">
        <button class="main-header__sign" type="button">Sign in</button>
        <form>
        <div class="dropdown">
            <button class="button" type="button"><i class=" fa fa-caret-down">Language</i></button>
            <div class="dropdown-content">
                <a href="#">English</a>
                <a href="#">Russian</a>
            </div>
        </div>
    </header>

    <main>
        <aside>
            <h2 class="main-nav__title">Menu</h2>
            <nav>
                <ul class="main-nav__links">
                    <li class="main-nav__link">
                        <form method = "POST" action = "/pharmacy/controller?command=aboutUs">
                        <button class="button" type="submit">About Us</button>
                        </form>
                    </li>
                    <li class="main-nav__link">
                        <form method = "GET" action = "/pharmacy/controller?command=contacts">
                        <button class="button">Contacts</button>
                        </form>
                    </li>
                    <li class="main-nav__link">
                        <form method = "GET" action = "/pharmacy/controller?command=departments">
                        <button class="button">Our Pharmacies</button>
                        </form>
                    </li>
                </ul>
            </nav>
        </aside>
        <section class="main-section">
            <h2 class="main-section__title">Welcome!</h2>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>