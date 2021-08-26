<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Add exposition"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/moderator/addExpo" method="post" name="add-exposition">
        <div class="form-group">
            <label for="topic"><fmt:message key="addexpo_jsp.label.topic"/>:</label>
            <input type="text" class="form-control" placeholder="<fmt:message key="addexpo_jsp.placeholder.topic" />"
                   id="topic" name="topic">
        </div>
        <div class="form-group">
            <label for="description"><fmt:message key="addexpo_jsp.label.description"/>:</label>
            <textarea class="form-control" id="description" name="description"
                      placeholder="<fmt:message key="addexpo_jsp.placeholder.description"/>"
                      rows="3"></textarea>
        </div>
        <div class="row form-group">
            <div class="col">
                <label for="start_date"><fmt:message key="addexpo_jsp.label.start.date"/>:</label>
                <input type="text" class="form-control datepicker" id="start_date"
                       placeholder="<fmt:message key="addexpo_jsp.placeholder.start.date" />"
                       name="start_date" autocomplete="off">
            </div>
            <div class="col">
                <label for="end_date"><fmt:message key="addexpo_jsp.label.end.date"/>:</label>
                <input type="text" class="form-control datepicker" id="end_date"
                       placeholder="<fmt:message key="addexpo_jsp.placeholder.end.date" />"
                       name="end_date" autocomplete="off">
            </div>
        </div>
        <div class="row form-group">
            <div class="col">
                <label for="start_time"><fmt:message key="addexpo_jsp.label.start.time"/>:</label>
                <input type="text" class="form-control timepicker" id="start_time"
                       placeholder="<fmt:message key="addexpo_jsp.placeholder.start.time" />"
                       name="start_time" autocomplete="off">
            </div>
            <div class="col">
                <label for="end_time"><fmt:message key="addexpo_jsp.label.end.time"/>:</label>
                <input type="text" class="form-control timepicker" id="end_time"
                       placeholder="<fmt:message key="addexpo_jsp.placeholder.end.time" />"
                       name="end_time" autocomplete="off">
            </div>
        </div>
        <div class="form-group row">
            <div class="col">
                <label for="price"><fmt:message key="addexpo_jsp.label.price"/>:</label>
                <input type="text" class="form-control" id="price" name="price"
                       placeholder="<fmt:message key="addexpo_jsp.placeholder.price" />"
                       autocomplete="off">
            </div>
            <div class="col">
                <label for="category"><fmt:message key="addexpo_jsp.label.category"/>:</label>
                <select class="custom-select" id="category" name="category">
                    <c:choose>
                        <c:when test="${sessionScope.lang eq 'ru'}">
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.id}">${category.nameRu}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.id}">${category.nameEn}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="halls"><fmt:message key="addexpo_jsp.label.halls"/>:</label>
            <select class="custom-select" id="halls" name="halls" multiple>
                <c:forEach var="hall" items="${freeHallList}">
                    <option value="${hall.id}"><fmt:message key="updateexpo_jsp.option.hall" /> ${hall.id}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="addexpo_jsp.button.add"/></button>
    </form>


    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/form-validation.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
<!-- this should go after your </body> -->
<link rel="stylesheet" type="text/css" href="/css/jquery.datetimepicker.css"/>
<script src="/js/jquery.js"></script>
<script src="/js/jquery.datetimepicker.full.min.js"></script>
<script>
    jQuery.noConflict();
    jQuery('.datepicker').datetimepicker({
        timepicker: false,
        format: 'd/m/Y'
    });
    jQuery('.timepicker').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });
</script>
</html>