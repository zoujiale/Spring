//分页
$(function(){

		function createDemo(name){
			var container = $('#pagination-' + name);
			var sources = function(){
				var result = [];
                var dataList = ["<a href='industrynews_details4.html'><div class='new_pic'><img src='img/newsImg/wxx2.jpg'></div><div class='new_txt'><h3>医院出局！供应商货款要由医保部门结算了</h3><time>2017-11-27</time><p>医药人心中都有一个难言的痛，那就是货款结算难。医疗机构各种押账、拖延支付等的且不说，赖账不给钱的也不少。 为了解决医院货款支付老大难的问题，近年来，许多地方也在进行变革，或是发文予以规范。</p></div></a>",
                "<a href='industrynews_details3.html'><div class='new_pic'><img src='img/newsImg/wxx1.jpg'></div><div class='new_txt'><h3>国家中医药管理局关于推进中医药健康服务与互联网融合发展的指导意见！</h3><time>2017-12-09</time><p>全面贯彻落实党的十九大精神，以习近平新时代中国特色社会主义思想为指导，围绕健康中国建设，统筹推进中医药事业传承发展，以实现人人基本享有中医药服务为出发点和落脚点，充分发挥中医药特色优势，大力拓展中医药健康服务与互联网融合的广度和深度，着力创新中医药健康服务模式，释放发展潜力和活力，为人民群众提供全方位全周期健康服务。</p></div></a>",
                "<a href='industrynews_details1.html'><div class='new_pic'><img src='img/newsImg/wx3.jpg'></div><div class='new_txt'><h3>医生用药，五大限制来了！</h3><time>2017-11-27</time><p>所谓巧妇难为无米之炊，医务人员在临床工作中离不开一个“药”字，药品质量是否安全，品种是否齐全都是医生关心的问题。但近年，在有关部门对用药安全、医保控费、细菌耐药等综合监管愈发严格下，医生用药也受到了限制，这种限制在今年年底更显得尤为突出，纵观之下，我们看到医生用药即将面临5大限制。</p></div></a>",
               "<a href='industrynews_details2.html'><div class='new_pic'><img src='img/newsImg/wx7.jpg'></div><div class='new_txt'><h3>惊呆！这家药企过亿品种28个！</h3><time>2017-11-27</time><p>日前，中国生物制药公布了2017年前三季度业绩，营业收入114.47亿元，同比增长9.5%;研发投入14.69亿元，占营收12.8%。此外，有28个品种销售额过亿元，其中3个已超10亿元。</p></div></a>",
               "<a href='industrynews_details1.html'><div class='new_pic'><img src='img/newsImg/wx3.jpg'></div><div class='new_txt'><h3>医生用药，五大限制来了！</h3><time>2017-11-27</time><p>所谓巧妇难为无米之炊，医务人员在临床工作中离不开一个“药”字，药品质量是否安全，品种是否齐全都是医生关心的问题。但近年，在有关部门对用药安全、医保控费、细菌耐药等综合监管愈发严格下，医生用药也受到了限制，这种限制在今年年底更显得尤为突出，纵观之下，我们看到医生用药即将面临5大限制。</p></div></a>",
               "<a href='industrynews_details1.html'><div class='new_pic'><img src='img/newsImg/wx3.jpg'></div><div class='new_txt'><h3>医生用药，五大限制来了！</h3><time>2017-11-27</time><p>所谓巧妇难为无米之炊，医务人员在临床工作中离不开一个“药”字，药品质量是否安全，品种是否齐全都是医生关心的问题。但近年，在有关部门对用药安全、医保控费、细菌耐药等综合监管愈发严格下，医生用药也受到了限制，这种限制在今年年底更显得尤为突出，纵观之下，我们看到医生用药即将面临5大限制。</p></div></a>",
               "<a href='industrynews_details1.html'><div class='new_pic'><img src='img/newsImg/wx3.jpg'></div><div class='new_txt'><h3>医生用药，五大限制来了！</h3><time>2017-11-27</time><p>所谓巧妇难为无米之炊，医务人员在临床工作中离不开一个“药”字，药品质量是否安全，品种是否齐全都是医生关心的问题。但近年，在有关部门对用药安全、医保控费、细菌耐药等综合监管愈发严格下，医生用药也受到了限制，这种限制在今年年底更显得尤为突出，纵观之下，我们看到医生用药即将面临5大限制。</p></div></a>",
               "<a href='industrynews_details1.html'><div class='new_pic'><img src='img/newsImg/wx3.jpg'></div><div class='new_txt'><h3>医生用药，五大限制来了！</h3><time>2017-11-27</time><p>所谓巧妇难为无米之炊，医务人员在临床工作中离不开一个“药”字，药品质量是否安全，品种是否齐全都是医生关心的问题。但近年，在有关部门对用药安全、医保控费、细菌耐药等综合监管愈发严格下，医生用药也受到了限制，这种限制在今年年底更显得尤为突出，纵观之下，我们看到医生用药即将面临5大限制。</p></div></a>"
                
                ];
				for(var i = 0; i < dataList.length; i++){
					result.push(dataList[i]);
				}

				return result;
			}();

			var options = {
				dataSource: sources,
				className: 'paginationjs-theme-blue',
				callback: function(response, pagination){
					window.console && console.log(response, pagination);
					var dataHtml = '<ul>';

					$.each(response, function(index, item){
						dataHtml += '<li>'+item+'</li>';
					});

					dataHtml += '</ul>';

					container.prev().html(dataHtml);
				}
			};

			//$.pagination(container, options);

			container.addHook('beforeInit', function(){
				window.console && console.log('beforeInit...');
			});
			container.pagination(options);

			container.addHook('beforePageOnClick', function(){
				window.console && console.log('beforePageOnClick...');
				//return false
			});

			return container;
		}

		createDemo('demo1');

	});