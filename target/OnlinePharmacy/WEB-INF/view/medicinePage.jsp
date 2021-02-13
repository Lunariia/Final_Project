<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>

<html lang="<fmt:message key="html.lang" />">
<head>
    <title>MedicinePage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/Style.css">
</head>

<div class="backdrop"></div>
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

<body>

<aside>
    <h2 class="main-nav__title">
        <fmt:message key="aside.form.menu"/>
    </h2>
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

    <nav>
        <ul class="main-nav__links">
            <li class="main-nav__link">
                <form method="POST" action="controller?command=newMedicine">
                    <button class="button" type="submit">
                        <fmt:message key="medicine.button.add"/>
                    </button>
                </form>
            </li>
        </ul>
    </nav>
</aside>

<section class="main-section__purchase">
<main class="page-main">
    <div class="row">
        <div class="main">
            <h2><fmt:message key="medicine.header"/></h2>

            <table class="table" id="medicines">
                <tr>
                    <th class="wide"><fmt:message key="medicine.table.header.number"/></th>
                    <th><fmt:message key="medicine.table.header.title"/></th>
                    <th class="wide"><fmt:message key="medicine.table.header.dosage"/></th>
                    <th class="wide"><fmt:message key="medicine.table.header.cost"/></th>
                    <th><fmt:message key="medicine.table.header.formula"/></th>
                    <th><fmt:message key="medicine.table.header.amount"/></th>
                    <th><fmt:message key="medicine.table.header.edit"/></th>
                    <th><fmt:message key="medicine.table.header.delete"/></th>

                </tr>
                <c:forEach var="medicine" items="${requestScope.medicines}" varStatus="counter">
                    <tr data-medicine-page="<c:url value="controller?command=medicine&id=${medicine.id}"/>">
                        <td class="wide">${counter.count + requestScope.itemsPerPage * (requestScope.page - 1)}</td>

                        <td class="wide">${medicine.title}</td>
                        <td class="wide">${medicine.dosage}</td>
                        <td class="wide">${medicine.cost}</td>

                        <c:choose>
                            <c:when test="${medicine.prescription == 'true'}">
                                <td class="main-section__purchase"><fmt:message key="medicine.table.inline.yes"/></td>
                            </c:when>
                            <c:otherwise>
                                <td class="main-section__purchase"><fmt:message key="medicine.table.inline.no"/></td>
                            </c:otherwise>
                        </c:choose>

                        <td class="wide">${medicine.quantity}</td>

                        <td>
                            <ctg:access accessName="edit">
                                <form method="post" action="controller?command=editMedicinePage">
                                    <input type="hidden" name="id" value="${medicine.id}">
                                    <button class="button" type="submit">
                                        <fmt:message key="medicine.table.button.edit"/>
                                    </button>
                                </form>
                                </ctg:access>
                        </td>
                        <td>
                            <ctg:access accessName="delete">
                                <form method="post" action="controller?command=deleteMedicine">
                                    <input type="hidden" name="id" value="${medicine.id}">
                                    <button class="button" type="submit">
                                        <fmt:message key="medicine.table.button.delete"/>
                                    </button>
                                </form>
                            </ctg:access>
                        </td>

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
        </div>
    </div>
</main>

</section>
<script src="${pageContext.request.contextPath}/static/js/Login.js"></script>
</body>

</html>
