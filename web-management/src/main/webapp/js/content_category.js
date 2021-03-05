new Vue({
    el: "#root",
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 0,
        searchEntity: {},
        categoryList: [],
        selectIds: [], //记录选择了哪些记录的id
        categoryEntity: {},     // 广告分类实体
    },
    methods: {
        /**
         * 删除广告分类信息
         */
        deleteCategory: function () {
            let _this = this;
            //使用qs插件 处理数组
            axios.post('/contentCategory/delete.do', Qs.stringify({ids: _this.selectIds}, {indices: false}))
                .then(function (response) {
                    _this.pageHandler(_this.page);
                    _this.selectIds = [];
                }).catch(function (reason) {
                alert(reason.message);
            })
        },
        /**
         * checkbox 勾选状态
         */
        updateSelection: function (event, id) {
            // 复选框选中
            if (event.target.checked) {
                // 向数组中添加元素
                this.selectIds.push(id);
            } else {
                // 从数组中移除
                let idx = this.selectIds.indexOf(id);
                this.selectIds.splice(idx, 1);
            }
        },
        /**
         * 查找分类
         */
        findOne: function (id) {
            let _this = this;
            axios.post("/contentCategory/findOne.do?id=" + id)
                .then(function (res) {
                    //刷新页面
                    res = res.data;
                    if (res.code === 1) {
                        _this.categoryEntity = res.data;
                    } else {
                        alert(res.message);
                    }
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        /**
         *  保存广告分类信息
         */
        saveCategory: function () {
            let _this = this;
            let url = "";
            if (_this.categoryEntity.id != null) {
                url = "/contentCategory/update.do";
            } else {
                url = "/contentCategory/add.do";
            }
            axios.post(url, _this.categoryEntity)
                .then(function (value) {
                    console.log(value.data);
                    //刷新页面
                    _this.pageHandler(_this.page);
                    _this.categoryEntity = {};
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        /**
         * 分页
         * @param page
         */
        pageHandler: function (page) {
            let _this = this;
            this.page = page;
            axios.post("/contentCategory/search.do?page=" + page + "&pageSize=" + _this.pageSize, _this.searchEntity)
                .then(function (response) {
                    //取服务端响应的结果
                    _this.categoryList = response.data.rows;
                    _this.total = response.data.total;
                }).catch(function (reason) {
                console.log(reason);
            })
        },
    },
    created: function () {
        this.pageHandler(1);
    },
});