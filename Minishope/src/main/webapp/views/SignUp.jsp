<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="include/Header.jsp"></jsp:include>
<link rel="stylesheet" href='<c:url value="/resources/css/LogIn.css" />'>
<title>Đăng Nhập</title>
</head>
<body>
	<div class="container-fluid background">
		<div class="card" style="width: 18rem;">
			<form id="idsignup"  method="post" action='<c:url value="/SignUp/"></c:url>'>
				<div class="card-body sigup">
					<h5 class="card-title">Sign up </h5>
						<div class="messenger local" style="display:none">Vui lòng không để trống thông tin</div>
					<div class="card-subtitle mb-2 text-muted">
						<a href=""><i class="fa fa-facebook-square" aria-hidden="true"></i></a>
						<a href=""><i class="fa fa-google-plus-square"
							aria-hidden="true"></i></a> <a href=""> <i
							class="fa fa-twitter-square" aria-hidden="true"></i></a>
					</div>
					<div class="input-group flex-nowrap one ">
						<div class="input-group-prepend">
							<span class="input-group-text" id="addon-wrapping"><i
								class="fa fa-user" aria-hidden="true"></i></span>
						</div>
						<input id="userNameSignUp" name="tenDangNhap" type="text" 
							class="form-control" placeholder="Username" aria-label="Username"
							aria-describedby="addon-wrapping">
					</div>
					<label id="labelUserNameSignUp" class="messages" for="tenDangNhap"></label>
					<div class="input-group flex-nowrap ">
						<div class="input-group-prepend">
							<span class="input-group-text " id="addon-wrapping"><i
								class="fa fa-envelope" aria-hidden="true"></i></i></span>
						</div>
						<input id="email" name="email" type="text" class="form-control ok" 
							placeholder="Email" aria-label="Username"
							aria-describedby="addon-wrapping">
					</div>
					<label id="labelEmail" for="email" class="messages"></label>
					<div class="input-group flex-nowrap ">
						<div class="input-group-prepend">
							<span class="input-group-text " id="addon-wrapping"><i
								class="fa fa-key" aria-hidden="true"></i></span>
						</div>
						<input id="passwordSignUp" name="matKhau" type="password" 
							class="form-control ok" placeholder="Password" aria-label="Username"
							aria-describedby="addon-wrapping">
					</div>
					<label id="lablePassword" for="matKhau" class="messages"></label>
					<div class="input-group flex-nowrap " >
						<div class="input-group-prepend">
							<span class="input-group-text " id="addon-wrapping"><i
								class="fa fa-unlock-alt" aria-hidden="true"></i></i></span>
						</div>
						<input id="pre-password" name="pre_password" type="password" 
							class="form-control ok" placeholder="Pre-password"
							aria-label="Username" aria-describedby="addon-wrapping">
					</div>
					<label id="labelPre-password" for="pre-password" class="messages"></label>
						<button id="redirect" type=button class="btn btn-infomation">Log In</button>
					<button id="nutSignUp" type="submit" class="btn btn-warning">Sign Up</button>
					
				</div>
			</form>


		</div>

	</div>
	<jsp:include page="include/Scrip.jsp"></jsp:include>
<script src='<c:url value="/resources/js/1.js" />'  type="text/javascript" ></script>

</body>
</html>