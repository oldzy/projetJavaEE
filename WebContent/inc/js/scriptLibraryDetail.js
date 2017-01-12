function init() {
	
	eventFiche();
	eventSignOut();
	eventLib();
	eventMovie();
}

function eventFiche() {
	var tab = document.getElementsByClassName("fiche");

	var i = 0;
	for (i = 0; i < tab.length; i++) {
		tab[i].onclick = function() {
			$("formulaire").setAttribute("action", "MovieDetail");
			var hiddenField = createElement("input");
			hiddenField.setAttribute("type", "text");
			hiddenField.setAttribute("name", "id");
			hiddenField.setAttribute("value", this.id);
			$("formulaire").appendChild(hiddenField);
			hide(hiddenField);
			$("formulaire").submit();
		};
	}
}

function eventSignOut(){
	$("sign_out").onclick = function(){
		$("formulaire").setAttribute("action", "Deconnection");
		$("formulaire").submit();
	}
}

function eventLib(){
	$("lib").onclick = function(){
		$("formulaire").setAttribute("action", "Library");
		$("formulaire").submit();
	}
}

function eventMovie() {
	$("movie").onclick = function() {
		$("formulaire").setAttribute("action", "Movie");
		$("formulaire").submit();
	}
}