<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="be.belouh.modele.Connection" 
	import="java.util.Map" 
	import="be.belouh.modele.Inscription" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Projet Java EE 2016/2017</title>
<link rel="stylesheet" href="inc/css/styleConnection.css">
</head>
<body>
	<div class="container">
      <div id="container_form" class="container_form">
        <form id="formulaire" class="form" method="post">
          <div class="container_tab">
            <ul class='ul_tabs'>
              <li>
                <a id="sign_in_tab" href="#">SIGN IN</a>
                <span id="sign_in_ligne" class="ligne_bas"></span>
              </li>
              <li>
                <a id="sign_up_tab" href="#">SIGN UP</a>
                <span id="sign_up_ligne" class="ligne_bas"></span>
              </li>
            </ul>
          </div>
          <div class="container_input">
            <input id="last_name" type="text" class="input_form" placeholder="Last name" name="last_name" autocomplete="off"/>
            <input id="first_name" type="text" class="input_form" placeholder="First name" name="first_name" autocomplete="off"/>
            <input id="e_mail" type="text" class="input_form" placeholder="E-mail" name="e_mail" autocomplete="off"/>
            <input id="date" type="text" class="input_form" placeholder="Birthdate" onfocus="(this.type='date')" onblur="(this.type='text')" name="date">
            <input id="password" type="password" class="input_form" placeholder="Password" name="password" />
            <input id="conf_password" type="password" class="input_form" placeholder="Confirm password" name="conf_password" />
          </div>
          <div class="container_button">
            <button id="sign_in" class="button" type="submit" name="button">SIGN IN</button>
            <button id="sign_up" class="button" type="submit" name="button">SIGN UP</button>
          </div>
        </form>
        <div id="error">
        <% 
        	Connection c = (Connection)request.getAttribute("connection");
        	Inscription i = (Inscription)request.getAttribute("inscription");
        	if(c != null)
        	{
        		for (String value : c.getErreurs().values()) {
        		    out.println("<p>" + value + "</p>");
        		}
        	}
        	if(i != null){
        		for (String value : i.getErreurs().values()) {
        		    out.println("<p>" + value + "</p>");
        		}
        	}
        %>
        </div>
      </div>
    </div>
    <script type="text/javascript" src="inc/js/common.js"></script>
    <script type="text/javascript" src="inc/js/scriptConnection.js"></script>
    <script type="text/javascript">
    <%
    	if(i != null)
    		out.println("document.onreadystatechange = function () { if (document.readyState == \"complete\") { init2(); } }");
    	else
    		out.println("document.onreadystatechange = function () { if (document.readyState == \"complete\") { init1(); } }");
    %>
    </script>
</body>
</html>