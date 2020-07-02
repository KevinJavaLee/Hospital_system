$.ajax({
	url:"http://localhost:8888/hospital_system/json/doctorList",
	complete:function(res){
		var cnt_neike = 0;
		var doctorList = res.responseJSON;
		console.log(doctorList);
//		$(".web_left").html("");
		doctorList.forEach(function(item,index){
        console.log(item);
        
            if (item.s_type=="内科")
            {
                cnt_neike++;
                
                if (cnt_neike > 3){
                    $(".mymk_wrap:eq(1) .jk_list ul").append(`<li>
                    <div class="jk_img"><a href="#"><img
                                src="${item.imgurl}"
                                alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
                    <p>${item.brief} </p>
                    <span><a
                            href="#">擅长：${item.specialty}</a></span>
                </li>`);
                } else{
                    $(".jk_list ul").eq(0).append(`<li>
                    <div class="jk_img"><a href="#"><img
                                src="${item.imgurl}"
                                alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
                    <p>${item.brief} </p>
                    <span><a
                            href="#">擅长：${item.specialty}</a></span>
                    </li>`);
                }

            }
            if (item.s_type=="外科")
            {
                  $(".mymk_wrap:eq(2) .jk_list ul").append(`<li>
                          <div class="jk_img"><a href="#"><img
                          src="${item.imgurl}"
                          alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
              <p>${item.brief} </p>
              <span><a
                      href="#">擅长：${item.specialty}</a></span>
          </li>`);
            }
            if (item.s_type=="中医科")
            {
                  $(".mymk_wrap:eq(3) .jk_list ul").append(`<li>
                          <div class="jk_img"><a href="#"><img
                          src="${item.imgurl}"
                          alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
              <p>${item.brief} </p>
              <span><a
                      href="#">擅长：${item.specialty}</a></span>
          </li>`);
                  


            }
//            if (item.s_type=="外科")
//            {
//                console.log("外科");
//                $(".web_left div:eq(1)").html("");
//            //     $(".jk_list ul").eq(1).append(`<li>
//            //     <div class="jk_img"><a href="#"><img
//            //                 src="${item.imgurl}"
//            //                 alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
//            //     <p>${item.brief} </p>
//            //     <span><a
//            //             href="#">擅长：${item.specialty}</a></span>
//            // </li>`);
//
//            } 
//            if (item.s_type=="中医科")
//            {
//            	$(".webleft:last").html("");
////                $(".webleft:last").append(`<li>
////                <div class="jk_img"><a href="#"><img
////                            src="${item.imgurl}"
////                            alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
////                <p>${item.brief} </p>
////                <span><a
////                        href="#">擅长：${item.specialty}</a></span>
////            </li>`);
//
//            }
            
        


        // <ul><li class="">1</li><li class="navOn">2</li><li class="">3</li></ul>
		// 	$(".web_left").append(`
		// 	<div class="mymk_wrap mymk_czys clearfix">
		// 	<div class="mymk_titbar clearfix">
		// 		<h3><b>${item.s_type}</b></h3>
		// 		<h3>【名医诊区出诊医生】</h3>
		// 		<div class="focus_btn" id="mymk_czys_btn"></div>
		// 	</div>
		// 	<div class="mymk_imglist" id="mymk_czys">
		// 		<ul>
		// 			<li>
		// 				<div class="jk_wrap clearfix">
		// 					<div class="jk_list ">
		// 						<ul>

		// 							<li>
		// 								<div class="jk_img"><a href="#"><img
		// 											src="${item.imgurl}"
		// 											alt=""><i></i><strong><b>${item.name}</b> ${item.s_name}</strong></a></div>
		// 								<p>${item.brief} </p>
		// 								<span><a
		// 										href="#">擅长：${item.specialty}</a></span>
		// 							</li>
		// // 						</ul>
		// 					</div>
		// 				</div>
		// 			</li>
		// 		</ul>
		// 	</div>
		// </div>
		// 	`)
		} )
		

	}
	

})