<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href='//fonts.googleapis.com/css?family=Lato:400,700'rel='stylesheet' type='text/css'>
<link href='https://api.cloudmqtt.com/sso/css/bootstrap.min.css'rel='stylesheet' type='text/css'>
<script src='https://api.cloudmqtt.com/sso/js/jquery.min.js'type='text/javascript'></script>
<script src='https://api.cloudmqtt.com/sso/js/bootstrap.min.js'	type='text/javascript'></script>
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"rel="stylesheet">
<link href="http://www.4liongroup.com/sockets/sai_admin/assets/css/bootstrap.css"rel="stylesheet">
<script	src="http://www.4liongroup.com/sockets/sai_admin/assets/js/jquery-1.10.2.js"></script>
<script	src="http://www.4liongroup.com/sockets/sai_admin/assets/js/jquery3.1.js"></script>
<script	src="http://www.4liongroup.com/sockets/sai_admin/assets/js/bootstrap.js"></script>
<script	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<link href="http://www.4liongroup.com/sockets/sai_admin/assets/css/bootstrap.css" rel="stylesheet">
<script src="http://www.4liongroup.com/sockets/sai_admin/assets/js/bootstrap.js"></script>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<script src='https://customer.cloudmqtt.com/js/site.js?219776' type='text/javascript'></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>

<style>
body {
	font-family: "Lato", sans-serif;
}

.sidenav {
	height: 100%;
	width: 0;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	background-color: rgba(19, 35, 47, 0.9);
	overflow-x: hidden;
	transition: 0.5s;
	padding-top: 60px;
}

.sidenav a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 25px;
	color: #3cbc6f;
	display: block;
	transition: 0.3s;
}

.sidenav a:hover {
	color: #3cbc6f;
}

.sidenav .closebtn {
	position: absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
	margin-left: 50px;
}

#main {
	transition: margin-left .5s;
	padding: 16px;
}

@media screen and (max-height: 450px) {
	.sidenav {
		padding-top: 15px;
	}
	.sidenav a {
		font-size: 18px;
	}
	#main {
		transition: margin-left .5s;
		padding: 16px;
	}
}
</style>
</head>
<body>

	<div class="col-xs-12">
		<div class="col-xs-12"
			style="background-color: rgba(19, 35, 47, 0.9);">
			<center>
				<p style="color: white">Home Automation</p>
			</center>
		</div>
	</div>
	<div class="col-xs-12">
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="#">Home</a> <a href="#">Privacy</a> <a href="#">Configuration</a>
			<a href="#">Support</a> <a href="#">Logout</a>
		</div>
	</div>

	<div class="container" id="mySidenav">
		<!-- Trigger the modal with a button -->
		<br> <br>
		<center>
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModal">Add Device</button>
		</center>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<input type="text" id="device">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" onclick="adddevice()" data-dismiss="modal">OK</button>
					</div>
					
					<div>
					<input type = "hidden" value="0" id="default">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="main" class="col-xs-12">
		<div class="col-xs-3">
			<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;</span>
		</div>

		<div class="col-xs-9">
			<span style="font-size: 30px; cursor: pointer" onclick="add()";></span>
		</div>
	</div>
	<center>
		
			<c:forEach items="${devices}" var="device">
			<div >
				${device.device}<input  value="0"
									type='checkbox' data-toggle='toggle' checked="true"><button onclick="remove(${device.id})">remove</button>
				</div>
			</c:forEach>
		
	</center>
	
	<br>
	
	<center>
		<button type="button" class="btn btn-info btn-lg" onclick="submit()">Submit</button>
	</center>

</body>
</html>
<script>


$(document).ready(function() {
    $.ajax({
    	url  :"getAlldevice",
    	type :"post",
    	success : function(data){
    		alert(data);
    	},
    	error : function(){
    	}
    });
});

function remove(id){
	$.ajax({
    	url  :"removedevice",
    	type :"post",
    	data : {id : id},
    	success : function(data){
    	location.reload();
    	},
    	error : function(){
    	}
    });
}

/* Subscription */	

/* After Subscription */
	
	function adddevice() {
		var div = document.createElement('div');
		var v = $("#device").val();
	    div.className = 'row';

	    
	    var device = $("#device").val();
	    var value = $("#default").val(); 
	    $.ajax({
			url :"makeProfile",
			type : "post",
			data : {device : device,value : value},
			success : function(data) {	
				location.reload();
			},
			error : function() {
			}
		});
	 }
	  
	function submit(){
		var v=$("#check").val();
		alert(v);
		
		$.ajax({
			url : "Publish",
			type : "post",
			data : {value : v},
			success : function (data){
			alert(data);
			},
			error : function(){
			alert("error");	
			},
		});
	}
	
	function removeRow(input) {
	    document.getElementById('content').removeChild( input.parentNode );
	    
	    $.ajax({
	    	url  :"removeDevice",
	    	type :"post",
	    	success : function(data){
	    	alert(data);		
	    	},
	    	error : function(){
	    		
	    	}
	    });
	}
	
	function openNav() {
		document.getElementById("mySidenav").style.width = "250px";
		document.getElementById("main").style.marginLeft = "250px";
	}

	function closeNav() {
		document.getElementById("mySidenav").style.width = "0";
		document.getElementById("main").style.marginLeft = "0";
	}
</script>

</body>
</html>
