function init() {
	getStatus();
}

function getStatus() {
	var xhr = getXHR();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				setTimeout(function(){}, 2000);
				if(xhr.responseText.trim()!=''){
					var jsonObject = JSON.parse(xhr.responseText);
					update(jsonObject);
				}else{
					getStatus();
				}
			}
		}
	};

	xhr.open("GET", "MovieWatch?id=" + $("progression").dataset.id);
	xhr.send(null);
}

function update(json) {
	if (json.is_finished) {
		var i = 0;
		var path = "http://qlf.ddns.net/Downloads/"

		for (i; i < json.files.length; i++) {
			if (json.files[i].path.indexOf(".mp4") !== -1) {
				path += json.files[i].path
			}
		}

		$("video").children[0].setAttribute("src", path);
		$("video").load();
		$("video").play();
	} else {
		$("progression").children[0].innerHTML = Math.round(json.progress) + "%";
		getStatus();
	}
}