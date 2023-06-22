package com.guzel.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CustomerDbUtil {

		private DataSource dataSource;
		
		public CustomerDbUtil (DataSource theDataSource) {
			dataSource = theDataSource;
		}
		
		public List<Customer> getCustomers() throws Exception {
			List<Customer> customers = new ArrayList<>();
			
			Connection myConn = null;
			 Statement myStmt = null;
			 ResultSet myRs = null;
			 		
			 try {
				myConn = dataSource.getConnection();
				
				String sql = "SELECT * FROM customer WHERE is_deleted=0";
				
				myStmt = myConn.createStatement();
				
				myRs = myStmt.executeQuery(sql);
				
				while(myRs.next()) {
					
					int id = myRs.getInt("id");
					String name = myRs.getString("name");
					String age = myRs.getString("age");
					String address = myRs.getString("address");
					
					Customer tempCustomer = new Customer (id, name, age, address);
					customers.add(tempCustomer);
						
				}
				 return customers;
			 }
			 finally {
				 close(myConn, myStmt, myRs);
			 }
			
		}

		private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			
			try {
				if (myRs != null) {
					myRs.close();
				}
				
				if(myStmt != null) {
					myStmt.close();
				}
				
				if(myConn != null) {
					myConn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
public void addCustomer(Customer theCustomer) throws Exception{
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection 
				myConn = dataSource.getConnection(); 
				
				// create sql for the insert
				String sql = "insert into customer"+ "(name, age, address) "+ "values (?, ?, ? )";
				
				myStmt = myConn.prepareStatement(sql);
				
				//set the param values for the customer
				 myStmt.setString(1, theCustomer.getName());
				 myStmt.setString(2, theCustomer.getAge());
				 myStmt.setString(3, theCustomer.getAddress());
				 
				   //execute sql insert 
				 myStmt.execute();
				 
			}
			finally {
				//clean up 
				close(myConn, myStmt, null);
			}
			
		}

		public Customer getCustomer(int customerId) throws Exception{
			Customer theCustomer  = null; 
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
	
			
			try {
				
			   //get connection to db
			   myConn = dataSource.getConnection();
			   
			   //create sql to get selected customer
			   String sql = "select * from customer where id=?";
			   
			   //create prepared statement
			   myStmt  = myConn.prepareStatement(sql);
			   
			   //set execute statement
			   myStmt.setInt(1, customerId);
			   
			   //retrieve data from result set row
			   myRs = myStmt.executeQuery();
			   
			   if(myRs.next()) {
				   
				   String name = myRs.getString("name");
				   String age = myRs.getString("age");
				   String address = myRs.getString("address");
				   
				   //use the customerId during construction
				   theCustomer = new Customer (customerId, name, age, address);
				   
			   } else {
				     throw new Exception ("ID は見つかりません" + customerId);
			   }
			   
				return theCustomer;
			}
			finally{
				//clean up /close
				close (myConn, myStmt, myRs);
			}		
		}

		public void updateCustomer(Customer theCustomer) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
			//get DB connection
			myConn = dataSource.getConnection();
			
			//create sql update statement 
			String sql = "update customer " + "set name=?, age=?, address=? "+ "where ID=? ";
					 
			// prepare statement 
			myStmt = myConn.prepareStatement(sql); 
			
			//set params
			myStmt.setString(1, theCustomer.getName());
			myStmt.setString(2, theCustomer.getAge());
			myStmt.setString(3, theCustomer.getAddress());
			myStmt.setInt(4, theCustomer.getId());
			
			
			//execute sql statement 
			myStmt.execute();
		}
		finally {
			// clean up 
			close(myConn, myStmt, null);
		}	
	}

		public void deleteCustomer(int customerId) throws SQLException{
			
			Connection myConn = null; 
			PreparedStatement myStmt = null;
			
			try {
				
				myConn = dataSource.getConnection();
				
				  // create sql to delete student 
				String sql = "update customer set is_deleted = 1 where id = ?";
				
				 // prepare the statement 
				myStmt = myConn.prepareStatement(sql);
	
				// set params 
				myStmt.setInt(1, customerId);	
				// execute the sql statement 
				myStmt.execute();
			}
			finally {
				//clean up JDBC code 
				close(myConn, myStmt, null);
				
			}
			
		}

		public List<Customer> searchCustomers(String theSearchName) throws Exception {
			
			List<Customer> customers = new ArrayList<>();
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			
			try {
				myConn = dataSource.getConnection();
				
				if (theSearchName != null && theSearchName.trim().length() > 0) {
					String sql = "SELECT * FROM customer WHERE is_deleted=0 AND lower(name) LIKE ?";
					
					System.out.println("searchCustomers: sql=" + sql);
					
					myStmt = myConn.prepareStatement(sql); 
					String theSearchNameLike = "%" + theSearchName + "%";
					myStmt.setString(1, theSearchNameLike);
					
					System.out.println("theSearchNameLike=" + theSearchNameLike);
					
				} else {
					String sql = "SELECT * FROM customer WHERE is_deleted=0 ORDER BY name"; 
					myStmt = myConn.prepareStatement(sql);
				}
				
				myRs = myStmt.executeQuery();
				
				while (myRs.next()) {
					
					int id = myRs.getInt("id");
					String name = myRs.getString("name");
					String age = myRs.getString("age");
					String address = myRs.getString("address");
					
					Customer tempCustomer = new Customer (id, name, age, address);
					
					customers.add(tempCustomer);
				}
				return customers;
			}
			finally {
				
				close(myConn, myStmt, myRs);
			}
			
		}
}


