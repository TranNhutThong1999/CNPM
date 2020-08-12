<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="include/Header.jsp"></jsp:include>
<link rel="stylesheet" href='<c:url value="/resources/css/Menu.css" />'>
<title>Home</title>
</head>
<body>
<nav id="menu" class="navbar navbar-expand-sm navbar-dark bg-primary ">
	<button class="navbar-toggler d-lg-none" type="button"
		data-toggle="collapse" data-target="#collapsibleNavId"
		aria-controls="collapsibleNavId" aria-expanded="false"
		aria-label="Toggle navigation"></button>
	<div class="collapse navbar-collapse" id="collapsibleNavId">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0 center">
			<li class="nav-item"><a class="nav-link" href=''>SẢN PHẨM</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdownId"
				 aria-haspopup="true" aria-expanded="false">DANH MỤC</a>
				
				<div class="tooltip-z ">
					<a class="dropdown-item" href=''>Account</a> 
				</div>
				</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdownId"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">KHUYẾN
					MÃI</a>
				<div class="dropdown-menu" aria-labelledby="dropdownId">
					<a class="dropdown-item" href="#">Khuyến mãi 1</a> <a
						class="dropdown-item" href="#">Khuyên mãi 2</a>
				</div></li>
			      <input name="keyWords" class="form-control mr-sm-2" type="search" placeholder="
			      " aria-label="Search">
			      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</ul>
		<ul class="nav justify-content-center right">
			<security:authorize access="isAnonymous()">
			<li class="nav-item">
					<a class="nav-link active"
						href='<c:url value="/login"></c:url>'> Đăng Nhập </a>
			</li>
			</security:authorize>
			
			<security:authorize access="isAuthenticated()">
			<li class="nav-item">
					<a class="nav-link active"
						href=''> <security:authentication property="principal.username"/> </a>	
					<a class="nav-link active logout" onclick="FBlogout();"
						href='<c:url value="/logout"></c:url>'> thoat </a>
			</li>
			</security:authorize>
			
		</ul>
	</div>

</nav>

<!-- xong menu -->
		<jsp:include page="include/Scrip.jsp"></jsp:include>
</body>
</html>