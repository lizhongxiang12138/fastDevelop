/**
 * Created by Administrator on 2016/3/21.
 */
$(function(){
    $(".itabtitle a").each(function(j){
        var num=j+1;
        $(this).click(function(){
            $(".itabtitle a").attr("class","");
            $(this).attr("class","hovera");
            for(var i=1;i<=2;i++){
                $("#tab_kc"+i).css("display","none");
            }
            $("#tab_kc"+num).css("display","block");
        });
    });
})