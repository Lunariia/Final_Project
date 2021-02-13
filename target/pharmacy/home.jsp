<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<html lang="<fmt:message key="html.lang" />">

<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>

<body>
    <div class="backdrop"></div>
    <header class="main-header">
        <button class="mobile-btn"></button>
        <h1 class="main-header__title">Medical Garden</h1>

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
        <aside>
            <h2 class="main-nav__title">
                <fmt:message key="aside.form.menu"/>
            </h2>
            <c:choose>
            <c:when test="${sessionScope.account.role == 'WORKER'}">
            <nav>
                <ul class="main-nav__links">
                    <li class="main-nav__link">
                        <form method="POST" action="controller?command=medicine">
                            <button class="button" type="submit">
                                <fmt:message key="aside.form.medicines"/>
                            </button>
                        </form>
                    </li>
                </ul>
            </nav>
            </c:when>
                <c:when test="${sessionScope.account.role == 'DOCTOR'}">
                    <nav>
                        <ul class="main-nav__links">
                            <li class="main-nav__link">
                                <form method="POST" action="controller?command=prescriptions">
                                    <button class="button" type="submit">
                                        <fmt:message key="aside.form.prescriptions"/>
                                    </button>
                                </form>
                            </li>
                        </ul>
                    </nav>
                </c:when>
            <c:otherwise>
                <nav>
                    <ul class="main-nav__links">
                        <li class="main-nav__link">
                            <form method="POST" action="controller?command=purchases">
                                <button class="button" type="submit">
                                    <fmt:message key="aside.form.purchase"/>
                                </button>
                            </form>
                        </li>
                        <li class="main-nav__link">
                            <form method="POST" action="controller?command=accountPrescriptions">
                                <button class="button" type="submit">
                                    <fmt:message key="aside.form.myRecipes"/>
                                </button>
                            </form>
                        </li>
                        <li class="main-nav__link">
                            <form method="POST" action="controller?command=myPurchases">
                                <button class="button" type="submit">
                                    <fmt:message key="aside.form.myPurchases"/>
                                </button>
                            </form>
                        </li>
                    </ul>
                </nav>
            </c:otherwise>
            </c:choose>
        </aside>
        <section class="main-section">
            <h2 class="main-section__title">${sessionScope.account.role}</h2>
        </section>
    </main>

    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>