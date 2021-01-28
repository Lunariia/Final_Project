<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>


<html lang="<fmt:message key="html.lang" />">
<head>
    <title><fmt:message key="page.medicine.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>
<body>
<div class="backdrop"></div>
<header class="main-header">
    <button class="mobile-btn"></button>

    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value=" controller?command=home "/>">Medical Garden</a>
    </div>

    <form method="POST" action="/pharmacy/controller?command=logout">
        <button class="main-header__sign" type="submit">
            <fmt:message key="header.form.signOut"/>
        </button>
    </form>

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
    <section class="login-section">
        <c:choose>
            <c:when test="${requestScope.medicine != null}">
                <h2 class="login-section__login_title"><fmt:message key="page.medicine.title.edit"/></h2>
            </c:when>
            <c:otherwise>
                <h2 class="login-section__login_title"><fmt:message key="page.medicine.title.add"/></h2>
            </c:otherwise>
        </c:choose>

        <form method="POST" action="${pageContext.request.contextPath}/controller?command=saveMedicine" class="login-section__form">

            <input type="hidden" name="id" value="${requestScope.medicine.id}">

            <h2><label for="edit-title"><fmt:message key="medicine.editor.form.label.title"/></label></h2>
            <input type="text" id="edit-title" name="title"
                   placeholder="<fmt:message key="medicine.editor.form.label.title.placeholder"/>"
                   value="${requestScope.medicine.title}" maxlength="255" required>

            <h2><label for="edit-dosage"><fmt:message key="medicine.editor.form.label.dosage"/></label></h2>
            <input type="text" id="edit-dosage" name="dosage"
                   placeholder="<fmt:message key="medicine.editor.form.label.dosage.placeholder"/>"
                   value="${requestScope.medicine.dosage}" min="0" required>

            <h2><label for="edit-cost"><fmt:message key="medicine.editor.form.label.cost"/></label></h2>
            <input type="text" id="edit-cost" name="cost"
                   placeholder="<fmt:message key="medicine.editor.form.label.cost.placeholder"/>"
                   value="${requestScope.medicine.cost}" min="1" required>

            <h2><label for="edit-prescription"><fmt:message key="medicine.editor.form.label.prescription"/></label></h2>
            <input type="text" id="edit-prescription" name="prescription"
                   placeholder="<fmt:message key="medicine.editor.form.label.prescription.placeholder"/>"
                   value="${requestScope.medicine.prescription}" maxlength="10" required>

            <h2><label for="edit-quantity"><fmt:message key="medicine.editor.form.label.quantity"/></label></h2>
            <input type="text" id="edit-quantity" name="quantity"
                   placeholder="<fmt:message key="medicine.editor.form.label.quantity.placeholder"/>"
                   value="${requestScope.medicine.quantity}" max="1000" required>

            <div class="login-section__form_btn">
                <c:choose>
                <c:when test="${requestScope.medicine != null}">
                    <input  type="submit" value="<fmt:message key="medicine.editor.form.button.edit"/>"/>
                    <input  type="button" onclick="history.back()" value="<fmt:message key="medicine.editor.form.button.cancel"/>"/>
                </c:when>
                <c:otherwise>
                    <input  type="submit" value="<fmt:message key="medicine.editor.form.button.submit"/>"/>
                    <input  type="button" onclick="history.back()" value="<fmt:message key="medicine.editor.form.button.cancel"/>"/>
                </c:otherwise>
                </c:choose>
            </div>
        </form>
    </section>
</main>
</body>
</html>
