//$.ajax({
//	url:"./json/articleList",
//	complete:function(res){
//		console.log(res)
//		var newList = res.responseJSON;
//		$(".txtlist").html("");
//		newList.forEach(function(item,index){
//			$(".txtlist").append(`
			// <li><span class="tl_title"><a href="javascript:;">${item.title}</a></span><i class="tl_time">昨天</i></li>
			// `)
//		})
//	}
//})
$.ajax({
	url:"./json/articleList",
	complete:function(res){
		console.log(res);
		var newList = res.responseJSON;
		$(".txtlist").html("");
		 newList.forEach(function(item,index){
			 console.log(item);
		 	$(".txtlist").eq(0).append(`
		 	<li><span class="tl_title"><a href="./article/a_${item.a_id}.html">${item.a_title}</a></span><i class="tl_time">${item.a_pubtime}</i></li>
		 	`)
		 })

	}
	

})
$.ajax({
	url:"./json/articleList1",
	complete:function(res){
		console.log(res);
		var newList = res.responseJSON;
		 newList.forEach(function(item,index){
		 	$(".txtlist").eq(1).append(`
		 	<li><span class="tl_title"><a href="./article/a_${item.a_id}.html">${item.a_title}</a></span><i class="tl_time">${item.a_pubtime}</i></li>
		 	`)
		 })

	}
	

})
