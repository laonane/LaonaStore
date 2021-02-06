new Vue({
    el: "#root",
    data: {
        brandList: [],
        brand: {
            name: "",
            firstChar: ''
        },
        page: 1,    /*当前页码*/
        pageSize: 10,   /*一页有多少条数据*/
        total: 100,     /*总记录数*/
        maxPage: 10,     /*最大页码*/
        selectedIds: [],     /* 选中id */
        searchBrand: {          /*搜索对象*/
            name: "",
            firstChar: ''
        },
    },
    methods: {
        /**
         * 分页处理
         * @param page
         */
        pageHandler: function (page) {
            this.page = page;
            let _this = this;
            axios.post("/brand/getBrandPageList.do?page=" + _this.page + "&pageSize=" + _this.pageSize,
                _this.searchBrand).then(function (res) {
                _this.brandList = res.data.rows;
                _this.total = res.data.total;
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 保存品牌信息
         */
        brandSave: function () {
            let url = "/brand/add.do";
            // 如果有 id 属性，就说明是编辑页面，进行更新操作
            if (this.brand.id != null) {
                url = "/brand/update.do"
            }
            let _this = this;
            axios.post(url, this.brand).then(function (res) {
                console.log(res);
                if (res.data.code === 1) {
                    // alert("保存成功");
                    _this.pageHandler(_this.page);
                } else {
                    alert("保存失败");
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 根据id查询商品分类
         * @param id
         */
        findById: function (id) {
            let _this = this;
            axios.get("/brand/getBrandById.do", {
                params: {
                    id: id
                }
            }).then(function (res) {
                // 数据回显
                _this.brand = res.data.data;
            }).catch(function (err) {
                console.log(err);
            })
        },
        /**
         * 选中列表
         * @param $event
         * @param id
         */
        deleteSelect: function ($event, id) {
            // 获取选中状态
            let checked = event.target.checked
            if (checked) {
                this.selectedIds.push(id);
            } else {
                // 找到 id 所在位置
                let index = this.selectedIds.indexOf(id);
                // 删除集合中对应id
                this.selectedIds.splice(index, 1);
            }
        },
        /**
         * 删除按钮点击事件
         */
        deleteBrands: function () {
            let _this = this;
            axios.post("/brand/delete.do", Qs.stringify({ids: this.selectedIds}, {indices: false})).then(function (res) {
                // 数据回显
                // _this.pageHandler(1);
                _this.selectedIds = [];
                window.location.reload();
            }).catch(function (err) {
                console.log(err);
            })
        }
    },
    created: function () {
        this.pageHandler(1);
    }

});