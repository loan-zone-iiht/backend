<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Sign-up</title>
</head>
<body>
<form action="/customer_signup">
  <label for="name">name:</label><br>
  <input type="text" id="name" name="name"><br>
    <label for="email">Email:</label><br>
  <input type="text" id="email" name="email"><br>
  
    <label for="password">Set Password:</label><br>
  <input type="text" id="password" name="password"><br>

  
  <label for="panCard">PAN CARD No:</label><br>
  <input type="text" id="panCard" name="panCard"><br>
  
  <label for="cibilScore">Set civil score:</label><br>
  <input type="number" id="cibilScore" name="cibilScore"><br>
  
    <label for="salary">Current Salary:</label><br>
  <input type="number" id="salary" name="salary"><br>
  
    <label for="phone">Mobile number:</label><br>
  <input type="text" id="phone" name="phone"><br>

  <br><br>
  <input type="submit" value="Submit">
  
</form>
</body>
</html>