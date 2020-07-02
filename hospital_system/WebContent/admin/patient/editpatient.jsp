<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base/header.jsp" %>

    <title>修改病患信息</title>
  </head>
  
  <body>
    <div class="weadmin-body">
        <form class="layui-form" action="<%=request.getContextPath() %>/admin/patient/editpatient" method="post">
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>用户名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="username" required="" lay-verify="required"
                  autocomplete="off" value="${patientlist.p_username }" readonly="readonly" class="layui-input">
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
                  <input type="text" id="name" name="name" required="" lay-verify="required"
                  autocomplete="off" value="${patientlist.p_name }" class="layui-input">
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
                  <input type="text" id="age" name="age" required="" lay-verify="required"
                  autocomplete="off" value="${patientlist.p_age }" class="layui-input">
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
                  <input type="text" value="${patientlist.p_tel }" id="tel" name="tel" required="" lay-verify="phone"
                  autocomplete="off"  class="layui-input">
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
      			<textarea name="brief" placeholder=""  class="layui-textarea" >${patientlist.p_brief}</textarea>
    		</div>
  		 </div>
          <div class="layui-form-item">
              <label class="layui-form-label"><span class="we-red">*</span>性别:</label>
              <div class="layui-input-block">
              <c:if test="${patientlist.p_sex=='男' }">
              	<input type="radio" name="sex" value="男" lay-skin="primary" title="男" checked="checked">
                	<input type="radio" name="sex" value="女" lay-skin="primary" title="女">
              </c:if>
              <c:if test="${patientlist.p_sex=='女' }">
              	    <input type="radio" name="sex" value="男" lay-skin="primary" title="男" >
                	<input type="radio" name="sex" value="女" lay-skin="primary" title="女" checked="checked">
              </c:if>
              <c:if test="${patientlist.p_sex !='女'&&patientlist.p_sex !='男'}">
              		<input type="radio"  name="sex" value="男" lay-skin="primary" title="男" >
                	<input type="radio" name="sex"  value="女" lay-skin="primary" title="女" >
              	</c:if>	
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="we-red"></span>密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required="" lay-verify="pass"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  6到16个字符
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="we-red">*</span>确认密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                                     修改病患
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