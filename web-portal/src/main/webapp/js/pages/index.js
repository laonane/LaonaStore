new Vue({
    el: "#root",
    data: {
        contentList: [],             /*广告列表*/
    },
    methods: {
        /**
         * 通过分类id加载广告数据 -> 轮播图
         * @param categoryId
         */
        loadCategoryDataById: function (categoryId) {
            let _this = this;
            axios.post("/content/findByCategoryId.do?categoryId=" + categoryId)
                .then(function (res) {
                    res = res.data;
                    //取服务端响应的结果
                    _this.contentList[categoryId] = res.data;
                    // 在全局对象上通知
                    Vue.set(_this.contentList, categoryId, _this.contentList[categoryId]);
                }).catch(function (err) {
                console.log(err.data);
            });
        },
    },
    created: function () {
        this.loadCategoryDataById(1);
    },
});


















function resizeFloorView() {
    if ($(document.body).width() > 1200) {
        ele_width = $(document.body).width() - 1200;
        ele_width = ele_width / 2 - 65;
        $("#floor-index").css("left", ele_width + "px");
    } else {
        $("#floor-index").css("left", "6px");
    }
}

$(function () {
    resizeFloorView();
    $(window).resize(function () {
        resizeFloorView();
    });
});

/*生活服务区*/
$(function () {

    $(".close").click(function () {
        $("li.tab-item").animate({
            "top": 0
        });
        $(".life-detail").removeClass("lifenow");

    });
    $("li.tab-item").each(function (index) {
        var liNode = $(this);
        $(this).mouseover(function () {
            $("li.tab-item").animate({
                "top": -36
            }, 150);
            $("div.lifenow").removeClass("lifenow");
            $("div.life-detail").eq(index).addClass("lifenow");
            $(this).addClass("tabin");
        });
    })

});

//影像力换一换
var getyxl = jQuery('#picLBxxl li').eq(0).width();  /*获取li的宽度*/
(function ($) {
    var arartta = window['arartta'] = function (o) {
        return new das(o);
    }
    das = function (o) {
        this.obj = $('#' + o.obj);
        this.bnt = $('#' + o.bnt);
        this.showLi = this.obj.find('li');  /*找到每个li*/
        this.current = 0;
        this.myTimersc = '';
        this.init()
    }
    das.prototype = {
        chgPic: function (n) {
            var _this = this;
            for (var i = 0, l = _this.showLi.length; i < l; i++) {
                _this.showLi.eq(i).find(".pic").find('img').eq(n).attr('src', _this.showLi.eq(i).find(".pic").find('img').eq(n).attr('nsrc'));

                $('#picLBxxl dl:not(:animated)').animate({left: -(n * getyxl) + "px"}, {easing: "easeInOutExpo"}, 1500, function () {
                });
                /*点击dl,使没有动画的dl执行动画，向左移动负值*/
            }
        },
        rotate: function () {
            var _this = this;
            clearInterval(_this.myTimersc);
            _this.bnt.children().css({
                '-webkit-transform': 'rotate(0deg)',
                '-moz-transform': 'rotate(0deg)'
            });
            var tt = 0;
            var getBnts = _this.bnt.children();
            _this.myTimersc = setInterval(function () {
                tt += 10;
                if (tt >= 180) {
                    clearInterval(_this.myTimersc);
                }
                rotateElement(getBnts, tt);
            }, 25)
        },
        init: function () {
            var _this = this;
            this.bnt.bind("click", function () {
                _this.current++;
                if (_this.current > 1) { /*如果当前页面数大于1就回到第1页*/
                    _this.current = 0;
                }
                _this.chgPic(_this.current);
                _this.rotate();

            })
            this.bnt.mouseenter(function () {
                _this.rotate();
            });

        }
    }
})(jQuery)

arartta({
    bnt: 'xxlChg',
    obj: 'picLBxxl'
});

function rotateElement(element, angle) {
    var rotate = 'rotate(' + angle + 'deg)';
    if (element.css('MozTransform') != undefined)
        element.css('MozTransform', rotate);
    else if (element.css('WebkitTransform') != undefined)
        element.css('WebkitTransform', rotate);
}