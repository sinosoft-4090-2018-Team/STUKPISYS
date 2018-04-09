
let trainee = new Vue({
    el: '#trainee',
    data: {
        name: '',
        state:'正常',
        job:'',
        birth:'',
        dept:'',
        email:'',
        enterTime:'',
        sex:'',
        hrName:'',
        nativePlace:'',
        phone:'',
        state:'',
        graduationTime:'',
        highestEducate:'',
        major:'',
        schoolName:'',
        judgeLabel:[],
        judgeValue:[],
        scoreLabel1:[],
        scoreValue1:[],
        scoreLabel2:[],
        scoreValue2:[],
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
            this.getJudge();
            this.getPass();
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
        getPass(){
            this.$http.get('/trainee/pass?name='+this.name).then((response) => {
                console.log(response);
                let data = response.data;
                let label = Object.keys(data).map((key)=>{
                    if(key%2==0)
                        this.scoreLabel1.push(data[key].labelName);
                    else
                        this.scoreLabel2.push(data[key].labelName);
                    return data[key].labelName;
                })
                let value = Object.keys(data).map((key)=>{
                    if(key%2==0)
                        this.scoreValue1.push(data[key].value);
                    else
                        this.scoreValue2.push(data[key].value);
                    return data[key].value;
                })

            }).catch(function (error) {
                alert("载入信息出错，"+error)
            });
        },
        getJudge(){
            this.$http.get('/trainee/judge?name='+this.name).then((response) => {
                console.log(response);
                let data = response.data.judge;
                this.judgeLabel = Object.keys(data).map((key)=>{
                    return data[key].labelName;
                })
                this.judgeValue = Object.keys(data).map((key)=>{
                    return data[key].valueString;
                })
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
