
/*********************STUDENT REG SCRIPT*********************************/
$(function(){
	$("body").on("click","#body .offerCheck",function(){
		var currentState = $(this).prop("checked");
		$("#body .offerCheck").prop("checked",false);
		if(currentState){
			$(this).prop("checked",true);
		}else{
			$(this).prop("checked",false);
		}
		
		if($(this).attr("name")=="SC"){
			if($(this).prop("checked")){
				$("#body #stdScDetails").fadeIn();
				var discount = $("#body #stdScDiscount").val();
				addSCOFFDiscount("SC",discount);
			}
			else{
				addSCOFFDiscount("NONE",0);
				$("#body #stdScDetails").hide();
			}
		}else{
			if($(this).prop("checked")){
				$("#body #stdScDetails").fadeIn();
				addSCOFFDiscount("OFF",12);
			}
			else{
				addSCOFFDiscount("NONE",0);
				$("#body #stdScDetails").hide();
			}
		}
		
	});
});


function addSCOFFDiscount(discType,disc){
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/AddSCOFFDiscount",
		data:{
			discType:discType,
			discount:disc,
			currentTrId:$("#body #currentTrId").val()
		},success:function(res){
			$("#body #feeCalculator-box").html(res);
		},error:function(){
			alert(arguments[2]);
		}
	})
}


function changeStuRegTab(targetTab,currentTab){
	$("#body #reg-tabs "+currentTab).fadeOut("fast",function(){
		$("#body #bradcrumb>span").removeClass("active");
		$("#body #bradcrumb "+targetTab).addClass("active");
		$("#body #reg-tabs "+targetTab).fadeIn("fast");
	});
	
}
	
function addNewCourse(){
	var i=0;
	$("#body #edu-courses form.eduForm").each(function(){
		i++;
	});
	if(i<8){
		$("#body #edu-courses").append($("#body #temp-edu").html());
	}else{
		alrt("You can't add more than 8 courses");
	}
}

function removeCourse(){
	var i=0;
	$("#body #edu-courses form.eduForm").each(function(){
		i++;
	});
	if(i<5){
		alrt("You can't remove default courses");
	}else{
		$("#body #edu-courses form.eduForm").last().remove();
	}
}


function addNewUplForm(){
	$("#body #upld-forms").append($("#body #temp-doc").html());
}

function makeAddressDisabled(eleObj){
	if($(eleObj).prop("checked")){
		$("#body .cadd").prop("readonly",true);
		$("#body #cAddress").val($("#body #pAddress").val());
		$("#body #cCity").val($("#body #pCity").val());
		$("#body #cState").val($("#body #pState").val());
		$("#body #cCountry").val($("#body #pCountry").val());
		$("#body #cPin").val($("#body #pPin").val());
	}else{
		$("#body .cadd").prop("readonly",false);
	}
}

function changeWorkExp(eleObj){
	var v = $(eleObj).val();
	if(v=='1'||v==1){
		$("#body .wsfrm").slideDown("fast");
		$("#body #totalExp").focus();
	}else{
		$("#body .wsfrm").hide(); 
		$("#body .wsfrm input").val("");
	}
}

