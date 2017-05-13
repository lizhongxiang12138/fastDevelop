/**
 * Created by Administrator on 2016/3/21.
 */
/**
 * Created by Administrator on 2016/3/21.
 */
$(function(){
    $('.incontenta ul').width(383*$('.incontenta li').length+'px');
    $(".intabaa a").mouseover(function(){
        $(this).addClass('onb').siblings().removeClass('onb');
        var index = $(this).index();
        number = index;
        var distance = -383*index;
        $('.incontenta ul').stop().animate({
            left:distance
        });
    });

    var auto = 1;  //等于1则自动切换，其他任意数字则不自动切换
    if(auto ==1){
        var number = 0;
        var maxNumber = $('.intabaa a').length;
        function autotab(){
            number++;
            number == maxNumber? number = 0 : number;
            $('.intabaa a:eq('+number+')').addClass('onb').siblings().removeClass('onb');
            var distance = -383*number;
            $('.incontenta ul').stop().animate({
                left:distance
            });
        }
        var tabChange = setInterval(autotab,3000);
        //鼠标悬停暂停切换
        $('.insrtab').mouseover(function(){
            clearInterval(tabChange);
        });
        $('.insrtab').mouseout(function(){
            tabChange = setInterval(autotab,3000);
        });
    }
});