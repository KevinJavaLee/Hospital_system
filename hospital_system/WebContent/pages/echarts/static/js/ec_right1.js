var ec_right1 = echarts.init(document.getElementById('r1'),"dark");
var ec_right1_option = {
	//标题样式
	title : {
	    text : "非湖北地区城市确诊TOP10",
	    textStyle : {
	        color : 'white',
	    },
	    left : 'left'
	},
	  color: ['#8B0000'],
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
	            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
    xAxis: {
        type: 'category',
		data: [1,1,1,1,1,1,1],
		axisTick: {
			alignWithLabel: true
		}
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [3,5,6,7,8,9,10],
        type: 'bar',
		barMaxWidth:"50%"
    }]
};
ec_right1.setOption(ec_right1_option)