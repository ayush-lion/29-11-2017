function searchStaffBatch(){
	var domainIds = $("#batch-domain").val();
	var courseIds = $("#batch-course").val();
	$(".batchOption").css({"border":"1px solid #CCCCCC"});
	if(domainIds==0||domainIds=='0'){
		$("#batch-domain").css({"border-color":"red"});
		return false;
	}
	/*if(courseIds==0||courseIds=='0'){
		$("#batch-course").css({"border-color":"red"});
		return false;
	}*/
	$.ajax({
		url:ctxPath+"/Staff/Ajax/searchStaffBatch",
		type:"post",
		data:{courseId:courseIds, domainId:domainIds},
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