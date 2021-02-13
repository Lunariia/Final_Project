<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="WEB-INF/tld/localdateformat" prefix="f" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>


<html lang="<fmt:message key="html.lang" />">
<head>
    <title><fmt:message key="page.order.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>
<body>
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
<section class="login-section">
    <h2 class="login-section__login_title"><fmt:message key="page.order.title"/></h2>

    <form method="POST" action="${pageContext.request.contextPath}/controller?command=buy" class="login-section__form">
        <input type="hidden" name="userId" value="${requestScope.myPrescriptionAccount.id}">
        <input type="hidden" name="balance" value="${requestScope.myPrescriptionAccount.balance}">
        <input type="hidden" name="cost" value="${requestScope.order.cost}">
        <input type="hidden" name="title" value="${requestScope.order.title}">
        <input type="hidden" name="prescription" value="${requestScope.order.prescription}">
        <input type="hidden" name="purchaseDate" value="${requestScope.purchaseDate}">

        <input type="hidden" name="id" value="${requestScope.order.id}">

        <h2><label for="edit-title"><fmt:message key="order.form.title"/></label></h2>
        <h1 id="edit-title">${requestScope.order.title}</h1>

        <h2><label for="edit-dosage"><fmt:message key="order.form.dosage"/></label></h2>
        <h1 id="edit-dosage">${requestScope.order.dosage}</h1>

        <h2><label for="edit-cost"><fmt:message key="order.form.cost"/></label></h2>
        <h1 id="edit-cost">${requestScope.order.cost}</h1>

        <div class="login-section__form_btn">
            <input  type="submit" value="<fmt:message key="order.form.button.buy"/>"/>
            <input  type="button" onclick="history.back()" value="<fmt:message key="order.form.button.cancel"/>"/>
        </div>
    </form>
</section>
</main>

</body>
</html>
