new Vue({
    el: "#root",
    data: {
        loginName: "",
    },
    methods: {
        /**
         * 获取登录信息
         */
        getUserInfo: function () {
            let _this = this;
            axios.get("/login/getLoginName.do").then(function (res) {
                let data = res.data;
                if (data.code === 1) {
                    _this.loginName = data.data.username;
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
    },
    created: function () {
        this.getUserInfo();
    }
});