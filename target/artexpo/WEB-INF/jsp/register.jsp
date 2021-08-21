<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<c:set var="title" value="Register"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form action="/jsp/register" method="post" name="registration">
        <div class="row form-group">
            <div class="col">
                <label for="fname">First name:</label>
                <input type="text" class="form-control" id="fname" placeholder="Enter first name" name="fname">
            </div>
            <div class="col">
                <label for="lname">Last name:</label>
                <input type="text" class="form-control" id="lname" placeholder="Enter last name" name="lname">
            </div>
        </div>
        <div class="form-group">
            <label for="email">Email address:</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script src="/js/form-validation.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
