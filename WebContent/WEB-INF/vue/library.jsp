<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="be.belouh.bean.Mediatheque" 
	import="java.util.ArrayList" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Projet Java EE 2016/2017</title>
<link rel="stylesheet" href="inc/css/styleMain.css">
</head>
<body>
	<form id="formulaire" method="post">
      <div class="navigation">
        <ul>
          <li><a id="movie" class="non_active" href="#">Movies</a></li>
          <li><a id="lib" class="active" href="#">Libraries</a></li>
          <li style="float:right"><a id="sign_out" class="non_active" href="#">Sign out</a></li>
        </ul>
      </div>
      <%
      	@SuppressWarnings("unchecked")
      	ArrayList<Mediatheque> list = (ArrayList<Mediatheque>)session.getAttribute("mediatheque");
      	
      	for(Mediatheque m : list){
      		out.println("<div class=\"cont_fiche\" style=\"margin-left:25px;\">");
      		out.println("<div id=\"" + m.getNom() + "\" class=\"fiche libraries\" style=\"background-image:url('inc/images/iconLibrary.jpg');background-position:center;\">");
      		out.println("<div class=\"overlay\">");
      		out.println("</div>");
      		out.println("</div>");
      		out.println("<div class=\"cont_titre\">");
      		out.println("<p class=\"titre\">" + m.getNom() + "</p>");
      		out.println("</div>");
      		out.println("</div>");
      	}
      %>
      <div class="cont_fiche" style="margin-left:25px;">
      	<div id="add" class="fiche" style="background-image:url('inc/images/iconAdd.png');background-position:center;background-size:auto">
      		<div class="overlay">
      		</div>
      	</div>
      	<div class="cont_titre">
      		<input id="library_name" type="text" placeholder="Name of the library" name="library_name" autocomplete="off"/>
      	</div>
      </div>
    </form>
    <script type="text/javascript" src="inc/js/common.js"></script>
    <script type="text/javascript" src="inc/js/scriptLibrary.js"></script>
    <script type="text/javascript">
	    document.onreadystatechange = function () {
	        if (document.readyState == "complete") {
	        init();
	      }
	    }
    </script>
</body>
</html>