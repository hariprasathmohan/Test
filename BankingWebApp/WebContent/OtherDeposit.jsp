<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="MyStyle.css">
<script type="text/javascript">
function hideAll() {
	document.getElementById("action").style.display = "none";
	document.getElementById("create").style.display = "none";
	document.getElementById("withdraw").style.display = "none";
	document.getElementById("view").style.display = "none";
}
function hideOtherDep() {
	document.getElementById("create").style.display = "none";
	document.getElementById("withdraw").style.display = "none";
	document.getElementById("view").style.display = "none";
}
function showThis(id) {
	hideOtherDep();
	document.getElementById(id).style.display = "block";
}
</script>
<title>Other Deposit</title>
</head>
<body onload="hideAll()">
<div class="main">
<form class="form1" action="otherDeposit" method="post">
:
	<select class="un" id="type" name="type" onchange="showThis('action')">
	<option disabled selected>--Select Deposit account--</option>
	<option value="fixeddeposit">Fixed Deposit</option>
	<option value="recurringdeposit">Recurring Deposit</option>
	</select><br><br>
	<div id="action">
:
		<select class="un" id="action" name="action" onchange="showThis(this.options[this.selectedIndex].value)">
		<option disabled selected>--Select Option--</option>
		<option value="create">Create account</option>
		<option value="withdraw">Withdraw</option>
		<option value="view">View account details</option>
		</select><br><br>
	</div>

	<div id="create">
	
		<input class="un" type="text" name="numtxt" id="numtxt" size="15" placeholder="Number Of Months"/> <br> <br>
		
		<input class="un" type="text" name="amountxt" id="amountxt" size="15" placeholder="Amount"/> <br> <br>
		
		<input class="submit" type="submit" value="submit">
	</div>
	<div id="withdraw">
		<input class="un" type="text" name="amountxt" id="amountxt" size="15" placeholder="Amount"/> <br> <br>
		
		<input class="submit" type="submit" value="submit">
	</div>
	<div  id="view"> 
		<input class="submit" type="submit" value="Show Account">
	</div>
</form>
</div>
</body>
</html>