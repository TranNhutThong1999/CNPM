<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="UTF-8">
<jsp:include page="include/Header.jsp"></jsp:include>
<link rel="stylesheet" href='<c:url value="/resources/css/LogIn.css" />'>
<title>Đăng Nhập</title>
</head>
<body>
<!-- fdfd -->
<!--modal-->
<div id="pwdModal" class="modal fade"  tabindex="-1" role="dialog" aria-hidden="true" style="background-color: rgba(17, 17, 17, 0.5);" data-keyboard="true" data-backdrop="static">
  <div id="inputEmail" class="modal-dialog modal-dialog-centered">
	  <div class="modal-content">
	      <div class="modal-header">
	       <h1 class="text-center">What's My Password?</h1>
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	      </div>
	      <div class="modal-body">
	          <div class="col-md-12">
	                <div class="panel panel-default">
	                    <div class="panel-body">
	                        <div class="text-center">
	                          <p id="message-mail">If you have forgotten your password you can reset it here.</p>
	                            <div class="panel-body">
	                                <fieldset>
	                                <form>
	                                    <div class="form-group">
	                                        <input id="userNameSendToken" class="form-control input-lg" placeholder="User Name" name="username" type="text">
	                                    </div>	                                   
	                                    <div id="timer"> After <span id="dem"></span> you can resend code to your mail</div>
	                                    <input id="btn-mailSendToken" class="btn btn-lg btn-primary btn-block" value="Send code to your Email" type="button">
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
	          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	           <button id="back-changePassword"class="btn" >Change Password</button>
			  </div>	
	      </div>
	  </div>
  </div>
  <div id="inputPassword" class="modal-dialog modal-dialog-centered">
	  <div class="modal-content">
	      <div class="modal-header">
	       <h1 class="text-center">Change Password</h1>
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	      </div>
	      <div class="modal-body">
	          <div class="col-md-12">
	                <div class="panel panel-default">
	                    <div class="panel-body">
	                        <div class="text-center">
	                          <p id="message-password">You can change new password on 15 minutes.</p>
	                            <div class="panel-body">
	                                <fieldset>
	                                <form>
	                              	 <!--  <div class="form-group">
	                                        <input id="token" class="form-control input-lg code" placeholder="code" name="token" type="text">
	                                   		<label for="code" style="color:black;">Code send to your mail</label>
	                                    </div> -->
	                                    <div class="form-group">
	                                        <input id="forgot-newPassword" class="form-control input-lg" placeholder="New Password" name="password" type="password">
	                                    </div>
	                                     <div class="form-group">
	                                        <input id="forgot-prePassword" class="form-control input-lg" placeholder="Confirm" name="prePassword" type="password">
	                                    </div>
	                                    <input id="btn-changePassword"class="btn btn-lg btn-primary btn-block" value="Change My Password" type="button">
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
	          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	           <button id="back-sendToken"class="btn" >Send Code</button>
			  </div>	
	      </div>
	  </div>
  </div>
</div>
	<div id="login" class="container-fluid background">
		<div class="card" style="width: 18rem;">		
		<div class="card-subtitle mb-2 text-muted">
						<a href=""><i class="fa fa-facebook-square" aria-hidden="true"></i></a>
						<a href=""><i class="fa fa-google-plus-square"
							aria-hidden="true"></i></a> <a href=""> <i
							class="fa fa-twitter-square" aria-hidden="true"></i></a>
					</div>
			<form  id="idsignin">  <%-- method="post" action='<c:url value="/j_spring_security_check" />' --%>
				<div class="card-body sigin" >
					<h5 class="card-title">Sign in</h5>
						<div class="alert alert-${alert} messenger" style="text-align:center; color:#2a950f" role="alert">
			 			 <h3>${message} ${SignUp}</h3>
					</div>
					
					<div class="input-group flex-nowrap one">
						<div class="input-group-prepend">
							<span class="input-group-text" id="addon-wrapping"><i
								class="fa fa-user" aria-hidden="true"></i></span>
						</div>
						<input id="userName" name="username" type="text" required
							class="form-control" placeholder="Username" aria-label="Username"
							aria-describedby="addon-wrapping">
					</div>
					<div class="input-group flex-nowrap">
						<div class="input-group-prepend">
							<span class="input-group-text" id="addon-wrapping"><i
								class="fa fa-key" aria-hidden="true"></i></span>
						</div>
						<input id="password" name="password" type="password" required
							class="form-control" placeholder="Password" aria-label="Username"
							aria-describedby="addon-wrapping">
					</div>
					<div class="check">
							<div class="custom-control custom-checkbox mb-2 mt-1 rememberme">
								    <input type="checkbox" class="custom-control-input" name="remember-me" id="remeberme" >
								    <label class="custom-control-label" for="remeberme">Remember me</label>
								  </div>	
					<button id="nutSigIn" type="button" class="btn btn-warning">Login</button>
					<!--  <button id="nutAjax" type=button class="btn btn-warning">ajax</button> -->
					
				</div>
				<div class="bottom">
				<div class="content1">
					<h3>If you haven't account?</h3> <a id="redirectsignup" href='<c:url value="/SignUp/" />' >Create account?</a>
				</div>
				<div class="content2"> <a id="clickForget" href="#" data-target="#pwdModal" data-toggle="modal">Forgot my password?</a></div>
				</div>
				</div>
			</form>
			<div id="status">
</div>
<div class="login_fb">
<p>Login with </p>
	<fb:login-button class="fb"
 		 scope="public_profile,email"
 		 onlogin="checkLoginState();">
	</fb:login-button>
</div>

