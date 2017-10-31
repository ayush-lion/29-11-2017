<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Smart Home Automation</title>
<link href='//fonts.googleapis.com/css?family=Lato:400,700'
	rel='stylesheet' type='text/css'>
<link href='https://api.cloudmqtt.com/sso/css/bootstrap.min.css'
	rel='stylesheet' type='text/css'>
<script src='https://api.cloudmqtt.com/sso/js/jquery.min.js'
	type='text/javascript'></script>
<script src='https://api.cloudmqtt.com/sso/js/bootstrap.min.js'
	type='text/javascript'></script>
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<link
	href="http://www.4liongroup.com/sockets/sai_admin/assets/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://www.4liongroup.com/sockets/sai_admin/assets/js/jquery-1.10.2.js"></script>
<script
	src="http://www.4liongroup.com/sockets/sai_admin/assets/js/jquery3.1.js"></script>
<script
	src="http://www.4liongroup.com/sockets/sai_admin/assets/js/bootstrap.js"></script>
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<script>

	function toggleinput() {
		var b = $("#fan").val();
		var topicname = "fan";
		$.ajax({
			url :"PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
				
			},
			error : function() {
			}
		});
	}

	function toggleinput1() {
		var b = $("#light").val();
		var topicname = "light";
		
		$.ajax({
			url :"PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
				
			},
			error : function() {
			}
		});
	}

	function toggleinput2() {
		var b = $("#washing_machine").val();
		var topicname = "washing_machine";
		
		$.ajax({
			url : "PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
				
			},
			error : function() {
			}
		});
	}

	function toggleinput3() {
		var b = $("#air_conditioner").val();
		var topicname = "air_conditioner";
	
		$.ajax({
			url :"PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
			
			},
			error : function() {
			}
		});
	}

	function toggleinput4() {
		var b = $("#bulb").val();
		var topicname = "bulb";

		$.ajax({
			url :"PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
				
			},
			error : function() {
			}
		});
	}

	function toggleinput5() {
		var b = $("#cfl").val();
		var topicname = "cfl";
		
		$.ajax({
			url :"PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
				
			},
			error : function() {
			}
		});
	}

	function toggleinput6() {
		var b = $("#tubelight").val();
		var topicname = "tubelight";
		
		$.ajax({
			url :"PublishInformationUrl",
			type : "post",
			data : {
				topicname : topicname,
				b : b
			},
			success : function(data) {
				
			},
			error : function() {
			}
		});
	}
	
</script>
</head>
<body style="background-color: #2F4F4F;">

	<div>
		<h1 align='center' style='margin-top: 20px'>Home Automation</h1>
		<table class="table table-bordered "
			style='margin-top: 50px; width: 300px; font-size: 20px'
			align='center'>
			<thead>
				<tr style='font-size: 25px'>
					<th>DEVICE</th>
					<th>SWITCH</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="device" items="${device}">
					<c:choose>
						<c:when test="${device.devicename == 'fan' && device.state=='1'}">
							<tr>
								<td>FAN</td>
								<td><input class="inputs" id='fan' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput()"></input></td>
							</tr>
						</c:when>

						<c:when test="${device.devicename == 'fan' && device.state=='0'}">
							<tr>
								<td>FAN</td>
								<td><input class="inputs" value='1' id='fan'
									type='checkbox' data-toggle='toggle' onchange="toggleinput()"></input>
								</td>
							</tr>
						</c:when>
						<c:when
							test="${device.devicename == 'light' && device.state=='0'}">
							<tr>
								<td>Light</td>
								<td><input class="inputs" id='light' value="1"
									type='checkbox' data-toggle='toggle' onchange="toggleinput1()"></input></td>

							</tr>
						</c:when>
						<c:when
							test="${device.devicename == 'light' && device.state=='1'}">
							<tr>
								<td>Light</td>
								<td><input class="inputs" id='light' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput1()"></input></td>

							</tr>
						</c:when>
						<c:when
							test="${device.devicename == 'washing_machine' && device.state=='0'}">
							<tr>
								<td>Washing Machine</td>
								<td><input class="inputs" id='washing_machine' value="1"
									type='checkbox' data-toggle='toggle' onchange="toggleinput2()"></input></td>

							</tr>
						</c:when>
						<c:when
							test="${device.devicename == 'washing_machine' && device.state=='1'}">
							<tr>
								<td>Washing Machine</td>
								<td><input class="inputs" id='washing_machine' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput2()"></input></td>

							</tr>
						</c:when>

						<c:when
							test="${device.devicename == 'air_conditioner' && device.state=='0'}">
							<tr>
								<td>Air Conditioner</td>
								<td><input class="inputs" id='air_conditioner' value="1"
									type='checkbox' data-toggle='toggle' onchange="toggleinput3()"></input></td>

							</tr>
						</c:when>
						<c:when
							test="${device.devicename == 'air_conditioner' && device.state=='1'}">
							<tr>
								<td>Air Conditioner</td>
								<td><input class="inputs" id='air_conditioner' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput3()"></input></td>

							</tr>
						</c:when>


						<c:when test="${device.devicename == 'bulb' && device.state=='0'}">
							<tr>
								<td>Bulb</td>
								<td><input class="inputs" id='bulb' value="1"
									type='checkbox' data-toggle='toggle' onchange="toggleinput4()"></input></td>

							</tr>
						</c:when>
						<c:when test="${device.devicename == 'bulb' && device.state=='1'}">
							<tr>
								<td>Bulb</td>
								<td><input class="inputs" id='bulb' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput4()"></input></td>

							</tr>
						</c:when>

						<c:when test="${device.devicename == 'cfl' && device.state=='0'}">

							<tr>
								<td>Cfl</td>
								<td><input class="inputs" id='cfl' value="1"
									type='checkbox' data-toggle='toggle' onchange="toggleinput5()"></input></td>

							</tr>
						</c:when>

						<c:when test="${device.devicename == 'cfl' && device.state=='1'}">

							<tr>
								<td>Cfl</td>
								<td><input class="inputs" id='cfl' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput5()"></input></td>

							</tr>
						</c:when>
						<c:when
							test="${device.devicename == 'tubelight' && device.state=='0'}">
							<tr>
								<td>Tubelight</td>
								<td><input class="inputs" id='tubelight' value="1"
									type='checkbox' data-toggle='toggle' onchange="toggleinput6()"></input></td>

							</tr>
						</c:when>

						<c:when
							test="${device.devicename == 'tubelight' && device.state=='1'}">
							<tr>
								<td>Tubelight</td>
								<td><input class="inputs" id='tubelight' value="0"
									type='checkbox' data-toggle='toggle' checked="true"
									onchange="toggleinput6()"></input></td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>