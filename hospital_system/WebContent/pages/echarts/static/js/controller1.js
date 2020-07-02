function gettime() {
	$.ajax({
		url: "http://localhost:8888/hospital_system/sendtime",
		type:"GET",
		dataType:"json",
		timeout: 10000, //超时时间设置为10秒；
		success: function(data) {
			console.log(data);
			$("#tim h4").html(data);
		},
		error: function(xhr, type, errorThrown) {

		}
	});
}




// // $("#tim").html(data)
function get_c1_data() {
	$.ajax({
        url: "http://localhost:8888/hospital_system/spider/senddataofhebei",
		type:"GET",
		success: function(data) {
            // console.log(data);
            // var str = JSON.stringify(data);
            console.log(data[0].confirmedNum);
            
			$(".num h1").eq(0).text(data[0].confirmedNum);
			// $(".num h1").eq(1).text(data[0].confirmedIncr);
			$(".num h1").eq(2).text(data[0].curesNum);
			// $(".num h1").eq(3).text(data[0].deathsNum);
		},
		error: function(xhr, type, errorThrown) {

		}
	})
}
function get_c2_data() {
    $.ajax({
		url:"http://localhost:8888/hospital_system//spider/sendhebeidata",
		method:"Get",
		dataType: "JSON",
        success: function(data) {
			console.log(data);
			var newArr = [];
			for(var i = 0 ;i< data.length;i++){
				var json = {
					name: data[i].name+'市',
					value: data[i].confirmedNum
				  }
				  newArr.push(json);
			}
			console.log(newArr);
			ec_center_option.series[0].data=newArr;
            ec_center.setOption(ec_center_option)
		},
		error: function(xhr, type, errorThrown) {

		}
    })
}

// function dateFormat(fmt, date) {
// 	let ret;
// 	const opt = {
// 				// 年
// 		"m+": (date.getMonth() + 1).toString(),     // 月
// 		"d+": date.getDate().toString(),            // 日
		
// 		// 有其他格式化字符需求可以继续添加，必须转化成字符串
// 	};
// for (let k in opt) {
// 	ret = new RegExp("(" + k + ")").exec(fmt);
// 	if (ret) {
// 		fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
// 	};
// 	};
// 	return fmt;
// }

// function get_l1_data() {
//     $.ajax({
// 		url:"http://localhost:8888/hospital_system/sendhistorydata",
// 		method:"Get",
// 		dataType:"json",
//         success: function(data) {
// 			console.log(data);
// 			var dateArr = []; 
// 			var confirmedNumArr = [];
// 			var curesNumArr = [];
// 			var deathsNumArr = [];
// 			var suspectedNumArr=[];
// 			for(var i = data.length-1 ;i>=0;i--){
// 				var date = new Date(data[i].date);
// 				console.log(dateFormat("mm-dd",date));
				
// 				dateArr.push(dateFormat("mm-dd",date));
// 				confirmedNumArr.push(data[i].confirmedNum);
// 				curesNumArr.push(data[i].curesNum);
// 				deathsNumArr.push(data[i].deathsNum);
// 				suspectedNumArr.push(data[i].suspectedNum)

				
// 			}
// 			console.log(dateArr);
// 			console.log(confirmedNumArr);
// 			console.log(curesNumArr);
// 			console.log(deathsNumArr);
// 			console.log(suspectedNumArr);

// 			ec_left1_Option.xAxis[0].data=dateArr
//             ec_left1_Option.series[0].data=confirmedNumArr
//             ec_left1_Option.series[3].data=curesNumArr
// 			ec_left1_Option.series[2].data=deathsNumArr
// 			ec_left1_Option.series[1].data=suspectedNumArr
//             ec_left1.setOption(ec_left1_Option)

//             // ec_left1_Option.xAxis[0].data=10
//             // ec_left1_Option.series[0].data=80000
//             // ec_left1_Option.series[1].data=400
//             // ec_left1_Option.series[2].data=3000
//             // ec_left1.setOption(ec_left1_Option)
// 		},
// 		error: function(xhr, type, errorThrown) {

// 		}
//     })
// }

// function get_l2_data() {
//     $.ajax({
// 		url:"http://localhost:8888/hospital_system/sendIncdata",
// 		method:"get",
// 		dataType:"json",
//         success: function(data) {

// 			console.log(data);
// 			var suspectedIncrArr = [];
// 			var asymptomaticIncrArr=[];
// 			var dateArr = [];
// 			for(var i = data.length-1 ;i>=0;i--){
// 				var date = new Date(data[i].date);
				
// 				console.log(dateFormat("mm-dd",date));
				
// 				dateArr.push(dateFormat("mm-dd",date));
// 				suspectedIncrArr.push(data[i].suspectedIncr);
// 				asymptomaticIncrArr.push(data[i].asymptomaticIncr);
// 				// deathsNumArr.push(data[i].deathsNum);
// 				// suspectedNumArr.push(data[i].suspectedNum)

				
// 			}
// 			ec_left2_Option.xAxis[0].data=dateArr;
//             ec_left2_Option.series[0].data=asymptomaticIncrArr;
// 			ec_left2_Option.series[1].data=suspectedIncrArr;
// 			console.log("l2:"+dateArr); 
// 			console.log(asymptomaticIncrArr);
// 			console.log(suspectedIncrArr);
//             ec_left2.setOption(ec_left2_Option)
// 		},
// 		error: function(xhr, type, errorThrown) {

// 		}
//     })
// }

function get_r1_data() {
    $.ajax({
		url: "http://localhost:12236/hospital_system/spider/SortedData",
		method:"GET",
		dataType:"json",
        success: function (data) {
			var confirmedNumArr = [];
			var nameArr = [];
			for(var i = 0 ;i<data.length;i++){
				
				confirmedNumArr.push(data[i].confirmedNum);
				nameArr.push(data[i].name);
				
			}
			console.log(nameArr);
			console.log(confirmedNumArr);
			ec_right1_option.xAxis.data=nameArr;
			console.log("测试："+ec_right1_option.xAxis.data);
            ec_right1_option.series[0].data=confirmedNumArr;
            ec_right1.setOption(ec_right1_option);
        }
    })
}
// function get_r2_data() {
//     $.ajax({
//         url: "http://localhost:8888/hospital_system/freshdata",
//         success: function (data) {
//            console.log(data);
//         }
//     })
// }


// get_r2_data()
gettime()
get_c1_data()
get_c2_data()
// get_l1_data()
// get_l2_data()
get_r1_data()


setInterval(gettime,1000)
setInterval(get_c1_data,1000*10)
setInterval(get_c2_data,10000*10)
// setInterval(get_l1_data,10000*10)
// setInterval(get_l2_data,10000*10)
setInterval(get_r1_data,10000*10)
// // setInterval(get_r2_data,10000*10)
