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
        <script src="http://smtpjs.com/smtp.js"></script>
        
        <script type="text/javascript" src="scripts.js"></script>
        
	</head>

	<style type="text/css">

		body{
			margin: 0;
			padding: 0;
			font-family: lato;
            background: url(http://i.imgur.com/TWDjKyL.png) no-rsepeat center top;
		}
		
		#Lbanner img{
			height:100vh;
			width:100%;
			position:relative;
		}

	
        #OurServices .container-fluid .jumbotron{
			background-color: transparent;
		}

		#OurServices .container-fluid #feedback-form{
			
			color: black;
			margin-top: 4em;
			padding-top: 3em;
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
                    <li><a href=aboutus.jsp>About Us</a></li>
					<li><a href=index.jsp>Booking</a></li>
					<li><a href=Bookingstatus.jsp>Booking Status</a></li>
					<li><a href=Cancellation.jsp>Cancellation</a></li>
					<li><a href="#feedback-form">Feedback</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div id="OurServices">
      <div class="container-fluid">
       <div class="row" id="feedback-form">
	    		<div class="col-md-6 col-md-offset-3">
	    			<center><h3>Feedback</h3></center>

	    			<form action="EmailFeedback.jsp" id="feedback" method="POST">
	    				<div class="form-group">
		    				<label>Name : </label>
		    				<input type="text" name="mailername" class="form-control" placeholder="Name" id ="name1" />
		    			</div>		    			
		    			<div class="form-group">
		    				<label>Email : </label>
		    				<input type="email" name="email" class="form-control" placeholder="example@exmail.com" id= "email"/>
		    			</div>
		    			<div class="form-group">
		    				<label>Comments : </label>
		    				<textarea placeholder="Enter your comments here" name="comments" rows="10" class="form-control" id = "comments" ></textarea> 
		    			</div>
		    			<div class="form-group text-center">
		    				<input type="submit" name="submit" class="btn btn-primary" value="Submit" onclick="submit_feedback()" />
		    			</div>
	    			</form>

	    		</div>
	    	</div>

	    </div>
    </div>	


	</body>
</html>