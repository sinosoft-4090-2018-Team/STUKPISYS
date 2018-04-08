
let trainee = new Vue({
    el: '#trainee',
    data: {
        name: '',
        state:'正常',
        locat:'江苏南京',
        job:'开发',
        birth:'',
        dept:'',

        //todo
    },
    mounted: function () {
        this.initialization();
    },
    methods: {
        initialization() {
            this.name = decodeURI(window.location.href.split('=')[1]);
            console.log(this.name);
            this.getScore();
            this.getInfo();
        },
        getInfo(){
            this.$http.get('/trainee/info?name='+this.name).then((response) => {
                console.log(response);
                let data = response.data;
                this.birth=data.userInfo.birth;
                this.dept=data.userInfo.dept;
                this.email=data.userInfo.email;
                this.enterTime=data.userInfo.enterTime;
                this.sex=data.userInfo.gender==0?'男':'女';
                this.hrName=data.userInfo.hrName;
                this.job=data.userInfo.job;
                this.nativePlace=data.userInfo.nativePlace;
                this.phone=data.userInfo.phone;
                this.state=data.userInfo.state;
                this.graduationTime=data.education.graduationTime;
                this.highestEducate=data.education.highestEducate;
                this.major=data.education.major;
                this.schoolName=data.education.schoolName;
            });
        },
        getJudge(){
            this.$http.get('/trainee/judge?name='+this.name).then((response) => {
                console.log(response);
                let data = response.data;
                //todo
            }).catch(function (error) {
                alert("载入信息出错，"+error)
            });
        },
        getScore(){
            this.$http.get('/trainee/score?name='+this.name).then((response) => {
                console.log(response);
            let data = response.data;
            let res = {
                x:[],
                bar:[]
            }
            for (let k in data.score) {
                // console.log(data.score[k].belong);
                if ('sum' == data.score[k].belong) {
                    res.x.push(data.score[k].labelName.split('，')[1].trim());
                    res.bar.push(data.score[k].valueInt);
                }
            }
            res.pie=Object.keys(res.bar).map(function (key) {
                return {
                    name: res.x[key],
                    value: res.bar[key]
                }
            });
            creatTrainee(res);
        }).catch(function (error) {
                alert("载入信息出错，"+error)
            });
        },
        doLogout() {
            localStorage.clear();
            window.location.href="index.html";
        }
    }
});
