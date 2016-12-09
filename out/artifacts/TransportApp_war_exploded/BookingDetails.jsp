<%@ page import="java.io.PrintWriter" %>
<%@ page import="in.edu.ashoka.TransportApp.Bookings" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="in.edu.ashoka.TransportApp.MainApplication" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" errorPage="Error.jsp" %>
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
        String name = request.getParameter("name");
        String bookingStatus = request.getParameter("booking");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        System.out.println(name+" "+bookingStatus+" "+destination+" "+date+" "+time);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date oldDate = null;
            try {
                oldDate = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            simpleDateFormat.applyPattern("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(oldDate);
        System.out.println(formattedDate+" "+time);

        if("Booking".equals(bookingStatus)){
            System.out.println("User opted to book, it seems");
            String combiDT = formattedDate.replaceAll("\\s+", "")+" "+time.replaceAll("\\s+", "");
            //String finalDT = combiDT.replaceAll("\\s+","");
            System.out.println("finalDT = "+combiDT);
            int BookingResult = MainApplication.BookUser(name,combiDT,destination);
            switch(BookingResult){
                case 1: System.out.println("Booking successful!");
                String confirmText = "<h2> Booking successful!</h2>\n\n"+"Name: "+name+"\n"+"Destination: "+destination+"\n"+"Date: "+formattedDate+"\n"+"Time: "+time;
                request.setAttribute("processResult",confirmText);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
                requestDispatcher.forward(request,response);
                System.out.println("Request sent");
                %>
                <script>
                    window.location = 'display.jsp';
                </script>
                <%
                            case -1: System.out.println("Booking alert");
        }
        }
        %>

</body>
</html>

