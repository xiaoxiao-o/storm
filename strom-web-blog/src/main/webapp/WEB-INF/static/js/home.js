layui.use(['jquery', 'util','carousel'], function (carousel) {
    var $ = layui.jquery
        , util = layui.util
        , device = layui.device();

    var serverTime = new Date();
    var startTime = new Date(Number($(".bloginfo-runtime").text().trim()));  //开始时间  js月份从0开始;

    $(function () {
        playAnnouncement();
        playRunTimeStr(startTime, serverTime, '.bloginfo-runtime');
    });

    function showRunTime(startTime, serverTime, selecter) {
        var date3 = serverTime.getTime() - startTime.getTime()  //时间差的毫秒数

        //计算出相差天数
        var days = Math.floor(date3 / (24 * 3600 * 1000))

        //计算出小时数
        var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
        var hours = Math.floor(leave1 / (3600 * 1000))
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
        var minutes = Math.floor(leave2 / (60 * 1000))
        //计算相差秒数
        var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
        var seconds = Math.round(leave3 / 1000);

        var str = '博客已运行<span style="margin-left:4px;">' + util.digit(days, 2) + '天' + util.digit(hours, 2) + '时' + util.digit(minutes, 2) + '分' + util.digit(seconds, 2) + '秒</span>';
        $(selecter).html(str);
    }

    function playRunTimeStr(startTime, serverTime, selecter) {
        showRunTime(startTime, serverTime, selecter);
        setInterval(function () {
            showRunTime(startTime, serverTime, selecter);
            serverTime = new Date(serverTime.getTime() + 1000 * 1);
        }, 1000);
    }

    //播放公告
    function playAnnouncement() {
        var index = 0;
        var $announcement = $('.home-tips-container>span');
        //自动轮换
        setInterval(function () {
            index++;    //下标更新
            if (index >= $announcement.length) {
                index = 0;
            }
            $announcement.eq(index).stop(true, true).fadeIn().siblings('span').fadeOut();  //下标对应的图片显示，同辈元素隐藏
        }, 3000);
    }
    
    //首页轮播
    var carousel = layui.carousel;
    //设定各种参数
	var ins3 = carousel.render({
	    elem: '#carousel',
	    width: '100%',
	    height: '307'
	});
    
    
    
    
});