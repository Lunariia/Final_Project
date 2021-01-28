<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>


<html lang="<fmt:message key="html.lang" />">
<head>
    <title>Prescriptions</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/PurchaseStyle.css">
</head>
<body>
<div class="backdrop"></div>
<%-- <jsp:include page="../templates/header.jsp"/> --%>
<header class="main-header">
    <button class="mobile-btn"></button>

    <div class="main-header__title">
        <a class="main-header__title" href="<c:url value="controller?command=home"/>">Medical Garden</a>
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
    <section class="main-section__purchase">
        <table class="table" id="prescriptions">
            <tr>
                <th><fmt:message key="prescription.table.header.id"/></th>
                <th><fmt:message key="prescription.table.header.userID"/></th>
                <th><fmt:message key="prescription.table.header.medicine"/></th>
                <th><fmt:message key="prescription.table.header.startDate"/></th>
                <th><fmt:message key="prescription.table.header.endDate"/></th>
                <th><fmt:message key="prescription.table.header.status"/></th>

            </tr>
            <c:forEach var="prescription" items="${requestScope.prescriptions}" varStatus="counter">
            <tr data-prescription-page="<c:url value="controller?command=accountPrescriptions&id=${prescription.id}"/>">
                <td>${counter.count + requestScope.itemsPerPage * (requestScope.page - 1)}</td>
                <td>${prescription.user}</td>
                <td>${prescription.medicine}</td>
                <td>${prescription.startDate}</td>
                <td>${prescription.endDate}</td>

                <c:choose>
                <c:when test="${prescription.access == 'true'}">
                <td style="color: green" class="main-section__purchase">
                    <fmt:message key="prescription.table.text.accepted"/>
                </td>
                </c:when>
                <c:when test="${prescription.access == 'false'}">
                <td style="color: red" class="main-section__purchase">
                    <fmt:message key="prescription.table.text.refused"/>
                </td>
                </c:when>
                <c:otherwise>
                <td style="color: grey" class="main-section__purchase">
                    <fmt:message key="prescription.table.text.processed"/>
                </td>
                </c:otherwise>
                </c:choose>

                </c:forEach>
        </table>

        <div class="pagination-wrapper">
            <div class="pagination">
                <c:choose>
                    <c:when test="${requestScope.page > 1}">
                        <a class="pagination-available"
                           href="<c:url value="controller?command=accountPrescriptions&page=${requestScope.page - 1}"/>">&laquo;</a>
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
                               href="<c:url value="controller?command=accountPrescriptions&page=${i}"/>">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${requestScope.page < requestScope.numberOfPages}">
                        <a class="pagination-available"
                           href="<c:url value="controller?command=accountPrescriptions&page=${requestScope.page + 1}"/>">&raquo;</a>
                    </c:when>
                    <c:otherwise>
                        <a class="pagination-disabled">&raquo;</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </section>
</main>
<script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>
</html>