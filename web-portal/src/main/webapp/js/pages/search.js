new Vue({
    el: "#root",
    data: {
        searchMap: {                         /*搜索map*/
            'keywords': '',              //搜索关键字
            'category': '',              //分类
            'brand': '',                 //品牌
            'spec': {},                  //规格
            'price': '',                 //价格
            'pageNo': 1,                 //当前页
            'pageSize': 40,              //每页展示多少条数据
            'sort': '',                  //排序
            'sortField': ''              //排序的字段
        },
        resultMap:{                         /*搜索结果集*/
            rows:[],
            total:0,
            totalPages:0
        },
    },
    methods: {
        getQueryString: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return (decodeURI(r[2]));
            }
            return null;
        },
        search: function () {
            this.searchMap.pageNo = parseInt(this.searchMap.pageNo);//转换为数字
            let _this = this;
            axios.post("/itemsearch/search.do", this.searchMap)
                .then(function (response) {
                    _this.resultMap = response.data;
                }).catch(function (reason) {
                console.log(reason.data);
            });
        },
    },
    watch: { //监听属性的变化

    },
    created: function () {//创建对象时调用

    },
    mounted: function () {//页面加载完
        // 获取参数
        let sc = this.getQueryString("sc");
        this.searchMap.keywords = sc;
        // 搜索
        this.search();
    }
});