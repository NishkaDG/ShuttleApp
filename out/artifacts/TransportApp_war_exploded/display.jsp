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

    #returnlink {
        text-align: center;
    }

    #rlink{
        display: inline-block;
    }



</style>
<body>

    <div class="container-fluid">
        <div id="returnlink">
            <a href="index.jsp" align="center" id="rlink">Return to home screen</a>
        </div>
        <%
            String content = (String)request.getAttribute("processResult");
            PrintWriter writer = response.getWriter();
            writer.println(content);
        %>
    </div><!-- /.container-fluid -->
</nav>
</body>
</html>