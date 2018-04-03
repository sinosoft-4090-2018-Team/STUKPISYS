$(function () {
    fakeTrainee();
});
function randomData() {
    return Math.round(Math.random()*100);
}
function fakeTrainee() {
    var data={};
    data.title="各阶段成绩";
    data.x=["第一阶段","第二阶段","第三阶段"];
    data.bar=[];
    for(var i=0;i<3;i++)
        data.bar.push(randomData());
    data.pie=Object.keys(data.bar).map(function (key) {
        return {
            name: data.x[key],
            value: data.bar[key]
        }
    });
    creatTrainee(data);
}
function creatTrainee(data) {
    traineeView( echarts.init(document.getElementById("traineeView"),'macarons'),data);
}
function traineeView (chart,data) {
    chart.setOption({
        xAxis: {
            type: 'category',
            data: data.x
        },
        yAxis: {
            type: 'value'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} :{c}"
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