function init() {
	eventLibraries();
	eventAdd();
	eventSignOut();
	eventMovie();
	eventLib();
}

function eventSignOut() {
	$("sign_out").onclick = function() {
		$("formulaire").setAttribute("action", "Deconnection");
		$("formulaire").submit();
	}
}

function eventMovie() {
	$("movie").onclick = function() {
		$("formulaire").setAttribute("action", "Movie");
		$("formulaire").submit();
	}
}

function eventLib(){
	$("lib").onclick = function(){
		$("formulaire").setAttribute("action", "Library");
		$("formulaire").submit();
	}
}

function eventAdd() {
	$("add").onclick = function() {
		$("formulaire").setAttribute("action", "Library");
		$("formulaire").submit();
	}
}

function eventLibraries() {
	var tab = document.getElementsByClassName("libraries");

	var i = 0;
	for (i = 0; i < tab.length; i++) {
		tab[i].onclick = function() {
			$("formulaire").setAttribute("action", "LibraryDetail");
			var hiddenField = createElement("input");
			hiddenField.setAttribute("type", "text");
			hiddenField.setAttribute("name", "name");
			hiddenField.setAttribute("value", this.id);
			$("formulaire").appendChild(hiddenField);
			hide(hiddenField);
			$("formulaire").submit();
		};
	}
}