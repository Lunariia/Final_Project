<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>


<html lang="<fmt:message key="html.lang" />">
<head>
    <title><fmt:message key="page.my.prescription.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>
<body>
<header class="main-header">
    <button class="mobile-btn"></button>
    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value="controller?command=homePage"/>">Medical Garden</a>
    </div>

    <form method="POST" action="/pharmacy/controller?command=logout">
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
        <h2 class="login-section__login_title"><fmt:message key="page.my.prescription.title"/></h2>

        <form method="POST" action="${pageContext.request.contextPath}/controller?command=createPrescription" class="login-section__form">

            <input type="hidden" name="userId" value="${requestScope.myPrescriptionAccount.id}">
            <input type="hidden" name="medicineId" value="${requestScope.myPrescriptionPurchase.id}">
            <input type="hidden" name="startDate" value="${requestScope.myPrescriptionStartDate}">
            <input type="hidden" name="endDate" value="${requestScope.myPrescriptionEndDate}">

            <h2><label for="edit-user"><fmt:message key="my.prescription.form.user"/></label></h2>
            <h1 id="edit-user">${requestScope.myPrescriptionAccount.firstName} ${requestScope.myPrescriptionAccount.lastName}</h1>

            <h2><label for="edit-medicine"><fmt:message key="my.prescription.form.medicine"/></label></h2>
            <h1 id="edit-medicine" >${requestScope.myPrescriptionPurchase.title}</h1>

            <h2><label for="edit-start-date"><fmt:message key="my.prescription.form.start.date"/></label></h2>
            <h1 id="edit-start-date">${requestScope.myPrescriptionStartDate}</h1>

            <h2><label for="edit-end-date"><fmt:message key="my.prescription.form.end.date"/></label></h2>
            <h1 id="edit-end-date">${requestScope.myPrescriptionEndDate}</h1>

            <div class="login-section__form_btn">
                <input  type="submit" value="<fmt:message key="my.prescription.form.button.order"/>"/>
                <input  type="button" onclick="history.back()" value="<fmt:message key="my.prescription.form.button.cancel"/>"/>
            </div>
        </form>
    </section>
</main>

</body>
</html>
