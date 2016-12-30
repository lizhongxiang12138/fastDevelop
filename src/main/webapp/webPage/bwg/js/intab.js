/**
 * Created by Administrator on 2016/3/21.
 */
$(function(){
    $('.incontent ul').width(792*$('.incontent li').length+'px');
    $(".intaba a").mouseover(function(){
        $(this).addClass('ona').siblings().removeClass('ona');
        var index = $(this).index();
        number = index;
        var distance = -792*index;
        $('.incontent ul').stop().animate({
            left:distance
        });
    });

    var auto = 1;  //等于1则自动切换，其他任意数字则不自动切换
    if(auto ==1){
        var number = 0;
        var maxNumber = $('.intaba a').length;
        function autotab(){
            number++;
            number == maxNumber? number = 0 : number;
            $('.intaba a:eq('+number+')').addClass('ona').siblings().removeClass('ona');
            var distance = -792*number;
            $('.incontent ul').stop().animate({
                left:distance
            });
        }
        var tabChange = setInterval(autotab,3000);
        //鼠标悬停暂停切换
        $('.intab').mouseover(function(){
            clearInterval(tabChange);
        });
        $('.intab').mouseout(function(){
            tabChange = setInterval(autotab,3000);
        });
    }
});