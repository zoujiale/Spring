'use strict';

//头部尾部相关源码请查看beifen.html
// 全局注册网页头部组件

var basePath = '/ycdjk'; 
Vue.component('header_components', {
	
    template:
    	'\
        <header>\
				<div class="container">\
					<div style="padding: 20px 0 10px 0;">\
					     <div id="logo">\
						     <h1>\
							    <a href="index.html"><img src="img/logo.jpg" title="广州迎春大健康服务有限公司" alt="广州迎春大健康服务有限公司"/></a>\
						    </h1>\
					    </div><div id="search">\
						      <form action="index.html">\
							     <input type="text" placeholder="请输入关键词"><input type="submit" value="">\
						      </form><div class="collection">\
							           <p><span><a onclick="SetHome(window.location)" href="javascript:void(0)">设置首页</a></span></p>\
							            <p><span><a onclick="AddFavorite(window.location,document.title)" href="javascript:void(0)">收藏本站</a></span></p>\
						     </div>\
					</div>\
					</div>\
				</div>\
				<div class="nav">\
					<nav class="container">\
						<ul>\
							<li class="on"><a href="index.html">首页</a></li><li>\
							<a href="about.html">关于迎春</a>\
							<dl>\
								<dd><a href="about.html">迎春简介</a></dd>\
								<dd><a href="history.html">迎春历史</a></dt>\
								<dd><a href="purpose.html">迎春文化</a></dd>\
								<dd><a href="contact.html">联系我们</a></dd>\
							</dl>\
							</li><li>\
							<a href="news.html">新闻动态</a>\
							<dl>\
								<dd><a href="news.html">公司动态</a></dd>\
								<dd><a href="industry_news.html">业界资讯</a></dd>\
								<dd><a href="video_news.html">视频资讯</a></dd>\
							</dl>\
							</li><li>\
							<a href="product.html">产品展示</a>\
							<dl>\
								<dd><a href="product.html">液体敷料</a></dd>\
								<dd><a href="product1.html">黄柏八味片</a></dd>\
								<dd><a href="product2.html">华蟾素胶囊</a></dd>\
							    <dd><a href="product3.html">3D导向模版</a></dd>\
							</dl>\
							</li><li>\
							<a href="recruit.html">招贤纳士</a>\
							<dl>\
								<dd><a href="recruit.html">用人之道</a></dd>\
								<dd><a href="talent.html">人才招聘</a></dd>\
								<dd><a href="online_apply.html">在线应聘</a></dd>\
							</dl>\
							</li><li>\
							<a href="contact.html">联系我们</a>\
							<dl>\
								<dd><a href="contact.html">联系方式</a></dd>\
								<dd><a href="friend.html">友情链接</a></dd>\
							</dl></li>\
						</ul>\
					</nav>\
				</div>\
			</header>\
			',
    data: function data() {
		return {};
	},
	methods: {
		aa: function aa() {
			alert('aa');
		}
	}
});
/*全局注册网页尾部组件*/
Vue.component('footer_components', {
  template: '\
    <footer>\
		<div class="company_list">\
			<div class="container">\
				<section class="type">\
					<ul>\
						<li>\
							<a href="about.html">走进迎春</a>\
						</li>\
						<li>\
							<a href="about.html">公司简介</a>\
						</li>\
						<li>\
							<a href="history.html">公司历史</a>\
						</li>\
						<li>\
							<a href="purpose.html">公司宗旨</a>\
						</li>\
						<li>\
							<a href="contact.html">联系我们</a>\
						</li>\
					</ul><ul>\
						<li>\
							<a href="news.html">新闻动态</a>\
						</li>\
						<li>\
							<a href="news.html">公司动态</a>\
						</li>\
						<li>\
							<a href="industry_news.html">业界资讯</a>\
						</li>\
						<li>\
							<a href="video_news.html">视频资讯</a>\
						</li>\
					</ul><ul>\
						<li>\
							<a href="product.html">产品展示</a>\
						</li>\
						<li>\
							<a href="product.html">液体敷料</a>\
						</li>\
						<li>\
							<a href="product1.html">黄柏八味片</a>\
						</li>\
						<li>\
							<a href="product2.html">华蟾素胶囊</a>\
						</li>\
						<li>\
							<a href="product3.html">3D导向模版</a>\
						</li>\
					</ul><ul>\
						<li>\
							<a href="recruit.html">招贤纳士</a>\
						</li>\
						<li>\
							<a href="recruit.html">用人之道</a>\
						</li>\
						<li>\
							<a href="talent.html">人才招聘</a>\
						</li>\
						<li>\
							<a href="online_apply.html">在线应聘</a>\
						</li>\
					</ul><ul>\
						<li>\
							<a href="contact.html">联系我们</a>\
						</li>\
						<li>\
							<a href="friend.html">联系方式</a>\
						</li>\
					</ul>\
				</section><section class="service">\
					<div class="tel">\
						<p>客服热线：<span>020-34479727</span></p>\
					</div>\
					<div class="weixin">\
						<div class="left"><img src="img/weixin.jpg" title="欢迎关注微信公众号" alt="欢迎关注微信公众号">\
						</div><div class="right">\
							<p>官方微信</p>\
							<p>扫一扫关注</p>\
						</div>\
					</div>\
				</section>\
			</div>\
		</div>\
		<div class="copyright">\
			<div class="container">\
				<div class="left-inf">\
					<ul>\
						<li>\
							<a href="contact.html">网站地图</a>\
						</li><li>\
							<a href="friend.html">友情链接</a>\
						</li><li>\
							<a href="contact.html">联系我们</a>\
						</li><li>\
							<a href="http://new.cnzz.com/v1/login.php?siteid=1263264418"><img src="img/zhanzhang.gif" title="站长统计" alt="站长统计"></a>\
						</li>\
					</ul>\
					<p>广州迎春大健康服务有限公司 版权所有 Copyright © 2016 - 2017\
						<a href="http://www.miitbeian.gov.cn">粤ICP备17128896号</a>\
					</p>\
					<p>地址：广东省广州市海珠区工业大道北67号凤凰创意园1号楼2楼</p>\
				</div><div class="right_inf">\
					<div class="move">\
						<!-- JiaThis Button BEGIN -->\
                        <div class="jiathis_style_24x24">\
	                        <a class="jiathis_button_qzone"></a>\
	                        <a class="jiathis_button_tsina"></a>\
	                        <a class="jiathis_button_tqq"></a>\
	                        <a class="jiathis_button_weixin"></a>\
	                        <a class="jiathis_button_renren"></a>\
	                        <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>\
                        </div>\
                        <!-- JiaThis Button END -->\
                         </div>\
				</div>\
			</div>\
		</div>\
	</footer>\
	',
});
// 创建根实例
new Vue({
	el: '#header_wrapper'
});
new Vue({
	el: '#footer_wrapper'
});