//分页
$(function(){

		function createDemo(name){
			var container = $('#pagination-' + name);
			var sources = function(){
				var result = [];
                var dataList = ["<a href='news_details.html'><div class='new_pic'><img src='img/huiyi.jpg'></div><div class='new_txt'><h3>迎春大健康十月份总结大会</h3><time>2017-11-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展十月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
               "<a href='news_details1.html'><div class='new_pic'><img src='img/butie.jpg'></div><div class='new_txt'><h3>炎炎夏日送清凉，领导为员工发放高温津贴，你们领了吗？</h3><time>2017-10-10</time><p>眼下正值盛夏高温季节，大健康领导高度重视防暑降温工作，心系在高温下坚持工作的一线职工。 总责任人董事会明确要求公司相关部门，要及早准备，精心安排，做好夏令慰问工作。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>迎春大健康七月份总结大会</h3><time>2017-07-10</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>公司董事会亲自驾临开展七月底总结大会</h3><time>2017-07-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>公司董事会亲自驾临开展七月底总结大会</h3><time>2017-07-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>公司董事会亲自驾临开展七月底总结大会</h3><time>2017-07-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>公司董事会亲自驾临开展七月底总结大会</h3><time>2017-07-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>公司董事会亲自驾临开展七月底总结大会</h3><time>2017-07-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>",
                "<a href='news1.html'><div class='new_pic'><img src='img/pic1.jpg'></div><div class='new_txt'><h3>公司董事会亲自驾临开展七月底总结大会</h3><time>2017-07-1</time><p>公司董事会和各级领导在百忙中抽空到公司开展7月份工作总结大会.重新确立新时期企业宣传工作指导思想，董事长在会上强调：企业报、网站是企业的形象。报网建设是手段， 服务企业发展是目的。</p></div></a>"
                
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
					/*window.console && console.log(response, pagination);*/
					var dataHtml = '<ul>';

					$.each(response, function(index, item){
						dataHtml += '<li>'+item+'</li>';
					});

					dataHtml += '</ul>';

					container.prev().html(dataHtml);
				}
			};

			//$.pagination(container, options);

			/*container.addHook('beforeInit', function(){
				window.console && console.log('beforeInit...');
			});*/
			container.pagination(options);

			/*container.addHook('beforePageOnClick', function(){
				window.console && console.log('beforePageOnClick...');
				//return false
			});*/

			return container;
		}

		createDemo('demo1');

	});