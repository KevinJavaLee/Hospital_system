<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="base/header.jsp" %>

    <title>用户信息</title>
  </head>
  
  <body>
    <div class="weadmin-body">
        <form class="layui-form" action="<%=request.getContextPath() %>/admin/userinfo" method="post">
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>登录名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="username" readonly="readonly" required="" lay-verify="required"
                  autocomplete="off" value="${user.username }" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>将会成为您唯一的登入名
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red"></span>真实姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" readonly="readonly" required="" lay-verify="required"
                  autocomplete="off" value="${user.name }" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>你的真实姓名
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red"></span>年龄
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="age" name="age" readonly="readonly" required="" lay-verify="required"
                  autocomplete="off" value="${user.age }" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>你的年龄
              </div>
          </div>
          <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="we-red"></span>手机号码：
              </label>
              <div class="layui-input-inline">
                  <input type="text" readonly="readonly" value="${user.phone }" id="phone" name="phone" required="" lay-verify="phone"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>通讯方式
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="we-red"></span>邮箱
              </label>
              <div class="layui-input-inline">
                  <input type="text" readonly="readonly" value="${user.mail}" id="L_email" name="mail" required="" lay-verify="email"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>通讯方式
              </div>
          </div>
          <div class="layui-form-item layui-form-text">
    	  	<label class="layui-form-label">
    	  		<span class="we-red"></span>自我介绍:
    	  	</label>
    		<div class="layui-input-block">
      			<textarea name="brief" readonly="readonly" placeholder="" value="" class="layui-textarea" >${user.brief}</textarea>
    		</div>
  		 </div>
          <div class="layui-form-item">
              <label class="layui-form-label"><span class="we-red">*</span>性别:</label>
              <div class="layui-input-block" >
              	<c:if test="${user.sex =='男' }">
              		<input type="radio" name="sex" value="男" lay-skin="primary" title="男" checked="" readonly="readonly">
                	<input type="radio" name="sex" value="女" lay-skin="primary" title="女">
              	</c:if>
              	<c:if test="${user.sex =='女' }">
              		<input type="radio" name="sex" value="男" lay-skin="primary" title="男" >
                	<input type="radio" name="sex" value="女" lay-skin="primary" title="女" checked="" readonly="readonly">
              	</c:if>
              	<c:if test="${user.sex !='女'&&user.sex !='男'}">
              		<input type="radio"  name="sex" value="男" lay-skin="primary" title="男" >
                	<input type="radio" name="sex"  value="女" lay-skin="primary" title="女" >
              	</c:if>
                
                
              </div>
          </div>
           <div class="layui-form-item">
              <label class="layui-form-label"><span class="we-red">*</span>角色</label>
              <div class="layui-input-block">
              	<c:if test="${user.userType == 'admin'}">
              		<input type="radio" name="userType" value="admin" lay-skin="primary" title="超级管理员" checked="" readonly="readonly">
                	<input type="radio" name="userType" value="doctor" lay-skin="primary" title="医生">
                	
              	</c:if>
              	<c:if test="${user.userType =='doctor' }">
              		<input type="radio" name="userType" value="admin" lay-skin="primary" title="超级管理员" >
                	<input type="radio" name="userType" value="doctor" lay-skin="primary" title="医生" checked="" readonly="readonly">
                	
              	</c:if>
              	<c:if test="${user.userType !='doctor'&&userType!='admin' }">
              		<input type="radio" name="userType" value="admin" lay-skin="primary" title="超级管理员" >
                	<input type="radio" name="userType" value="doctor" lay-skin="primary" title="医生" checked="" readonly="readonly">
                	
              	</c:if>
                
              </div>
          </div>
          
         
      </form>
    </div>
	<script src="<%=request.getContextPath() %>/lib/layui/layui.js" charset="utf-8"></script>
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
          form.on('submit(add)', function(data){
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
          });
          
        });
    </script>
  </body>

</html>