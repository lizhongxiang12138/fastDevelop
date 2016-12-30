/**
 * Created by Administrator on 2016/3/22.
 */
$(function(){
    $(".sharea3").hover(function(){
        $(".swx").toggle();
    });
    $(".sharea5").hover(function(){
        $(this).css("z-index","10");
        $(".sphone").toggle();
    });
    $(".sharea6").click(function(){
        $("html, body").animate({
            "scroll-top":0
        },"fast");
    })
})