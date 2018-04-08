
let trainee = new Vue({
    el: '#trainee',
    data: {
        name: ''
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
