<%@ page import="java.util.Properties" %>
<%@ page import="in.edu.ashoka.TransportApp.EmailSender" %>
<%--
  Created by IntelliJ IDEA.
  User: Mayukh Nair
  Date: 08-Dec-16
  Time: 1:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feedback Response</title>
<%
    String name = request.getParameter("mailername");
    String emailId = request.getParameter("email");
    String comments = request.getParameter("comments");
    EmailSender es = new EmailSender();
    if(es.sendFeedback(name,emailId,comments)==0){
        System.out.println("Email sent successfully!");
    %>
    <script>
        alert("Feedback recorded!");
        window.location = 'index.jsp';
    </script>
    <%
    }
    else {
        %>
        <script>
            alert("Error in sending feedback. Please try again.");
        </script>
        <%
    }

%>
</head>
<body>

</body>
</html>
