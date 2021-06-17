<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
    <form>
        <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
        <button class="btn btn-warning">수정</button>
        <button class="btn btn-danger">삭제</button>
        <br/><br/>
        <div >
            <h3>${board.title}</h3>
        </div>
        <hr/>

        <div>
            <p>${board.content}</p>
        </div>
        <hr/>
    </form>

</div>


<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>

