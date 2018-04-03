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
				alert("网络出现故障，请检查");
			}
		})
	});
	var sex1 = new Vue({
		el: '#sexs',
		data: {
			items: [
				{ message: '男' },
				{ message: '女' }
			]
		}
	});
})();