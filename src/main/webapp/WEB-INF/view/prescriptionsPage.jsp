<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>


<html lang="<fmt:message key="html.lang" />">
<head>
    <title>PrescriptionPage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>

<div class="backdrop"></div>
<header class="main-header">
    <button class="mobile-btn"></button>
    <h1 class="main-header__title">Medical Garden</h1>

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

<body>
<main class="page-main">
<table class="table" id="prescriptions">
    <tr>
        <th><fmt:message key="prescription.table.header.forWhom"/></th>
        <th><fmt:message key="prescription.table.header.id"/></th>
        <th><fmt:message key="prescription.table.header.userID"/></th>
        <th><fmt:message key="prescription.table.header.medicine"/></th>
        <th><fmt:message key="prescription.table.header.startDate"/></th>
        <th><fmt:message key="prescription.table.header.endDate"/></th>
        <th><fmt:message key="prescription.table.header.status"/></th>
        <th><fmt:message key="prescription.table.header.action.accept"/></th>
        <th><fmt:message key="prescription.table.header.action.refuse"/></th>

    </tr>

    <c:forEach var="prescription" items="${requestScope.prescriptions}">
        <tr>
            <td>${sessionScope.account.firstName} ${sessionScope.account.lastName}</td>
            <td>${prescription.id}</td>
            <td>${prescription.user}</td>
            <td>${prescription.medicine}</td>
            <td>${prescription.startDate}</td>
            <td>${prescription.endDate}</td>

                <c:choose>
                    <c:when test="${prescription.access == 'true'}">
                        <td style="color: green"><fmt:message key="prescription.table.header.status.accepted"/></td>
                    </c:when>
                    <c:when test="${prescription.access == 'false'}">
                        <td style="color: red"><fmt:message key="prescription.table.header.status.refused"/></td>
                    </c:when>
                    <c:otherwise>
                        <td style="color: grey"><fmt:message key="prescription.table.header.status.none"/></td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${prescription.access == 'true'}">
                        <td></td>
                        <td>
                            <ctg:access accessName="refuse">
                                <form method="post" action="/pharmacy/controller?command=refuse">
                                    <input type="hidden" name="id" value="${prescription.id}">
                                    <button class="button" type="submit">
                                        <fmt:message key="prescription.table.button.refuse"/>
                                    </button>
                                </form>
                            </ctg:access>
                        </td>
                    </c:when>
                    <c:when test="${prescription.access == 'false'}">
                        <td>
                            <ctg:access accessName="accept">
                                <form method="post" action="/pharmacy/controller?command=accept">
                                    <input type="hidden" name="id" value="${prescription.id}">
                                    <button class="button" type="submit">
                                        <fmt:message key="prescription.table.button.accept"/>
                                    </button>
                                </form>
                            </ctg:access>
                        </td>
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <ctg:access accessName="accept">
                                <form method="post" action="/pharmacy/controller?command=accept">
                                    <input type="hidden" name="id" value="${prescription.id}">
                                    <button class="button" type="submit">
                                        <fmt:message key="prescription.table.button.accept"/>
                                    </button>
                                </form>
                            </ctg:access>
                        </td>
                        <td>
                            <ctg:access accessName="refuse">
                                <form method="post" action="/pharmacy/controller?command=refuse">
                                    <input type="hidden" name="id" value="${prescription.id}">
                                    <button class="button" type="submit">
                                        <fmt:message key="prescription.table.button.refuse"/>
                                    </button>
                                </form>
                            </ctg:access>
                        </td>
                    </c:otherwise>
                </c:choose>

        </tr>
            </c:forEach>
</table>
<div class="pagination-wrapper">
    <div class="pagination">
        <c:choose>
            <c:when test="${requestScope.page > 1}">
                <a class="pagination-available"
                   href="<c:url value="controller?command=medicines&page=${requestScope.page - 1}"/>">&laquo;</a>
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
                       href="<c:url value="controller?command=medicine&page=${i}"/>">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${requestScope.page < requestScope.numberOfPages}">
                <a class="pagination-available"
                   href="<c:url value="controller?command=medicine&page=${requestScope.page + 1}"/>">&raquo;</a>
            </c:when>
            <c:otherwise>
                <a class="pagination-disabled">&raquo;</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</main>
</body>

</html>
