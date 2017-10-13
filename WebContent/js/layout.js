
//SEARCH VARIABLES//

var stdDisplayItem=1;
var batchDispItem=1;

function loadPage(url,tabName){
	$("#body").hide();
	$.ajax({
		type:"POST",
		url:ctxPath+"/Master/Ajax/"+url,
		success:function(res){
			alert(res);
			if(tabName==""||tabName==undefined){
				$("#tabs .active tab-name").html("Activity");
			}else{
				$("#tabs .active tab-name").html(tabName);
			}
			$("#body").html(res);
			$("#body").fadeIn("fast");
		},error:function(){
			alert(argments[2]);
		}
	});
	
}

function loadToTab(res,tabName){
	$("#body").hide();
	if(tabName==""||tabName==undefined){
		$("#tabs .active tab-name").html("Activity");
	}else{
		$("#tabs .active tab-name").html(tabName);
	}
	$("#body").html(res);
	$("#body").fadeIn("fast");
}


function alrt(message,title){
	if(title==undefined || title==""){
		$("#avAlert #avAlertTitle").html("Alert Box");
	}else{
		$("#avAlert #avAlertTitle").html(title);
	}
	$("#avAlert .modal-body").html(message);
	$("#avAlert").modal("show");
}


var tabId=2;
function addNewTab(eleObj){
	$("#tabs .tab-trigger").removeClass("active");
	$("#tabs").append("<span class='tab-trigger active' href='#"+tabId+"'><tab-name>New Tab</tab-name><i class='close' >&times;</i></span>");
	$("#tab-panels .tab-panel").hide();
	$("#tab-panels .tab-panel").children().attr("id","");
	$("#tab-panels").append("<div class='av-row tab-panel' id='"+tabId+"'><div class='av-row' id='body'>New Tab "+tabId+"</div></div>");
	tabId++;
	var i=0;
	$("#tabs .tab-trigger").each(function(){
		i++;
	});
	if(i>5){
		$("#btnAddNewTab").hide();
	}
}



function loadStudent(studentRegNo){
	checkForSession(function(d){
		if(d){
			$.ajax({
				type:"POST",
				url:ctxPath+"/Master/Ajax/getStuDetails",
				data:{
					studentRegNo:studentRegNo,
					stdDisplayItem:stdDisplayItem
					},
				success:function(res){
					switch(stdDisplayItem){
					case 1:
						loadToTab(res,"Student Profile");
						break;
					case 2:
						loadToTab(res,"Student Fee");
						break;
					case 3:
						loadToTab(res,"Student Batch");
						break;
					default:
						loadToTab(res,"Activity Tab");
						break;
					}
					
				},error:function(){
					alert(arguments[2]);
				}
			});
		}
	});
	
}


$(function(){
	
	$('body').on('click', '#tabs .tab-trigger', function(event) {
		event.stopPropagation();
		var tabPanalId =  $(this).attr("href");
	   $("#tab-panels .tab-panel").hide();
	   $("#tab-panels .tab-panel").children().attr("id","");
	   $("#tab-panels "+tabPanalId).fadeIn("fast");
	   $("#tab-panels "+tabPanalId).children().attr("id","body");
	   $("#tabs .tab-trigger").removeClass("active");
	   $(this).addClass("active");
	 
	   
	});
	
	$('body').on('click', '#tabs .tab-trigger .close', function(event) {
			event.stopPropagation();
			var panelId = $(this).parent().attr("href");
			if($(this).parent().hasClass("active")){
				$("#tab-panels #1").fadeIn("fast");
				$("#tab-panels #1").children().attr("id","body");
				$("#tabs .tab-trigger").removeClass("active");
				$("#tabs #default-tab").addClass("active");
				$("#tab-panels "+panelId).remove();
				$(this).parent().remove();
			}else{
				$("#tab-panels "+panelId).remove();
				$(this).parent().remove();
			}
			$("#btnAddNewTab").show();
		
	});
	
	$('body').on('click', '#student-left-toolbar .fa', function(event) {
		$("#student-left-toolbar .fa").removeClass("active");
		$(this).addClass("active");
		stdDisplayItem=parseInt($(this).attr("stdDisplayItem"));
		var cStdId=$("#body #studentRegNo").val();
		if(cStdId!=undefined){
			loadStudent(cStdId);
		}
	});
	
	
	$('body').on('click', '#enq-left-toolbar .fa', function(event) {
		$("#enq-left-toolbar .fa").removeClass("active");
		$(this).addClass("active");
	});
	
	
	$('body').on('click', '#batch-left-toolbar .fa', function(event) {
		$("#batch-left-toolbar .fa").removeClass("active");
		$(this).addClass("active");
		batchDispItem=parseInt($(this).attr("batchDispItem"));
		if($("#body #batchId").val()!=undefined){
			loadBatch($("#body #batchId").val());
		}
	});
	
	
	
	$('body').on('click', '#search-tab label', function(event) {
		if(!$(this).hasClass("active")){
			$("#search-tab label").removeClass("active");
			$(this).addClass("active");
			var panelId = $(this).attr("href");
			$(".search-tab-panel").hide();
			$(panelId).fadeIn("fast");
			var toolbarId = $(this).attr("toolbar");
			$("#leftToolar .left-toolbar").hide();
			$("#leftToolar "+toolbarId).show();
		}
	});
	
	/*$('body').on('keydown', 'input, select, textarea', function(e) {
	    var self = $(this)
	      , form = self.parents('form:eq(0)')
	      , focusable
	      , next
	      ;
	    if (e.keyCode == 13) {
	        focusable = form.find('input,a,select,button,textarea').filter(':visible');
	        next = focusable.eq(focusable.index(this)+1);
	        if (next.length) {
	            next.focus();
	        } else {
	            //form.submit();
	        }
	        return false;
	    }
	});*/
	
	$('body').on('keydown', 'input, select', function(e) {
		var self = $(this)
		  , form = self.parents('form:eq(0)')
		  , focusable
		  , next
		  , prev
		  ;

		if (e.shiftKey) {
		 if (e.keyCode == 13) {
		     focusable =   form.find('input,a,select,button,textarea').filter(':visible');
		     prev = focusable.eq(focusable.index(this)-1); 

		     if (prev.length) {
		        prev.focus();
		     } else {
		        form.submit();
		    }
		  }
		}
		  else
		if (e.keyCode == 13) {
		    focusable = form.find('input,a,select,button,textarea').filter(':visible');
		    next = focusable.eq(focusable.index(this)+1);
		    if (next.length) {
		        next.focus();
		    } else {
		        //form.submit();
		    }
		    return false;
		}
		});
	
	
});

