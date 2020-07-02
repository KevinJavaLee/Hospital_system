<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base/header.jsp" %>
<link rel="stylesheet" href="../lib/layui/css/layui.css" media="all">
<title>预约列表</title>
</head>
<body>
		<div class="weadmin-nav">
			<span class="layui-breadcrumb">
				<a href="javascript:;">首页</a> <a href="javascript:;">我的预约管理</a>
				<a href="javascript:;"> <cite>预约列表</cite></a>
			</span>
			<a class="layui-btn layui-btn-sm" style="margin-top:3px;float:right" href="javascript:location.replace(location.href);"
			 title="刷新">
				<i class="layui-icon layui-icon-refresh"></i>
				<!-- <i class="layui-icon" style="line-height:30px">&#x1002;</i> -->
			</a>
		</div>

		<div class="weadmin-body">
			<%-- <div class="layui-row">
				<form class="layui-form layui-col-md12 we-search" action="<%=request.getContextPath() %>/admin/doctor/reservelist?username=${username}" method="post">
					 
					<div class="layui-inline">
						 <input type="text" placeholder="开始时间" class="layui-input" name="starttime" id="fromtime">
					</div>
					<div class="layui-inline">
						 <input type="text" placeholder="结束时间" class="layui-input" name="endtime" id="totime">
					</div>
					<button class="layui-btn" lay-submit="" lay-filter="sreach">
						<i class="layui-icon layui-icon-search"></i>
					</button> 
					<c:out value="${username}"></c:out>
				</form>
			</div> --%>
			<div class="weadmin-block">
				
				<%-- <a class="layui-btn" href="<%=request.getContextPath() %>/admin/adduser">
					<i class="layui-icon layui-icon-add-circle-fine"></i>添加
				</a> --%>
				<span class="fr" style="line-height:40px">共有数据：${total}条</span>
			</div>
			<table class="layui-table" id="memberList">
				<thead>
					<tr>
						<th>
							<div class="layui-unselect header layui-form-checkbox" lay-skin="primary" >
								<i class="layui-icon">&#xe605;</i>
							</div>
						</th>
						<th>ID</th>
						<th>医生姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>预约日期</th>
						<th>预约科室</th>
						<th>预约号码</th>
						<th>状态</th>
						<th>处理</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${reservelist }" var="item" varStatus="i">
					<tr data-id="item.count">
						<td>
							<div class="layui-unselect layui-form-checkbox"  lay-skin="primary" data-id="${item.r_id}">
								<i class="layui-icon">&#xe605;</i>
							</div>
						</td>
						<td>${item.r_id }</td>
						<td>${item.name }</td>
						<td>${item.sex }</td>
						<td>${item.age }</td>
						<td>${item.r_time }</td>
						<td>${item.s_name }</td>
						<td>${item.registernum }</td>
						
						<td class="td-status">
							<span class="layui-btn layui-btn-normal layui-btn-xs"><c:out value="${item.r_status }" default="未设置"></c:out></span>
						</td>
						<td class="td-manage">
							
							
							<%-- <a title="编辑"  href="<%=request.getContextPath() %>/admin/edituser?username=${item.username}">
								<i class="layui-icon layui-icon-edit"></i>
							</a>  --%>
							<!-- <a title="删除"  href="<%=request.getContextPath() %>/admin/deluser?id=${item.id}"> -->
							<div>
								<span title="取消预约" class="delBtn" data-id="${item.r_id}" data-userid="${item.uid}" data-time="${item.r_time }" data-username="${item.name}">
									<i class="layui-icon layui-icon-close-fill"></i>
								</span>
								
							</div>
								
							
						</td>
						</tr>
				</c:forEach>
					
					
				</tbody>
			</table>
			<div class="page">
				<div>
					<c:if test="${page!='1'}">
						<a class="prev" href="<%=request.getContextPath() %>/desktop/myreservation?page=${page-1}">&lt;&lt;</a>
					</c:if>
					 <span class="current">${page }</span>
					 <c:if test="${page+1 <=allpage }">
					 	 <a class="num" href="<%=request.getContextPath() %>/desktop/myreservation?page=${page+1}"> ${page+1}</a>
					 </c:if>
					<c:if test="${page+2<allpapge }">
						 <a class="num" href="<%=request.getContextPath() %>/desktop/myreservation?page=${page+2}">${page + 2 }</a>
					</c:if>
					<c:if test="${page+1 <allpage } ">
						<a class="num" href="<%=request.getContextPath() %>/desktop/myreservation?page=${allpage}">${allpage}</a> 
					</c:if>
					<c:if test="${ page+1 <=allpage}">
						<a class="next" href="<%=request.getContextPath() %>/desktop/myreservation?page=${page+1}">&gt;&gt;</a>
					</c:if>
					
				</div>
			</div>
			<script language=JavaScript>
		        function myrefresh()
		        {
		        	window.location.href=window.location.href;
		        }
		        setTimeout('myrefresh()',3000); //指定1秒刷新一次
		    </script>
			<script type="text/javascript" charset="UTF-8">
			layui.use(['layer', 'form','jquery'], function(){
			var layer = layui.layer
			,form = layui.form;
			var $ = layui.jquery;
			
			
			var tbody = document.querySelector("tbody");
			tbody.addEventListener("click",function(e){
				console.log(e);
				console.log(e.target.parentElement.className);  
				var id =e.target.parentElement.dataset.id;
				var userid = e.target.parentElement.dataset.userid;
				var username = e.target.parentElement.dataset.username;
				var time = e.target.parentElement.dataset.time
				if (e.target.parentElement.className=="delBtn"){
					console.log(id);
					console.log(username);
					console.log(userid);
					console.log(time);
					var alertId = layer.confirm('是否确认取消id为'+id+"医生为："+username+"的预约?", {
			  		  btn: ['考虑一下', '取消预约'], //可以无限个按钮
					  area: ['500px', '300px']
			  		}, function(index, layero){
			  			//关闭弹框
			  			layer.close(alertId); 
			  		  //按钮【按钮一】的回调
			  		}, function(index){
			  		  //按钮【按钮二】的回调
			  		location.href="<%=request.getContextPath() %>/desktop/cancel?id="+id+"&userid="+userid+"&time="+time;
			  		});
				}

				
			});

			//全选按钮的监听
				var allSelectBtn = document.querySelector(".layui-unselect.header.layui-form-checkbox")
				var isAllSelect = false;
				allSelectBtn.addEventListener("click",function(){
					console.log(123)
					if(isAllSelect){
						$(".layui-form-checkbox i").css("background","#fff")
						isAllSelect = false;
					}else{
						$(".layui-form-checkbox i").css("background","#1E9FFF")
						isAllSelect = true;
					}
					
			})


			$('.layui-unselect.layui-form-checkbox[data-id]').click(function(e){
				
				if(e.target.style.background=="rgb(30, 159, 255)"){
					  //可以放置原生对象,进行封装成jQuery对象
					$(e.target).css("background","#fff")
				}else{
					$(e.target).css("background","#1E9FFF")
						
				}
				isAllSelect = false;
				$('.layui-unselect.layui-form-checkbox:eq(0) i').css("background","#fff");
			})


			//   1.监听
			$("#pdelBtn").click(function(){
				var allCheckBox = document.querySelectorAll(".layui-unselect.layui-form-checkbox[data-id]");
				var delIdList = [];
				allCheckBox.forEach(function(item,index){
					console.log([item]);
					if(item.children[0].style.background=="rgb(30, 159, 255)"){
						delIdList.push(item.dataset.id);
						console.log(item.dataset.id);
					}	
					

					
				})
				console.log("批量删除的数据："+delIdList);
				var alertId = layer.confirm('是否确认可以批量删除id为'+delIdList, {
			  		btn: ['考虑一下', '确认完成'], //可以无限个按钮
					area: ['500px', '300px']
			  		}, function(index, layero){
			  			//关闭弹框
			  		layer.close(alertId); 
			  		  //按钮【按钮一】的回调
			  		}, function(index){
			  		  //按钮【按钮二】的回调
						$.ajax({
						url:"<%=request.getContextPath() %>/admin/doctor/finishreservation",
						method:"post",
				  	data:{
						ids:delIdList
				  	},
				  	complete:function(res){
						console.log(res)
						// 1.重新加载页面
						location.reload();
				  	}
				  })
			  		});
				

			});
			

		});
		</script>
		
		<script>
    		layui.use('laydate', function(){
    		var laydate = layui.laydate;
 
			//执行一个laydate实例
    		laydate.render({
	        elem: '#fromtime',
	        theme: 'molv'
	    	});
		    laydate.render({
		        elem: '#totime',
		        theme: 'molv'
		    });

    		});
    
   


		</script>
		
	</body>
</html>