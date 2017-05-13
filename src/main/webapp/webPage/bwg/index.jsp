<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="昭通市博物馆,昭通市,博物馆" name="keywords">
<meta content="" name="description">
<title>昭通市博物馆</title>

<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/index.css">

<link rel="stylesheet" href="${pathResource}/tools/assets/css/amazeui.min.css">
<link rel="stylesheet" href="${pathResource}/tools/assets/css/app.css">

<!-- swiper -->
<link rel="stylesheet" href="${pathResource }/tools/swiper/swiper-3.3.1.min.css">

<!-- 自己的样式 -->
<link rel="stylesheet" href="css/my/index.css">

<script type="text/javascript" src="${pathResource}/tools/jquery-fileupload/jquery-1.9.1.min.js"></script>

<!-- swiperJS -->
<script type="text/javascript" src="${pathResource}/tools/swiper/swiper-3.3.1.jquery.min.js"></script>

<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="${pathResource}/tools/assets/js/amazeui.min.js"></script>

<style type="text/css">

</style>


</head>
<body>


<!--S-头部-->
 <div class="header w1200 clearFloat">
     <!--header-top-->
     <div class="header-top text-right mt30">
     </div>
     <!--header-top end-->
     <!--header-width-->
     <div class="header-width clearFloat mb25">
         <div class="logo floatLeft">
             <a href=""><img src="images/logo.png" alt="" width="442" height="61"/></a>

         </div>
         <div class="header-phone floatRight">
             <div class="sea">
                 <form>
                     <input class="txtclass" type="text" value="请输入关键词">
                     <i class="search_a" type="submit" value="请输入关键词"></i>
                 </form>
             </div>
         </div>

     </div>
     <!--header-width end-->
 </div>
 <!--E-头部-->

<!-- S-菜单 -->
<div class="myMenu">
	<ul style="width: 1200px">
		<li><span>首页</span></li>
		<li><span>院情介绍</span><br/>
			<ul class="supMenu">
				<li><span>本院详情</span></li>
				<li><span>本院动态</span></li>
			</ul>
		</li>
		<li><span>文物鉴赏</span></li>
	</ul>
</div>
<!-- E-菜单 -->

<!-- S-滚动大图展示 -->
<div class="swiper-container" id="imgScrollShow">
  <div class="swiper-wrapper">
    <div class="swiper-slide">
    	<a href=""><img src="images/banner1.jpg"/></a>
    </div>
    <div class="swiper-slide">
    	<a href=""><img src="images/banner.jpg"/></a>
    </div>
    <div class="swiper-slide">
    	<a href=""><img src="images/banner1.jpg"/></a>
    </div>   
  </div>
  
  <!-- 如果需要分页器 -->
  <div class="swiper-pagination"></div>
  
  <!-- 如果需要导航按钮 -->
  <div class="swiper-button-prev"></div>
  <div class="swiper-button-next"></div>
    
</div>
<!-- E-滚动大图展示 -->



