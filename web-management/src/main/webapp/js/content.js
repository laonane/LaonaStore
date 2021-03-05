new Vue({
    el: "#root",
    data: {
        page: 1,  //显示的是哪一页
        pageSize: 10, //每一页显示的数据条数
        total: 0, //记录总数
        maxPage: 0,
        searchEntity: {},
        categoryList: [],   /*分类列表*/
        categoryNameList: [],   /*分类名称列表*/
        selectIds: [], //记录选择了哪些记录的id
        status: {       /*是否有效*/
            1: "有效",
            0: "无效"
        },
        contentEntity: {},      /*广告实体*/
        categorySelectedId: -1,     /*选中分类id*/
        adImageUrl: '',             /*广告图片地址*/
    },
    methods: {
        /**
         * 删除广告点击事件
         */
        deleteContent: function () {
            var _this = this;
            //使用qs插件 处理数组
            axios.post('/content/delete.do', Qs.stringify({ids: _this.selectIds}, {indices: false}))
                .then(function (res) {
                    _this.pageHandler(_this.page);
                    _this.selectIds = [];
                }).catch(function (err) {
                alert(err.message);
            })
        },
        /**
         * checkbox勾选事件处理
         * @param event checkbox 点击事件
         * @param id 广告信息id
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
         * 查询广告信息
         * @param id 广告信息id
         */
        findOne: function (id) {
            let _this = this;
            axios.post("/content/findOne.do?id=" + id)
                .then(function (res) {
                    res = res.data;
                    if (res.code === 1) {//刷新页面
                        _this.contentEntity = res.data;
                        // 设置图片信息
                        _this.adImageUrl = res.data.pic;
                        // 分类id
                        _this.categorySelectedId = res.data.categoryId;
                    }
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        /**
         * 保存
         */
        saveContent: function () {
            let _this = this;
            // 设置图片信息
            _this.contentEntity.pic = _this.adImageUrl;
            // 分类id
            _this.contentEntity.categoryId = _this.categorySelectedId;
            let url = "";
            if (_this.contentEntity.id != null) {
                url = "/content/update.do";
            } else {
                url = "/content/add.do";
            }
            axios.post(url, _this.contentEntity)
                .then(function (res) {
                    //取服务端响应的结果
                    _this.pageHandler(1);
                    _this.contentEntity = {};
                }).catch(function (err) {
                console.log(err);
            })
        },
        /**
         * 上传文件
         */
        uploadFile: function () {
            let formData = new FormData();
            formData.append('file', file.files[0])
            const instance = axios.create({
                withCredentials: true
            });
            let _this = this;
            instance.post("/upload/uploadFile.do", formData).then(function (res) {
                res = res.data;
                _this.adImageUrl = res.data;
            }).catch(function (reason) {
                console.log(reason);
            });
        },
        /**
         * 加载广告分类信息
         */
        loadCategoryContent: function () {
            let _this = this;
            axios.post("/contentCategory/findAll.do")
                .then(function (res) {
                    res = res.data;
                    //取服务端响应的结果
                    _this.categoryNameList = res.data;
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        /**
         * 分页显示
         * @param page 当前页码
         */
        pageHandler: function (page) {
            let _this = this;
            this.page = page;
            axios.post("/content/search.do?page=" + page + "&pageSize=" + this.pageSize, this.searchEntity)
                .then(function (res) {
                    //取服务端响应的结果
                    _this.categoryList = res.data.rows;
                    _this.total = res.data.total;
                }).catch(function (reason) {
                console.log(reason);
            })
        },
        getCategorySelected: function () {
            if (event.target.checked) {
                // 向数组中添加元素
                this.selectIds.push(id);
            } else {
                // 从数组中移除
                let idx = this.selectIds.indexOf(id);
                this.selectIds.splice(idx, 1);
            }
        }
    },
    created: function () {
        this.loadCategoryContent();
        this.pageHandler(1);
    },
});