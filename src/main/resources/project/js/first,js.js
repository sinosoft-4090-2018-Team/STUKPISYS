(()=>{
	$(".box").on("click",function(){
		console.log(this);
		$(this).toggleClass("bg-color");
	})
})();