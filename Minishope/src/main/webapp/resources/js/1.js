$(window).ready(function() {
	$("#redirect").click(function() {
		$("#idsignin").show();
		$("#idsignup").hide();
	});
	$("#redirectsignup").click(function() {
		$("#idsignin").hide();
		$("#idsignup").show();
	});
	var pre_password;
	var password;
	var userSubmit = false;
	var emailSubmit=false;
	var passwordSubmit=false;
	var prePasswordSubmit=false;
	var input;
	$("#redirect").click(function(){
		window.location.href="/Minishope/login";
	})
	$("#userNameSignUp").change(function() {
		var userName = $("#userNameSignUp").val();//lấy giá trị input 
		if(userName!==""){
			input = {//object
						tenDangNhap :userName,
				}
			$.ajax({
				url : "/Minishope/Api/CheckSignUp/",
				type : "post",
				contentType:"Application/json",
				data : JSON.stringify(input) ,
				success : function(value) {
					console.log(value)
					console.log(value["tenDangNhap"])
					$("#labelUserNameSignUp").text(value["tenDangNhap"]);
					if (value["tenDangNhap"] !== "") {
						userSubmit = false;
					} else {
						userSubmit = true;
						
					}
				},
				error : function(value) {
					alert(value + "orrrrrr")
				}
			})
		}

	});
	
	$("#email").blur(function() {
		var email = $("#email").val();
		if(email!==""){
			input = {
					email:email,
			}
			
			$.ajax({
				url : "/Minishope/Api/CheckSignUp/",
				type : "post",
				contentType:"Application/json",
				data : JSON.stringify(input) ,
				success : function(value) {
					$("#labelEmail").text(value["email"]);
					if (value["email"] !== "") {
						emailSubmit = false;
					} else {
						emailSubmit = true;
					}
				},
				error : function(value) {
					alert(value + "orrrrrr")
				}
			});
		}
	});
	
	$("#passwordSignUp").change(function() {
		var password = $("#passwordSignUp").val();
		if(password!==""){
			input = {
					matKhau:password
			}
			$.ajax({
				url : "/Minishope/Api/CheckSignUp/",
				type : "post",
				contentType:"Application/json",
				data : JSON.stringify(input) ,
				success : function(value) {
					$("#lablePassword").text(value["matKhau"]);
					if (value["matKhau"]!== "") {
						passwordSubmit = false;
					} else {
						passwordSubmit = true;
					}
					 password = $("#passwordSignUp").val();
					 pre_password = $("#pre-password").val();
					 if(pre_password!==""){
						if(password!==pre_password){
							$("#labelPre-password").text("không trung mat khau");
							prePasswordSubmit=false
						}else{
							$("#labelPre-password").text("");
							prePasswordSubmit=true
						}
					 }
				},
				error : function(value) {
					alert(value + "orrrrrr")
				}
			});
		}
	});
	
	$("#pre-password").blur(function(){
		 password = $("#passwordSignUp").val();
		 pre_password = $("#pre-password").val();
		if(password!==pre_password){
			$("#labelPre-password").text("không trung mat khau");
			prePasswordSubmit=false
		}else{
			$("#labelPre-password").text("");
			prePasswordSubmit=true
		}
	});
	
	$("#idsignup").submit(function(event) {
		if (userSubmit === false | emailSubmit===false | passwordSubmit === false | prePasswordSubmit===false) {
			var dataUserName = $("#userNameSignUp").val();
			var dataEmail = $("#email").val();
			var dataPW = $("#passwordSignUp").val();
			var dataP =	$("#pre-password").val();
			if(dataUserName==="" | dataEmail===""| dataPW==="" | dataP===""){
				$(".messenger").css("display","block")
			}
			$("#labelUserNameSignUp").css("color","red");
			$("#labelEmail").css("color","red");
			$("#lablePassword").css("color","red");
			$("#labelPre-password").css("color","red");
			event.preventDefault()//chặn sự kiện submit 
		}
	});
});
