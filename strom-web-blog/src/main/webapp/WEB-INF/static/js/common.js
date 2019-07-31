if (window.layui) {
    layui.use(['element', 'layer', 'form', 'util', 'flow', 'layedit'], function () {
        var util = layui.util;
        var $ = layui.jquery;

        util.fixbar({});

        $(function () {
            //滑动显示
            if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
                window.sr = new ScrollReveal({ reset: false });
                sr.reveal('.sr-left', {origin: 'left', scale: 1, opacity: .1, duration: 1200});
                sr.reveal('.sr-bottom', {scale: 1, opacity: .1, distance: '60px', duration: 1000});
                sr.reveal('.sr-listshow', {distance: '30px', duration: 1000, scale: 1, opacity: .1});
                sr.reveal('.sr-rightmodule', {origin: 'top', distance: '60px', duration: 1000, scale: 1, opacity: .1});
            };

        });


        $('.com.whyxs.controller.blog-navicon').click(function () {
            var sear = new RegExp('layui-hide');
            if (sear.test($('.com.whyxs.controller.blog-nav-left').attr('class'))) {leftIn();
            } else {leftOut();}
        });

        $('.com.whyxs.controller.blog-mask').click(function () {leftOut();});

        $('.com.whyxs.controller.blog-body,.com.whyxs.controller.blog-footer').click(function () {categoryOut();});

        $('.category-toggle').click(function (e) {e.stopPropagation();categroyIn();});

        $('.article-category').click(function () {categoryOut();});

        $('.article-category > a').click(function (e) { e.stopPropagation();});

        function categroyIn() {
            $('.category-toggle').addClass('layui-hide');
            $('.article-category').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');

            $('.article-category').removeClass('categoryOut');
            $('.article-category').addClass('categoryIn');
            $('.article-category').addClass('layui-show');
        }

        function categoryOut() {
            $('.article-category').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $('.article-category').removeClass('layui-show');
                $('.category-toggle').removeClass('layui-hide');
            });

            $('.article-category').removeClass('categoryIn');
            $('.article-category').addClass('categoryOut');
        }



        function leftIn() {
            $('.com.whyxs.controller.blog-mask').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
            $('.com.whyxs.controller.blog-nav-left').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');

            $('.com.whyxs.controller.blog-mask').removeClass('maskOut');
            $('.com.whyxs.controller.blog-mask').addClass('maskIn');
            $('.com.whyxs.controller.blog-mask').removeClass('layui-hide');
            $('.com.whyxs.controller.blog-mask').addClass('layui-show');

            $('.com.whyxs.controller.blog-nav-left').removeClass('leftOut');
            $('.com.whyxs.controller.blog-nav-left').addClass('leftIn');
            $('.com.whyxs.controller.blog-nav-left').removeClass('layui-hide');
            $('.com.whyxs.controller.blog-nav-left').addClass('layui-show');
        }

        function leftOut() {
            $('.com.whyxs.controller.blog-mask').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $('.com.whyxs.controller.blog-mask').addClass('layui-hide');
            });
            $('.com.whyxs.controller.blog-nav-left').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $('.com.whyxs.controller.blog-nav-left').addClass('layui-hide');
            });

            $('.com.whyxs.controller.blog-mask').removeClass('maskIn');
            $('.com.whyxs.controller.blog-mask').addClass('maskOut');
            $('.com.whyxs.controller.blog-mask').removeClass('layui-show');

            $('.com.whyxs.controller.blog-nav-left').removeClass('leftIn');
            $('.com.whyxs.controller.blog-nav-left').addClass('leftOut');
            $('.com.whyxs.controller.blog-nav-left').removeClass('layui-show');
        }

    });
}




//时间格式化
Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
    str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/M/g, (this.getMonth() + 1));

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}