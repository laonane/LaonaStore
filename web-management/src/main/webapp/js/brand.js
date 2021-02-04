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
                params:{
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
        brandSave:function () {
            axios.post("/brand/add.do", this.brand).then(function (res) {
                console.log(res);
                if (res.data.code === 1) {
                    console.log("保存成功");
                }else {
                    console.log("保存失败");
                }
            }).catch(function (err) {
                console.log(err);
            });
        }
    },
    created: function () {
        this.pageHandler(1);
    }

});