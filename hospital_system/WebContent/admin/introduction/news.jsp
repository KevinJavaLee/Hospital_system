<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../base/header.jsp" %>

    <title>修改科室信息</title>
  </head>
  
  <body>
    <div class="weadmin-body">
        <form class="layui-form" action="<%=request.getContextPath() %>/introduction/news?id=${news.id}" method="post" >
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>简介标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${news.title }" name="title" required="" lay-verify="required"
                  autocomplete="off" value="" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>医院简介的标题
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red"></span>作者
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${news.author }" name="author" required="" lay-verify="required"
                  autocomplete="off"  class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>修改的作者名
              </div>
          </div>
          
          
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>医院简介
              </label>
             
              <div class="">
              		<div id="editor" style="display:inline-block;width:800px;"></div>
              
              </div>
              
          </div>
          <textarea name="content" value="" id="content" cols="60" rows="10"></textarea>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
               	修改医院简介信息                  
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
    <script src="<%=request.getContextPath() %>/static/js/wangEditor.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    	var E = window.wangEditor;
    	var editor = new E('#editor');
       
    	editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
    	// 监控变化，同步更新到 textarea
        //console.log(html)
        var textareaDom = document.querySelector("#content");
        textareaDom.value = html;
       }
       editor.create();
    	

    </script>
  </body>

</html>