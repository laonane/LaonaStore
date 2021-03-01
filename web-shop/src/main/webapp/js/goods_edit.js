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
        curImageObj: {                /*图片上传对象*/
            color: '',
            url: ''
        },
        imageList: [],               /*图片上传列表*/
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
        },
        /**
         * 图片上传
         */
        uploadFile: function () {
            let formData = new FormData();
            let _this = this;
            formData.append("file", file.files[0]);
            // 打开 axios 的凭证信息
            let instance = axios.create({
                withCredentials: true
            });
            axios.post("/upload/uploadFile.do", formData).then(function (res) {
                res = res.data;
                _this.curImageObj.url = res.data;
                // console.log(_this.curImageObj.url);
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 保存图片
         */
        saveImage: function () {
            console.log("保存图片");
            if (this.curImageObj.color === '' || this.curImageObj.url === '') {
                alert("请输入颜色或者上传你的图片");
                return;
            }
            // 双向绑定不能直接 push curImageObj 对象，因为会动态变化
            let obj = {
                color: this.curImageObj.color,
                url: this.curImageObj.url
            };
            this.imageList.push(obj);
        },
        /**
         * 删除图片
         */
        deleteImg: function (delUrl, index) {
            let _this = this;
            // 从服务器删除该地址
            axios.get("/upload/deleteImage.do?url=" + delUrl).then(function (res) {
                // 移除 imageList 数据
                res = res.data;
                if (res.code === 1) {
                    // 切片删除
                    _this.imageList.splice(index, 1);
                } else {
                    alert(res.data);
                }
            }).catch(function (err) {
                console.log(err);
            });

        }
    }, created: function () {
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
            axios.post("/temp/getTempById.do?id=" + newVal).then(function (res) {
                res = res.data;
                _this.brandList = JSON.parse(res.data.brandIds);
            }).catch(function (err) {
                console.log(err);
            });
            //TODO 加载规格与规格选项

        }
    }
})