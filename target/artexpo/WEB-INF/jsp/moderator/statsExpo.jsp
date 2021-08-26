<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Statistics"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <h3 class="title">${exposition.topic}</h3>
    <p>${exposition.description}</p>
    <p class="text-muted">
        <i class="bi bi-calendar-event"></i> <fmt:message key="home_jsp.exposition.start"/>: ${exposition.startDateFormatted} |
        <fmt:message key="home_jsp.exposition.end"/>: ${exposition.endDateFormatted}.<br>
        <i class="bi bi-alarm"></i> <fmt:message key="home_jsp.exposition.hours"/>: ${exposition.startWorkTime}
        - ${exposition.endWorkTime}.<br>
        <i class="bi bi-credit-card"></i> <fmt:message key="home_jsp.exposition.price"/>: &#8372;${exposition.price}
    </p>
    <hr>
    <c:choose>
        <c:when test="${not empty ticketListStatistics}">
            <h2><fmt:message key="statsexpo_jsp.header.purchased" /></h2>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="statsexpo_jsp.table.fname" /></th>
                    <th><fmt:message key="statsexpo_jsp.table.email" /></th>
                    <th><fmt:message key="statsexpo_jsp.table.quantity" /></th>
                    <th><fmt:message key="statsexpo_jsp.table.total" /></th>
                    <th><fmt:message key="statsexpo_jsp.table.date" /></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ticket" items="${ticketListStatistics}">
                    <tr>
                        <td>${ticket.account.firstName}</td>
                        <td>${ticket.account.email}</td>
                        <td>${ticket.quantity}</td>
                        <td>${ticket.totalPrice}</td>
                        <td>${ticket.creationDateFormatted}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2><fmt:message key="statsexpo_jsp.header.notickets"/>.</h2>
        </c:otherwise>
    </c:choose>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>