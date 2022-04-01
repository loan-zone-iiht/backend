<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>The select element</h1>

<p>Login as12:</p>

<form action="/login_verify">
  <label for="username">User-name:</label><br>
  <input type="text" id="username" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password">
  <br><br>
  <input type="submit" value="Submit">

</form>

<form action="/customer_signup_controller">
<button type="submit" value="Submit">Sign up as Customer!</button>
</form>
<form action="/manager_signup_controller">
<button type="submit" value="Submit">Sign up as Manager!</button>
</form>

<p>Click the "Submit" button and the form-data will be sent to a page on the 
server called "loginverifycontroller.java".</p>

</body>
</html>