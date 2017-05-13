/**
 * Created by Administrator on 2016/3/21.
 */
$(function(){

    $('.slide').hover(function(){
        $(this).children('.abh3').stop(true,true).delay(100).animate({'top':0,opacity:0.8},300);
    },function(){
        $(this).children('.abh3').stop(true,true).animate({'top':-166,opacity:0},200);
    })

})