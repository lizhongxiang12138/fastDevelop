/**
 * Created by Administrator on 2016/3/21.
 */
;(function (window, $, undefined) {
    /*
     * tab切换插件
     * 用例：$('*').createTab();
     */
    $.fn.createTab = function (opt) {
        var def = {
            activeEvt: 'mouseover',
            activeCls: 'cur'
        }
        $.extend(def, opt);
        this.each(function () {
            var $this = $(this);
            var timer;
            $this.find('.inonetabula a').mouseover(def.activeEvt,function(){
                var index = $(this).index(),
                    that = $(this);
                timer = setTimeout(function(){
                    that.addClass('cur').siblings().removeClass('cur');
                    $this.find('div.inontawdh').animate({marginLeft:-383*index},'slow');
                },300);
            }).mouseout(function(){
                clearTimeout( timer );
            })
        });
    }

})(window, jQuery);
$(function(){
    $(".inonetab").createTab()
})
