<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student Details</title>
</head>
<body>

<h1>Student Details</h1>

<p>Name: <%= request.getAttribute("name") %></p>

<p>Registration Number:
    <%= request.getAttribute("registrationNumber") %>
</p>

<p>Email:
    <%= request.getAttribute("email") %>
</p>

<p>Course:
    <%= request.getAttribute("course") %>
</p>

</body>
</html>