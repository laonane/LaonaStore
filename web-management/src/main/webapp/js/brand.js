new Vue({
    el: "#root",
    data: {
        brandList: []
    },
    methods: {
        /**
         * 请求所有 brand 数据
         */
        findAllBrands: function () {
            axios.get("/brand/getBrandList.do").then(function (res) {
                console.log(res.data);
            }).catch(function (err) {
                console.log(err);
            })
        }
    },
    created: function () { /* vue 对象初始化完毕会自动调用 */
        this.findAllBrands();
    }
});