$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PaymentAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}
 
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidItemIDSave").val($(this).data("itemid")); 
 $("#connectionCode").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#name").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#amount").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#paymentType").val($(this).closest("tr").find('td:eq(3)').text());
 $("#date").val($(this).closest("tr").find('td:eq(4)').text());  

});


$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "PaymentAPI", 
 type : "DELETE", 
 data : "paymentID=" + $(this).data("itemid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});

function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
 }


function validateItemForm() 
{ 
// CODE
if ($("#connectionCode").val().trim() == "") 
 { 
 return "Insert Item Code."; 
 } 
// NAME
if ($("#name").val().trim() == "") 
 { 
 return "Insert Item Name."; 
 }
 // PRICE-------------------------------
if ($("#amount").val().trim() == "") 
 { 
 return "Insert Item Price."; 
 } 
// is numerical value
var tmpPrice = $("#amount").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Item Price."; 
 } 
// convert to decimal price
 $("#amount").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#paymentType").val().trim() == "") 
 { 
 return "Insert Item Description."; 
 } 
if ($("#date").val().trim() == "") 
 { 
 return "Insert Item Description."; 
 } 

return true; 
}
