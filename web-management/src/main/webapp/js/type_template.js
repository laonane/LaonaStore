// 注册 select 组件
Vue.component('v-select', VueSelect.VueSelect);

new Vue({
    el: "#root",
    data: {
        id: -1,
        addName: "",        /*新增姓名*/
        tempList: [],    /*回显规格数据*/
        page: 1,    /*当前页码*/
        pageSize: 6,   /*一页有多少条数据*/
        total: 0,     /*总记录数*/
        maxPage: 5,     /*最大页码*/
        searchTemp: {       /*查询类型模板的数据*/
            name: "",
            specIds: [],
            brandIds: [],
            customAttributeItems: ""
        },
        selectedTempIds: [],              /*选中id*/
        brandsOptions: [],                    /*关联品牌下拉菜单*/
        placeholder: "可以进行多选",                /*品牌下拉列表提示文字*/
        sel_brand_obj: [],                      /*品牌下拉列表选中列表对象*/
        selectBrands: [],                        /*品牌下拉列表选中列表的id*/
        specOptions: [],                        /*规格下拉列表提示文字*/
        selectSpecs: [],                        /*规格下拉列表选中列表id*/
        sel_spec_obj: [],                        /*规格下拉列表选中列表的对象*/
    },
    methods: {
        /**
         * 分页处理
         * @param page
         */
        pageHandler: function (page) {
            this.page = page;
            let _this = this;
            axios.post("/temp/getTempPageList.do?page=" + _this.page + "&pageSize=" + _this.pageSize,
                _this.searchTemp).then(function (res) {
                _this.tempList = res.data.rows;
                _this.total = res.data.total;
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 选中列表
         * @param $event
         * @param id
         */
        selectedItem: function (event, id) {
            // 获取选中状态
            let checked = event.target.checked
            if (checked) {
                this.selectedTempIds.push(id);
            } else {
                // 找到 id 所在位置
                let index = this.selectedTempIds.indexOf(id);
                // 删除集合中对应id
                this.selectedTempIds.splice(index, 1);
            }
            console.log(this.selectedTempIds);
        },
        /**
         * 删除按钮事件点击
         */
        deleteTempByIds: function () {
            let _this = this;
            axios.post("/temp/delete.do", Qs.stringify({ids: this.selectedTempIds}, {indices: false})).then(function (res) {
                // 数据回显
                _this.selectedTempIds = [];
                window.location.reload();
            }).catch(function (err) {
                console.log(err);
            })
        },
        /**
         * 字符串转成 json 对象
         * @param jsonStr
         */
        str2JsonObj: function (jsonStr, key) {
            //[{"name":"联想","id":1},{"name":"华为","id":2}]
            let jsonObj = JSON.parse(jsonStr);
            let res = '';
            for (let i = 0; i < jsonObj.length; i++) {
                if (i > 0) res += ",";
                res += jsonObj[i][key];
            }
            return res;
        },
        /**
         * v-select @input
         * @param values
         */
        selected_brand: function (values) {
            // 只获取 id
            this.selectBrands = values.map(function (obj) {
                return obj.id;
            })
        },
        /**
         * 加载下拉数据
         */
        selectDataLoad: function () {
            let _this = this;
            axios.get('/brand/getBrandMap.do').then(function (res) {
                _this.brandsOptions = res.data;
            }).catch(function (err) {
                console.log(err);
            });
            axios.get('/spec/getSpecMap.do').then(function (res) {
                _this.specOptions = res.data;
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 加载品牌规格数据
         */
        selected_spec: function (values) {
            this.selectSpecs = values.map(function (obj) {
                return obj.id
            });
        },
        addTemp: function () {
            this.id = -1;
            this.addName = "";
            this.sel_brand_obj = [];
            this.selectBrands = [];
            this.sel_spec_obj = [];
            this.selectSpecs = [];
        },
        /**
         * 保存、更新
         */
        saveTemp: function () {
            let _this = this;
            let entity = {
                name: this.addName,
                specIds: this.sel_spec_obj,
                brandIds: this.sel_brand_obj
            };
            let url = "/temp/add.do";
            if (_this.id > 0) {
                url = "/temp/update.do";
                entity["id"] = _this.id;
            }
            axios.post(url, entity)
                .then(function (response) {
                    _this.addName = "";
                    _this.sel_brand_obj = [];
                    _this.selectBrands = [];
                    _this.sel_spec_obj = [];
                    _this.selectSpecs = [];
                    _this.pageHandler(1);
                }).catch(function (reason) {
                console.log(reason);
            });
        },
        /**
         * 点击编辑 typeTemplate，数据回显
         * @param id 模板 id
         */
        updateTemp: function (id) {
            let _this = this;
            axios.get('/temp/getTempById.do', {
                params: {
                    id: id
                }
            }).then(function (res) {
                // 转换json字符串为对象
                let data = res.data.data;
                console.log(data);
                _this.id = data.id;
                _this.addName = data.name;
                _this.sel_brand_obj = JSON.parse(data.brandIds);
                _this.sel_spec_obj = JSON.parse(data.specIds);

                // 处理 id 集合
                _this.selectSpecs = [];
                _this.selectBrands = [];
                _this.selectBrands = _this.sel_brand_obj.map(function (obj) {
                    return obj.id;
                });
                _this.selectSpecs = _this.sel_spec_obj.map(function (obj) {
                    return obj.id;
                });
            }).catch(function (err) {
                console.log(err);
            });
        }
    },
    created: function () {
        this.pageHandler(1);
        this.selectDataLoad();
    }
});