<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pgc" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="${pgc}/img/t.ico" type="image/x-icon" />
		<link rel="stylesheet" href="${pgc }/css/reset.css" />
		<link rel="stylesheet" href="${pgc }/css/template.css" />
		<link rel="stylesheet" href="${pgc }/css/about.css" />
		<link rel="stylesheet" href="${pgc }/css/pagination.css" />
		<link rel="stylesheet" href="${pgc }/css/news.css" />
		<script type="text/javascript" src="${pgc }/js/collection.js" ></script>
		<script type="text/javascript" src="${pgc }/js/jquery-2.2.2.min.js"></script>
		<script type="text/javascript" src="${pgc }/js/vue.js" ></script>
		<title>新闻中心</title>
		<style>
			
			.nav li:nth-child(1){
				background: #188f36;
			}
			.nav li:nth-child(3){
				background-color: rgba(9, 117, 36, 0.61);
			}
		</style>
	</head>
	<body>
		<!--头部  B-->
		<div id="header_wrapper">
			<header_components></header_components>
		</div>
		<!--头部  E-->
		
		<!--内容部  B-->
		<div id="body_wrapper">
			<article>
				<section id="bg">
					<div class="bg">
						<img src="http://www.gzycdjk.com/img/news.jpg">
					</div>
				</section>
				<section id="nav_bg">
					<div class="container" id="nav">
						<ul>
							<li class="on"><a href="news.html">公司新闻</a></li>
							<li><a href="industry_news.html">业界资讯</a></li>
							<li><a href="video_news.html">视频资讯</a></li>
						</ul>
					</div>
				</section>
				<section id="news">
					<div id="wrapper" class="container">
							<section>
								<div class="data-container">
									<ul>
										<c:forEach items="${article.recordList}" var="a" >
											<li><a href="${pgc }/identity/news"><div class="new_pic"><img src="${a.imageUrl }"></div><div class="new_txt"><h3>${a.title }</h3><time>${a.createDate }</time><p>${a.simpleDescripe }</p></div></a></li>
										</c:forEach>
									</ul>
								</div>
								<div id="pagination-demo1"></div>
							</section>
						</div>
						
				</section>
			</article>
		</div>
		<!--内容部  E-->
		
		<!--尾部  B-->
		<div id="footer_wrapper">
			<footer_components></footer_components>
		</div>
		<!--尾部  E-->
		<!--分享jiathis-->
		<script type="text/javascript" src="${pgc}/code/jia.js" charset="utf-8"></script>
		<!--引入vue组件模板-->
		<script type="text/javascript" src="${pgc}/js/vueCom.js" ></script>
	</body>
</html>
