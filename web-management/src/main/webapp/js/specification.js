new Vue({
    el: "#root",
    data: {
        specList: [],    /*回显规格数据*/
        page: 1,    /*当前页码*/
        pageSize: 6,   /*一页有多少条数据*/
        total: 100,     /*总记录数*/
        maxPage: 5,     /*最大页码*/
        searchSpec: {       /*查询规格的数据*/
            specName: ""
        },
        selectedSpecIds: [],     /*选中id*/
        specEntity: {           /*规格实体*/
            spec: {},
            specOption: []
        }
    }, methods: {
        /**
         * 分页处理
         * @param page
         */
        pageHandler: function (page) {
            this.page = page;
            let _this = this;
            axios.post("/spec/getSpecPageList.do?page=" + _this.page + "&pageSize=" + _this.pageSize,
                _this.searchSpec).then(function (res) {
                _this.specList = res.data.rows;
                _this.total = res.data.total;
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 根据id查询商品规格
         * @param id
         */
        findById: function (id) {
            let _this = this;
            axios.get("/spec/getSpecById.do", {
                params: {
                    id: id
                }
            }).then(function (res) {
                // 数据回显
                _this.specEntity.spec = res.data.data.spec;
                _this.specEntity.specOption = res.data.data.specOption;
            }).catch(function (err) {
                console.log(err);
            })
        },
        /**
         * 添加规格
         */
        saveSpecEntity: function () {
            let _this = this;
            let url = "/spec/saveSpecEntity.do";
            // 更新操作地址
            if (_this.specEntity.spec.id !== null) {
                url = "/spec/updateSpecEntity.do";
            }
            axios.post(url, _this.specEntity).then(function (res) {
                if (res.data.code === 1) {
                    _this.specEntity.spec = {};
                    _this.specEntity.specOption = [];
                    // 数据回显
                    _this.pageHandler(1);
                } else {
                    alert(res.data.message);
                }
            }).catch(function (err) {
                console.log(err);
            });
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
                this.selectedSpecIds.push(id);
            } else {
                // 找到 id 所在位置
                let index = this.selectedSpecIds.indexOf(id);
                // 删除集合中对应id
                this.selectedSpecIds.splice(index, 1);
            }
        },
        /**
         * 删除按钮事件点击
         */
        deleteByIds: function () {
            let _this = this;
            axios.post("/spec/delete.do", Qs.stringify({ids: this.selectedSpecIds}, {indices: false})).then(function (res) {
                // 数据回显
                _this.selectedSpecIds = [];
                window.location.reload();
            }).catch(function (err) {
                console.log(err);
            })
        },
        /**
         * 添加一行规格操作
         */
        addRow: function () {
            this.specEntity.specOption.push({});
        },
        /**
         * 删除一行规格操作
         * @param index
         */
        removeRow: function (index) {
            this.specEntity.specOption.splice(index, 1);
        }
    },
    created: function () {
        this.pageHandler(1);
    }
});