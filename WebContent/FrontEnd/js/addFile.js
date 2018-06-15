
var last_view_id = null;
var content_id = "content";


function addfile(htmlFileName, id, detail_id){
	$("#save-detail-id").attr("data-detailId", detail_id).text(detail_id);
	
	//alert(idName);
	$("#" + id).addClass("current");
	$("#" + last_view_id).removeClass("current");
	last_view_id = id;
	$.ajax({
		url:htmlFileName,
		// async:false,
		type:"GET",
		contentType: "charset=utf-8", 
		success:function(result){ 
				$("#" + content_id ).empty().append(result); 
			}	
		});
}