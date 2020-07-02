<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html">
    <title>${article.a_title }</title>
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <!--PubLog:IsIncludeFile-->

<meta http-equiv="Cache-Control" content="no-transform">
<link rel="stylesheet" href="../html/css/nfyy_base.css">
<link rel="stylesheet" href="../html/css/nfyy_other.css">
<link rel="shortcut icon" href="../favicon.ico">
<script type="text/javascript" src="../html/js/jquery.min.js"></script>
<base target="_blank">

    <link rel="stylesheet" href="../html/css/nfyy_news-20140818000702.css"> 
</head>
    <body>
<div class="nav nav_k">
    <ul class="nav-bar clearfix">
        <li class="nav-item first">
            <div class="nav-item-tit"><a href="../index.html"></a></div>
        </li>
        <li class="nav-item home">
    <div class="nav-item-tit" id="nav_home"><a href="../index.html" target="_self">首页 </a></div>
</li>
<li class="nav-item">
    <div class="nav-item-tit" id="nav_yyjs"><a href="../introduction/introductionlist" target="_self">医院介绍</a></div>
</li>

        <li class="nav-item">
    <div class="nav-item-tit" id="nav_hzfw"><a href="" target="_self">服务导航</a></div>
        <ul class="nav-item-list">
       
        <li><a href="" target="_self">预约挂号</a></li>
    </ul>

</li>
<li class="nav-item">
    <div class="nav-item-tit" id="nav_mymk"><a href="../mymk/Index.html" target="_self">名医名科</a></div>
</li>
<li class="nav-item">
    <div class="nav-item-tit" id="nav_xwzx"><a href="" target="_self">新闻中心</a></div>
    <ul class="nav-item-list">
       
        <li><a href="../yyxw1.html" target="_self">医院新闻</a></li>
       
    </ul>
</li>
<li class="nav-item">
    <div class="nav-item-tit" id="nav_nyxs"><a href="" target="_self">疫情专栏</a></div>
    <ul class="nav-item-list">
        
    </ul>
</li>



    </ul>
</div>

<div class="header header_k clearfix">
    <span class="suoyin"><a href="../index.html">首页</a> - <a href="" title="新闻中心" target="_self">新闻中心</a> - <a href="Index.html" title="医院新闻" target="_self">医院新闻</a></span>
    <div class="person-center fr"><a href="">个人中心</a></div>

