<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Beneficiery List</title>
</head>
<body>
<div class="main" align="center">
<s:if test="beneList.size() <= 0">
<p class="value"><s:label value="No Records Found"></s:label></p>
</s:if>
<s:else>
<table id="tbl" border="1">
<tr align="center">
<th>Beneficiery name</th>
<th>Account number</th>
<th>IFSC</th>
<th>Action</th>
</tr>
<s:iterator value="beneList">
<tr align="center">
<td><s:property value="nickName"/></td>
<td><s:property value="beneAccNo"/></td>
<td><s:property value="ifsc"/></td>
<td><input type="button" value="pay" onclick="readData(this)"/></td>
</tr>
</s:iterator>
</table>
</s:else>
</div>
<script type="text/javascript">
function readData(id) {
	var tbl = document.getElementById("tbl");
	console.log(tbl.rows.length)
   tr = tbl.getElementsByTagName("tr");
	var name = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[0].innerHTML;
	var accNum = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[1].innerHTML;
	var ifsc = tr[id.parentNode.parentNode.rowIndex].getElementsByTagName("td")[2].innerHTML;
	var path = "benePay?name="+name+"&accountNum="+accNum+"&ifsc="+ifsc;
	window.location=path;
}
</script>
</body>
</html>