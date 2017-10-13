<!DOCTYPE html>
<!-- saved from url=(0042)http://4liongroup.com/sockets/sockets.html -->
<html class="gr__4liongroup_com"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>CloudMQTT Websocket Console</title>
   <link href='//fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
    <link href='https://api.cloudmqtt.com/sso/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
    <script src='https://api.cloudmqtt.com/sso/js/jquery.min.js' type='text/javascript'></script>
    <script src='https://api.cloudmqtt.com/sso/js/bootstrap.min.js' type='text/javascript'></script>
    <style>
      body, input, button, select, textarea {
        font-family: 'Lato', sans-serif;
        font-size: .8em;
      }
      #appharbor li { line-height: inherit; }
      .inline-block { display: inline-block; }
      .left-pading{padding: 0% 0% 0% 15%;}

    </style>
  </head>
  <body data-gr-c-s-loaded="true">
    
            <form action="https://cloudmqtt-mgmt.herokuapp.com/sso" class="form-inline" id="form" method="post" style="display: inline-block">
              <input name="name" type="hidden" value="znvlpcqy">
              <input name="password" type="hidden" value="4EkhqK7ma9Pa">
              <input name="hostname" type="hidden" value="m21.cloudmqtt.com">
              <input name="port" type="hidden" value="16336">
              <input name="token" type="hidden" value="9a395e99c175320ccf22f308308ec7bb496ea189">
              <input name="timestamp" type="hidden" value="1507375427">
                          
            </form>
            <form action="http://4liongroup.com/sockets/restart" class="form-inline" method="post" onsubmit="return confirm(&#39;Are you sure you want to restart your instance?&#39;)" style="display: inline-block">
              <button class="btn" type="submit">
                <i class="icon-refresh"></i>
                Restart
              </button>
            </form>
                 
      <script src="https://api.cloudmqtt.com/sso/js/mqttws31.js" type="text/javascript"></script>
      <h2 align="center">Websocket</h2>
      <div class="alert alert-error" id="error" style="display: none"></div>
      <div class="row">
      <div class="left-pading">
        <div class="span3" align="center">
          <h3>Send message</h3>
          <form id="send">
            <label>Topic</label>
            <input id="topicname" name="topic">
            <label>Message</label>
            <textarea id="messagename" name="message"></textarea>
            <button class="btn btn-block" onclick="send()">
              Send
            </button>
          </form>
        </div>
        <div class="span9">
          <h3>Received messages</h3>
          <table class="table">
            <thead>
             <tr>
               <th>Topic</th>
                <th>Message</th>
              </tr> 
            </thead>
           <tbody id="msgs"></tbody> 
          </table>
        </div>
      </div>
      </div>
      <style>
        pre { font-size: 11px; }
      </style>
      <script>
        var form = document.forms["send"];
        form.addEventListener("submit", function (e) {
          e.preventDefault();
          message = new Paho.MQTT.Message(document.getElementById("message").value);
          message.destinationName = document.getElementById("topic").value;
          client.send(message);
        });
        
        // called when the client connects
        function onConnect() {
          // Once a connection has been made, make a subscription and send a message.
          console.log("onConnect");
          client.subscribe("#");
        }
        
        // called when the client loses its connection
        function onConnectionLost(responseObject) {
          if (responseObject.errorCode !== 0) {
            console.log("onConnectionLost:", responseObject.errorMessage);
            setTimeout(function() { client.connect() }, 5000);
          }
        }
        
        // called when a message arrives
        function onMessageArrived(message) {
          var tdTopic = document.createElement("td");
          tdTopic.textContent = message.destinationName;
        
          var tdMsg = document.createElement("td");
          try {
            tdMsg.textContent = message.payloadString;
          } catch (e) {
        	  
            //tdMsg.textContent = "*** Binary data ***";
            
            var pre = document.createElement("pre");
            var base64 = btoa(String.fromCharCode.apply(null, message.payloadBytes));
            pre.textContent = base64.replace(/(.{72})/g, "$1\n");
            var note = document.createElement("em");
            note.textContent = "Binary data (base64 encoded)"
            tdMsg.appendChild(note);
            tdMsg.appendChild(pre)
          }
        
          var tr = document.createElement("tr");
          
          tr.appendChild(tdTopic);
          tr.appendChild(tdMsg);
        
          document.getElementById("msgs").appendChild(tr);
          
          console.log(tdTopic);
          console.log(tdMsg);
          
       	  var topic =tdTopic.innerHTML;
       	  var message = tdMsg.innerHTML; 
       	
       	   $.ajax({
       			url:"AjaxUrl",
       			type:"post",
       			data:{topic:topic,message:message},
       			success:function(data){
       			}, 
       			error:function(){
       			alert("error");
       			}
       		});
        } 
        
        function onFailure(invocationContext, errorCode, errorMessage) {
          var errDiv = document.getElementById("error");
          errDiv.textContent = "Could not connect to WebSocket server, most likely you're behind a firewall that doesn't allow outgoing connections to port 36336";
          errDiv.style.display = "block";
        }
        
        var clientId = "ws" + Math.random();
        // Create a client instance
        var client = new Paho.MQTT.Client("m21.cloudmqtt.com", 36336, clientId);
        
        // set callback handlers
        client.onConnectionLost = onConnectionLost;
        client.onMessageArrived = onMessageArrived;
        
        // connect the client
        client.connect({
          useSSL: true,
          userName: "znvlpcqy",
          password: "4EkhqK7ma9Pa",
          onSuccess: onConnect,
          onFailure: onFailure
        });
       
        // connect service with our Application and publish data
    	$.ajax({
    		url:"PublishUrl",
    		type:"post",
    		success: function(data){
     	    alert(data);
     	    } 
    	});   
        
      </script>
   </body>
</html>