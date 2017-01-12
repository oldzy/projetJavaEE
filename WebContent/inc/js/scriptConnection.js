function init1() {
	signInForm();
	$("sign_in_tab").onclick = function() {
		signInForm();
		$("error").innerHTML = "";
	};
	$("sign_up_tab").onclick = function() {
		signUpForm();
		$("error").innerHTML = "";
	};
}

function init2() {
	signUpForm();
	$("sign_in_tab").onclick = function() {
		signInForm();
		$("error").innerHTML = "";
	};
	$("sign_up_tab").onclick = function() {
		signUpForm();
		$("error").innerHTML = "";
	};
}

function signInForm() {
	hide($("last_name"));
	hide($("first_name"));
	show($("e_mail"));
	hide($("date"));
	show($("password"));
	hide($("conf_password"));
	show($("sign_in"));
	hide($("sign_up"));
	$("sign_in_ligne").style.backgroundColor = "#F73859";
	$("sign_in_tab").style.color = "#F73859";
	$("sign_up_ligne").style.backgroundColor = "#FFFFFF";
	$("sign_up_tab").style.color = "#FFFFFF";
	$("container_form").style.marginTop = "-144px";
	$("formulaire").setAttribute("action", "Connection");
}

function signUpForm() {
	show($("last_name"));
	show($("first_name"));
	show($("e_mail"));
	show($("date"));
	show($("password"));
	show($("conf_password"));
	hide($("sign_in"));
	show($("sign_up"));
	$("sign_in_ligne").style.backgroundColor = "#FFFFFF";
	$("sign_in_tab").style.color = "#FFFFFF";
	$("sign_up_ligne").style.backgroundColor = "#F73859";
	$("sign_up_tab").style.color = "#F73859";
	$("container_form").style.marginTop = "-268px";
	$("formulaire").setAttribute("action", "Inscription");

}
