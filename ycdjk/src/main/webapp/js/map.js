var map = new BMap.Map("map_address");
var point = new BMap.Point(113.263264, 23.100166);
map.centerAndZoom(point, 20);
//添加地图类型控件
map.addControl(new BMap.MapTypeControl({
	mapTypes: [
		BMAP_NORMAL_MAP,
		BMAP_HYBRID_MAP
	]
}));
map.setCurrentCity("广州");
map.enableScrollWheelZoom(true);
map.addControl(new BMap.NavigationControl(BMAP_NAVIGATION_CONTROL_PAN));
var marker = new BMap.Marker(point);
map.addOverlay(marker);
var opts = {
	width: 250, // 信息窗口宽度    
	height: 150, // 信息窗口高度 
	title: "广州迎春大健康服务有限公司" // 信息窗口标题   
}
var info = "<p style='font-size:12px;line-height:25px;'>地址：广州省广州市海珠区工业大道北67号凤凰创意园1号楼2楼</p>" +
	"<p style='font-size:12px;line-height:25px;'>电话：020-34479727</p>" +
	"<p style='font-size:12px;line-height:25px;'>传真：020-34479727</p>" +
	"<p style='font-size:12px;line-height:25px;'>邮编：510250</p>";
var infoWindow = new BMap.InfoWindow(info, opts);
marker.addEventListener("click", function() {
	map.openInfoWindow(infoWindow, point);
});