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
        <script src="jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="jquery.datetimepicker.css"/>
         <script src="jquery.datetimepicker.full.js"></script>
 
   
        
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

		
        #OurServices input[type="radio"]{
			margin:0 0.5em;
		}

		#OurServices .container-fluid .jumbotron{
			background-color: transparent;
		}

		#OurServices .container-fluid #feedback-form{
			background-color: #333;
			color: white;
			margin-top: 4em;
			padding-top: 3em;
		}

	</style>

	<script type="text/javascript">
		
		$(document).ready(function () {
            $('a[href^="#"]').on('click', function (e) {
                e.preventDefault();

                var target = this.hash,
                $target = $(target);

                $('html, body').stop().animate({
                    'scrollTop': $target.offset().top
                }, 1200, 'swing', function () {
                    window.location.hash = target;

                });
            });
        });
    </script>
    <script>
    $(function(){

    $('#datetimepicker').datetimepicker({
    format:'d-m-Y H:i',
    closeOnWithoutClick :false,
    defaultTime:'10:00',
    allowTimes:[
  '06:30',
'07:00',
'07:20',
'07:30',
'07:40',
'08:00',
'08:20',
'08:40',
'09:00',
'09:20',
'09:40',
'09:30',
'10:00',
'10:20',
'10:30',
'10:40',
'11:00',
'11:20',
'11:40',
'12:00',
'13:00',
'14:00',
'14:30',
'15:00',
'15:30',
'16:00',
'16:30',
'17:00',
'17:20',
'17:30',
'17:40',
'18:00',
'18:20',
'18:30',
'18:40',
'19:00',
'19:30',
'20:00',
'20:30',
'21:00',
'21:30',
'22:00',
 '22:45',
'23:00'
    ]
    
     });
    });
    </script>
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
					<li><a href="#OurServices">Booking</a></li>
					<li><a href=Bookingstatus.jsp>Booking Status</a></li>
					<li><a href=Cancellation.jsp>Cancellation</a></li>
					<li><a href=feedback.jsp>Feedback</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div id="OurServices">
	    <div class="container-fluid">
	    	<div class="jumbotron text-center">
	    		<h2>Our Services</h2>
	    	</div>

	    	<div class="row">
		    	<div class="col-md-6 col-md-offset-3">
		    		<center>
		    			<p>Please choose one of the options below according to your needs.</p><hr />
		    			<p><a href="https://www.ashoka.edu.in/shuttle-service/">Please click here for the shuttle schedule</a></p>
		    		</center>

		    		<form action="BookingDetails.jsp" method="POST" id="service">
		    			
		    			<div class="form-group text-center">
			    			<input type="radio" name="booking" class="ourservices" value="Booking" checked="checked">Booking</input>
			    			<input type="radio" name="booking" class="ourservices" value="Booking Status" >Booking Status</input>
			    			<input type="radio" name="booking" class="ourservices" value="Cancellation" >Cancellation</input>
		    			</div>

		    			<div class="form-group">
		    				<label>Name : </label>
		    				<input type="text" name="name" id = "name"  class="form-control" placeholder="Name" />
		    			</div>
		    			<div class="form-group">
		    				<label>Destination : </label>
		    				<select name="destination" class="form-control" id="destination">
		    					<option value="Campus">Campus</option>
		    					<option value="Jahangirpuri">Jahangirpuri</option>
		    				</select>
		    			</div>
		    			<div class="form-group">
		    				<label>Date and Time : </label>
		    				<input type="text"  name="date" class="form-control" id="datetimepicker" placeholder="Pick a date" />
		    			</div>
		    			
		    			<div class="form-group text-center">
		    				<input type="submit" name="submit" class="btn btn-primary" value="Submit" onclick="submit_service()" />
		    			</div>

		    		</form>

		    	</div>
	    	</div>	
        </div>
    </div>        


	</body>
</html>