/*setTimeout(function(){
	checkForSession();
},10000);
*/
function checkForSession(callback){
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/CheckForSession",
		success:function(res){
			//alert(res);
			if(res!=="ok"){
				$('#avLogin').modal({
					 backdrop: 'static',
					 keyboard: false
				});
				callback(false);
			}else{
				callback(true);
			}
		},error:function(){
			alert(arguments[2]);
		}
	});
}



function loadCourseOpt(eleObj, targetEle){
	var id= $(eleObj).val();
	if(id==0||id=='0'){
		$(targetEle).html("<option value='0'>Select Course</option>");
		return false;
	}
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/LoadCourseOpt",
		data:{domainId:id},
		success:function(res){
			$(targetEle).html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}


function loadTechOpt(eleObj, targetEle){
	var id= $(eleObj).val();
	if(id==0||id=='0'){
		$(targetEle).html("<option value='0'>Select Technology</option>");
		return false;
	}
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/LoadTechnologyOpt",
		data:{courseId:id},
		success:function(res){
			$(targetEle).html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}


function loadTopicOpt(eleObj, targetEle){
	var id= $(eleObj).val();
	if(id==0||id=='0'){
		$(targetEle).html("<option value='0'>Select Topic</option>");
		return false;
	}
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/LoadTopicOpt",
		data:{technologyId:id},
		success:function(res){
			$(targetEle).html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}



function loadSubTopicOpt(eleObj, targetEle){
	var id= $(eleObj).val();
	if(id==0||id=='0'){
		$(targetEle).html("<option value='0'>Select SubTopic</option>");
		return false;
	}
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/LoadSubTopicOpt",
		data:{topicId:id},
		success:function(res){
			$(targetEle).html(res);
		},error:function(){
			alrt(arguments[2]);
		}
	});
}


function validate(){
	if(!RequiredFieldTextBox("employeeId","employeeIdError","Please Enter EmployeeId")){
		return false;
	}else if(!RequiredFieldTextBox("password","passwordError","Please Enter Your Password")){
		return false;
	}
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/Login",
		data:$("#loginForm").serialize(),
		success:function(res){
			if(res=="ok"){
				//window.open($("#ctxPath").val()+"/Master","_self");
				//location.reload();
				$('#avLogin').modal("hide");
			}else{
				$("#employeeIdError").html(res);
			}
		},error:function(){
			alert(arguments[2]);
		}
	})
	
	return false;
}


var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
var b = ['', '', 'Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety'];

function inWords (num) {
    if ((num = num.toString()).length > 9) return 'overflow';
    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
    if (!n) return; var str = '';
    str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'Crore ' : '';
    str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'Lakh ' : '';
    str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'Thousand ' : '';
    str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'Hundred ' : '';
    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only /- ' : '';
    var sss = str.lastIndexOf("only");
	   if(sss==-1){
		   str += " only /-";	
	   }
    return str;
}


function printdiv(printpage,title)
{
var headstr = "<html><head><title>"+title+"</title></head><body>";
var footstr = "</body>";
var newstr = $(printpage).html();
var oldstr = document.body.innerHTML;
document.body.innerHTML = headstr+newstr+footstr;
window.print();
document.body.innerHTML = oldstr;
return false;
}