<jsp:include page="include/Scrip.jsp"></jsp:include>
<script>

 	
	function checkLoginState() {
		  FB.getLoginStatus(function(response) {
		    statusChangeCallback(response);
		  });
		}
	
 	function statusChangeCallback(response) {
                let token= response.authResponse.accessToken;
                let userID=response.authResponse.userID;
                if (response.status === 'connected') {
                    // Logged into your app and Facebook.
                    FB.api('/me?fields=name,email,picture,first_name,last_name', function (res) {
                    	 var object={
        						token:token,
        						userID:userID,
        						lastname:res.last_name,
        						firstname:res.first_name,
        						//picture:res.picture,
        						email:res.email
        				}
                    	 console.log(object)
                    	 sendInforToServer(object)
                    });
                    
                   
                } else {
                    document.getElementById('status').innerHTML = 'Please log ' +
                      'into this app.';
                }
            }

	 function sendInforToServer(object){
		console.log(object)
		var xhr = new XMLHttpRequest();
		var url = "/Minishope/Api/login-Facebook";
		xhr.open("POST", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		 xhr.onreadystatechange = function () {
		   /*  if (xhr.readyState === 4 && xhr.status === 200) {
		      window.location.href="/Minishope/Home"
		        console.log(json.email + ", " + json.password);
		    } */
		    var value=xhr.responseText;
		   if(value==="failure"){
			   console.log(value)
			 
		   }else{
			   sessionStorage.setItem('token', value);
			   window.location.href="/Minishope/views/test.jsp";
		   }
		}; 
		var data = JSON.stringify(object);
		xhr.send(data);
		
	}  

	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '346794289619979',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v7.0'
	    });
	      
	    FB.AppEvents.logPageView();   
	      
	  };

	  (function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "https://connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));
</script>
	
	
	<script src='<c:url value="/resources/js/1.js" />'  type="text/javascript" ></script>
	<script type="text/javascript">

		$(window).ready(function(){
			var dem=30;
			
			$("#pwdModal").hide();
			$('#inputEmail').hide();
			$("#inputPassword").hide();
			$("#back-changePassword").hide();
		
			$('#nutSigIn').click(function(e){
			//	e.preventdefault();
				let name=$("#userName").val();
				let password =$("#password").val();
				$.ajax({
					url:"/Minishope/Api/login",
					method:"post",
					data:{
						username:name,
						password:password
					},
					success:function(value){
						sessionStorage.setItem('token', value);
						window.location.href="/Minishope/views/test.jsp";
					},
					error:function(err){
						console.log(err)
					},
					statusCode: {
					    400: function() {
					    	window.location.href="/Minishope/login?message=failure";
					    },
					}
				})
				
			}) 
			
			$("#clickForget").click(function(){
				//$("#login").hide();
				$("#pwdModal").show();
				 $('#inputEmail').show();
			})
			$("#back-sendToken").click(function(){
				$("#pwdModal").show();
				$("#inputPassword").hide();
				$("#inputEmail").show();
			})
			
			 $("#back-changePassword").click(function(){
				$("#pwdModal").show();
				$("#inputPassword").show();
				$("#inputEmail").hide();
			}) 
			

		// signup
			var token=""
			getUrlVars();// Run code
			
			function getUrlVars() {
			
			    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
			        console.log(key)
			       if(key==="token" & key!==null){
			    	   token= value
			    	   $('#pwdModal').modal('show');
			    	   $("#inputPassword").show();
			    	   $("#back-changePassword").show();
			       }else{
			    	   
			       }
			    });
		}
				$("#timer").hide();	
			var path = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port;
			$("#btn-mailSendToken").click(function(e){
				var userNameSendToken=$("#userNameSendToken").val();	
				if( userNameSendToken===""){
					$("#message-mail").html("Không được để trống thông tin")
				}else{
					$.ajax({
						url:"/Minishope/Api/sendTokenPassword",
						type:"Post",
						data:{
							userName:userNameSendToken,
							url:path
						},
						success:function(value){
							if(value==="ok"){
							/* 	$("#inputPassword").show();
								$("#inputEmail").hide();
								$("#btn-mailSendToken").hide()
								$("#message-mail").html("")
								$("#message-mail").removeClass("red") */
								$("#message-mail").html("Vui long check your email")
								$("#message-mail").addClass("red")
								decrement()
							}else{
								$("#message-mail").html(value)
								$("#message-mail").addClass("red")
							}
						}
					})
			}
		})	
<<<<<<< HEAD
				
			$("#btn-changePassword").click(function(){
				var newPW=$("#forgot-newPassword").val();
				var preNewPS=$("#forgot-prePassword").val();
				let tokenT=token;
				console.log(newPW +" "+preNewPS)
				if(newPW==="" | preNewPS==="" | token===""){
					$("#message-password").html("Không được bỏ trống")
					return null;
				}else{
					if(newPW===preNewPS){
						$.ajax({
							url:"/Minishope/Api/changePW",
							type:"post",
							data:{
								password:newPW,
								token:tokenT
							},
							success:function(value){
								
								var ob= JSON.parse(value)
								console.log(ob)
								$.each(ob,function(index,value){
									if(index==="token"){
										$("#message-password").html(value)
									}else if(index==="matKhau"){
										$("#message-password").html(value)
									}else if(index==="DangKy"){
										window.location.href="/Minishope/login?message=changePW_OK"
									}else{
										$("#message-password").html(value)
									}
								})
							}
						})
					}else{
						$("#message-password").html("Confirm không trùng với Password");
					}
				}
			
			})
=======
		
>>>>>>> 79b8dc2151c51c144d4c264b66eb0d8e569456ab
		})//end
	</script>
</body>
</html>