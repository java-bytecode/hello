package com.cyberxnuke.hello.services;

import javax.servlet.jsp.JspWriter;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class dbDAO {

    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private String dbURL = "jdbc:mysql://localhost:3306/";

    private String dbName = "helloDB";
    private String dbUsername = "root";
    private String dbPassword = "Vineethp@1997";
    private String dbString = "?serverTimezone=UTC";

    private Connection con;

    private static dbDAO dbDAOObj;

    private dbDAO() {
        con = null;
    }

    public static dbDAO getInstance() {
        if (dbDAOObj == null) {
            dbDAOObj = new dbDAO();
        }

        return dbDAOObj;
    }

    public void connectToDatabase() throws ClassNotFoundException, SQLException {

        Class.forName(dbDriver);
        con = DriverManager.getConnection(dbURL + dbName + dbString, dbUsername, dbPassword);
    }

    public boolean isDatabaseConnected() throws SQLException {
        try {
            return con.isClosed();
        } catch (Exception e) {
            return true;
        }

    }

    public void closeConnection() throws SQLException {
        if (!con.isClosed()) {
            con.close();
        }
    }

    public boolean login(String username, String password) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM UsersLogin WHERE username=? AND password=?");
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM Users JOIN UsersLogin UL on UL.userID = Users.userID WHERE username=?;");
        ps.setString(1, username);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean signup(String username, String password) throws SQLException {
        // Need userID first so we could increment it
        Statement statement = con.createStatement();
        String getMaxUserID = "SELECT MAX(userID) FROM UsersLogin;";
        ResultSet rsMaxUserID = statement.executeQuery(getMaxUserID);
        rsMaxUserID.next();
        int maxUserID = Integer.parseInt(rsMaxUserID.getString(1));

        PreparedStatement ps = con.prepareStatement("INSERT INTO UsersLogin VALUES(?, ?, ?);");
        ps.setInt(1, maxUserID + 1);
        ps.setString(2, username);
        ps.setString(3, password);

        int insertStatus = ps.executeUpdate();
        if (insertStatus == 1) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList getUserDetails(String username, boolean newSignUp) throws SQLException {
        if (!newSignUp) {
            ArrayList userDetailsList = new ArrayList();
            System.out.println("UserDetails: " + username);
            String getUserDetailsString = "SELECT * FROM Users JOIN UsersLogin UL on UL.userID = Users.userID WHERE username=?;";
            PreparedStatement ps = con.prepareStatement(getUserDetailsString);
            ps.setString(1, username);

            ResultSet userDetails = ps.executeQuery();
            if (userDetails.next()) {

                userDetailsList.add(userDetails.getString(2)); // name
                userDetailsList.add(userDetails.getString(3)); // description
                userDetailsList.add(userDetails.getString(4)); // emailID
                userDetailsList.add(userDetails.getString(5)); // role

                return userDetailsList;
            }
        }
        return null;
    }

    public boolean updateUserDetails(String username, String name, String description, String email) throws SQLException {

        String setUserDetailsString = "UPDATE Users JOIN UsersLogin UL on UL.userID = Users.userID SET name=?, description=?, emailaddress=? \n" +
                "WHERE username=?;";
        PreparedStatement ps = con.prepareStatement(setUserDetailsString);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, email);
        ps.setString(4, username);

        int updateStatus = ps.executeUpdate();
        if (updateStatus == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertUserDetails(String username, String name, String description, String email) throws SQLException {
        // Need userID first so we could increment it
        String getUserIDString = "SELECT userID FROM UsersLogin WHERE username=?;";

        PreparedStatement psUserID = con.prepareStatement(getUserIDString);
        psUserID.setString(1, username);

        ResultSet rsUserID = psUserID.executeQuery();
        rsUserID.next();
        int userID = Integer.parseInt(rsUserID.getString(1));

        String setUserDetailsString = "INSERT INTO Users VALUES (?, ?, ?, ?, ?, null);";
        PreparedStatement ps = con.prepareStatement(setUserDetailsString);
        ps.setString(1, String.valueOf(userID));
        ps.setString(2, name);
        ps.setString(3, description);
        ps.setString(4, email);
        ps.setString(5, "Student");

        int updateStatus = ps.executeUpdate();
        if (updateStatus == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfUserInsertedSettings(String username) throws SQLException {
        // Need userID first so we could increment it
        String getUserIDString = "SELECT * FROM Users JOIN UsersLogin UL on UL.userID = Users.userID WHERE username=?;";

        PreparedStatement psUserID = con.prepareStatement(getUserIDString);
        psUserID.setString(1, username);

        ResultSet rsUserID = psUserID.executeQuery();
        if (rsUserID.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void getUserStatuses(JspWriter out) throws SQLException, IOException {
        String getUserStatusString = "SELECT name, message, likes FROM Status\n" +
                "    JOIN UserStatus US on US.statusID = Status.statusID\n" +
                "    JOIN Users U on US.userID = U.userID;";

        Statement statement = con.createStatement();
        ResultSet userStatuses = statement.executeQuery(getUserStatusString);

        while (userStatuses.next()) {
            out.println("<div class=\"post\">");
            out.println("<div class=\"postedBy\"><img src=\"assets/icons/chat.png\"\"/>" + userStatuses.getString(1) + "</div>");
            out.println("<div class=\"message\">" + userStatuses.getString(2) + "</div>");
            out.println("</div>");
        }
    }

    public void getFriendsList(String username, JspWriter out) throws SQLException, IOException {
        String getFriendsListString = "SELECT Users.userID, name, description FROM UsersLogin\n" +
                "    JOIN Friends on UsersLogin.userID = Friends.userID\n" +
                "    JOIN Users on Friends.friendID = Users.userID\n" +
                "    WHERE username=?;\n";

        PreparedStatement psFriendsList = con.prepareStatement(getFriendsListString);
        psFriendsList.setString(1, username);
        ResultSet friendsList = psFriendsList.executeQuery();

        while (friendsList.next()) {
            out.println("<div class=\"friend\">");
            out.println("<div class=\"user\"><img src=\"assets/icons/user.png\"\"/><a id=\"friendURL\" href=\"profileView.jsp?userID=" + friendsList.getString(1) + "\">" + friendsList.getString(2) + "</a></div>");
            out.println("<div class=\"role\">" + friendsList.getString(3) + "</div>");
            out.println("</div>");
        }
    }

    public void getProfilePicture(String username, JspWriter out) throws SQLException, IOException {
        String getProfilePictureString = "SELECT profileImgURL FROM Users\n" +
                "JOIN UsersLogin UL on UL.userID = Users.userID\n" +
                "WHERE username=?;";

        PreparedStatement psProfilePicture = con.prepareStatement(getProfilePictureString);
        psProfilePicture.setString(1, username);
        ResultSet profilePictureURL = psProfilePicture.executeQuery();

        while (profilePictureURL.next()) {
            out.println("\"assets/users/" + profilePictureURL.getString(1) + "\"");
        }
    }

    public String getUsername(int userID) throws SQLException {
        String getUsernameString = "SELECT username FROM UsersLogin WHERE userID=?;";
        PreparedStatement psUsername = con.prepareStatement(getUsernameString);
        psUsername.setString(1, String.valueOf(userID));
        ResultSet resultUsername = psUsername.executeQuery();

        if (resultUsername.next()) {
            return resultUsername.getString(1);
        } else {
            return null;
        }
    }
}
