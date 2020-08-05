<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="include/Header.jsp"></jsp:include>
<link rel="stylesheet" href='<c:url value="/resources/css/Verify.css" />'>
<title>Đăng Nhập</title>
</head>
<body>
<div id="pwdModal" class="model"  style="background-color: white;" >
	  <div id="inputEmail" class="modal-dialog modal-dialog-centered">
		  <div class="modal-content">
		      <div class="modal-header">
		       <h1 class="text-center">Verify Password</h1>
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		      </div>
		      <div class="modal-body">
		          <div class="col-md-12">
		                <div class="panel panel-default">
		                    <div class="panel-body">
		                        <div class="text-center">
									<div class="alert alert-${alert} messenger" style="text-align:center; color:#2a950f" role="alert">
							 			 <h3 id="mes">${message} ${SignUp}</h3>
									</div>
		                            <div class="panel-body">
		                                <fieldset>
		                                <form  id="idsignin" method="post" action='<c:url value="/SignUp/confirm-account" />'>
		                                    <div class="form-group">
		                                        <input id="userNameSendToken" class="form-control input-lg" placeholder="code" name="token" type="text">
		                                    </div>
		                                  
		                                    <input id="nutSigIn" class="btn btn-lg btn-primary btn-block" value="Submit" type="submit">
		                                </form>
		                                </fieldset>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		      </div>
		      <div class="modal-footer">
		          <div class="col-md-12">
		          <button id="cancel" class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				  </div>	
		      </div>
		  </div>
	  </div>
  </div>

	<jsp:include page="include/Scrip.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){

			$("#cancel").click(function(){
				window.location.href="/Minishope/login"
			})
		});
	</script>
</body>
</html>