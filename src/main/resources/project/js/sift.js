(() => {
    $(".btn1").on("click", function () {
        $(this).parent().parent().next().children(":first").removeClass("none1").next().addClass("none1");
    });
    $(".btn2").on("click", function () {
        $(this).parent().parent().next().children(":first").addClass("none1").next().removeClass("none1");
    });
})();

var sift = new Vue({
    el: '#sift',
    data: {
        name: '',
        role: '',
        sex: ['全部', '男', '女'],
        hr: ['全部'],
        job: ['全部'],
        major: ['全部'],
        highest_educate: ['全部'],
        school_name: ['全部'],
        enterTime: ['全部'],
        sift_sex: '全部',
        sift_hr: '全部',
        sift_job: '全部',
        sift_major: '全部',
        sift_highest_educate: '全部',
        sift_school_name: '全部',
        sift_enterTime: '全部',

    },
    mounted: function () {
        this.initialization();
    },
    methods: {
        initialization() {
            this.name = localStorage.getItem('name');
            this.role = localStorage.getItem('role');
            // console.log(localStorage.getItem('token'));
            this.$http.get('/sift/getSiftTerms').then((response) => {
                console.log(response);
                let data = response.data;
                for (let k in data.hr)
                    this.hr.push(data.hr[k]);
                for (let k in data.job)
                    this.job.push(data.job[k]);
                for (let k in data.major)
                    this.major.push(data.major[k]);
                for (let k in data.highest_educate)
                    this.highest_educate.push(data.highest_educate[k]);
                for (let k in data.school_name)
                    this.school_name.push(data.school_name[k]);
            }).catch(function (error) {
                alert("载入信息出错，" + error)
            });
            this.siftScore();
        },
        Authorization() {
            if ("ROLE_HR" == this.role)
                this.hr = [this.name];
            //todo

        },
        siftScore() {
            this.$http.get('/sift/score?HRName=' + this.sift_hr +
                "&job" + this.sift_job +
                "&school" + this.sift_school_name +
                "&Education" + this.sift_highest_educate +
                "&major" + this.sift_major +
                "&sex" + this.sift_sex +
                "&enterTime" + this.enterTime
            ).then((response) => {
                console.log(response);
                let data = response.data.score;
                //todo
            }).catch(function (error) {
                alert("载入信息出错，" + error)
            });
        },
        upload(e) {
            console.log(e.target);
            let file = e.target.files[0]
            /* eslint-disable no-undef */
            let param = new FormData()  // 创建form对象
            param.append('file', file, file.name)  // 通过append向form对象添加数据
            console.log(param.get('file')) // FormData私有类对象，访问不到，可以通过get判断值是否传进去
            let config = {
                headers: {'Content-Type': 'multipart/form-data'}
            }
            // 添加请求头
            this.$http.post('/file/upload', param, config)
                .then(response => {
                    console.log(response.data)
                })
        },

        doLogout() {
            localStorage.clear();
        }
    }
});

var table = new Vue({
    el: '#table',
    data: {
        //todo
        items7: [
            {message: '面试官所在部门'},
            {message: '阶段一成绩↓'},
            {message: '阶段二成绩↓'},
            {message: '阶段三成绩↓'},
            {message: '个人信息'},
            {message: '详细信息'}
        ],
        items8: [
            {message: '电子政务'},
            {message: '89'},
            {message: '91'},
            {message: '90'},
            {message: '91'},
            {message: '90'}
        ],
        items9: [
            {message: '电子政务'},
            {message: '89'},
            {message: '91'},
            {message: '90'},
            {message: '91'},
            {message: '90'}
        ],
        items10: [
            {message: '电子政务'},
            {message: '89'},
            {message: '91'},
            {message: '90'},
            {message: '91'},
            {message: '90'}
        ],
        items11: [
            {message: '电子政务'},
            {message: '89'},
            {message: '91'},
            {message: '90'},
            {message: '91'},
            {message: '90'}
        ],
        items12: [
            {message: '电子政务'},
            {message: '89'},
            {message: '91'},
            {message: '90'},
            {message: '91'},
            {message: '90'},
        ]
    }
});