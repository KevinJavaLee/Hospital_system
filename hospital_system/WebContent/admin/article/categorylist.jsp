<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base/header.jsp" %>

<title>用户列表</title>
</head>
<body>
		<div class="weadmin-nav">
			<span class="layui-breadcrumb">
				<a href="javascript:;">首页</a> <a href="javascript:;">文章分类管理</a>
				<a href="javascript:;"> <cite>文章分类列表</cite></a>
			</span>
			<a class="layui-btn layui-btn-sm" style="margin-top:3px;float:right" href="javascript:location.replace(location.href);"
			 title="刷新">
				<i class="layui-icon layui-icon-refresh"></i>
				<!-- <i class="layui-icon" style="line-height:30px">&#x1002;</i> -->
			</a>
		</div>

		<div class="weadmin-body">
			<div class="layui-row">
				<form class="layui-form layui-col-md12 we-search">
					
					<div class="layui-inline">
						<input type="text" name="likeuser" placeholder="请输入查找的类"名 autocomplete="off" class="layui-input" />
					</div>
					<button class="layui-btn" lay-submit="" lay-filter="sreach">
						<i class="layui-icon layui-icon-search"></i>
					</button>
				</form>
			</div>
			<div class="weadmin-block">
				<button class="layui-btn layui-btn-danger" id="pdelBtn" >
					<i class="layui-icon layui-icon-delete"></i>批量删除
				</button>
				<a class="layui-btn" href="<%=request.getContextPath() %>/admin/article/addcategory">
					<i class="layui-icon layui-icon-add-circle-fine"></i>添加
				</a>
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
						<th>分类标识</th>
						<th>分类描述</th>
						<th>上级类别</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${categorylist}" var="item" varStatus="i">
					<tr data-id="item.count">
						<td>
							<div class="layui-unselect layui-form-checkbox"  lay-skin="primary" data-id="${item.c_id}">
								<i class="layui-icon">&#xe605;</i>
							</div>
						</td>
						<td>${item.c_id}</td>
						<td>${item.c_category}</td>
						<td>${item.c_brief}</td>
						<td>${item.parentname}</td>
						<td class="td-manage">
							
							
							<a title="编辑"  href="<%=request.getContextPath() %>/admin/article/editcategory?id=${item.c_id}">
								<i class="layui-icon layui-icon-edit"></i>
							</a>
							<!-- <a title="删除"  href="<%=request.getContextPath() %>/admin/deluser?id=${item.id}"> -->
							<div>
								<span title="删除" class="delBtn" data-id="${item.c_id}" data-parentid="${item.c_parentid}" >
									<i class="layui-icon layui-icon-delete"></i>
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
						<a class="prev" href="<%=request.getContextPath() %>/admin/article/categorylist?page=${page-1}">&lt;&lt;</a>
					</c:if>
					 <span class="current">${page }</span>
					 <c:if test="${page+1 <=allpage }">
					 	 <a class="num" href="<%=request.getContextPath() %>/admin/article/categorylist?page=${page+1}"> ${page+1}</a>
					 </c:if>
					<c:if test="${page+2<allpapge }">
						 <a class="num" href="<%=request.getContextPath() %>/admin/article/categorylist?page=${page+2}">${page + 2 }</a>
					</c:if>
					<c:if test="${page+1 <allpage } ">
						<a class="num" href="<%=request.getContextPath() %>/admin/article/categorylist?page=${allpage}">${allpage}</a> 
					</c:if>
					<c:if test="${ page+1 <=allpage}">
						<a class="next" href="<%=request.getContextPath() %>/admin/article/categorylist?page=${page+1}">&gt;&gt;</a>
					</c:if>
					
				</div>
			</div>
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
				var parentid = e.target.parentElement.dataset.parentid;
				if (e.target.parentElement.className=="delBtn"){
					console.log(id);
					console.log(parentid);
					var alertId = layer.confirm('是否确认删除id为'+id+"父类id为："+parentid+"?", {
			  		  btn: ['考虑一下', '确认删除'], //可以无限个按钮
					  area: ['500px', '300px']
			  		}, function(index, layero){
			  			//关闭弹框
			  			layer.close(alertId); 
			  		  //按钮【按钮一】的回调
			  		}, function(index){
			  		  //按钮【按钮二】的回调
			  		location.href="<%=request.getContextPath() %>/admin/article/delcategory?id="+id;
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
				$('.layui-unselect.layui-form-checkbox:eq(0) i').css("background","#fff")
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
				console.log(delIdList);
				var alertId = layer.confirm('是否确认可以批量删除id为'+delIdList, {
			  		btn: ['考虑一下', '确认删除'], //可以无限个按钮
					area: ['500px', '300px']
			  		}, function(index, layero){
			  			//关闭弹框
			  		layer.close(alertId); 
			  		  //按钮【按钮一】的回调
			  		}, function(index){
			  		  //按钮【按钮二】的回调
						$.ajax({
						url:"<%=request.getContextPath() %>/admin/article/delcategory",
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
		
		
	</body>
</html>