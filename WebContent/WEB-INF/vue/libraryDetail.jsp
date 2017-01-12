<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" 
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
          <li><a id="lib" class="non_active" href="#">Libraries</a></li>
          <li style="float:right"><a id="sign_out" class="non_active" href="#">Sign out</a></li>
        </ul>
      </div>
      <%
      	@SuppressWarnings("unchecked")
      	ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>)request.getAttribute("mediatheque");
      	
      	for(HashMap<String, String> film : list){
      		out.println("<div class=\"cont_fiche\" style=\"margin-left:25px;\">");
      		out.println("<div id=\"" + film.get("id") + "\" class=\"fiche\" style=\"background-image:url('" + film.get("poster") + "')\">");
      		out.println("<div class=\"overlay\">");
      		out.println("</div>");
      		out.println("</div>");
      		out.println("<div class=\"cont_titre\">");
      		out.println("<p class=\"titre\">" + film.get("title") + "</p>");
      		out.println("<p class=\"annee\">" + film.get("year") + "</p>");
      		out.println("</div>");
      		out.println("</div>");
      	}
      %>
    </form>
    <script type="text/javascript" src="inc/js/common.js"></script>
    <script type="text/javascript" src="inc/js/scriptLibraryDetail.js"></script>
    <script type="text/javascript">
	    document.onreadystatechange = function () {
	        if (document.readyState == "complete") {
	        init();
	      }
	    }
    </script>
</body>
</html>