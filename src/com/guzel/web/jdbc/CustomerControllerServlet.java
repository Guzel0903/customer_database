package com.guzel.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
	
	/**
	 * Servlet implementation class CustomerControllerServlet
	 */
	@WebServlet("/CustomerControllerServlet")
	public class CustomerControllerServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
			
		private CustomerDbUtil customerDbUtil;
		
		@Resource (name= "jdbc/test_db")
		private DataSource dataSource;
		
	
		@Override
		public void init() throws ServletException {
			super.init();
			
			try {
				customerDbUtil = new CustomerDbUtil(dataSource);
			}
			catch(Exception e) {
				throw new ServletException(e);
			}
			
		}
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			try {
				String theCommand = request.getParameter("command");
				
				if (theCommand == null ) {
					theCommand = "LIST";
				}
				switch (theCommand) {
				
				case "LIST":
					listCustomers(request, response); 
					break;
					
					
				case "CONFIRMADD":
					showAddCustomerForm(request, response);
					break;
					
				case "ADD":
					addCustomer(request, response);
					break;
				
				case "SAVE":
					showConfirmationPage(request, response);
					break;
					
					
				case "LOAD":
					loadCustomer(request, response);
				    break;
				   
				    
				case "LOAD_UPDATE":
					 showUpdateCustomerForm(request, response);
		             break;
		                
				case "UPDATE":
					confirmUpdateCustomer(request, response);
					break;
					
				case "SAVE_UPDATE":
					saveUpdateCustomer(request, response);
					break;
				
				 
				case "DELETE":
					deleteCustomer(request, response);
					break;
					
				case "SAVEDELETE":
				  confirmDeleteCustomer(request, response);
				  break;
				  
				case "SEARCH":
				  searchCustomers(request,response);
				   break;
				
				    
			    default: 
			    	listCustomers(request, response);
				}
			
			} 
			catch (Exception e) {
				throw new ServletException(e);
			}
		
		}
	
		private void searchCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			String theSearchName = request.getParameter("theSearchName");
			
			List<Customer> customers = customerDbUtil.searchCustomers(theSearchName);
			
			request.setAttribute("CUSTOMERS_LIST", customers);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-customers.jsp");
			dispatcher.forward(request, response);
			
		}

		private void confirmDeleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			int customerId = Integer.parseInt(request.getParameter("customerId"));
		    
		    // Delete the customer from the database
		    customerDbUtil.deleteCustomer(customerId);
		    
		    // Redirect to a confirmation page
		    request.getRequestDispatcher("/confirmation-del-page.jsp").forward(request, response);
			
		}

		private void showConfirmationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String address = request.getParameter("address");

			request.setAttribute("name", name);
			request.setAttribute("age", age);
			request.setAttribute("address", address);

			request.getRequestDispatcher("/confirmation-page.jsp").forward(request, response);
		}


		private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			int customerId = Integer.parseInt(request.getParameter("customerId"));
		    
		    // Set the customer object as an attribute in the request
		    Customer theCustomer = customerDbUtil.getCustomer(customerId);
		 
		    request.setAttribute("THE_CUSTOMER", theCustomer);
		    request.setAttribute("customerId", customerId);
			// send them
			request.getRequestDispatcher("/confirm-delete-customer.jsp").forward(request, response);
		}
	
		private void showUpdateCustomerForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
			int customerId = Integer.parseInt(request.getParameter("customerId"));

		    Customer theCustomer = customerDbUtil.getCustomer(customerId);
		    request.setAttribute("THE_CUSTOMER", theCustomer);

		    request.getRequestDispatcher("/update-customer-form.jsp").forward(request, response);
		}
		
		private void confirmUpdateCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			
		    Customer theCustomer = customerDbUtil.getCustomer(customerId);
		    request.setAttribute("THE_CUSTOMER", theCustomer);
		    request.setAttribute("customerId", customerId);
		    
		    request.getRequestDispatcher("/confirm-update-customer.jsp").forward(request, response);
		}
		private void saveUpdateCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		    
		    int id = Integer.parseInt(request.getParameter("customerId"));
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String address = request.getParameter("address");
			
			//create a new customer object 
			Customer theCustomer = new Customer(id, name, age, address);
			customerDbUtil.updateCustomer(theCustomer);

		    request.getRequestDispatcher("/confirmation-upd-page.jsp").forward(request, response);
		}
		
	  private void showAddCustomerForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  
		    request.getRequestDispatcher("/confirm-add-customer.jsp").forward(request, response);
		}
		
	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
			 
			//read customer info from the form data
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		    	
 		Customer theCustomer = new Customer(name, age, address);
 		
		//add the customer to the DB 
		customerDbUtil.addCustomer(theCustomer);
		
		request.setAttribute("name", name);
	    request.setAttribute("age", age);
	    request.setAttribute("address", address);

	    request.getRequestDispatcher("/confirmation-page.jsp").forward(request, response);
		
		} 

		private void listCustomers(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
			
			List<Customer> customers = customerDbUtil.getCustomers();
			
			request.setAttribute("CUSTOMERS_LIST", customers);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-customers.jsp");
			dispatcher.forward(request, response);
		}
		
		private void loadCustomer(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
			
			//read customer id from db
			int theCustomerId = Integer.parseInt(request.getParameter("customerId"));
			
			// get customer from DB (db util)
			Customer theCustomer = customerDbUtil.getCustomer(theCustomerId);
			
			//place customer in the request attribute
			request.setAttribute("THE_CUSTOMER", theCustomer);
			
			//send to jsp page: update-customer-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-customer-form.jsp");
		    dispatcher.forward(request, response);
					
		}	
	}