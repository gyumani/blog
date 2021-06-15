<%--
  Created by IntelliJ IDEA.
  User: gyumani
  Date: 2021-06-14
  Time: 오후 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form action="#" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input name="remember" class="form-check-input"  type="checkbox"> Remember me
            </label>
        </div>

        <button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
    </form>
</div>


<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>




