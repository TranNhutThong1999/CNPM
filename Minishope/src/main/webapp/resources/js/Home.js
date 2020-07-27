		$(document).ready(function() {
			$(window).scroll(function(e) {
				var vitri = $("html,body").scrollTop();
				if (vitri > 700) {
					$("#menu").addClass("scroll");
				}else if(vitri<500){
					$("#menu").removeClass("scroll");
				}
			})
			
			$("#khuyenMai").click(function(){
				$("html,body").animate({scrollTop :1000}, 1000,"swing");
			})
		})
