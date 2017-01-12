<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<% 
	 
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Projet Java EE 2016/2017</title>
<link rel="stylesheet" href="inc/css/styleWatchM.css">
</head>
<body>
	<div id="container">
	  <a id="close" href="javascript:history.back()"><img src="inc/images/iconClose.png" height="48px"/></a>
      <div id="progression" data-id="<% out.println(request.getAttribute("id").toString()); %>">
        <p>0%</p>
      </div>
      <video id="video" width="1280" height="720" controls>
        <source type="video/mp4">
      </video>
    </div>
    <script type="text/javascript" src="inc/js/common.js"></script>
    <script type="text/javascript" src="inc/js/scriptMovieWatch.js"></script>
    <script type="text/javascript">
	    document.onreadystatechange = function () {
	        if (document.readyState == "complete") {
	        init();
	      }
	    }
    </script>
</body>
</html>