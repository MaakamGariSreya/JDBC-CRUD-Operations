package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class program3 {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection connection=null;
		Statement statement=null;
		PreparedStatement statement1=null;
		ResultSet res=null;
		System.out.println("----------------Welcome to JDBC CRUD Operations----------------");
		crud(connection, statement1, statement,res);

	}
	public static void crud(Connection connection,PreparedStatement statement1,Statement statement,ResultSet res)
	{
		Scanner scan=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password="root";
		String no="no";
		
		try
		{
			do
			{
				System.out.println("Do you want to do any operation");
				System.out.println("1.Insert\n2.update\n3.delete\n4.Display\n5.Exit");
				System.out.println("Enter your choice");
				int choice=scan.nextInt();
				if(choice==1)
				{
					do
					{
					String SQL1="INSERT into `employee`(`id`,`name`,`email`,`department`,`salary`)values(?,?,?,?,?)";
					connection=DriverManager.getConnection(url, username, password);
					statement1=connection.prepareStatement(SQL1);
					System.out.println("Enter ID");
					int id=scan.nextInt();
					System.out.println("Enter Name");
					String name=scan.next();
					System.out.println("Enter Email");
					String email=scan.next();
					System.out.println("Enter Department");
					String dept=scan.next();
					System.out.println("Enter Salary");
					int salary=scan.nextInt();
					statement1.setInt(1, id);
					statement1.setString(2, name);
					statement1.setString(3, email);
					statement1.setString(4, dept);
					statement1.setInt(5, salary);
					int i=statement1.executeUpdate();
					display(connection, statement, res);
					System.out.println("Do you want to insert more employees?Yes/No");
					}while(scan.next().equalsIgnoreCase("Yes"));
					if(no.equalsIgnoreCase("no"))
					{
						System.out.println("No more insertion can be done..");
					}
				}
				else if(choice==2)
				{
					do
					{
						System.out.println("Which Updation do you want\n1.Name\n2.Email\n3.Department\n4.Salary");
						System.out.println("Enter your choice");
						int ch=scan.nextInt();
						if(ch==1)
						{
							String SQL1="UPDATE `employee` SET `name`=? WHERE `id`=?";
							connection=DriverManager.getConnection(url, username, password);
							statement1=connection.prepareStatement(SQL1);
							System.out.println("Enter ID");
							int id=scan.nextInt();
							System.out.println("Enter Name");
							String name=scan.next();
							statement1.setString(1,name);
							statement1.setInt(2, id);
						}else if(ch==2)
						{
							String SQL1="UPDATE `employee` SET `email`=? WHERE `id`=?";
							connection=DriverManager.getConnection(url, username, password);
							statement1=connection.prepareStatement(SQL1);
							System.out.println("Enter ID");
							int id=scan.nextInt();
							System.out.println("Enter Email");
							String email=scan.next();
							statement1.setString(1, email);
							statement1.setInt(2, id);
						}
						else if(ch==3)
						{
							String SQL1="UPDATE `employee` SET `department`=? WHERE `id`=?";
							connection=DriverManager.getConnection(url, username, password);
							statement1=connection.prepareStatement(SQL1);
							System.out.println("Enter ID");
							int id=scan.nextInt();
							System.out.println("Enter Department");
							String dept=scan.next();
							statement1.setString(1, dept);
							statement1.setInt(2, id);
						}
						else
						{
							String SQL1="UPDATE `employee` SET `salary`=? WHERE `id`=?";
							connection=DriverManager.getConnection(url, username, password);
							statement1=connection.prepareStatement(SQL1);
							System.out.println("Enter ID");
							int id=scan.nextInt();
							System.out.println("Enter Salary");
							int salary=scan.nextInt();
							statement1.setInt(1, salary);
							statement1.setInt(2, id);
						}
						
						int i=statement1.executeUpdate();
						display(connection, statement, res);
						System.out.println("Do you want to update more employees?Yes/No");
					}while(scan.next().equalsIgnoreCase("Yes"));
					if(scan.next().equalsIgnoreCase("no"))
					{
						System.out.println("No more updations can be done...");
					}
				}
				else if(choice ==3)
				{
					do
					{
						String SQL1="DELETE from `employee` WHERE `id`=?";
						connection=DriverManager.getConnection(url, username, password);
						statement1=connection.prepareStatement(SQL1);
						System.out.println("Enter ID");
						int id=scan.nextInt();
						statement1.setInt(1, id);
						int i=statement1.executeUpdate();
						display(connection, statement, res);
						System.out.println("Do you want to delete more employees?Yes/No");
					}while(scan.next().equalsIgnoreCase("Yes"));
					if(no.equalsIgnoreCase("no"))
					{
						System.out.println("No more Deletions can be done...");
					}
				}
				else if(choice==4)
				{
					display(connection, statement, res);
				}
				else
				{
					System.out.println("Thank You......");
					System.exit(0);
				}
				System.out.println("Do you want to  more operations?Yes/No");
			}while(scan.next().equalsIgnoreCase("yes"));
			if(no.equalsIgnoreCase("no"))
			{
				System.out.println("Thank You......");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void display(Connection connection,Statement statement,ResultSet res)
	{
		System.out.println("Available Database .....");
		String url="jdbc:mysql://localhost:3306/jdbcclasses";
		String username="root";
		String password="root";
		String SQL;
		try {
			connection=DriverManager.getConnection(url, username, password);
			SQL="select * from employee";
			statement=connection.createStatement();
			res=statement.executeQuery(SQL);
			System.out.println("--------------------------------------------------------------");
			while(res.next())
			{
				int id=res.getInt("id");
				String name=res.getString("name");
				String email=res.getString("email");
				String dept=res.getString("department");
				int salary=res.getInt("salary");
				System.out.printf("%-3d | %-8s | %-18s | %-10s | %-5d\n",id,name,email,dept,salary);
			}
			System.out.println("--------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
