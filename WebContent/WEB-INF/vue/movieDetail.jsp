<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap"
         import="be.belouh.bean.Mediatheque" 
         import="java.util.ArrayList"
%>
<% 
	@SuppressWarnings("unchecked")
	HashMap<String, String> film = (HashMap<String, String>)request.getAttribute("film"); 
	@SuppressWarnings("unchecked")
	ArrayList<Mediatheque> listMed = (ArrayList<Mediatheque>)session.getAttribute("mediatheque");
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Projet Java EE 2016/2017</title>
<link rel="stylesheet" href="inc/css/styleDetailM.css">
</head>
<body>
	<div class="container">
		<img src="<% out.println(film.get("fanart")); %>"/>
		<a id="close" href="javascript:history.back()"><img src="inc/images/iconClose.png" height="48px"/></a>
		<form id="formulaire" method="post">
      		<div id="poster">
      			<img src="<% out.println(film.get("poster")); %>" />
      		</div>
      		<div id="detail">
      			<p id="titre"><% out.println(film.get("title")); %></p>
      			<div id="info">
      				<p><% out.println(film.get("year")); %>&nbsp;&nbsp;&nbsp;&#8226;&nbsp;&nbsp;&nbsp;</p>
      				<p><% out.println(film.get("runtime")); %>min&nbsp;&nbsp;&nbsp;&#8226;&nbsp;&nbsp;&nbsp;</p>
      				<p><% out.println(film.get("genres").substring(0, film.get("genres").length()-2)); %>&nbsp;&nbsp;&nbsp;&#8226;&nbsp;&nbsp;&nbsp;</p>
      				<a href="<% out.println(film.get("imdb_page"));%>"><img src="inc/images/iconImdb.png" height="16px"/></a>
      				<p>&nbsp;&nbsp;&nbsp;&#8226;&nbsp;&nbsp;&nbsp;<% out.println(film.get("note") + "%"); %></p>
      			</div>
      			<p id="synopsis"><% out.println(film.get("synopsis")); %></p>
      			<div id="reg_button">
      				<div id="visionner" data-magnet="<% out.println(film.get("magnet")); %>">Watch Now</div>
	      			<div class="dropdown">
					  <div id="add_to" class="dropbtn">Add to</div>
					  <div id="myDropdown" class="dropdown-content" style="display: none;">
					    <%
					    for (Mediatheque m : listMed) {
							out.println("<a class=\"mediatheque\" href=\"#\" data-med=\"" + m.getNom() +"\" data-id=\"" + film.get("id") +"\" data-title=\"" + film.get("title") +"\">" + m.getNom() + "</a>");
						}
					    %>
					  </div>
					</div>
      			</div>
      		</div>
    	</form>
	</div>
    <script type="text/javascript" src="inc/js/common.js"></script>
    <script type="text/javascript" src="inc/js/scriptMovieDetail.js"></script>
    <script type="text/javascript">
	    document.onreadystatechange = function () {
	        if (document.readyState == "complete") {
	        init();
	      }
	    }
    </script>
</body>
</html>