function traineeView (chart,data) {
    chart.setOption({
        xAxis: {
            type: 'category',
            data: data.x
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: data.bar,
            type: 'bar'
        },
            {
                type: 'pie',
                radius: [0, '30%'],
                center: ['75%', '25%'],
                data: data.pie
            },]
    });

}