// $(function () {
    // fakeSexData();
    // fakeEduData();
    // fake211Data();
    // fakeMajorData();
// });
let statistics = new Vue({
    el:'#statistics',
    mounted: function () {
        this.$http.get('/statistics/sex').then((response) => {
            console.log(response);
            let data = response.data;
            let res = {
                title:"男女分布",
                series:"男女分布",
                data:[]
            }
            res.data = Object.keys(data).map(function (key) {
                var sex;
                if(data[key].name)
                    sex='女';
                else
                    sex='男';
                return {
                    name: sex,
                    value: data[key].value
                }
            });
            creatSexView(res);
        }).catch(function (error) {
            alert("载入信息出错，"+error)
        });

        this.$http.get('/statistics/education').then((response) => {
            console.log(response);
            let data = response.data;
            let res = {
                title:"学历分布",
                series:"学历分布",
                data:data
            }
            creatEduView(res);
        }).catch(function (error) {
            alert("载入信息出错，"+error)
        });

        this.$http.get('/statistics/211').then((response) => {
            console.log(response);
            let data = response.data;
            let res = {
                title:"211分布",
                series:"211分布",
                data:data
            }
            creat211View(res);
        }).catch(function (error) {
            alert("载入信息出错，"+error)
        });

        this.$http.get('/statistics/major').then((response) => {
            console.log(response);
            let data = response.data;
            let res = {
                title:"专业分布",
                series:"专业分布",
                data:data
            }
            creatMajorView(res);
        }).catch(function (error) {
            alert("载入信息出错，"+error)
        });
    }
});

function randomData() {
    return Math.round(Math.random()*100);
}
function fakeSexData() {
    var data={};
    data.title="男女分布";
    data.series="男女分布";
    data.data=[
        {value:randomData(), name:'男'},
        {value:randomData(), name:'女'}
    ]
    creatSexView(data);
}
// function getRealSex() {
//     $.get({
//         url:baseURL+"statistics/sex",
//         headers: {
//             token: localStorage.getItem("token")
//         },
//         success: function (data) {
//             var res = Object.keys(data).map(function (key) {
//                 var sex;
//                 if(data[key].name)
//                     sex='女';
//                 return {
//                     name: sex,
//                     value: data[key].value
//                 }
//             });
//             creatSexView(res);
//         }
//     });
// }
function creatSexView(data) {
    initPie( echarts.init(document.getElementById("sexView"),'macarons'),data);
}
function fakeEduData() {
    var data={};
    data.title="学历分布";
    data.series="学历分布";
    data.data=[
        {value:randomData(), name:'本科'},
        {value:randomData(), name:'硕士'},
        {value:randomData(), name:'博士'}
    ]
    creatEduView(data);
}
function creatEduView(data) {
    initPie( echarts.init(document.getElementById("eduView"),'macarons'),data);
}
function fake211Data() {
    var data={};
    data.title="211占比";
    data.series="211占比";
    data.data=[
        {value:randomData(), name:'是'},
        {value:randomData(), name:'否'}
    ]
    creat211View(data);
}
function creat211View(data) {
    initPie( echarts.init(document.getElementById("211View"),'macarons'),data);
}
function fakeMajorData() {
    var data={};
    data.title="专业分布";
    data.series="专业分布";
    data.data=[
        {value:randomData(), name:'软件工程'},
        {value:randomData(), name:'网络工程'},
        {value:randomData(), name:'前端页面'},
        {value:randomData(), name:'UI设计'}
    ]
    creatMajorView(data);
}
function creatMajorView(data) {
    initPie( echarts.init(document.getElementById("majorView"),'macarons'),data);
}
function initPie(chart,data) {
    console.log(data);
    chart.setOption({
        title: {
            text: data.title,
            left: 'center',
            top: 20
        },

        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20
        },
        // visualMap: {
        //     show: false,
        //     min: 80,
        //     max: 600,
        //     inRange: {
        //         colorLightness: [0, 1]
        //     }
        // },
        series : [
            {
                name:data.series,
                type:'pie',
                radius : '70%',
                center: ['50%', '55%'],
                data:data.data.sort(function (a, b) { return a.value - b.value; }),
                roseType: 'radius',
                // label: {
                //     normal: {
                //         formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                //         backgroundColor: '#eee',
                //         borderColor: '#aaa',
                //         borderWidth: 1,
                //         borderRadius: 4,
                //         rich: {
                //             a: {
                //                 color: '#999',
                //                 lineHeight: 22,
                //                 align: 'center'
                //             },
                //             hr: {
                //                 borderColor: '#aaa',
                //                 width: '100%',
                //                 borderWidth: 0.5,
                //                 height: 0
                //             },
                //             b: {
                //                 fontSize: 16,
                //                 lineHeight: 33
                //             },
                //             per: {
                //                 color: '#eee',
                //                 backgroundColor: '#334455',
                //                 padding: [2, 4],
                //                 borderRadius: 2
                //             }
                //         }
                //     }
                // },
                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]
    });
}