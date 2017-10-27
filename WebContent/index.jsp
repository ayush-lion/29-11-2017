<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<link href='<%=request.getContextPath() %>/js/bootstrap.css'
	rel='stylesheet' type='text/css'>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/FormValidation.js"></script>
</head>

<body id='body' style="background-color: #f4f4f4">
	<input type="hidden" value="<%=request.getContextPath()%>" id='ctxPath'>
	<br><br><br><br><br><br>
	<div class='container'>
		<div class='row'>
			<div class='col-xs-12'></div>
			<div class='col-xs-12'>
			   <div  style='background-color:#fff; height: 170px'>
				<div class="av-row-10" style='background-color:#4aaaa6;height: 36px;padding-top: 9px'>
			      <span style='font-size: 16px;color:white;margin-left: 120px;'>User Login</span>
				</div>
				<div class="av-row-90" style=' padding: 10px;'>
					<form id='loginForm' action="Login" method="post" onsubmit="return validate()">
						<span class='text-danger' id='studentIdError'></span> 
						<input name='StudentName' required="required" maxlength="12" id='studentId' type="text" placeholder='Enter Username' class="form-control input-lg" style="margin-bottom: 10px" /> 
						<span class='text-danger' id='passwordError'></span>
						<input name='StudentIds' required="required" maxlength="100" id='password' type="password" placeholder='Enter Userpassword' class="form-control input-lg" style="margin-bottom: 10px" />
						<button class=' btn-md' style="float: right;">Login</button>	
					</form>
					<form>
					<button class=' btn-md' style="float: left ;" onclick="Validate">Signup</button>	
					</form>
                </div>
			  </div>
			</div>
			<div class='col-xs-12'></div>
		</div>
	</div>
</body>
</html>