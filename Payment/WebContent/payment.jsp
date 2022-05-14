<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Payment Service</h1>
<form id="formItem" name="formItem">
 connectionCode: 
 <input id="connectionCode" name="connectionCode" type="text" 
 class="form-control form-control-sm">
 <br> Consumer Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> amount: 
 <input id="amount" name="amount" type="text" 
 class="form-control form-control-sm">
 <br> paymentType: 
 <input id="paymentType" name="paymentType" type="text" 
 class="form-control form-control-sm">
 <br> date: 
 <input id="date" name="date" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%

 Payment payObj = new Payment();
 out.print(payObj.getPayments());
 %>
</div>
</div> </div> </div> 
</body>
</html>
