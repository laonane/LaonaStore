new Vue({
    el: "#root",
    data: {
        sellerList: [],         /*商家审核列表*/
        seller: {},                 /*商家信息*/
        searchSeller: {},             /*搜索品牌*/
        page: 1,    /*当前页码*/
        pageSize: 8,   /*一页有多少条数据*/
        total: 80,     /*总记录数*/
        maxPage: 10,     /*最大页码*/
    }, methods: {
        /**
         * 分页处理
         * @param page
         */
        pageHandler: function (page) {
            this.page = page;
            let _this = this;
            // 商户状态为待审核
            _this.searchSeller.status = 0;
            axios.post("/seller/getSellerAuditingList.do?page=" + _this.page + "&pageSize=" + _this.pageSize, _this.searchSeller)
                .then(function (res) {
                    res = res.data;
                    if (res.code === 1) {
                        _this.sellerList = res.data.rows;
                        _this.total = res.data.total;
                    } else {
                        alert(res.message);
                    }
                }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 显示商家详情
         * @param id 商家 id
         */
        detail: function (id) {
            let _this = this;
            axios.get("/seller/getSellerAuditing.do", {
                params: {
                    sid: id
                }
            }).then(function (res) {
                res = res.data;
                if (res.code === 1) {
                    _this.seller = res.data;
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        /**
         * 操作商家审核状态
         * @param sid 商家id
         * @param optNum 操作编号
         */
        optionAudit: function (optNum) {
            let _this = this;
            _this.seller.status = optNum;
            axios.post("/seller/updateSellerAuditing.do", _this.seller).then(function (res) {
                res = res.data;
                if (res.code === 1) {
                    _this.pageHandler(1);
                    alert("审核成功");
                }else {
                    alert(res.message);
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
    },
    created: function () {
        this.pageHandler(1);
    }
});