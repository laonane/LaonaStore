new Vue({
    el: "#root",
    data: {
        categoryList: {},    /*分类列表*/
        selectedItemCateIds: [],      /*选中分类列表*/
        grade: 1,                   /*面包屑级别*/
        gradeEntity1: {},               /*二级目录*/
        gradeEntity2: {},               /*三级目录*/
    }, methods: {
        /**
         * 获取父分类
         * @param pid
         */
        getItemCategoryByParentId: function (pid) {
            let _this = this;
            axios.post("/itemCate/getItemCategoryList.do?parentId=" + pid).then(function (res) {
                res = res.data;
                _this.categoryList = res.data;
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 选中列表
         * @param event
         * @param id
         */
        selectedItem: function (event, id) {
            // 获取选中状态
            let checked = event.target.checked
            if (checked) {
                this.selectedItemCateIds.push(id);
            } else {
                // 找到 id 所在位置
                let index = this.selectedItemCateIds.indexOf(id);
                // 删除集合中对应id
                this.selectedItemCateIds.splice(index, 1);
            }
        },
        /**
         * 查询下级
         * @param item
         */
        getNextGrade: function (item) {
            console.log("grade旧值", this.grade);
            let _this = this;
            // console.table(item);
            if (_this.grade === 1) {
                _this.gradeEntity1 = {};
                _this.gradeEntity2 = {};
            }
            if (_this.grade === 2) {
                _this.gradeEntity1 = item;
                _this.gradeEntity2 = {};
                for (let val in _this.gradeEntity1) {
                    console.log(val, " = ", item[val]);
                }
            }
            if (_this.grade === 3) {
                _this.gradeEntity2 = item;
                for (let val in _this.gradeEntity2) {
                    console.log(val, " = ", item[val]);
                }
            }
            _this.getItemCategoryByParentId(item.id);
        },
        /**
         * 设置级数
         * @param num
         */
        setGradeNum: function (num) {
            this.grade = num;
        },
    }, created: function () {
        this.getItemCategoryByParentId(0);
    },
/*    watch: {
        grade(newValue, oldValue) {
            console.log("grade新值: id=", newValue.id, " name=", newValue.name, "grade旧值", oldValue);
        },
        gradeEntity1(newValue, oldValue) {
            if (newValue != null) {
                console.log("gradeEntity1新值: id=", newValue.id, " name=", newValue.name, "id=", oldValue.id, " name=", oldValue.name);
            }
        },
        gradeEntity2(newValue, oldValue) {
            if (newValue != null) {
                console.log("gradeEntity2新值: id=", newValue.id, " name=", newValue.name, "id=", oldValue.id, " name=", oldValue.name);
            }
        },
    }*/
});