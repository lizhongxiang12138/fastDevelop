/**
 * Created by Administrator on 2016/3/22.
 */
/**
 * Created by Administrator on 15-7-1.
 */
$(document).ready( function(){
    var buttons = {
        previous:$('#lofslidecontent45 .lof-next'),
        next:$('#lofslidecontent45 .lof-previous')
    };

    $obj = $('#lofslidecontent45').lofJSidernews({
        interval : 4000,
        direction		: 'opacitys',
        easing			: 'easeInOutExpo',
        duration		: 1200,
        auto		 	: false,
        maxItemDisplay  : 3,
        navPosition     : 'horizontal', // horizontal , vertical 可设置水平和垂直样式
        navigatorHeight : 55,
        navigatorWidth  : 140,
        mainWidth:1920,
        buttons			: buttons
    });

});