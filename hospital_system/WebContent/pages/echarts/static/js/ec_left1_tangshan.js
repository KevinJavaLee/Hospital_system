// var ec_left1 = echarts.init(document.getElementById('l1'), "dark");

// var ec_left1_Option = {
// 	//标题样式
// 	title: {
// 		text: "全国累计趋势",
// 		textStyle: {
// 			// color: 'white',
// 		},
// 		left: 'left',
// 	},
// 	tooltip: {
// 		trigger: 'axis',
// 		//指示器
// 		axisPointer: {
// 			type: 'line',
// 			lineStyle: {
// 				color: '#7171C6'
// 			}
// 		},
// 	},
// 	legend: {
// 		data: ['累计确诊', '现有疑似', "累计治愈", "累计死亡"],
// 		left: "right"
// 	},

// 	//图形位置
// 	grid: {
// 		left: '4%',
// 		right: '6%',
// 		bottom: '4%',
// 		top: 50,
// 		containLabel: true
// 	},
// 	xAxis: [{
// 		type: 'category',
// 		//x轴坐标点开始与结束点位置都不在最边缘
// 		// boundaryGap : true,
// 		data: []//['01.20', '01.21', '01.22']
// 	}],
// 	yAxis: [{
// 		type: 'value',
// 		//y轴字体设置
// 		axisLabel: {
// 			show: true,
// 			color: 'white',
// 			fontSize: 12,
// 			formatter: function(value) {
// 				if (value >= 1000) {
// 					value = value / 1000 + 'k';
// 				}
// 				return value;
// 			}
// 		},
// 		//y轴线设置显示
// 		axisLine: {
// 			show: true
// 		},
// 		//与x轴平行的线样式
// 		splitLine: {
// 			show: true,
// 			lineStyle: {
// 				color: '#17273B',
// 				width: 1,
// 				type: 'solid',
// 			}
// 		}
// 	}],
// 	series: [{
// 		name: "累计确诊",
// 		type: 'line',
// 		smooth: true,
// 		data: []//[260, 406, 529]
// 	}, {
// 		name: "现有疑似",
// 		type: 'line',
// 		smooth: true,
// 		data: []//[54, 37, 3935]
// 	},
// 		{
// 		name: "累计死亡",
// 		type: 'line',
// 		smooth: true,
// 		data: []//[25, 25, 25]
// 	}, {
// 		name: "累计治愈",
// 		type: 'line',
// 		smooth: true,
// 		data: []//[6, 9, 17]
// 	}]
// };

// ec_left1.setOption(ec_left1_Option)


        var ec_left1 = echarts.init(document.getElementById('l1'), "dark");
        var markdata=[];//这是为了给地图上添加气泡图标,拿取备用数据
		//取得json的样式，读取json文件
		$.getJSON("tangshan.json", "", function(data) {

			markdata=convertData(data.features)
			//生成地图
			createMap(data);
		})
		//生成地图
		function createMap(data){
			echarts.registerMap('demo',data);
			
			// var chart = echarts.init(document.getElementById('main'));
			ec_left1.setOption({
				
                title: {
                text: '唐山最新疫情图',
                left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                    },
                    legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['河北疫情图']
                    },
                    visualMap: {
                    type: 'piecewise',//分段型
                    pieces: [
                        { min: 1000, max: 1000000, label: '大于等于1000人', color: '#372a28' },
                        { min: 500, max: 999, label: '确诊500-999人', color: '#4e160f' },
                        { min: 100, max: 499, label: '确诊100-499人', color: '#974236' },
                        { min: 10, max: 99, label: '确诊10-99人', color: '#ee7263' },
                        { min: 1, max: 9, label: '确诊1-9人', color: '#f5bba7' },
                        ],
                        color: ['#E0022B', '#E09107', '#A3E00B']
                    },
                    toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                    mark: { show: true },
                    dataView: { show: true, readOnly: false },
                    restore: { show: true },
                    saveAsImage: { show: true }
                    }
                    },
				    series: [{
                    name:'确诊人数',
					type: 'map',
					map: 'demo',
                    roam: false,
                    label: {
                    show: true,
                    color: 'rgb(249, 249, 249)'
                    },
					
					data:markdata,
					
				}]
			});
		}
		//处理整合   气泡图标数据
		function convertData(arrs){
			var markdata=[];
			var valuess=[3,5,7,4,2,6,3,2,5,4,4,3,4,6];
			for(var i=0; i<arrs.length; i++){
				markdata.push({})
				if(arrs[i].properties.name=='安次区'){
					markdata[i].coord=new Array(parseFloat(arrs[i].properties.center[0])+0.1,parseFloat(arrs[i].properties.center[1])-0.18);
					console.log(markdata[i])
				}else{
					markdata[i].coord=arrs[i].properties.center;
				}
				markdata[i].name=arrs[i].properties.name;
				
				markdata[i].value=valuess[i]
			}
			return markdata
		}