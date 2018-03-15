//分页
$(function(){

		function createDemo(name){
			var container = $('#pagination-' + name);
			var sources = function(){
				var result = [];
                var dataList = ["<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
               "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
               "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
               "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
               "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
               "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
                "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
                 "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
                  "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>",
                   "<a href='desaien.html'><img src='img/dse.jpg'><h4>德赛恩液体敷料</h4></a>"
                
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