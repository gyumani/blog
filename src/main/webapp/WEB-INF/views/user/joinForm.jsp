<%--
  Created by IntelliJ IDEA.
  User: gyumani
  Date: 2021-06-14
  Time: 오후 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary">회원가입완료</button>
</div>
<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>




