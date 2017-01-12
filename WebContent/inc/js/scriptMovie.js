function init() {
	display();
	
	eventFiche();
	eventNav();
	eventSignOut();
	eventLib();
	eventMovie();
	
	window.onresize = function() {
		display();
	}
}

function display() {
	var tab = document.getElementsByClassName("cont_fiche");

	var nb = Math.floor(document.documentElement.clientWidth / 155);
	if (50 % nb != 0){
		while(50 % nb != 0)
			nb--;
	}
	var reste = document.documentElement.clientWidth - (155 * nb);
	margin = reste / nb;
	var i = 0;
	for (i = 0; i < tab.length; i++) {
		tab[i].style.marginLeft = margin / 2 + "px";
		tab[i].style.marginRight = margin / 2 + "px";
	}
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

function eventNav(){
	var tab = getChildrenByTagName($("footer"), "a");
	
	var i = 0;
	for(i = 0; i < tab.length; i++){
		tab[i].onclick = function() {
			$("formulaire").setAttribute("action", "Movie");
			var hiddenField = createElement("input");
			hiddenField.setAttribute("type", "text");
			hiddenField.setAttribute("name", "numero");
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