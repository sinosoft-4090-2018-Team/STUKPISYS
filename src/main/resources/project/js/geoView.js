$(function () {
    fakeGeoData();
});
function randomData() {
    return Math.round(Math.random()*100);
}
function fakeGeoData() {
    var data={};
    data.title="地区分布";
    data.max=100;
    data.data=[
        {name: '北京',value: randomData() },
        {name: '天津',value: randomData() },
        {name: '上海',value: randomData() },
        {name: '重庆',value: randomData() },
        {name: '河北',value: randomData() },
        {name: '河南',value: randomData() },
        {name: '云南',value: randomData() },
        {name: '辽宁',value: randomData() },
        {name: '黑龙江',value: randomData() },
        {name: '湖南',value: randomData() },
        {name: '安徽',value: randomData() },
        {name: '山东',value: randomData() },
        {name: '新疆',value: randomData() },
        {name: '江苏',value: randomData() },
        {name: '浙江',value: randomData() },
        {name: '江西',value: randomData() },
        {name: '湖北',value: randomData() },
        {name: '广西',value: randomData() },
        {name: '甘肃',value: randomData() },
        {name: '山西',value: randomData() },
        {name: '内蒙古',value: randomData() },
        {name: '陕西',value: randomData() },
        {name: '吉林',value: randomData() },
        {name: '福建',value: randomData() },
        {name: '贵州',value: randomData() },
        {name: '广东',value: randomData() },
        {name: '青海',value: randomData() },
        {name: '西藏',value: randomData() },
        {name: '四川',value: randomData() },
        {name: '宁夏',value: randomData() },
        {name: '海南',value: randomData() },
        {name: '台湾',value: randomData() },
        {name: '香港',value: randomData() },
        {name: '澳门',value: randomData() }
    ];
    creatGeoView(data);
    // console.log(Object.keys(name));
}
function creatGeoView(data) {
    initGeo( echarts.init(document.getElementById("mapView"),'macarons'),data);
}

function initGeo(chart,data) {
    chart.setOption({
        title: {
            text: data.title,
            left: 'center',
            top: 20
        },
        tooltip: {
            trigger: 'item'
        },
        // legend: {
        //     left: 'left'
        // },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        visualMap: {
            max: data.max,
            text: ['多', '少'],           // 文本，默认为数值文本
            calculable: true,
            inRange: {
                color: ['#2ec7c9','#5ab1ef', '#b6a2de','#ffb980','#d87a80']
            }
        },
        series: {
            name:'地区分布',
            type: 'map',
            mapType: 'china',
            roam: true,
            data:data.data
        }
    });
}