<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Sign-up</title>
</head>
<body>
<form action="/manager_signup">

  <label for="name">Name:</label><br>
  <input type="text" id="name" name="name"><br>
  
    <label for="email">Email:</label><br>
  <input type="text" id="email" name="email"><br>

    <label for="password">Set Password:</label><br>
  <input type="text" id="password" name="password"><br>
  
  <label for="phone">Phone Number:</label><br>
  <input type="text" id="phone" name="phone"><br>
  <input type="submit" value="Submit">
  
</form>

</body>
</html>