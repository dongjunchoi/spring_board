<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${cp}/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="${cp}/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${cp}/resources/bootstrap/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<style>
body.login-page {
	background-image: url('/resources/images/intro.jpg');
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>

<script>
	
	
		$(function() {
			//userid, rememberme 쿠키를 확인하여 존재할 경우 값설정, 체크
			if(Cookies.get('rememberme')=="Y"){
				$("#userid").val(Cookies.get('userid'))
				$("#rememberme").attr("checked", true);
			}
			
			
			//singin 아이디를 select
			$("#signin").on("click",function(){
				// rememberme 체크박스가 체크되어 있는지 확인
				// 체크되어있을 경우
				if($("#rememberme").is(":checked")){
					// userid input에 있는 값을 userid쿠키로 저장
					Cookies.set("userid",$("#userid").val());
					// rememberme 쿠키로 Y값을 저장			
					Cookies.set("rememberme","Y");
					
				}else{
					// 체크 해제되어 있는 경우 : 더이상 사용하지 않는다는 의미 이므로 userid, rememberme 쿠키 삭제
					Cookies.remove("userid");
					Cookies.remove("rememberme");
				}
				
				// form태그를 이용해서 signin 요청
				$("#frm").submit();
			});
			
		});
		
		
		
	</script>


</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>관리자 로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form action="${cp}/user/login" id="frm" method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" id="userid" name="userid"
							placeholder="아이디를 입력하세요." value=""> <span
							class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="pass"
							placeholder="패스워드를 입력하세요." value=""> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label> <input type="checkbox" id="rememberme" name="rememberMe"
									value=""> Remember Me
								</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4">
							<button type="button" id="signin" class="btn btn-primary btn-block btn-flat">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="${cp}/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="${cp}/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${cp}/resources/bootstrap/dist/js/adminlte.min.js"></script>


</body>
</html>