function validatePersonalInfoForm(){
	
	if(!RequiredFieldTextBox("fname","errorFname","Enter First Name")){
		return false;
	}/*else if(!RequiredFieldTextBox("lname","errorLname","Enter Last Name")){
		return false;
	}else if(!RequiredFieldTextBox("dob","errorDOB","Enter your Date of Birth")){
		return false;
	}*/else if(!RequiredFieldTextBox("mobile","errorMobile","Enter your mobile Number")){
		return false;
	}else if(!LengthTextBox("mobile","errorMobile","Enter 10 digit mobile Number",10,"EQUAL")){
		return false;
	}else if(!RequiredFieldTextBox("emailId","errorEmail","Enter Email Id")){
		return false;
	}else if(!EmailValidation("emailId","errorEmail","Invalid Email Id")){
		return false;
	}
	
	if($("#workStatus").val()==1||$("#workStatus").val()=='1'){
		
		if(!RequiredFieldTextBox("totalExp","errorTotalExp","Enter Your Total Exp")){
			return false;
		}else if(!RequiredFieldTextBox("companyName","errorCompanyName","Enter Your Company Name")){
			return false;
		}else if(!RequiredFieldTextBox("designation","errorDesignation","Enter your Designation")){
			return false;
		}else if(!RequiredFieldTextBox("skills","errorSkills","Enter Your Skills")){
			return false;
		}
	}
	
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/getStuScholarshipDetails",
		data:{mobileNumber:$("#body #mobile").val()},
		success:function(res){
			if($.trim(res)!=='Failed'){
				$("#body #stdCourseSCTitle").css("font-weight","bolder");
				$("#body #stdCourseSCTitle").css("color","#a6692b");
				$("#body #stdScDetails").html(res);
				
			}else{
				$("#body #stdCourseSCTitle").css("font-weight","normal");
				$("#body #stdCourseSCTitle").css("color","black");
				$("#body #stdScDetails").html(" ");
				
			}
		},error:function(){
			alrt(arguments[2]);
		}
		
	});
	
	if($("#body #addressCheck").prop("checked")){
		$("#body .cadd").prop("readonly",true);
		$("#body #cAddress").val($("#body #pAddress").val());
		$("#body #cCity").val($("#body #pCity").val());
		$("#body #cState").val($("#body #pState").val());
		$("#body #cCountry").val($("#body #pCountry").val());
		$("#body #cPin").val($("#body #pPin").val());
	}
	return true;
}


function validateEduForm(){
	
	$("#body #eduErrorMessage").hide();
	
	if(!eduRequiredFieldValidate("#body #height-school-info #unvBoard","Enter HeighSchool Board")){
		return false;
	}else if(!eduRequiredFieldValidate("#body #height-school-info #percentage","Enter HeighSchool Percentage")){
		return false;
	}else if(!eduRequiredFieldValidate("#body #intermediate-school-info #unvBoard","Enter Intermediate Board")){
		return false;
	}else if(!eduRequiredFieldValidate("#body #intermediate-school-info #percentage","Enter Intermediate Percentage")){
		return false;
	}
	
	if($("#body #graduation-info #courseType").val()!=='0'){
		if(!eduRequiredFieldValidate("#body #graduation-info #unvBoard","Enter Board or University Name")){
			return false;
		}else if(!eduRequiredFieldValidate("#body #graduation-info #percentage","Enter Your Percentage")){
			return false;
		}
	}
	
	if($("#body #post-graduation-info #courseType").val()!=='0'){
		if(!eduRequiredFieldValidate("#body #post-graduation-info #unvBoard","Enter Board or University Name")){
			return false;
		}else if(!eduRequiredFieldValidate("#body #post-graduation-info #percentage","Enter Your Percentage")){
			return false;
		}
	}

	
	return true;
}

function eduRequiredFieldValidate(eleId, message){
	$(eleId).css("border","1px solid #ccc");	
	if($.trim($(eleId).val())==""){
		$(eleId).css("border","1px solid red");
		$(eleId).focus();
		eduError(message);
		return false;
	}
	return true;
}


function eduError(message){
	$("#body #eduErrorMessage").html(message);
	$("#body #eduErrorMessage").fadeIn("fast");
	/*setTimeout(function(){
		$("#body #eduErrorMessage").fadeOut();
	},4000)*/
}


function loadTechPackData(st){
	var trType=$("#body .trType:checked").val();
	var courseId = $("#body #std-course-opt").val();
	if(courseId==0||courseId=='0'){return false}
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/getTechPackData",
		data:{
			trType:trType,
			courseId:courseId,
			currentTrId:$("#body #currentTrId").val(),
			
		},
		success:function(res){
			if(st){
				$("#body #tech-pack-list-view").html(res);
			}else{
				$("#body #tech-pack-list-view").hide();
				$("#body #tech-pack-list-view").html(res);
				$("#body #tech-pack-list-view").fadeIn("slow");
			}
		},error:function(){
			alrt(arguments[2]);
		}
	});
	
}


