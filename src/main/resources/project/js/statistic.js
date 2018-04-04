/**
 * Created by Administrator on 2018/4/2.
 */
(()=>{
	var sex1 = new Vue({
		el: '#content',
		data: {
			items: [
				{ message: '男' },
				{ message: '女' }
			],
			items1: [
				{ message: '面试官A' },
				{ message: '面试官B' }
			],
			items2: [
				{ message: '研发' },
				{ message: 'ui' }
			],
			items3: [
				{ message: '计算机' },
				{ message: '软件工程' },
				{ message: '其他' }
			],
			items4: [
				{ message: '本科' },
				{ message: '研究生' },
				{ message: '专科' }
			],
			items5: [
				{ message: '学校A' },
				{ message: '学校B' },
				{ message: '学校C' }
			],
			items6: [
				{ message: '2017-12' },
				{ message: '2018-3' },
				{ message: '2018-4' }
			],
			items7: [
				{ message: '面试官所在部门' },
				{ message: '阶段一成绩↓' },
				{ message: '阶段二成绩↓' },
				{ message: '阶段三成绩↓' },
				{ message: '个人信息' },
				{ message: '详细信息' }
			],
			items8: [
				{ message: '电子政务' },
				{ message: '89' },
				{ message: '91' },
				{ message: '90' },
				{ message: '91' },
				{ message: '90' }
			],
			items9: [
				{ message: '电子政务' },
				{ message: '89' },
				{ message: '91' },
				{ message: '90' },
				{ message: '91' },
				{ message: '90' }
			],
			items10: [
				{ message: '电子政务' },
				{ message: '89' },
				{ message: '91' },
				{ message: '90' },
				{ message: '91' },
				{ message: '90' }
			],
			items11: [
				{ message: '电子政务' },
				{ message: '89' },
				{ message: '91' },
				{ message: '90' },
				{ message: '91' },
				{ message: '90' }
			],
			items12: [
				{ message: '电子政务' },
				{ message: '89' },
				{ message: '91' },
				{ message: '90' },
				{ message: '91' },
				{ message: '90' },
			]
		}
	});
	$(".btn1").on("click",function(){
		$(this).parent().parent().next().children(":first").removeClass("none1").next().addClass("none1");
	});
	$(".btn2").on("click",function(){
		$(this).parent().parent().next().children(":first").addClass("none1").next().removeClass("none1");
	});
})();
