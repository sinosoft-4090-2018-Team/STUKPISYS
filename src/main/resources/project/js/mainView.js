// $(function () {
//     fakeMainData();
// });
function fakeMainData() {
    var xAxisData = [];
    var data1 = [];
    var data2 = [];
    var data3 = [];
    var data4 = [];

    for (var i = 0; i < 50; i++) {
        xAxisData.push('Class' + i);
        data1.push(Math.random()*100);
        data2.push(Math.random()*100);
        data3.push(Math.random()*100);
        data4.push(Math.random()*100);
    }

    var data = {};
    data.x=xAxisData;
    data.xName='分数';
    data.a=data1.sort().reverse();
    data.b=data2.sort().reverse();
    data.c=data3.sort().reverse();
    data.d=data4.sort().reverse();
    data.lable=['第一阶段','第二阶段','第三阶段'];
    data.avg=[getSUM(data.a)/data.a.length,getSUM(data.b)/data.b.length+getSUM(data.a)/data.a.length,getSUM(data.c)/data.c.length+getSUM(data.a)/data.a.length+getSUM(data.b)/data.b.length];
    // console.log(data.avg);
    showMainView(data);
}
function getSUM(array){
    let sum = 0;
    array.forEach(function (i) {
        sum+=i;
    });
    return sum;
}
function showMainView(data) {
    var chart = echarts.init(document.getElementById("mainView"),'macarons');
    chart.off('click');
    chart.clear();
    chart.setOption({
        legend: {
            data: data.lable,
            align: 'left',
            left: 10
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['line', 'bar','stack', 'tiled']
                },
                dataView: {},
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: [
            {
                show:true
            },
            {
                type: 'inside'
            }
        ],
        tooltip : {
            trigger: 'axis'
        },
        xAxis: {
            data: data.x,
            name: data.xName,
            // silent: false,
            // axisLine: {onZero: true},
            // splitLine: {show: false},
            // splitArea: {show: false}
        },
        yAxis: {
            // inverse: true,
            splitArea: {show: false}
        },
        grid: {
            left: 100
        },
        visualMap: {
            type: 'continuous',
            dimension: 1,
            text: ['High', 'Low'],
            itemHeight: 200,
            calculable: true,
            max:300,
            min:0,
            top: 60,
            left: 10,
            inRange: {
                colorLightness: [0.7, 0.7]
            },
            outOfRange: {
                color: '#bbb'
            },
            controller: {
                inRange: {
                    color: '#2f4554'
                }
            }
        },
        series: [
            {
                name: data.lable[0],
                type: 'bar',
                stack: 'one',
                markLine: {
                    data: [
                        {yAxis:data.avg[0], name: data.lable[0]+'平均值'}
                    ]
                },
                data: data.a
            },
            {
                name: data.lable[1],
                type: 'bar',
                stack: 'one',
                markLine: {
                    data: [
                        {yAxis:data.avg[1], name: data.lable[1]+'平均值'}
                    ]
                },
                data: data.b
            },
            {
                name: data.lable[2],
                type: 'bar',
                stack: 'one',
                markLine: {
                    data: [
                        {yAxis:data.avg[2], name: data.lable[2]+'平均值'}
                    ]
                },
                data: data.c
            },
            // {
            //     name: 'bar4',
            //     type: 'bar',
            //     stack: 'one',
            //     data: data.d
            // }
        ]
    });

    chart.on('click', function (params) {
        console.log(params);
        window.open("trainee.html?user=" + params.name);
    });
}