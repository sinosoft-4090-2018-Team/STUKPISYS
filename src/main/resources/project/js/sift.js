(() => {


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
            isFired:false,
            isNew:false,
            hasErr:false,
            back2:false,
            back3:false,

            isActive: false,
            label: [],
            row: []
        },
        mounted: function () {
            // console.log(this.isActive);
            if ("ROLE_ADMIN" == localStorage.getItem("role")) {
                this.isActive = true;
                // console.log(this.isActive);
            }
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
                let sex = '';
                if(this.sift_sex=='男')
                    sex = 0;
                else if(this.sift_sex=='女')
                    sex = 1;
                else if(this.sift_sex=='全部')
                    sex='全部';
                this.$http.get('/sift/score?HRName=' + this.sift_hr +
                    "&job=" + this.sift_job +
                    "&school=" + this.sift_school_name +
                    "&Education=" + this.sift_highest_educate +
                    "&major=" + this.sift_major +
                    "&sex=" + sex +
                    "&enterTime=" + this.enterTime +
                    "&isFired=" + this.isFired +
                    "&isNew=" + this.isNew +
                    "&hasErr=" + this.hasErr
                ).then((response) => {
                    console.log(response);
                    let data = response.data;
                    data = Object.keys(data).map(function (key) {
                        return {
                            x: data[key].name,
                            a: parseInt(data[key].firstStageScore),
                            b: parseInt(data[key].secondStageScore),
                            c: parseInt(data[key].thirdStageScore),
                            sum: parseInt(data[key].firstStageScore)
                            + parseInt(data[key].secondStageScore)
                            + parseInt(data[key].thirdStageScore)
                        }
                    });
                    var colId = "sum"
                    var desc = function (x, y) {
                        return (x[colId] < y[colId]) ? 1 : -1
                    }
                    //对json进行升序排序函数
                    var asc = function (x, y) {
                        return (x[colId] > y[colId]) ? 1 : -1
                    }
                    data = data.sort(desc); //升序排序
                    // console.log(data);
                    let res = {
                        x: Object.keys(data).map(function (key) {
                            return data[key].x;
                        }),
                        a: Object.keys(data).map(function (key) {
                            return data[key].a;
                        }),
                        b: Object.keys(data).map(function (key) {
                            return data[key].b;
                        }),
                        c: Object.keys(data).map(function (key) {
                            return data[key].c;
                        }),
                        xName:'分数',
                        lable:['第一阶段','第二阶段','第三阶段']
                    };
                    res.avg=[getSUM(res.a)/res.a.length,getSUM(res.b)/res.b.length+getSUM(res.a)/res.a.length,getSUM(res.c)/res.c.length+getSUM(res.a)/res.a.length+getSUM(res.b)/res.b.length];
                    console.log(res.avg);
                    showMainView(res);
                }).catch(function (error) {
                    alert("载入信息出错，" + error)
                });
            },
            siftJudge() {
                let sex = '';
                if(this.sift_sex=='男')
                    sex = 0;
                else if(this.sift_sex=='女')
                    sex = 1;
                this.$http.get('/sift/judge?HRName=' + this.sift_hr +
                    "&job=" + this.sift_job +
                    "&school=" + this.sift_school_name +
                    "&Education=" + this.sift_highest_educate +
                    "&major=" + this.sift_major +
                    "&sex=" + sex +
                    "&enterTime=" + this.enterTime +
                    "&isFired=" + this.isFired +
                    "&isNew=" + this.isNew +
                    "&hasErr=" + this.hasErr
                ).then((response) => {
                    console.log(response);
                    let data = response.data;
                    for(let label in data[0]){
                        this.label.push(label)
                    }
                    Object.keys(data).map((key)=> {
                        console.log(this.row);
                        this.row[key]=[];
                        // let i = 0;
                        // console.log(data[key]);
                        for(let cell in data[key]){
                            this.row[key].push(data[key][cell]);
                            // i++;
                        }
                    });
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

    // var table = new Vue({
    //     el: '#table',
    //     data: {
    //         //todo
    //         isActive: false,
    //         label: [],
    //         row: []
    //     },
    //     mounted: function () {
    //         console.log(this.isActive);
    //         if ("ROLE_ADMIN" == localStorage.getItem("role")) {
    //             this.isActive = true;
    //             console.log(this.isActive);
    //         }
    //     },
    //     methods: {
    //
    //     }
    // });

    $(".btn1").on("click", function () {
        $(this).parent().parent().next().children(":first").removeClass("none1").next().addClass("none1");
        $(this).parent().parent().next().children(":first").children("table").children("tbody").children().children("td").children().attr("disabled",false);
    });
    $(".btn5").on("click", function () {
        $(this).parent().parent().next().children(":first").removeClass("none1").next().addClass("none1");
        //console.log($(this).parent().parent().next().children(":first"));
        $(this).parent().parent().next().children(":first").children("table").children("tbody").children().children("td").children().attr("disabled",true);
    });

    $(".btn2").on("click", function () {
        $(this).parent().parent().next().children(":first").addClass("none1").next().removeClass("none1");
    });

    $(".btn4").on("click", function () {
        $(this).parent().parent().next().children(":first").addClass("none1").next().removeClass("none1");
    });


})();
function startLottery() {
    $("#Start").css("display","none");
    $("#End").css("display","block");
}
function endGift() {
    $("#Start").css("display","block");
    $("#End").css("display","none");
}
function startLottery1() {
    $("#Start1").css("display","none");
    $("#End1").css("display","block");
}
function endGift1() {
    $("#Start1").css("display","block");
    $("#End1").css("display","none");
}

