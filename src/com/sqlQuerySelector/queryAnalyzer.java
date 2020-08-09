package com.sqlQuerySelector;

import java.sql.*;
import java.util.Scanner;



public class queryAnalyzer {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		analyzer a = new analyzer();
		
		ResultSet rs;
		
		System.out.println("\t\t\t\tSQL QUERY ANALYZER\n\n");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.print("Enter your DB URL with quote (\"jdbc:mysql://localhost:3307/testdb\"): ");
			String url = input.nextLine();
			a.setURL(url);
			
			System.out.print("Enter your DB username with quote: ");
			String userName = input.nextLine();
			a.setUsername(userName);
			
			System.out.print("Enter your DB password with quote: ");
			String password = input.nextLine();
			a.setPassword(password);
			
			//Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(a.getURL(), a.getUsername(), a.getPassword());
			Statement s = con.createStatement();
			
			System.out.print("Enter your Options (select,insert,update,and delete): ");
			String choice = input.nextLine();
			
			if(choice.equalsIgnoreCase("select")) {
				System.out.print("Enter your query: ");
				String query = input.nextLine();
				a.setQuery(query);
				rs = s.executeQuery(a.getQuery());
				while(rs.next()) {
			    	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3));
			    }
			}
			//Check your table, the update works but show the exceptions
			else if(choice.equalsIgnoreCase("insert") || choice.equalsIgnoreCase("update") || choice.equalsIgnoreCase("delete")) {
					System.out.print("Enter your query: ");
					String query = input.nextLine();
					a.setQuery(query);
					int result = s.executeUpdate(a.getQuery());
					System.out.print("Enter a select statement to print your table after(update, insert, and delete): ");
					String query1 = input.nextLine();
					a.setQuery(query1);
					rs = s.executeQuery(a.getQuery());
					while(rs.next()) {
				    	System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3));
				    }
					
				}
			
			else {
				System.out.println("Invalid Input");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
