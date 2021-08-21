<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Expos"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="row">
        <div class="col-md-3">
            <form class="search_form" id="search_form" method="post" action="/jsp/search">
                <input class="form-control form-control-sm search_box" type="search" placeholder="Search" name="search_data"
                       aria-label="Search" autocomplete="off">
                <button class="btn btn-outline-secondary btn-sm btn-block" type="submit"><i class="bi bi-search" aria-hidden="true">Search</i></button>

                <button type="button" class="btn btn-outline-info btn-sm btn-block" data-toggle="collapse"
                        data-target="#search_feed">Refine your search <span class="caret"></span>
                </button>

                <div id="search_feed" class="collapse in">
                    <hr>
                    <div class="list-group list-group">
                        <h4>Categories</h4>
                        <c:forEach var="category" items="${categoryList}">
                            <div class="list-group-item">
                                <input type="checkbox" id="category${category.id}" name="search_categories"
                                       value="${category.id}" checked>
                                <label for="category${category.id}">${category.nameEn}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                    <h4>Price</h4>
                    <select class="custom-select" name="search_price">
                        <option value="ascending" selected>Ascending&#8593;</option>
                        <option value="descending">Descending&#8595;</option>
                    </select>
                    <hr>
                    <h4>Date</h4>
                    <select class="custom-select" name="search_date">
                        <option value="ascending" selected>Ascending&#8593;</option>
                        <option value="descending">Descending&#8595;</option>
                    </select>
                </div>
            </form>
        </div>
        <div class="col-md-9">
            <c:forEach var="exposition" items="${expositionList}">

                <h3 class="title">${exposition.topic}</h3>
                <p>${exposition.description}</p>
                <p class="text-muted">
                    <i class="bi bi-calendar-event"></i> Exposition lasts from the ${exposition.startDate}
                    to the ${exposition.endDate}.<br>
                    <i class="bi bi-alarm"></i> Business hours: ${exposition.startWorkTime} - ${exposition.endWorkTime}.<br>
                    <i class="bi bi-credit-card"></i> Price: &#8372;${exposition.price}
                </p>
                <ul class="list-inline">
                    <li class="list-inline-item">
                        <button type="button" class="btn btn-outline-primary">Visit</button>
                    </li>
                    <li class="list-inline-item">
                        <form class="form-inline" action="/jsp/moderator/updateExpo">
                            <input type="hidden" name="exposition_id" value="${exposition.id}">
                            <button type="submit" class="btn btn-outline-secondary">Edit</button>
                        </form>
                    </li>
                    <li class="list-inline-item">
                        <form class="form-inline" action="/jsp/moderator/deleteExpo" method="post">
                            <input type="hidden" name="exposition_id" value="${exposition.id}">
                            <button type="submit" class="btn btn-outline-danger">Delete</button>
                        </form>
                    </li>
                </ul>
                <hr>
            </c:forEach>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>