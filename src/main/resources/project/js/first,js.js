(()=>{
	$(".box").on("click",function(){
		console.log(this);
		$(this).toggleClass("bg-color");
	});

	$(".pwd").click(function(e){
		e.preventDefault();
		var u = $("[name='uname']").val();
		var p = $("[upwd='upwd']").val();
		$.ajax({
			type:"POST",
			url:"/user/login",
			data:{ uname:u,upwd:p},
			success:function(data){
			},
			error:function(data){
				alert("������ֹ��ϣ�����");
			}
		})
	});
	var sex1 = new Vue({
		el: '#sexs',
		data: {
			items: [
				{ message: '��' },
				{ message: 'Ů' }
			]
		}
	});
})();