<%--
  Created by IntelliJ IDEA.
  User: Mayukh Nair
  Date: 10-12-2016
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking is being processed | Ashoka Shutlle Booking System</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.2.1/material.min.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <link rel="shortcut icon" href="http://i.imgur.com/RdtMmPg.png"/>
    <style type="text/css">

        body{
            margin: 0;
            padding: 0;
            font-family: lato;
            background: url(http://i.imgur.com/TWDjKyL.png) no-repeat center top;
        }

        h4{
            text-align:center;


            display: block;

        }

        #space{
            display:block ;
            width:100%;
            height: 15em;
        }

        #p2{
            padding-left:20em;
            margin: auto;
        }

    </style>

</head>
<body>

<div id ="space"></div>
<div id="p1">

    <h4 >Your request is being processed...please wait.</h4>
    <p align="center">Do not refresh, close, or move away from this page while the request is being processed.</p>

</div>
<!-- MDL Progress Bar with Indeterminate Progress -->

<div id="p2"  class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div>

<%
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookingDetails.jsp");
    String name = request.getParameter("name");
    String bookingStatus = request.getParameter("booking");
    String destination = request.getParameter("destination");
    String date = request.getParameter("date");
    String time = request.getParameter("time");
    request.setAttribute("name",name);
    request.setAttribute("booking",bookingStatus);
    request.setAttribute("destination",destination);
    request.setAttribute("date",date);
    request.setAttribute("time",time);
    requestDispatcher.forward(request,response);
%>
</body>
</html>

