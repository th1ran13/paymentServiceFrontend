package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Payment {
	
	private Connection connect(){ 
		Connection con = null; 
		
		try{ 
			
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
			//For testing
			System.out.print("Successfully connected"); 
		
		}catch (Exception e) {
			e.printStackTrace();
			
		} 
		return con; 
	 }
	
	public String insertPayment(String accountNo , String name , String amount , String paymentType , String date ) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "error while connecting database";
			}
			
			String sql = "insert into paymentNew (`paymentID` , `connectionCode` , `name` , `amount` , `paymentType` , `date`)"
					    + "values (?,?,?,?,?,?)";
			
			PreparedStatement state = con.prepareStatement(sql);
			
			state.setInt(1, 0);
			state.setString(2, accountNo);
			state.setString(3, name);
			state.setDouble(4, Double.parseDouble(amount));
			state.setString(5, paymentType);
			state.setString(6, date);
			
			state.execute();
			con.close();
			
			String newItems = getPayments();
			output = "{\"status\":\"success\", \"data\": \"" + 
			newItems + "\"}";
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	public String getPayments(){
		
		String output = "";
		
		

		
		try {
			
			Connection con = connect(); 
			
			output = "<table border='1'><tr><th>connection Code</th><th>Name</th>" +
					 "<th>Amount</th>" 
					 +"<th>Payment Type</th>"
					 +"<th>Date</th>"
					 +"<th>Update</th><th>Remove</th></tr>\"; \r\n";
			
			String sql = "select * from paymentNew";
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()) {
				
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String connectionCode = rs.getString("connectionCode");
				String name = rs.getString("name");
				String amount = Double.toString(rs.getDouble("amount"));
				String paymentType = rs.getString("paymentType");
				String date = rs.getString("date");
				
				 output += "<tr><td>" + connectionCode + "</td>"; 
				 output += "<td>" + name + "</td>"; 
				 output += "<td>" + amount + "</td>"; 
				 output += "<td>" + paymentType + "</td>"; 
				 output += "<td>" + date + "</td>"; 
				 
				// buttons
				 output += "<td><input name='btnUpdate' "
				 		+ "type='button' value='Update' "
				 		+ "class='btnUpdate btn btn-secondary' data-itemid='" + paymentID + "' ></td>"
				 		+ "<td><input name='btnRemove' type='button' value='Remove' "
				 		+ "class='btnRemove btn btn-danger' data-itemid='" + paymentID + "'></td></tr>"; 
				
			}
			
			con.close();
			
			output += "</table>"; 
		}catch(Exception e) {
			output = "Error while reading the data."; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
		
	}
	
	public String updatePayment(String paymentID ,String connectionCode , String name , String amount , String paymentType , String date) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if(con == null) {
				
				return "error while connecting database";
			}
			
			String sql = "UPDATE paymentNew SET connectionCode = ? , name = ? , amount = ? , paymentType = ? , date = ?"
					+ "WHERE paymentID = ?";
			
			PreparedStatement state = con.prepareStatement(sql);
			
			state.setString(1, connectionCode);
			state.setString(2, name);
			state.setDouble(3, Double.parseDouble(amount));
			state.setString(4, paymentType);
			state.setString(5, date);
			state.setInt(6, Integer.parseInt(paymentID));
			
			state.execute();
			con.close();
			
			String newItems =  getPayments(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
			
			
		}catch(Exception e) {
			
			
			output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			System.err.println(e.getMessage()); 
		}
		
		return output;
	}
	
	public String deletePayment(String paymentID) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if(con == null) {
				return "error while deleting payment";
			}
			
			String sql = "delete from paymentNew where paymentID = ?";
			
			PreparedStatement state = con.prepareStatement(sql);
			
			state.setInt(1 , Integer.parseInt(paymentID));
			
			state.execute();
			con.close();
			
			String newItems = getPayments(); 
			output = "{\"status\":\"success\", \"data\": \"" + 
			newItems + "\"}"; 
			
		}catch(Exception e) {
			
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
			System.err.println(e.getMessage()); 
			
		}
		
		return output;
	}

}
