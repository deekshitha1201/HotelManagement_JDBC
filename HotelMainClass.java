package com.btl.hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HotelMainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Hotel Management System ");
		System.out.println("=========================================");
		int amount = 0, famount = 0;

		while (true) {
			System.out.println("1.Add Rooms\n2.Booking Rooms\n3.Food Menu ");
			System.out.println("4.Check Out\n5.Exit");
			System.out.println("Enter the Choice ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root",
							"root");
					PreparedStatement ps = connection.prepareStatement("insert into hotel values(?,?,?)");
					System.out.println("Enter Room number");
					int rno = sc.nextInt();
					ps.setInt(1, rno);
					System.out.println("Enter Room Cost");
					int cost = sc.nextInt();
					ps.setInt(2, cost);
					System.out.println("Sharing Number");
					int share = sc.nextInt();
					ps.setInt(3, share);
					ps.executeUpdate();
					// statement.execute("delete from hotel");
					connection.close();
					System.out.println("Rooms added suscessfully!!");
					System.out.println("-----------------------------------------------");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:// room booking
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root",
							"root");
					Statement statement = connection.createStatement();
					ResultSet res = statement.executeQuery("select * from hotel");
					while (res.next()) {
						System.out.println("Room Number : " + res.getInt(1) + "" + " Cost is : " + res.getInt(2)
								+ " Sharing is : " + res.getInt(3));
					}

					connection.close();
					System.out.println("-----------------------------------------------");
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root",
							"root");
					PreparedStatement ps = connection.prepareStatement("select * from hotel where Room_num=?");

					System.out.println("Enter Room Number");
					int r = sc.nextInt();
					ps.setInt(1, r);
					ResultSet res1 = ps.executeQuery();
					while (res1.next()) {
						// System.out.println(res1.getInt(1)+" "+res1.getInt(2)+" "+res1.getInt(3));
						if (r == res1.getInt(1)) {
							System.out.println(
									"Room number " + res1.getInt(1) + " is booked!! and Cost is : " + res1.getInt(2));
							amount += res1.getInt(2);
							System.out.println("Rooms Totsl bill is : " + amount);
						} else
							System.out.println("Invalid room number");
					}

					System.out.println("---------------------------------");
				}

				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;

			case 3:// order food
				System.out.println("1.Add Food\n2.Order Food\n3.Check out");

				System.out.println("Enter the choice");
				int choi = sc.nextInt();
				switch (choi) {
				case 1:// add food
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root",
								"root");
						PreparedStatement ps = connection.prepareStatement("insert into food values(?,?,?,?)");
						System.out.println("Enetr the id");
						int id = sc.nextInt();
						ps.setInt(1, id);
						// sc.next();
						System.out.println("Enter the food name");
						String name = sc.next();
						ps.setString(2, name);
						System.out.println("Enter the food cost");
						int cost = sc.nextInt();
						ps.setInt(3, cost);
						System.out.println("Enter quantity");
						int qt = sc.nextInt();
						ps.setInt(4, qt);
						ps.execute();
						connection.close();
						System.out.println("Food added successfully");
						System.out.println("--------------------------");

					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					break;
				case 2:// order food
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root",
								"root");
						PreparedStatement ps = connection.prepareStatement("select * from food");
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							System.out.println("Id is : " + rs.getInt(1) + " Food name : " + rs.getString(2) + ""
									+ " Cost : " + rs.getInt(3) + " Quantity : " + rs.getInt(4));

						}
						System.out.println("---------------------------------");

					}

					catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root",
								"root");
						PreparedStatement ps = connection.prepareStatement("select * from food where id=?");
						System.out.println("enter id");
						int id = sc.nextInt();
						ps.setInt(1, id);
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							if (id == rs.getInt(1)) {
								System.out.println(
										rs.getString(2) + " Is Ordered successfully and Cost is : " + rs.getInt(3));
								famount = rs.getInt(3);
								amount += famount;
							}
							System.out.println("-------------");
						}
					}

					catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}

					break;
				case 3:
					System.out.println("Food bill is : " + famount);
					break;
				default:
					System.out.println("Invalid choice..!!");
				}
			case 4:
				System.out.println("Total Bill is : " + amount);
				System.out.println("-------------------------");
				break;
			case 5:
				System.out.println("Thank You!!!");
				System.exit(0);
			default:
				System.out.println("Invalid choice.....");
			}
		}
		
	}

}
