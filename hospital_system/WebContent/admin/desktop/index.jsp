<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base/header.jsp" %>
	<title>康健服务管理平台</title>
	</head>

	<body>
		<!-- 顶部开始 -->
		<div class="container">
			<div class="logo">
				<a href="./Index">预约挂号</a>
			</div>
			<div class="left_open">
				<!-- <i title="展开左侧栏" class="iconfont">&#xe699;</i> -->
				<i title="展开左侧栏" class="layui-icon layui-icon-shrink-right"></i>
				
			</div>
			<ul class="layui-nav left fast-add" lay-filter="">
				<!-- <li class="layui-nav-item">
					<a href="javascript:;">+新增</a>
					<dl class="layui-nav-child">
						二级菜单
						<dd>
							<a onclick="WeAdminShow('资讯','https://www.baidu.com/')"><i class="layui-icon layui-icon-list"></i>资讯</a>
						</dd>
						<dd>
							<a onclick="WeAdminShow('图片','http://www.baidu.com')"><i class="layui-icon layui-icon-picture-fine"></i>图片</a>
						</dd>
						<dd>
							<a onclick="WeAdminShow('用户','https://www.baidu.com/')"><i class="layui-icon layui-icon-user"></i>用户</a>
						</dd>
					</dl>
				</li> -->
			</ul>
			<ul class="layui-nav right" lay-filter="">
				<li class="layui-nav-item">
					<a href="javascript:;">${username}</a>
					<dl class="layui-nav-child">
						<!-- 二级菜单 -->
						<dd>
							<a onclick="WeAdminShow('个人信息','./userinfo')">个人信息</a>
						</dd>
						<dd>
							<a href="./login">切换账号</a>
						</dd>
						<dd>
							<a class="./loginout" href="./loginout">退出</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item to-index">
					<a href="<%=request.getContextPath() %>/index.html">前台首页</a>
				</li>
			</ul>

		</div>
		<!-- 顶部结束 -->
		<!-- 中部开始 -->
		<!-- 左侧菜单开始 -->
		<div class="left-nav">
			<div id="side-nav">
				<ul id="nav">
				
					<li>
						<a href="javascript:;">
							<i class="iconfont layui-icon">&#xe66f;</i>
							<cite>用户预约挂号</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="./schedulinglist">
									<i class="iconfont">&#xe6a7;</i>
									<cite>预约挂号</cite>

								</a>
							</li>
							
							
						</ul>
					</li> 
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe705;</i>
							<cite>我的预约管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							
							<li>
								<a _href="./myreservation">
									<i class="iconfont">&#xe6a7;</i>
									<cite>我的预约</cite>
								</a>
							</li>
							
						</ul>
					</li>
					<%-- <li>
						<a href="javascript:;">
							<i class="iconfont">&#xe6ce;</i>
							<cite>系统统计</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath() %>/pages/echarts/Echart.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>全国最新疫情图</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath() %>/pages/echarts/hebei.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>河北省疫情图</cite>
								</a>
							</li>
							<li>
								<a _href="<%=request.getContextPath() %>/pages/echarts/hunan.html">
									<i class="iconfont">&#xe6a7;</i>
									<cite>湖南省疫情地图</cite>
								</a>
							</li>
							
							
							
						</ul>
					</li> --%>
				</ul>
			</div>
		</div>
		<!-- <div class="x-slide_left"></div> -->
		<!-- 左侧菜单结束 -->
		<!-- 右侧主体开始 -->
		<div class="page-content">
			<div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
				<ul class="layui-tab-title" id="tabName">
					<li>我的桌面</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<iframe src='<%=request.getContextPath() %>/pages/welcome.html' frameborder="0" scrolling="yes" class="weIframe"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div class="page-content-bg"></div>
		<!-- 右侧主体结束 -->
		<!-- 中部结束 -->
		<!-- 底部开始 -->
		<div class="footer">
			<div class="copyright">Copyright ©2020 康健医疗管理平台 All Rights Reserved</div>
		</div>
		<!-- 底部结束 -->
		<script language="JavaScript">
		        function myrefresh()
		        {
		           window.location.reload();
		        }
		        /* setTimeout('myrefresh()',1000); */ //指定1秒刷新一次
		</script>
		<script type="text/javascript">
			// //layui扩展模块的两种加载方式-示例
		    // layui.extend({
			// admin: '{/}../../static/js/admin'
			// });
			// //使用拓展模块
			// layui.use('admin', function(){
			//   var admin = layui.admin;
			// });
			layui.config({
			  base: '<%=request.getContextPath() %>/static/js/'
			  ,version: '101100'
			}).use('admin');
			layui.use(['jquery','admin'], function(){
				var $ = layui.jquery;
				/* $(function(){
					var login = JSON.parse(localStorage.getItem("login"));
					if(login){
						if(login=0){
							window.location.href='./login.html';
							return false;
						}else{
							return false;
						}
					}else{
						window.location.href='./login.html';
						return false;
					}
				}); */
			});

		</script>
	</body>
	<!--Tab菜单右键弹出菜单-->
	<ul class="rightMenu" id="rightMenu">
        <li data-type="fresh">刷新</li>
        <li data-type="current">关闭当前</li>
        <li data-type="other">关闭其它</li>
        <li data-type="all">关闭所有</li>
    </ul>

</html>