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
<%-- <jsp:include page="../templates/header.jsp"/> --%>
<header class="main-header">
    <button class="mobile-btn"></button>

    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value="controller?command=home"/>">Medical Garden</a>
    </div>

    <form method="POST" action="/pharmacy/controller?command=loginPage">
        <button class="main-header__sign" type="submit">
            <fmt:message key="header.form.signIn"/>
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
            <h2 class="main-nav__title"><fmt:message key="aside.form.menu"/></h2>
            <nav>
                <ul class="main-nav__links">
                    <li class="main-nav__link">
                        <form method="POST" action="/pharmacy/controller?command=aboutUs">
                            <button class="button" type="submit">
                                <fmt:message key="aside.form.aboutUs"/>
                            </button>
                        </form>
                    </li>
                    <li class="main-nav__link">
                        <form method="POST" action="/pharmacy/controller?command=contacts">
                            <button class="button">
                                <fmt:message key="aside.form.contacts"/>
                            </button>
                        </form>
                    </li>
                    <li class="main-nav__link">
                        <form method="POST" action="/pharmacy/controller?command=departments">
                            <button class="button">
                                <fmt:message key="aside.form.departments"/>
                            </button>
                        </form>
                    </li>
                </ul>
            </nav>
        </aside>
        <section class="main-section">
            <h2 class="main-section__title__tetra">About Us</h2>
            <h2 class="main-section__information">Medical garden pharmacies is the largest pharmacy chain in Belarus.</p>
                <p> Today, the ADEL network of pharmacies has more than 100 pharmacies throughout the territory of the Republic of Belarus!</p>
            </h2>
            </h2>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>