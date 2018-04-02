/**
 * Created by Administrator on 2018/4/2.
 */
(()=>{
	$(".btn1").on("click",function(){
		$(this).parent().parent().next().children(":first").removeClass("none1").next().addClass("none1");
	});
	$(".btn2").on("click",function(){
		$(this).parent().parent().next().children(":first").addClass("none1").next().removeClass("none1");
	})
})();