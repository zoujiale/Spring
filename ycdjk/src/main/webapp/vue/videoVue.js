var vm3 = new Vue({
	el: '#news',
	data: {
		videoList: []
	},
	filters: {

	},
	mounted: function() {
		this.$nextTick(function() {
			this.videoView();
		});
	},
	methods: {
		videoView: function() {
			var _this = this;
			this.$http.get("data/video.json").then(function(response) {
				_this.videoList = response.body.result.list3;
			});
		},
		beginPlay: function(index, btnId) {
			var myVideo1 = document.getElementById(index);
			var btn1 = document.getElementById(btnId);
			myVideo1.autoplay = false;
			myVideo1.addEventListener('pause', function() {
				btn1.style.display = "block";
			});
			myVideo1.addEventListener('play', function() {
				btn1.style.display = "none";
			});
			myVideo1.addEventListener('ended', function() {
				btn1.style.display = "block";
				myVideo1.controls = false;
				myVideo1.currentTime = 9;
			});
			if(myVideo1.paused) {
				myVideo1.play();
				myVideo1.controls = true;
				btn1.style.display = "none";
			}
		}
	}
});