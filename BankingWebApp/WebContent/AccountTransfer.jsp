<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer</title>
<link rel="stylesheet" href="MyStyle.css">
</head>
<body>
<div class="main">
<form class="form1" method="post" action="accountTransfer">
		<input class="un" type="text" name="name" size="15" placeholder="Name" /> <br> <br>
		
		<input class="un" type="text" name="accountNumber" size="15" placeholder="Account Number" required="required" /> <br> <br>
		
		<input class="un" type="text" name="ifsc" size="15" placeholder="IFSC code" required="required" /> <br> <br>
		
		<input class="un" type="text" name="amount" size="15" placeholder="Amount" required="required" /> <br> <br>
		
		<input class="submit" type="submit" value="Send" onclick="" > <br><br>
</form>
</div>
</body>
</html>