</div>
<div class="main">
    <div class="web_mainbox">
        <div class="web_left">

            <!--art_left-->
            <div class="art_box_c">

                <h1>${article.a_title}</h1>
                 <div class="remark">作者：${article.a_author}　　${article.a_pubtime }  阅读量：${article.a_readnum }</div>
                <div class="digest"><cite>[摘要]</cite></div> 
                <div class="art_con">
                    ${article.a_content }
                </div>
                <div class="share-wrap"><!--分享-->
			<div class="weibo_fx">
				<script type="text/javascript" src="../html/js/nfyy_share.js" charset="utf-8"></script>
				<script language="JavaScript" type="text/javascript"> 
				function toDesktop(sUrl,sName){ 
					try{ 
						var WshShell = new ActiveXObject("WScript.Shell"); 
						var oUrlLink = WshShell.CreateShortcut(WshShell.SpecialFolders("Desktop") + "\\" + sName + ".url"); 
						oUrlLink.TargetPath = sUrl; 
						oUrlLink.Save(); 
						alert("已成功将页面保存在桌面！");
					}catch(e){ 
						alert("当前IE安全级别不允许操作！请按Ctrl+S保存"); 
					} 
				}
				
				//加入收藏
				var isIE=(document.all&&document.getElementById&&!window.opera)?true:false; 
				var isMozilla=(!document.all&&document.getElementById&&!window.opera)?true:false; 
				var isOpera=(window.opera)?true:false;
				var seturl='url(#default#homepage)';
				var weburl=window.location.href;
				var webname=document.title;
				
				function addfavorite()
				{
					/*if(isMozilla){
						if (document.all){ window.external.addFavorite(weburl,webname);}
						else if (window.sidebar){ window.sidebar.addPanel(webname, weburl,"");}
					}
					if(isIE){window.external.AddFavorite(weburl, webname);}*/
					try{
						window.external.addFavorite(weburl,webname);
					}catch(e){
						try{
							window.sidebar.addPanel(webname,weburl,"");
						}catch(e){
							alert("加入收藏失败，请使用Ctrl+D进行添加");
						};
					};
				}
				</script>
				<!--===============weixin start==================-->
				<script src="../html/js/jquery-1.7.2.min.js" type="text/javascript"></script>
				<script>
				$(function(){
					
					$(".art_weixin").hover(function(){
					  $(".art_weixin_ewm").toggle();
					})
			
				})
				</script>   
					
					
					<script language="javascript" src="../html/js/ShareToWeiXin.js"></script>
					<script language="javascript">
				  var article =  {
					title: function(){
					var t = document.title.split("_");
					if (t.length > 0) return t[0];
					return "";
				},
				articleid:function(){
					var reg=/^.*?\/(\d{6,7})(_\d+)?\.html?$/;
					if (reg.exec( window.location.pathname) )
					return 1 *   window.location.pathname.replace(reg, "$1"); 
					else
					return 0;
				
				}
				// ,
				// img:function(){
				//   var reg=/<img [^>]+>/ig;
				//   var articleContent=$("#contentText").html();
				//   var imgHtml = articleContent .match(reg);
				//  if(imgHtml) { 
				// 	for(i=0;i<imgHtml.length;i++)
				// 	{ 
				// 		var regUri= /^.+src\s*?=\s*?[\"\']?(http:\/\/((pimg)|(photo))(\.39\.net\/[0-9a-z\/\-_]+\.jpg))[\"\']?.+$/ig ;
				// 	   if (regUri.exec(imgHtml[i]) ) { 
				// 		   var domain=imgHtml[i].replace(regUri,"$2")
				// 		   return  imgHtml[i].replace(regUri,"$1").replace("http://"+domain+".39.net/","http://npimg.39.net/");
				// 		}
				// 	  }
				//    }
				//    else
				//    return "";
				// }
				
				} ;
				 var a_title=article.title();
				var a_aid=article.articleid();
				var a_thumbImage=article.img();
				 
						/*$(function(){ 
						   if ( a_title=="" || a_aid<=0) return;
							$("[weixin_Share]").each(function(){
							  $(this).show();
							 $(this).hover (function(){
								$(this).addClass("art_weixin_hover");
								var imgBox=$(this).find("#wx_Share_img");
								if(imgBox.html()=="")
								 {	
									 ShareToWeiXin.GetShareCodeImgUrl(a_title,"http://m.39.net/f_weixin/a_"+a_aid+".html",a_thumbImage,function(stateid,imgUrl){
										if (stateid==1)
												imgBox.html("<img src=\""+imgUrl+"\">");
										else
										imgBox.html("获取二维码错误。");
												 }); 
										 }
							},function(){
								$(this).removeClass("art_weixin_hover");
							});
							$(".ewm_box i").click(function(){
								$(".art_weixin").removeClass("art_weixin_hover");
							});
							});
						});*/
					</script>
					<!--===============weixin end==================-->
			<br class="clear">    
			</div>
			<!--分享 end--></div>

            </div>
            </div>
            <!--PubLog:IsIncludeFile-->	

<div class="web_right">
<div class="rbox rightPic"></div>     

    <!--热点视频-->
			
     <div class="rbox hot-art">
		
			</div><!--热门标签-->
<div class="rbox clearfix">
    
    <div class="hotTag">
     
    </div>
</div>
<!--热门标签 end-->
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
    <script type="text/javascript">document.write(unescape("%3Cspan id='_ideConac' %3E%3C/span%3E%3Cscript src='http://dcs.conac.cn/js/20/000/0000/60414543/CA200000000604145430001.js' type='text/javascript'%3E%3C/script%3E"));</script>
	<div style="width:220px;margin:0 auto; padding:20px 0; float:right;">
		<!-- <a target="_blank" href="javascript:;" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="../../img/ghs.png" style="float:left;"><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">粤公网安备 44011102000028号</p></a> -->
	</div>
</div>
<div style="margin:0px auto; text-align:center;">
    <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://"); document.write(unescape("%3Cspan id='cnzz_stat_icon_1255838524'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1255838524%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</div>
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fdf36900115c9078dccb665ac42a36e49' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>