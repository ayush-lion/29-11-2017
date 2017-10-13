/* .......START :  Methods which is call from BatchSearch.jsp page.....*/
$(function(){
	$("#batch-search-keywords").keyup(function(event){
		if (event.keyCode == 13) {
			SearchBatchs();
		}
	});
});

function SearchBatchs(){
	//alert("java");
	$.ajax({
		url:ctxPath+"/Ajax/batchSearch",
		type:"POST",
		data:{keywords:$("#batch-search-keywords").val()},
		success:function(res){
			$("#batch-result").fadeIn("slow");
			$("#batch-result").html(res);
		},
		error:function(){
			alert("BatchSearch "+arguments[2]);
		}
	});
	
}

function batchSearchForEnter(e){
	if(e.charCode==13){
		SearchBatchs();
	}
}

function courseBatch(){
	var domainIds = $("#batch-domain").val();
	var courseIds = $("#batch-course").val();
	var startDates = $("#batch-startDate").val();
	//$(".batchOption").css({"border":"1px solid #CCCCCC"});
	/*if(domainIds==0||domainIds=='0'){
		$("#batch-domain").css({"border-color":"red"});
		return false;
	}*/
	/*if(courseIds==0||courseIds=='0'){
		$("#batch-course").css({"border-color":"red"});
		return false;
	}*/
	$.ajax({
		url:ctxPath+"/Ajax/courseBatch",
		type:"post",
		data:{courseId:courseIds, domainId:domainIds, startDate:startDates},
		success:function(data){
			//alert(data);
			$("#batch-result").fadeIn("slow");
			$("#batch-result").html(data);
		},
		error:function(){
			document.getElementById("batch-domain").focus();
			//alert("courseBatch "+arguments[2]);
		}
	});
}
/* .......END : Methods which is call from BatchSearch.jsp page.....*/

/* .......START : Methods which is call from BatchSearchResult.jsp page.....*/
$(function(){
	//alert("java");
	$(".search-item").click(function(){
		//alert("jaavv");
		$(".search-item").children("div.batchName").css({"color":"black","font-weight":"normal"});
		$(this).children("div.batchName").css({"color":"red","font-weight":"bold"});
	});
});
function loadBatch(batchIds){
	//alert(batchIds);
	$.ajax({
		type:"POST",
		url:ctxPath+"/Ajax/getBatchDetails",
		data:{
			batchId:batchIds,
			batchDispItem:batchDispItem
			},
		success:function(res){
			//alert(res);
			switch(batchDispItem){
			case 1:
				loadToTab(res,"Batch Assign");
				break;
			case 2:
				loadToTab(res,"Batch Schdule");
				break;
			case 3:
				loadToTab(res,"Scheduler Manager");
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
/* .......END : Methods which is call from BatchSearchResult.jsp page.....*/
