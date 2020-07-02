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
	url:".//introduction/doctornews",
	complete:function(res){
		console.log(res);
		var newList = res.responseJSON;
		$(".side-navlist").html("");
		 newList.forEach(function(item,index){
		 	$(".side-navlist").append(`
		 	<li class="side-nav-item">
            <div class="side-navlist-tit">
                <h3><a href="./section/a_${item.s_id}.html">${item.s_name}</a></h3>                
            </div>
        	</li>
		 	`)
		 })

	}
	

})