<!-- S-内容 -->
<div class="myContent">

		<!-- S-本院介绍，新闻动态 -->
		<div data-am-widget="tabs" class="am-tabs am-tabs-d2 widthPrec65 float-left">
			<ul class="am-tabs-nav am-cf" style="width: 35%!important;">
				<li class="am-active float-left"><a href="[info-museum]">本院介绍</a></li>
				<li class="loat-left"><a href="[new-list]">新闻动态</a></li>
			</ul>
			<div class="am-tabs-bd">
				<!-- S-本院介绍 -->
				<div info-museum class="am-tab-panel am-active height300">
					<ul>
						<li><a>本院介绍1</a></li>
						<li><a>本院介绍2</a></li>
						<li><a>本院介绍3</a></li>
						<li><a>本院介绍4</a></li>
						<li><a>本院介绍5</a></li>
						<li><a>本院介绍7</a></li>
						<li><a>本院介绍8</a></li>
						<li><a>本院介绍9</a></li>
						<li><a>本院介绍10</a></li>
					</ul>
				</div>
				<!-- E-本院介绍 -->

				<!-- S-新闻动态 -->
				<div new-list class="am-tab-panel height300">
					<ul>
						<li><a>新闻动态1</a><span class="float-right color-red">2017-01-28</span></li>
						<li><a>新闻动态2</a></li>
						<li><a>新闻动态3</a></li>
						<li><a>新闻动态4</a></li>
						<li><a>新闻动态5</a></li>
						<li><a>新闻动态6</a></li>
						<li><a>新闻动态7</a></li>
						<li><a>新闻动态8</a></li>
						<li><a>新闻动态9</a></li>
						<li><a>新闻动态10</a></li>
					</ul>
				</div>
				<!-- E-新闻动态 -->
			</div>
		</div>
		<!-- E-本院介绍，新闻动态 -->

		<!-- S-公告 -->
		<div class="am-panel am-panel-default widthPrec30 float-right"style="margin: 10px 10px">
			<div class="am-panel-hd" style="text-align: center;">公告</div>
			<div class="am-panel-bd height300">
			</div>
		</div>
		<!-- E-公告 -->
		
		<!-- S-常设陈列 -->
		<div class="clear-float bgClolor-white am-panel am-panel-default" style="margin:10px 10px;height: 478px;position: relative;">
			<div ><a class="more-info">>>更多</a></div>
			<div class="am-panel-bd" style="height: 100%">
				<div class="float-left" style="display:block;border-right:2px #A92121 solid;width:70px;height:100%;position:relative">
					<div class="left-title"style="width:1px">常设陈列</div>
					<i class="triangleShape"style="border-right-color: #A92121;"></i>
				</div>
				<!-- S-陈列列表 -->
				<div class="float-left widthPrec90">
				
					<div class="display-list-item">
						<a href="#"><img alt="" src="http://www.hebeimuseum.org/upload/images/2014/6/619212265.jpg">
						<p>《石器时代的河北》</p></a>
					</div>
				
					<div class="display-list-item">
						<a href="#"><img alt="" src="http://www.hebeimuseum.org/upload/images/2014/6/619212265.jpg">
						<p>《石器时代的河北》</p></a>
					</div>
				
					<div class="display-list-item">
						<a href="#"><img alt="" src="http://www.hebeimuseum.org/upload/images/2014/6/619212265.jpg">
						<p>《石器时代的河北》</p></a>
					</div>
				
					<div class="display-list-item">
						<a href="#"><img alt="" src="http://www.hebeimuseum.org/upload/images/2014/6/619212265.jpg">
						<p>《石器时代的河北》</p></a>
					</div>
				
					<div class="display-list-item">
						<a href="#"><img alt="" src="http://www.hebeimuseum.org/upload/images/2014/6/619212265.jpg">
						<p>《石器时代的河北》</p></a>
					</div>
				
					<div class="display-list-item">
						<a href="#"><img alt="" src="http://www.hebeimuseum.org/upload/images/2014/6/619212265.jpg">
						<p>《石器时代的河北》</p></a>
					</div>
				</div>
				<!-- E-陈列列表 -->
			</div>
		</div>
		<!-- E-常设陈列 -->



		<!-- S-馆藏集珍 -->
		<div data-am-widget="tabs"
			class="am-tabs am-tabs-d2 widthPrec65 float-left">
			<ul class="am-tabs-nav am-cf" style="width: 13% !important;">
				<li class="am-active float-left"><a href="[museum-valuable]">馆藏集珍</a></li>
			</ul>
			<div class="am-tabs-bd">
				<div museum-valuable class="am-tab-panel am-active height300">

				</div>
			</div>
		</div>
		<!-- E-馆藏集珍-->


		<!-- S-视频鉴赏 -->
		<div class="am-panel am-panel-default widthPrec30 float-right"style="margin: 10px 10px">
			<div class="am-panel-hd" style="text-align: center;"><a href="#">视频鉴赏</a></div>
			<div class="am-panel-bd height240">
				<video width="100%" height="100%" controls="controls" autoplay="autoplay">
				  <source src="/document/video/heheheh.mp4" type="video/mp4" />
				</video>
			</div>
		</div>
		<!-- E-视频鉴赏 -->
		
		<!-- S-观众留言 -->
		
		<div class="am-panel am-panel-default widthPrec30 float-right"style="margin: 10px 10px">
			<div class="am-panel-bd">
				<a class="am-btn am-btn-danger" href="http://www.bing.com" target="_blank" style="width: 100%">观众留言</a>
			</div>
		</div>
		
		<!-- E-观众留言 -->


		<div class="clear-float"></div>
	</div>
<!-- E-内容 -->


<!--S-尾部-->
<div class="refooter pb20 pt20">
    <span class="display"><a href="">首页</a>|<a href="">文化视频</a>|<a href="">联系方式</a></span>
</div>
<div class="footerp text-center pt15 pb15">
    <p>ICP备案编号：京ICP备xxxxxx号&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;备案编号：京公网安备xxxxxx&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;版权所有：xxxxxx&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</p>
</div>
<!--E-尾部-->

<script>
/* S-菜单 */
$(function(){
 	$('.myMenu>ul>li').mouseover(function(){
		$(this).find('.supMenu').show();
	});
	$('.myMenu>ul>li').mouseleave(function(){
		$(this).find('.supMenu').hide();
	}); 
});
/* E-菜单 */

/*S-滚动大图*/
$(function(){
	var imgScrollShow = new Swiper('#imgScrollShow',{
		autoplay: 5000,//可选选项，自动滑动
		
		// 如果需要分页器
	    pagination: '.swiper-pagination',
	    paginationClickable :true,
	    
	    // 如果需要前进后退按钮
	    nextButton: '.swiper-button-next',
	    prevButton: '.swiper-button-prev',
	    
	});
});
/*E-滚动大图*/

</script>

</body>
</html>