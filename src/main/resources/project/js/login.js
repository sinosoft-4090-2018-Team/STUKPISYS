

let login = new Vue({
    el: '#login',
    data: {
            name: '',
            password: '',
            remember:false
    },
    mounted: function () {
        this.initialization();
    },
    methods: {
        initialization() {
        },
        doLogin() {
            // this.$http.post('/user/login', {
            //     username: this.name,
            //     password: this.password
            // }).then(function (response) {
            //     console.log(response);
            //     let data = response.data;
            //     if(this.remember) {
            //         localStorage.setItem('role', data.role);
            //         localStorage.setItem('token', data.token);
            //     }
            //     if("ROLE_USER"==data.role){
            //         window.location.href="/trainee.html/user/"+this.name;
            //     }else{
            //         window.location.href="/sift.html";
            //     }
            // }).catch(function (error) {
            //     alert("登录失败")
            // });
            

        },
        doLogout() {
            localStorage.clear();
        }
    }
});