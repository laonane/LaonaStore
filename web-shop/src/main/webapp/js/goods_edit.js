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
        specList: [],                   /*规格选项*/
        /*        specSelectedList: [{              /!*当前选择的规格选择*!/
                    "specName": "选择颜色",
                    "specOptions": ["秘境黑", "晨曦白"]
                },{
                    "specName": "选择版本",
                    "specOptions": ["6G+128G", "12G+512G"]
                }],*/
        specSelectedList: [],           /*当前选择的规格选择*/
        rowList: [],                    /*规格与规格选项的列表*/
        isEnableSpec: false,                  /*是否启用规格*/
        goodsEntity: {                   /*商品实体*/
            goods: {},              // 商品信息
            goodsDesc: {},          // 商品描述
            itemList: []            // 商品条目
        }
    }, methods: {
        /**
         * 生成规格和规格选项的列表
         */
        createRowList: function () {
            let rowList = [
                {spec: {}, price: 0, num: 999, status: '0', isDefault: '0'}
            ]
            for (let i = 0; i < this.specSelectedList.length; i++) {
                let specObj = this.specSelectedList[i];
                let specName = specObj.specName;
                let specOptions = specObj.specOptions;
                let newRowList = [];
                for (let j = 0; j < rowList.length; j++) {
                    let oldRow = rowList[j];
                    for (let k = 0; k < specOptions.length; k++) {
                        let newRow = JSON.parse(JSON.stringify(oldRow));
                        newRow.spec[specName] = specOptions[k];
                        newRowList.push(newRow);
                    }
                }
                rowList = newRowList;
            }
            this.rowList = rowList;
        },
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

        },
        /**
         * 规格选择 checkbox 事件
         * @param event checkbox 选择
         * @param spec_name 规格姓名
         * @param optionName 选项姓名
         */
        updateSpecState: function (event, spec_name, optionName) {
            let specSelectedObj = this.searchPositionInListByKey(this.specSelectedList, "specName", spec_name);
            // 不存在则说明已经有对应的值，需要添加到对应的位置
            if (specSelectedObj != null) {
                if (event.target.checked) {      /*选中状态*/
                    // 已存在 key，勾选则添加到对应的 key
                    // this.specSelectedList[spec_name].push(optionName);
                    specSelectedObj.specOptions.push(optionName);
                } else {
                    // 存在，取消勾选则删除
                    let idx = specSelectedObj.specOptions.indexOf(optionName);
                    specSelectedObj.specOptions.splice(idx, 1);
                    // 当 options 为空， 则删除整个节点
                    if (specSelectedObj.specOptions.length === 0) {
                        let idx = this.specSelectedList.indexOf(specSelectedObj);
                        this.specSelectedList.splice(idx, 1);
                    }
                }
            } else {
                this.specSelectedList.push({
                    "specName": spec_name,
                    "specOptions": [optionName]
                });
            }
            this.createRowList();
        },
        /**
         * 给定集合中找出对应键值对，并返回该对象，不存在返回空
         * @param list 给定集合
         * @param key 键
         * @param value 值
         */
        searchPositionInListByKey: function (list, key, value) {
            for (let i = 0; i < list.length; i++) {
                if (list[i][key] === value) {
                    return list[i];
                }
            }
            return null;
        },
        /**
         * 是否允许编辑规格
         */
        enableSpec: function (event) {
            this.isEnableSpec = event.target.checked;
        },
        /**
         * 保存商品信息
         */
        saveGoods: function () {
            let _this = this;
            console.log("来到了保存商品信息");
            this.goodsEntity.goods.category1Id = this.categorySelected1;
            this.goodsEntity.goods.category2Id = this.categorySelected2;
            this.goodsEntity.goods.category3Id = this.categorySelected3;
            this.goodsEntity.goods.typeTemplateId = this.typeId;
            this.goodsEntity.goods.brandId = this.selectBrand;
            this.goodsEntity.goods.isEnableSpec = this.isEnableSpec ? '1' : '0';

            this.goodsEntity.goodsDesc.itemImages = this.imageList;
            this.goodsEntity.goodsDesc.specificationItems = this.specSelectedList;
            // 商品详情介绍 - 富文本
            this.goodsEntity.goodsDesc.introduction = UE.getEditor('editor').getContent();

            // 商品库存
            this.goodsEntity.itemList = this.rowList;

            if (this.selectBrand === -1) {
                alert("请选择品牌");
                return;
            }

            //发送请求
            axios.post("/goods/add.do", _this.goodsEntity)
                .then(function (res) {
                    res = res.data;
                    console.log(res.data);
                    location.href = "goods.html";
                }).catch(function (err) {
                alert(err.data);
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
            axios.post("/temp/findBySpecWithId.do?id=" + newVal).then(function (res) {
                res = res.data;
                _this.specList = res.data;
                console.log(_this.specList);
            }).catch(function (err) {
                console.log(err);
            });
        }
    }
})