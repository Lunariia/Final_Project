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

    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value=" controller?command=home "/>">Medical Garden</a>
    </div>

    <div class="dropdown">
        <button class="button" type="button"><i class=" fa fa-caret-down">Language</i></button>
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
            <h2 class="main-section__title__tetra">Our Pharmacies</h2>
            <h2 class="main-section__information__departments">
                <p>st. Rokossovskogo, 1/a</p>
                <p>FarmOstrov Pharmacy on Rokosovskogo until 22:00</p>
                <br>
                <p>st.Lynkova, 19</p>
                <p>FarmOstrov Pharmacy on Lynkova until 22:00</p>
                <br>
                <p>Rokossovsky Ave., 145/a</p>
                <p>FarmOstrov Pharmacy on Rokossovskogo until 22:00</p>
                <br>
                <p>st.V.Horuzhei, 31, pom. 1N (mn "Euroopt")</p>
                <p>InLek Pharmacy # 1 until 21:00</p>
            </h2>
        </section>
    </main>
    <script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>