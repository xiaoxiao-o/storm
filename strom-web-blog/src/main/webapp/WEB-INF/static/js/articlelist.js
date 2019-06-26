layui.use(['laypage','flow'], function () {

    var $ = layui.jquery;
    var laypage = layui.laypage;

    loadArticleList(1,8);
    function loadArticleList(page,limit){
        $.ajax({
            url : 'article/page',           //数据接口
            data : {page:page,limit:limit},
            success : function(res){
                //数据的回显
                var articleAll = '';
                $.each(res.records,function(i,d){
                    var article = [
                        '<div class="article shadow clearfix">',
                        '<div class="article-left">',
                        '<img src="'+d.cover+'" alt="'+d.title+'" />',
                        '</div>',
                        '<div class="article-right">',
                        '<div class="article-title">',
                        '<a href="article/detail/'+d.id+'">'+d.title+'</a>',
                        '</div>',
                        '<div class="article-abstract">'+d.summary+'</div>',
                        '<div class="article-footer">',
                        '<span class="layui-hide-xs"><i class="fa fa-tag" aria-hidden="true"></i>&nbsp;<a style="color:#009688" href="article/subject/'+d.subject+'">'+d.subjectName+'</a></span>',
                        '<span><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;'+new Date(Number(d.createTime)).toLocaleString().replace(new RegExp("/","g"),'-').substr(0,9)+'</span>',
                        '<span class="article-viewinfo">'+d.readCount+'阅读</span>',
                        '<span class="article-viewinfo">'+d.commentCount+'评论</span>',
                        '<span class="article-viewinfo">'+d.praiseCount+'点赞</span>',
                        '<a class="read layui-btn layui-btn-xs layui-btn-normal layui-hide-xs" href="article/detail/'+d.id+'" title="阅读全文：'+d.title+'">阅读全文</a>',
                        '</div>',
                        '</div>',
                        d.recom==1?'<div class="flag flag-left">推荐</div>':'',
                        '</div>'
                    ].join(' ');
                    articleAll += article;
                });
                $("#article-page-list").html(articleAll);
                //执行一个laypage实例
                laypage.render({
                    elem : 'page',
                    count : res.total,
                    limit : res.size,
                    curr : res.current,
                    jump:function(obj,first){
                        if(!first) {
                            var curr = obj.curr;
                            //回调该展示数据的方法,数据展示
                            loadArticleList(obj.curr,obj.limit);
                        }
                    }
                });
            }
        });
    }

    $(".load-more i").click(function(){
        if($(this).attr("class").indexOf("down")>0){
            $(this).removeClass("fa-angle-double-down").addClass("fa-angle-double-up").attr("title","收起更多");
            $(this).parent().parent().prev().find(".subject-list").removeClass("subject-list-less").addClass("subject-list-more");
        }else{
            $(this).removeClass("fa-angle-double-up").addClass("fa-angle-double-down").attr("title","加载更多");
            $(this).parent().parent().prev().find(".subject-list").removeClass("subject-list-more").addClass("subject-list-less");
        }
    });

    var colorArr=["#2ec770","#c62e2e","#c4c62e","#3bf51c","#1c49f5","#f51ce1"],i=0;
    $(".a-tag").each(function(){
        $(this).css("cssText","background-color:"+colorArr[i++]+" !important")
        if(i==6) i=0;
    });

});