<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<html lang="${sessionScope.localization.locale}">
<head>
    <title><fmt:message key="project.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/PurchaseStyle.css">
</head>

<body>

<div class="backdrop"></div>
<%-- <jsp:include page="../templates/header.jsp"/> --%>
<header class="main-header">
    <button class="mobile-btn"></button>

    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value="/controller?command=home"/>">Medical Garden</a>
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

        <div class="pagination-wrapper">
            <div class="pagination">
                <c:choose>
                    <c:when test="${requestScope.page > 1}">
                        <a class="pagination-available"
                           href="<c:url value="controller?command=purchases&page=${requestScope.page - 1}"/>">&laquo;</a>
                    </c:when>
                    <c:otherwise>
                        <a class="pagination-disabled">&laquo;</a>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="i" begin="1" end="${requestScope.numberOfPages}">
                    <c:choose>
                        <c:when test="${i == requestScope.page}">
                            <a class="pagination-active">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="pagination-available"
                               href="<c:url value="controller?command=purchases&page=${i}"/>">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${requestScope.page < requestScope.numberOfPages}">
                        <a class="pagination-available"
                           href="<c:url value="controller?command=purchases&page=${requestScope.page + 1}"/>">&raquo;</a>
                    </c:when>
                    <c:otherwise>
                        <a class="pagination-disabled">&raquo;</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    <section class="main-section__purchase">
        <table class="table,main-section__purchase" id="medicines">
            <tr>
                <th class="main-section__purchase"><fmt:message key="medicine.table.header.number"/></th>
                <th class="main-section__purchase"><fmt:message key="purchase.table.header.title"/></th>
                <th class="main-section__purchase"><fmt:message key="purchase.table.header.dosage"/></th>
                <th class="main-section__purchase"><fmt:message key="purchase.table.header.cost"/></th>
                <th class="main-section__purchase"><fmt:message key="purchase.table.header.prescription"/></th>
                <th class="main-section__purchase"><fmt:message key="purchase.table.header.action.order"/></th>
                <th class="main-section__purchase"><fmt:message key="purchase.table.header.action.prescription"/></th>

            </tr>
            <c:forEach var="purchase" items="${requestScope.purchases}" varStatus="counter">
                <tr data-purchase-page="<c:url value="controller?command=purchase&id=${purchase.id}"/>">

                    <td class="main-section__purchase">${counter.count + requestScope.itemsPerPage * (requestScope.page - 1)}</td>
                    <td class="main-section__purchase">${purchase.title}</td>
                    <td class="main-section__purchase">${purchase.dosage}</td>
                    <td class="main-section__purchase">${purchase.cost}</td>

                    <c:choose>
                        <c:when test="${purchase.prescription == 'true'}">
                            <td class="main-section__purchase"><fmt:message key="purchase.table.inline.need"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="main-section__purchase"><fmt:message key="purchase.table.inline.no.need"/></td>
                        </c:otherwise>
                    </c:choose>

                    <td class="main-section__purchase">
                        <ctg:access accessName="order">
                            <form method="post" action="/pharmacy/controller?command=myOrder">
                                <input type="hidden" name="id" value="${purchase.id}">
                                <button class="button" type="submit">
                                    <fmt:message key="purchase.table.button.order"/>
                                </button>
                            </form>
                        </ctg:access>
                    </td>
                    <td class="main-section__purchase">
                        <c:choose>
                        <c:when test="${purchase.prescription == 'true'}">
                        <ctg:access accessName="getPrescription">
                            <form method="post" action="/pharmacy/controller?command=createPrescriptionPage">
                                <input type="hidden" name="id" value="${purchase.id}">
                                <button class="button" type="submit">
                                    <fmt:message key="purchase.table.button.get"/>
                                </button>
                            </form>
                        </ctg:access>
                        </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>
</main>
<script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>