new Vue({
    el: "#app",
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 0,
        searchEntity: {},        /*搜索实体*/
        goodsList: [],           /*商品列表*/
        categoryList: [],        /*商品分类列表*/
        status: {                   /*商品状态*/
            0: "未申请",
            1: "申请中",
            2: "审核通过",
            3: "已驳回"
        },
        selectIds:[],                /*记录选择了哪些记录的id*/
    },
    methods: {
        /**
         * 删除商品按钮事件点击
         */
        deleteGoods: function () {
            let _this = this;
            //使用qs插件 处理数组
            axios.post('/goods/deleteByIds.do', Qs.stringify({ids: _this.selectIds}, {indices: false}))
                .then(function (response) {
                    _this.pageHandler(_this.page);
                    _this.selectIds = [];
                    alert("删除成功");
                }).catch(function (reason) {
                alert(reason.message);
            })
        },
        /**
         * 删除商品 checkbox 监听
         * @param event checkbox点击
         * @param id 商品id
         */
        deleteSelection: function (event, id) {
            console.log(id);
            // 复选框选中
            if (event.target.checked) {
                // 向数组中添加元素
                this.selectIds.push(id);
            } else {
                // 从数组中移除
                let idx = this.selectIds.indexOf(id);
                this.selectIds.splice(idx, 1);
            }
            console.log(this.deletedIds);
        },
        pageHandler: function (page) {
            let _this = this;
            this.page = page;
            axios.post("/goods/search.do?page=" + page + "&pageSize=" + this.pageSize, this.searchEntity)
                .then(function (res) {
                    //取服务端响应的结果
                    let pageList = JSON.parse(JSON.stringify(res.data.rows));
                    // 处理分类信息
                    for (let i = 0; i < pageList.length; i++) {
                        let id1 = pageList[i]["category1Id"];
                        let id2 = pageList[i]["category2Id"];
                        let id3 = pageList[i]["category3Id"];
                        pageList[i]["category1Id"] = _this.categoryList[id1];
                        pageList[i]["category2Id"] = _this.categoryList[id2];
                        pageList[i]["category3Id"] = _this.categoryList[id3];
                    }
                    _this.goodsList = pageList;
                    _this.total = res.data.total;
                }).catch(function (err) {
                console.log(err);
            })
        },
        /**
         * 查询所有分类信息
         */
        loadAllCategoryList: function () {
            let _this = this;
            //发送请求
            axios.post("/itemCate/getAllCategoryList.do")
                .then(function (res) {
                    res = res.data;
                    let list = res.data;
                    if (res.code === 1) {
                        // 处理分类级别信息
                        for (let i = 0; i < list.length; i++) {
                            let item = list[i];
                            _this.categoryList[item.id] = item.name;
                        }

                    } else {
                        _this.categoryList = [];
                    }
                    // 加载分页数据(因为需要重写分类信息，需要等待获取了所有分类信息之后再进行分页处理)
                    _this.pageHandler(1);
                }).catch(function (err) {
                alert(err.data);
            });
        },
    },
    created: function () {
        // 加载所有分类信息
        this.loadAllCategoryList();
    }
});