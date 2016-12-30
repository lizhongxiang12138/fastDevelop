/**
 * Created by Administrator on 2016/3/18.
 */
$(function(){
    $(".navul li").hover(function(){
        $(this).find(".subNav").show();
    },function(){
        $(this).find(".subNav").hide();
    });

    $(".navul li").eq(4).find(".navPadding").css({ "width": 480, "display": "inline-block" });
    $(".navul li").eq(6).find(".navPadding").css({ "width": 520, "display": "inline-block" });
    $(".navul li").eq(7).find(".navPadding").css({ "width": 930, "display": "inline-block" });
})