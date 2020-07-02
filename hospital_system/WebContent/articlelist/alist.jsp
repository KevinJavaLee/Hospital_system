<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html">
    <title>新闻中心</title>
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <!--PubLog:IsIncludeFile-->

    <meta http-equiv="Cache-Control" content="no-transform">
    <link rel="stylesheet" href="./html/css/nfyy_base.css">
    <link rel="stylesheet" href="./html/css/nfyy_news.css">
    <link rel="shortcut icon" href="./favicon.ico"> 
    <script type="text/javascript" src="./html/js/jquery.min.js"></script>
    <base target="_blank">

</head>

<body>
    <!--PubLog:IsIncludeFile-->
    <div class="top-bar-wrap">
        <div class="top-bar clearfix">
            <div class="tb-tools fr">
                <span class="tb-com tb-pop tb-wx fl">



                </span>
            </div>
        </div>
    </div>
    <div class="main">
        <div class="header clearfix">
            <div class="logo fl"></div>
            <div class="person-center fr"><a href="">个人中心</a></div>

        </div>
        <!--导航栏部分-->
        <div class="nav" id="topMainNav">
            <ul class="nav-bar clearfix">
                <li class="nav-item home">
                    <div class="nav-item-tit" id="nav_home"><a href="./index.html" target="_self">首页 </a></div>
                </li>
                <li class="nav-item">
                    <!--li class="nav-item first"-->
                    <div class="nav-item-tit" id="nav_yyjs"><a href="#" target="_self">医院概况</a></div>
                    <ul class="nav-item-list">
                        <li><a href="http://localhost:8888/hospital_system/introduction/introductionlist"
                                target="_self">医院简介</a></li>
                    </ul>
                </li>

                <li class="nav-item">
                    <div class="nav-item-tit" id="nav_hzfw"><a href="" target="_self">服务导航</a></div>
                    <ul class="nav-item-list">
                        <li><a href="./desktop/login" target="_self">预约挂号</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <div class="nav-item-tit" id="nav_mymk"><a href="./mymk/Index.html" target="_self">名医名科</a></div>
                </li>
                <li class="nav-item">
                    <div class="nav-item-tit" id="nav_xwzx"><a href="" target="_self">新闻中心</a></div>
                    <ul class="nav-item-list">
                        
                        <li><a href="" target="_self">医院新闻</a></li>
                        
                    </ul>
                </li>
                <li class="nav-item">
                    <div class="nav-item-tit" id="nav_nyxs"><a href="http://localhost:8888/hospital_system/pages/echarts/templates/main.html" target="_self">疫情专栏</a></div>
                    <ul class="nav-item-list">
                        <li><a href="http://localhost:8888/hospital_system/pages/echarts/templates/main1.html">河北省疫情图</a></li>
                    </ul>
                </li>
                

            </ul>
        </div>
        <!--导航栏部分-->
    </div>
    <div class="main">
        <div class="web_location">
            <span class="location_text"><a href="../index.html" title="首页">首页</a> - <a href="Index.html" title="新闻中心"
                    target="_self">新闻中心</a> - <a href="yyxw/Index1.html" title="医院新闻" target="_self">医院新闻</a></span>
        </div>
        <div class="web_mainbox">
            <div class="web_left">
                <div class="list_box">
                    <h2>医院新闻</h2>
                    <ul class="clearfix">
                        <c:forEach items="${alist }" var="item">
                            <li class="nopic clearfix">
                                <div class="cont clearfix">
                                    <h3><a href="<%=request.getContextPath() %>/article/a_${item.a_id}.html">${item.a_title
                                            }</a></h3>
                                    <em>${item.a_pubtime }</em>
                                    <p></p>
                                    <span>

                                    </span>
                                </div>
                            </li>
                        </c:forEach>



                    </ul>
                </div>
                <!--web_page-->
                <div class="web_page">
                    <a href="javascript:void(0);" target="_self" class="prev page_none">上一页</a><b>1</b><a
                        href="javascript:;" target="_self">2</a><a href="javascript:;" target="_self">3</a><a
                        href="javascript:;" target="_self">4</a><a href="javascript:;" target="_self">5</a><a
                        href="javascript:;" target="_self">6</a><a href="javascript:;" target="_self">7</a><a
                        href="javascript:;" target="_self">8</a>...<a href="javascript:;" class="next"
                        target="_self">下一页</a><span class="page_input">到第 <input type="text" class="page_num"
                            name="page_num" id="txtPageNo12270"> 页</span><input type="button" class="page_btn formbtns"
                        name="page_btn" id="page_btn" value="确定"
                        onclick="if(isNum($('#txtPageNo12270').val()) && $('#txtPageNo12270').val()*1>0){location.href=($('#txtPageNo12270').val()*1)>2?'http://www.nfyy.com/xwzx/yyxw/index_'+$('#txtPageNo12270').val()+'.html':'http://www.nfyy.com/xwzx/yyxw/index.html'}else{alert('请输入大于0的数字');$('#txtPageNo12270').val('');$('#txtPageNo12270').focus();}">
                </div>
                <!--web_page end-->
            </div>
            <!--PubLog:IsIncludeFile-->

            <div class="web_right">
                <div class="rbox">

                </div>
                <div class="rbox">





                    <div class="rbox rightPic">

                    </div>


                </div>
            </div>
        </div>
    </div>
    <!--PubLog:IsIncludeFile-->
    <div class="footer-wrap">
        <div class="footer">
            <div class="ft-copy fl">
                <p class="yy-line"><i class="arrow"></i><span>预约咨询</span><b></b></p>
                <p>地址：唐山市大学西道9号
                    &nbsp;&nbsp;邮编：063000&nbsp;&nbsp;技术支持:<a style="color: #fff" href="javascript:;">计算机科学与技术学院</a></p>
                <p>Copyright &copy;2020公寓村医院.All rights reserved. &nbsp;&nbsp;Kevin xiao</p>
            </div>


        </div>
    </div>


    <script type="text/javascript" src="./html/js/Marquee.js"></script>
    <script type="text/javascript" src="./html/js/jcarousellite.js"></script>
    <script type="text/javascript" src="./html/js/common.js"></script>
    <script type="text/javascript">
        $(function () {
            var col = document.location.pathname.split('/')[1];
            $('#topMainNav').find("li a").each(function () {

                if (col == "search") {
                    $("#nav_home").addClass('on');
                    return false;
                }
                if ($(this).attr('href').indexOf(col) > 0) {
                    $("#topMainNav").find('div').removeClass('on');
                    $(this).parent('div').addClass('on');
                    return false;
                };
            });

            $("body a").click(function () {
                var href = $(this).attr("href");
                var host = window.location.host;
                if (href.indexOf('http') != -1) {
                    if (href.indexOf(host) == -1) {
                        event.returnValue = confirm("您是否要离开本站点，跳转到其它页面吗？");
                    }
                }
            });
        });
    </script>

    <div style="width:300px;margin:2px auto; text-align:center;">
        <script
            type="text/javascript">document.write(unescape("%3Cspan id='_ideConac' %3E%3C/span%3E%3Cscript src='http://dcs.conac.cn/js/20/000/0000/60414543/CA200000000604145430001.js' type='text/javascript'%3E%3C/script%3E"));</script>
        <div style="width:220px;margin:0 auto; padding:20px 0; float:right;">

        </div>
    </div>
    <div style="margin:0px auto; text-align:center;">
        <script
            type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://"); document.write(unescape("%3Cspan id='cnzz_stat_icon_1255838524'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1255838524%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
    </div>
    <script type="text/javascript">
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fdf36900115c9078dccb665ac42a36e49' type='text/javascript'%3E%3C/script%3E"));
    </script>
</body>

</html>