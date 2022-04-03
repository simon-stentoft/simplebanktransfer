package com.example.simplebanktransfer;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.*;

public class Database {

    private Connection connection; //make final
    private Statement stmt;
    private final String url = "jdbc:sqlite:D:\\IntelliJ Java projecter\\SimpleBankTransfer\\SimpleBankDBase";

    private String rememberEmail = "";
    private int displayWealthFromEmail;

    Database() {
        connection = null; //move to final and add throws clause to database constructor
        stmt = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS USER(\n"
                    + " name TEXT NOT NULL,\n"
                    + "	address TEXT NOT NULL,\n"
                    + "	email TEXT PRIMARY KEY,\n"
                    + "	mobileNr INTEGER NOT NULL,\n"
                    + "	accountNr TEXT NOT NULL, \n"
                    + "	checkDigits TEXT NOT NULL, \n"
                    + "	expirationDate_M TEXT NOT NULL, \n"
                    + "	expirationDate_y TEXT NOT NULL, \n"
                    + "	code TEXT NOT NULL, \n"
                    + " wealth INTEGER NOT NULL"
                    + ");";

            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(ActionEvent event, String name, String address, String email, int mobileNr, String accountNr, String checkDigits, String expirationDateM, String expirationDateY, String code, int wealth) {
        Controller c = new Controller();
        String sql = "INSERT INTO USER(name, address, email, mobileNr, accountNr, checkDigits, expirationDate_M, expirationDate_y, code, wealth) " +
                "VALUES ('" + name + "','" + address + "','" + email + "','" + mobileNr + "','" + accountNr + "','" + checkDigits + "','" + expirationDateM + "','" + expirationDateY + "','" + code + "','" + wealth + "')";

        stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            System.out.println("User created.");
            c.switchToLogInView(event);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void login(ActionEvent event, String email, String password) {
        Controller c = new Controller();
        String sql = "SELECT * FROM USER WHERE email = ? AND code = ?";
        try {
            connection = DriverManager.getConnection(url); //remove
            PreparedStatement prestmt = connection.prepareStatement(sql);
            prestmt.setString(1, email);
            prestmt.setString(2, password);
            ResultSet rs = prestmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful.");
                c.switchToTransferPage(event);
                setRememberEmail(email);
            } else {
                System.out.println("Wrong login information.");
                c.switchToLogInView(event);
            }
            connection.close();
            prestmt.close();
            rs.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void checkBalance(ActionEvent event) {
        Controller c = new Controller();
        String sql = "SELECT wealth FROM USER WHERE email = '" + getRememberEmail() + "'";

        stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c.balanceLabel.setText(String.valueOf(rs.getInt("wealth")));
            }
            stmt.close();
            System.out.println("Viewing your balance.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    public String getRememberEmail() {
        return rememberEmail;
    }

    public void setRememberEmail(String rememberEmail) {
        this.rememberEmail = rememberEmail;
    }

    public int getDisplayWealthFromEmail() {
        return displayWealthFromEmail;
    }

    public void setDisplayWealthFromEmail(int displayWealthFromEmail) {
        this.displayWealthFromEmail = displayWealthFromEmail;
    }
}
