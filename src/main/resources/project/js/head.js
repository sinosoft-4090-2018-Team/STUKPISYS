/**
 * Created by Administrator on 2018/4/8.
 */
(()=>{
	ajax("get","head.html","","text")
		.then(html=> {
			document.getElementById("header").innerHTML = html;
			$(".info-select").on("click","li",function(){
            	$(this).addClass("bg-color1").siblings().removeClass("bg-color1");
            });
            // $(".sta1").on("click",function(){
				// $(this).addClass("bg-color1").next().removeClass("bg-colocr1");
            // });
            // $(".sta2").on("click",function(){
            //     $(this).addClass("bg-color1").prev().removeClass("bg-colocr1");
            // });
			$(".img1").on("click",function(){
				$(".reset").toggleClass("none2");
			});
		});
})();