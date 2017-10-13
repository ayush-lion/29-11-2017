/* .......START :  Methods which is call from SchedulerManager.jsp page.....*/
function submitLecture(batchId, schId, submitBtnId, holdBtnId){
	var deliveryDate = $("#body #"+schId+"deliveryDate").val();
	if(deliveryDate!=null && deliveryDate!=""){
		$.ajax({
			url:ctxPath+"/Ajax/submitLecture", /*in Lms controller*/
			type:"post",
			data:{scheduleId:schId,submitDate:deliveryDate},
			success:function(data){
				$("#body "+submitBtnId).html("Submited");
				$("#body "+submitBtnId).prop("disabled",true);
				$("#body "+holdBtnId).prop("disabled",true);
				alrt("successfully submit","Submit lecture");
			},
			error:function(){
				alert(arguments[2]);
			}
		});
	}
	else{
		alrt("Select a date","Date Error");
	}
}

function holdLecture(batchIds, schId, submitBtnId, holdBtnId){
	if($("#body "+holdBtnId).html()=="Hold"){
		$.ajax({
			url:ctxPath+"/Ajax/holdLecture", /*in Lms controller*/
			type:"post",
			data:{scheduleId:schId,batchId:batchIds},
			success:function(data){
				//alert(data);
				$("#body "+submitBtnId).prop("disabled",true);
				//$(holdBtnId).prop("disabled",true);
				$("#body "+holdBtnId).html("Unhold");
				alrt("successfully hold","Hold lecture");
				$("#body "+holdBtnId).html("Unhold");
			},
			error:function(){
				alert(arguments[2])
			}
		});
	}
	else{
		unHoldLecture(batchIds, schId, submitBtnId, holdBtnId);
	}
}

function unHoldLecture(batchIds, schId, submitBtnId, holdBtnId){
	$.ajax({
		url:ctxPath+"/Ajax/unHoldLecture", /*in Lms controller*/
		type:"post",
		data:{scheduleId:schId,batchId:batchIds},
		success:function(data){
			//alert(data);
			$("#body "+submitBtnId).prop("disabled",false);
			$("#body "+holdBtnId).html("Hold");
  			alrt("successfully unhold","unhold lecture");
		},
		error:function(){
			alert(arguments[2])
		}
	});
}


function holdData(){
	//var schCurrentList = "${schCurrentList}";
	//alert(schCurrentList)
	var holdCheck = $("#body #hold").prop("checked");
	if(holdCheck){
		$("#body #holdLecture").show();
	}
	else{
		$("#body #holdLecture").hide();
	}
}

function unhold(batchIds, schId, submitBtnId, unholdBtnId){
	holdLecture(batchIds, schId, submitBtnId, unholdBtnId);
}

function refreshLecture(batchIds){
	loadBatch(batchIds);
}
/* .......END :  Methods which is call from SchedulerManager.jsp page.....*/