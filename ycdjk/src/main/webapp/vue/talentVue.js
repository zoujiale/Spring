var vue = new Vue({
	el: '#position',
	data: {
		positionList: [],
		isModal: true,
		isOverlay: false,
		currentIndex:0
	},
	filters: {},
	mounted: function() {
		this.$nextTick(function(){
			this.positionView();
		});
	},
	methods: {
		positionView: function() {
			var _this = this;
			this.$http.get("data/position.json").then(function(res){
				_this.positionList = res.body.result.list;
			})
		},
		bompbox: function(m){
			var _this = this;
			var modal = document.getElementById(m);
			modal.style.display = "block";
			_this.isOverlay = true;
		},
		closebox: function(m) {
			var _this = this;
			var modal = document.getElementById(m);
			modal.style.display = "none";
			_this.isOverlay = false;
		}
	},
});
