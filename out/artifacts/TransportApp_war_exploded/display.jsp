<%@ page import="java.io.PrintWriter" %>
<%@page contentType="text/html" language="java" pageEncoding="UTF-8" %>
<html lang="en">
	<head>
		<title>Ashoka Shuttle Service</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1,target-densitydpi=device-dpi" />
		<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
		<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
		<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" href="http://i.imgur.com/RdtMmPg.png"/>
        
       
        <script type="text/javascript" src="scripts.js"></script>
        
	</head>

	<style type="text/css">

		body{
			margin: 0;
			padding: 0;
			font-family: lato;
            background: url(http://i.imgur.com/TWDjKyL.png) no-repeat center top;
		}
		
		#Lbanner img{
			height:100vh;
			width:100%;
			position:relative;
		}

		#Mbanner{
			height: 100vh;
			background-image: url('images/2.jpg');
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-position: center;
			background-position-x: -17em;
			background-size: cover;
		}

		#Mbanner .container-fluid{
			background: rgba(0,0,0,0.7);
			height: inherit;
		}

		#Mbanner .container-fluid .jumbotron{
			background-color: transparent;
			font-family: lato;
			position: relative;
			margin-top:50%;
			color: white;
		}
        
        #disp{
            margin:auto;
        }

		
	</style>

	<body>
		
	<nav class="navbar navbar-default" role="navigation" style="position:fixed;width:100%;z-index:5;background-color:white">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand"><img src = "http://i.imgur.com/gnSKAbk.png" style = "height:30px;" /></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<li><a href=index.jsp>Home</a></li>
                    <li><a href=aboutus.html>About Us</a></li>
					<li><a href="#OurServices">Booking</a></li>
					<li><a href="#OurServices">Booking Status</a></li>
					<li><a href="#OurServices">Cancellation</a></li>
					<li><a href=feedback.jsp>Feedback</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div id = "disp">
    <%
            	String content = (String)request.getAttribute("processResult");
            	PrintWriter writer = response.getWriter();
		        writer.println(content);
            %>
    </div>        
    </body>
</html>   