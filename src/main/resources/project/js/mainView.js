$(function () {
    fakeMainData();
});
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
    data.a=data1.sort().reverse();
    data.b=data2.sort().reverse();
    data.c=data3.sort().reverse();
    data.d=data4.sort().reverse();

    showMainView(data);
}

function showMainView(data) {
    var chart = echarts.init(document.getElementById("mainView"),'macarons');
    chart.setOption({
        backgroundColor: '#eee',
        legend: {
            data: ['bar', 'bar2', 'bar3', 'bar4'],
            align: 'left',
            left: 10
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['line', 'bar','stack', 'tiled']
                },
                dataView: {}
            }
        },
        dataZoom: [
            {
                type: 'inside'
            }
        ],
        tooltip : {
            trigger: 'axis'
        },
        xAxis: {
            data: data.x,
            name: 'X Axis',
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
            max:400,
            min:0,
            top: 60,
            left: 10,
            inRange: {
                colorLightness: [0.4, 0.8]
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
                name: 'bar',
                type: 'bar',
                stack: 'one',
                data: data.a
            },
            {
                name: 'bar2',
                type: 'bar',
                stack: 'one',
                data: data.b
            },
            {
                name: 'bar3',
                type: 'bar',
                stack: 'one',
                data: data.c
            },
            {
                name: 'bar4',
                type: 'bar',
                stack: 'one',
                data: data.d
            }
        ]
    });

}