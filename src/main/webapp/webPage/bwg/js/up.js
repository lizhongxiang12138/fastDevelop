///**
// * Created by Administrator on 2016/3/23.
// */
function initMenu() {
    //$('#menu ul').hide();
    //$('#menu ul:first').show();
    $('.indexnav li a').click(
        function() {
            var checkElement = $(this).next();
            if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                return false;
            }
            if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('.indexnav-ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
                return false;
            }
        }
    );
}
$(function() {initMenu();});