function calculateFee(id,trType,actionType){
	
	if(actionType==undefined){
		actionType="ADD";
	}
	
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/LoadFeeCalculator",
		data:{
			id:id,
			trType:trType,
			currentTrId:$("#body #currentTrId").val(),
			actionType:actionType
		},
		success:function(res){
			$("#body #feeCalculator-box").html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}


function addOrRemoveTech(id,trType,eleObj){
	if($(eleObj).prop("checked")){
		calculateFee(id,trType,"ADD");
	}else{
		calculateFee(id,trType,"REMOVE");
	}
}

function loadTechPackInfo(id,trType,eleObj){
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/loadTechPackInfo",
		data:{
			id:id,
			trType:trType
		},
		success:function(res){
			$("#body .tech-view").css("font-weight","normal");
			$(eleObj).css("font-weight","bold");
			$("#body #tech-pack-info-box").html(res);
		}
	});
}

function changeInstallment(eleObj){
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/changeNoOfInstallment",
		data:{
			NOI:$(eleObj).val(),
			currentTrId:$("#body #currentTrId").val()
		},
		success:function(res){
			$("#body #feeCalculator-box").html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}


function submitStudentRegistration(){
	personalInfoJSON = [];
	personalInfoJSON.push(jsonFormDataToJsonString($("#body #student-presonal-info-form").serializeArray()));
	var personalInfo=JSON.stringify(jsonFormDataToJsonString($("#body #student-presonal-info-form").serializeArray()));

	eduJSON = [];
	$("#body #edu-courses .eduForm").each(function(){
		var jobj = $(this).serializeArray();
		eduJSON.push(jsonFormDataToJsonString(jobj));
	})
	
	var eduInfo = JSON.stringify(eduJSON);
	
	docJSON = [];
	$("#body #upld-forms .doc-form").each(function(){
		var jobj = $(this).serializeArray();
		docJSON.push(jsonFormDataToJsonString(jobj));
	});
	
	var documents = JSON.stringify(docJSON); 
	
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/SubmitStudentReg",
		data:{
			personalInfo : personalInfo,
			eduInfo : eduInfo,
			documents : documents,
			currentTrId:$("#body #currentTrId").val()
		},success:function(res){
			$("#stdRegSuccessModal #stdRegIdForModal").html(res);
			$("#stdRegSuccessModal").modal("show");
		},error:function(){
			alrt(arguments[2]);
		}
	});
	
}

function jsonFormDataToJsonString(jObj){
	item = {};
	$.each(jObj,function(){
		 item[this.name]=this.value;
	});
	return item;
}

function temp(){
	
	$("#stdRegSuccessModal #stdRegIdForModal").html("1101021");
	$('#stdRegSuccessModal').modal({
		 backdrop: 'static',
		 keyboard: false
	});
}

var mgtLoopSt=false;
function beginMgtDisc(action){
	mgtLoopSt=true;
	changeMgtDisc(action);
}

function endMgtDisc(){
	mgtLoopSt=false;
	addMgtDisc($("#body #mgt-disc-text").html());
}

function changeMgtDisc(action){
	setTimeout(function(){
		if(mgtLoopSt){
			var s=$("#body #mgt-disc-text").html();
			s=parseFloat(s);
			if(action=="ADD"){
				if(s<=(20-.1))
				s = s + 0.1;
			}else{
				if(s>=(0+.1))
				s = s - 0.1;
			}
			
			$("#body #mgt-disc-text").html(s.toFixed(1));
			changeMgtDisc(action);
		}
	},60);
}

function addMgtDisc(disc){
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/addMgtDisc",
		data:{
			disc:disc,
			currentTrId:$("#body #currentTrId").val()
		},
		success:function(res){
			$("#body #feeCalculator-box").html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}



function loadStudentPrintPrevPage(stdRegNo){
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/loadStudentProfilePrintPage",
		data:{studentRegNo:stdRegNo},
		success:function(res){
			//loadToTab(res,"stdPrint Preview");
			printdiv(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}









