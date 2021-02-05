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
        maxPage: 10     /*最大页码*/
    },
    methods: {
        pageHandler: function (page) {
            this.page = page;
            let _this = this;
            axios.get("/brand/getBrandPageList.do", {
                params: {
                    page: _this.page,
                    pageSize: _this.pageSize
                }
            }).then(function (res) {
                _this.brandList = res.data.rows;
                _this.total = res.data.total;
                console.log('_this.brandList:', _this.brandList);
                console.log('_this.total:', _this.total);
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
                    alert("保存成功");
                    _this.pageHandler(_this.page);
                } else {
                    alert("保存失败");
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
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
        }
    },
    created: function () {
        this.pageHandler(1);
    }

});