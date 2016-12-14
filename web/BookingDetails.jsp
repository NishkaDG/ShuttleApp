<%@ page import="java.io.PrintWriter" %>
<%@ page import="in.edu.ashoka.TransportApp.Bookings" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="in.edu.ashoka.TransportApp.MainApplication" %>
<%@ page import="java.awt.print.Book" %>
<%@ page import="in.edu.ashoka.TransportApp.DBScribe" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("display.jsp");
            String name = request.getParameter("name");
            String bookingStatus = request.getParameter("booking");
            String destination = request.getParameter("destination");
            String date = request.getParameter("date");
            System.out.println(name+" "+bookingStatus+" "+destination+" "+date);
            DBScribe dbScribe = new DBScribe();

            %>
                <div id ="space"></div>
                <div id="p1">

                    <h4 >Your request is being processed...please wait.</h4>
                    <p align="center">Do not refresh, close, or move away from this page while the request is being processed.</p>
                </div>
                <!-- MDL Progress Bar with Indeterminate Progress -->

                <div id="p2"  class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div>
            <%

            if("Booking".equals(bookingStatus)){
            System.out.println("User opted to book, it seems");
                int BookingResult = 3;
            try {
                    BookingResult = MainApplication.manager(0,name,date,destination);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            System.out.println(BookingResult);
            switch(BookingResult){
                            case 1: System.out.println("Booking successful!");
                            dbScribe.addToList("confirmed",name,destination,date);
                            String confirmText = "<h2 align='center'> Booking successful!</h2> <br>"+"<p align='center'>Name: "+name+"<br>"+" Destination: "+destination+"<br>"+"Date and time: "+date+"<br></p>";
                            request.setAttribute("processResult",confirmText);
                            requestDispatcher.forward(request,response);
                            System.out.println("Request sent");
                            %>
                            <script>
                                window.setTimeout(function(){

                                    // Move to a new location or you can do something else
                                    window.location.href = "display.jsp";

                                }, 5000);
                            </script>
                            <%
                                break;
                            case 0: System.out.println("Booking added to waitlist");
                                    dbScribe.addToList("waitlist",name,destination,date);
                                    String waitingText = "<h2 align='center'> You've been added to a waitlist </h2><br><br>"+"<p align='center'> Name: "+name+"<br>"+"Destination: "+destination+"<br>"+"Date and Time: "+date+"</p> <br> <br>"+" <p align='center'> You can check your status using Booking Status anytime, or talk to the guards minutes before the shuttle departs. </p>";
                                    request.setAttribute("processResult",waitingText);
                                    requestDispatcher.forward(request,response);
                                    System.out.println("Request sent");
                                    %>
                                    <script>
                                            window.setTimeout(function(){

                                        // Move to a new location or you can do something else
                                        window.location.href = "display.jsp";

                                    }, 5000);
                                    </script>
                                    <%
                                    break;
                            case 2: System.out.println("Waitlist is full; Booking rejected");
                                    String rejectedText = "<h2 align='center'> Your booking has been rejected </h2><br><br>"+"<p align='center'> No more passengers can be allotted on this time slot. However, you can contact the guards minutes before the time slot for possible seat vacancies. <p>";
                                    request.setAttribute("processResult",rejectedText);
                                    requestDispatcher.forward(request,response);
                                    System.out.println("Request sent");
                                    %>
                                    <script>
                                        window.setTimeout(function(){

                                            // Move to a new location or you can do something else
                                            window.location.href = "display.jsp";

                                        }, 5000);
                                    </script>
                                    <%
                                    break;
                            case -1: System.out.println("Booking error occurred");
                                    String errorText = "<h2 align='center'> An error occurred while processing your request </h2><br><br>"+"<p align='center'> You have submitted incorrect data while requesting the booking. If the problem persists repeatedly, please contact us through the Feedback section. <p>";
                                    request.setAttribute("processResult",errorText);
                                    requestDispatcher.forward(request,response);
                                    System.out.println("Request sent");
                                    %>
                                    <script>
                                            window.setTimeout(function(){

                                                // Move to a new location or you can do something else
                                                window.location.href = "display.jsp";

                                            }, 5000);
                                    </script>
                                    <%
                                    break;
                                            }
                                        }

              if("Booking Status".equals(bookingStatus)){
                  System.out.println("User opted to check booking, it seems");
                  int StatusResult = 3;
                  try {
                      StatusResult = MainApplication.manager(2,name,date,destination);
                      } catch (Exception e) {
                        e.printStackTrace();
                      }
                      System.out.println(StatusResult);
                      switch(StatusResult){
                          case 1: System.out.println("User is booked");
                                  String userBookedText = "<h2 align='center'> User Booking Check </h2> <br>" + "<p align='center'> You are booked currently on the shuttle. Details are as follows: </p><br><br>"+"<p align='center'>Name: "+name+"<br>"+"Destination: "+destination+"<br>"+"Date and Time: "+date+"<br> <br> </p>"+" <p align='center'> You can check your status using Booking Status anytime, or talk to the guards minutes before the shuttle departs. <p>";
                                  request.setAttribute("processResult",userBookedText);
                                  requestDispatcher.forward(request,response);
                                  System.out.println("Request sent");
                                    %>
                                        <script>
                                        window.setTimeout(function(){

                                            // Move to a new location or you can do something else
                                            window.location.href = "display.jsp";

                                        }, 5000);
                                    </script>
                                    <%
                                    break;
                            case 0: System.out.println("User booking not found");
                                     String userUnconfirmedText = "<h2 align='center'> User Booking Check </h2> <br>" + "<p align='center'> Your booking has not been found in our records. </p>";
                                     request.setAttribute("processResult",userUnconfirmedText);
                                     requestDispatcher.forward(request,response);
                                     System.out.println("Request sent");
                                        %>
                                            <script>
                                            window.setTimeout(function(){

                                            // Move to a new location or you can do something else
                                            window.location.href = "display.jsp";

                                            }, 5000);
                                            </script>
                                        <%
                                     break;
                            case -1: System.out.println("User did not request status properly");
                                String userNotFoundText = "<h2 align='center'> User Booking Check </h2> <br>" + "<p align='center'> You have submitted incorrect data while requesting the booking. If the problem persists repeatedly, please contact us through the Feedback section. </p>";
                                request.setAttribute("processResult",userNotFoundText);
                                requestDispatcher.forward(request,response);
                                System.out.println("Request sent");
                                %>
                                    <script>
                                        window.setTimeout(function(){

                                            // Move to a new location or you can do something else
                                            window.location.href = "display.jsp";

                                        }, 5000);
                                    </script>
                                <%
                                break;
                      }
            }

            if("Cancellation".equals(bookingStatus)){
                System.out.println("User opted to cancel booking, it seems");
                int StatusResult = 3;
                try {
                    StatusResult = MainApplication.manager(1,name,date,destination);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(StatusResult);
                switch(StatusResult){
                    case 1: System.out.println("User has cancelled booking");
                        dbScribe.addToList("cancellation",name,destination,date);
                        String userBookedText = "<h2 align='center'> Cancellation Request </h2> <br>" + "<p align='center'> Your booking has been successfully cancelled. </p>";
                        request.setAttribute("processResult",userBookedText);
                        requestDispatcher.forward(request,response);
                        System.out.println("Request sent");
                        %>
                            <script>
                                window.setTimeout(function(){

                                    // Move to a new location or you can do something else
                                    window.location.href = "display.jsp";

                                }, 5000);
                            </script>
                        <%
                        break;
                    case -1: System.out.println("User cancellation error");
                        String userUnconfirmedText = "<h2 align='center'> User Booking Check </h2> <br>" + "<p align='center'> Your booking based on provided details was not retrieved by the system. Please try again </p>";
                        request.setAttribute("processResult",userUnconfirmedText);
                        requestDispatcher.forward(request,response);
                        System.out.println("Request sent");
                    %>
                    <script>
                        window.setTimeout(function(){

                            // Move to a new location or you can do something else
                            window.location.href = "display.jsp";

                        }, 5000);
                    </script>
                    <%
                    break;
                }
            }
                    %>

</body>
</html>

