//添加discover-question 项
var pagecount=0;

function clear() {
    $('.discover-question').empty();
}
function getQuestin(pageNo,pageSize){
    $.get("/ajax/getQuestion",{'pageNo':pageNo,'pageSize':pageSize},function(data){
        if(data.code=='ok'){
            var $discover_question=$('.discover-question');
            pagecount=data.pageCount;
            $(data.ques).each(function(){
                $discover_question.append("<div class=\"media question-item\">" +
                    "                <div class=\"media-left\">" +
                    "                    <a href=\"#\">" +
                    "                        <img class=\"media-object user-image\" src=\""+ this.picurl+"\" alt=\"没有这个图\">" +
                    "                    </a>" +
                    "                </div>" +
                    "                <div class=\"media-body\">" +
                    "                    <h4 class=\"media-heading\">"+this.question.qtitle+"</h4>" +
                    "                    "+this.question.qcontent+"<br>" +
                    "                    <span class=\"des\">点赞·"+this.question.qlike+" 踩·"+this.question.qshit+" 浏览·"+this.question.qread+" 发布·"+this.question.qcreate_at+"</span>" +
                    "                </div>" +
                    "                       </div>");
            });
        }
    },'json');
}
