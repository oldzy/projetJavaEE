function init() {
	posterSize();
	detailSize();
	eventWatch();
	eventDropDown();
	eventAdd();

	window.onresize = function() {
		posterSize();
		detailSize();
	}
}

function posterSize() {
	$("poster").style.width = $("poster").children[0].width + "px";
}

function eventDropDown() {
	$("add_to").onclick = function() {
		toggle($("myDropdown"));
	}
}
function detailSize() {
	$("detail").style.width = $("formulaire").offsetWidth
			- $("poster").children[0].width - 25 + "px";
}

function eventWatch() {
	$("visionner").onclick = function() {
		$("formulaire").setAttribute("action", "MovieWatch");
		var hiddenField = createElement("input");
		hiddenField.setAttribute("type", "text");
		hiddenField.setAttribute("name", "magnet");
		hiddenField.setAttribute("value", this.dataset.magnet);
		$("formulaire").appendChild(hiddenField);
		hide(hiddenField);
		$("formulaire").submit();
	}
}

function eventAdd() {
	var tab = document.getElementsByClassName("mediatheque");

	var i = 0;
	for (i = 0; i < tab.length; i++) {
		tab[i].onclick = function() {
			add(this.dataset.med, this.dataset.id, this.dataset.title);
		};
	}
}

function add(med, id, title) {
	var xhr = getXHR();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				
			}
		}
	};

	xhr.open("GET", "Library?id=" + id +"&med=" + med + "&title=" + title);
	xhr.send(null);
}