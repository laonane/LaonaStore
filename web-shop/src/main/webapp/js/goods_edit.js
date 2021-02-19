new Vue({
    el: "#root",
    data: {
        categoryList1: [],       /*分页数据1*/
        categoryList2: [],       /*分页数据2*/
        categoryList3: [],       /*分页数据3*/
        grade: 1,               /*下拉列表级数*/
        categorySelected1: -1,   /*分类列表选中的值1*/
        categorySelected2: -1,   /*分类列表选中的值2*/
        categorySelected3: -1,   /*分类列表选中的值3*/
        typeId: 0,               /*模板id*/
        selectBrand: -1,            /*选择品牌序号*/
        brandList: [],              /*品牌列表*/
    }, methods: {
        /**
         * 加载商品分类下拉列表
         */
        loadCategoryDate: function (pid) {
            let _this = this;
            axios.post("/itemCate/getItemCategoryList.do?parentId=" + pid).then(function (res) {
                res = res.data;
                if (_this.grade === 1) {
                    _this.categoryList1 = res.data;
                } else if (_this.grade === 2) {
                    _this.categoryList2 = res.data;
                } else if (_this.grade === 3) {
                    _this.categoryList3 = res.data;
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        getCategoryChange: function (grade) {
            let _this = this;
            switch (grade) {
                case 1:
                    // 清空 2 3 子项
                    _this.categoryList2 = [];
                    _this.categorySelected2 = -1;

                    _this.categoryList3 = [];
                    _this.categorySelected3 = -1;

                    _this.loadCategoryDate(_this.categorySelected1);
                    _this.grade = 2;
                    break;
                case 2:
                    _this.loadCategoryDate(_this.categorySelected2);
                    _this.grade = 3;
                    break;
                case 3:
                    _this.grade = 4;
                    axios.post("/itemCate/getItemCategoryById.do?id=" + _this.categorySelected3).then(function (res) {
                        res = res.data;
                        _this.typeId = res.data.typeId;
                    }).catch(function (err) {
                        console.log(err);
                    });
                    break;
            }
        }
    }, created: function () {
        //初始化富文本编程器
        var ue = UE.getEditor('editor');
        ue.ready(function () {

        });
        this.loadCategoryDate(0);
    },
    /**
     * 监听值变化
     */
    watch: {
        typeId(newVal, oldVal) {
            let _this = this;
            _this.selectBrand = -1;
            _this.brandList = [];
            console.log(newVal);
            axios.post("/temp/getTempById.do?id=" + newVal).then(function (res) {
                res = res.data;
                _this.brandList = JSON.parse(res.data.brandIds);
            }).catch(function (err) {
                console.log(err);
            })
        }
    }
})