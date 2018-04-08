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
			$(".img1").on("click",function(){
				$(".reset").toggleClass("none2");
			});
		});
})();