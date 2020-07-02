<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base/header.jsp" %>
<link rel="stylesheet" href="lib/layui/css/layui.css" media="all">
    <title>添加排班信息</title>
  </head>
  
  <body>
    <div class="weadmin-body">
        <form class="layui-form" action="<%=request.getContextPath() %>/admin/scheduling/addscheduling " method="post" >
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>医生名
              </label>
              <div class="layui-input-inline">
                  <select name="doctorid">
					<option value="0">请选择医生</option>
                  	<c:forEach items="${doctorlist}" var="item">
                  		<option value="${item.id}">${item.name }</option>
                  	</c:forEach>
        				
				  </select>
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>需要排班的医生名
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red"></span>接诊人数
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="num" required="" lay-verify="required"
                  autocomplete="off" value="" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>接诊的人数
              </div>
          </div>
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red"></span>日期
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="datetime"  name="time" required="" lay-verify="required"
                  autocomplete="off" value="" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>排班的日期
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
               	新增排班                 
              </button>
          </div>
      </form>
    </div>
	
    <script type="text/javascript" charset="UTF-8">
    	layui.extend({
    		admin: '{/}../../static/js/admin'
			});
	    layui.use(['form','layer','admin'], function(){
	      var form = layui.form,
	      	admin = layui.admin,
	      	layer = layui.layer;
        
          //自定义验证规则
          form.verify({
            nikename: function(value){
              if(value.length < 5){
                return '昵称至少得5个字符啊';
              }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
          });

          //监听提交
          /* form.on('submit(add)', function(data){
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
          }); */
        
        });
    </script>
   	<script src="lib/layui/layui.js"></script>
    <script type="text/javascript">
    	layui.use('laydate', function(){
        	var laydate = layui.laydate;
     
    //执行一个laydate实例
        	laydate.render({
            	elem: '#datetime',
            	min: 0 //7天前
            	,max: 14,//14天后
            	value:new Date()
        	});
        	});
    	

    </script>
  </body>

</html>