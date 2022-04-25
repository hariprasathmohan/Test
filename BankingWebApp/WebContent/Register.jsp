<%@page import="com.bank.utils.Utils"%>
<%@page import="com.bank.utils.MyDbConnection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.bank.bank.Bank"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.models.BranchModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>

<link rel="stylesheet" href="MyStyle.css">
</head>
<body>





<div class="main">
    <p class="sign" align="center">Login</p>
    <form class="form1" action="register" method="post">
    
    <input class="un" type="text" name="name" size="15" placeholder="Name" required="required" />
    
    <input class="un" type="text" name=email size="15" placeholder="Email" required="required" />
    
    <input class="un" type="text" name="mobile" size="15" placeholder="Mobile" required="required" />
    
    <input class="un" type="text" name="address1" size="15" placeholder="Address 1" required="required" />
    
    <input class="un" type="text" name="address2" size="15" placeholder="Address 2" />
    
    <input class="un" type="text" name="city" size="15" placeholder="City" required="required" />
    
    <input class="un" type="text" name="state" size="15" placeholder="State" required="required" />
    
    <select class="un" id="banklist">
    	<option disabled selected>Select Bank</option>
    	<%
					
					Bank bank=Bank.getInstance();
					try {
						ResultSet rs=bank.getAllBanks();
						if(rs!=null){
							while(rs.next()){%>
								<option value=<%=rs.getString("name") %>> <%=rs.getString("name") %> </option>
						<%}
					} 
					}catch (SQLException e) {
						e.printStackTrace();
					}
					%>
    </select>
    
    <select class="un" id="branch">
    	<option disabled selected>Select Branch</option>
    </select>
    
    <input class="un" type="text" name="password" size="15" placeholder="Password" required="required" />
    
    <input class="submit" type="reset" value="Reset"><br><br>
    
    <input class="submit" type="submit" value="Register"><br><br>
              
     </form>   
        
        <a href="login.html">Already have Account Login here</a>     
    </div>





















<%-- 

	<form action="register" method="post">
		<table style="width: 30%">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" size="15" required="required" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name=email size="15" required="required" /></td>
			</tr>
			<tr>
				<td>Mobile</td>
				<td><input type="text" name="mobile" size="15" required="required" /></td>
			</tr>
			<tr>
				<td>Address 1</td>
				<td><input type="text" name="address1" size="15" required="required" /></td>
			</tr>
			<tr>
				<td>Address 2</td>
				<td><input type="text" name="address2" size="15" /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><input type="text" name="city" size="15" required="required" /></td>
			</tr>
			<tr>
				<td>State</td>
				<td><input type="text" name="state" size="15" required="required" /></td>
			</tr>
			<tr>
				<td>Bank</td>
				<td>
					<select id="banklist">
					<option disabled selected>Select Bank</option>
					<%
					
					Bank bank=Bank.getInstance();
					try {
						ResultSet rs=bank.getAllBanks();
						if(rs!=null){
							while(rs.next()){%>
								<option value=<%=rs.getString("name") %>> <%=rs.getString("name") %> </option>
						<%}
					} 
					}catch (SQLException e) {
						e.printStackTrace();
					}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<td>Branch</td>
				<td>
					<select name="branch" id="branch" onchange="">
							<option disabled selected>Select Branch</option>				
					</select>
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password" size="15" required="required" /></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset"></td>
				<td><input type="submit" value="Register"></td>
			</tr>
		</table>
	</form>
	<br><br>
	<a href="login.html">Already have Account Login here</a> --%>
	<script type="text/javascript">
		function getBranchList(){
			var bankList = document.getElementById("banklist");
			
			var bank = bankList.options[bankList.selectedIndex].value;
 
			console.log(bank);
			
			var ajax = new XMLHttpRequest();
			var url = 'branchList.jsp?bank=' + bank;
			ajax.open('GET', url);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4 && ajax.status == 200){
					var branch = document.getElementById("branch");
					console.log(ajax.responseText);
					branch.innerHTML = ajax.responseText;
					branch.style.display='inline';
				}
			}
			ajax.send();
		} 
		var bankList = document.getElementById("banklist");
		bankList.addEventListener("change", getBranchList);
	</script>	
</body>
</html>