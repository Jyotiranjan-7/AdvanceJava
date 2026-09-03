<%@ page import="jakarta.servlet.http.Cookie" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student Home</title>
</head>

<body>

<h1>Welcome Student</h1>

<%
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {

        for (Cookie ck : cookies) {

            if ("registrationNumber".equals(ck.getName())) {
%>

                <h2>
                    Registration Number: <%= ck.getValue() %>
                </h2>

<%
            }
        }
    }
%>

</body>
</html>