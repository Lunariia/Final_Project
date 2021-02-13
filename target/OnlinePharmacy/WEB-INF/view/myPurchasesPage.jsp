<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.localization.locale}"/>
<fmt:setBundle basename="${sessionScope.localization.baseBundleName}"/>


<html lang="<fmt:message key="html.lang" />">
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

    <aside>
        <h2 class="main-nav__title">
            <fmt:message key="aside.form.menu"/>
        </h2>
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
    </aside>

    <section class="main-section__purchase">
        <table class="table" id="myPurchases">
            <tr>
                <th><fmt:message key="my.purchase.table.id"/></th>
                <th><fmt:message key="my.purchase.table.medicine"/></th>
                <th><fmt:message key="my.purchase.table.amount"/></th>
                <th><fmt:message key="my.purchase.table.date"/></th>
            </tr>
            <c:forEach var="purchase" items="${requestScope.myPurchases}" varStatus="counter">
            <tr data-prescription-page="<c:url value="controller?command=myPurchases&id=${purchase.id}"/>">
                <td>${counter.count + requestScope.itemsPerPage * (requestScope.page - 1)}</td>
                <td>${purchase.medicine}</td>
                <td>${purchase.amount}</td>
                <td>${purchase.dateOfPurchase}</td>
            </c:forEach>
        </table>

        <div class="pagination-wrapper">
            <div class="pagination">
                <c:choose>
                    <c:when test="${requestScope.page > 1}">
                        <a class="pagination-available"
                           href="<c:url value="controller?command=myPurchases&page=${requestScope.page - 1}"/>">&laquo;</a>
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
                               href="<c:url value="controller?command=myPurchases&page=${i}"/>">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${requestScope.page < requestScope.numberOfPages}">
                        <a class="pagination-available"
                           href="<c:url value="controller?command=myPurchases&page=${requestScope.page + 1}"/>">&raquo;</a>
                    </c:when>
                    <c:otherwise>
                        <a class="pagination-disabled">&raquo;</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>
</main>

</body>
</html>
