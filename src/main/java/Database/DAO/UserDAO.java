package Database.DAO;


import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sun.plugin2.main.server.ResultHandler;

import Database.DBClasses.User;


/**
 * Created by alexey on 14.03.14.
 */
public class UserDAO {
        private static Connection connection = null;

        private static Driver driver;
        public UserDAO()
        {

            try{
                connect();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        private void connect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
        {

            driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/feedback?" + "user=root&password=qwerty");
            //driver = (Driver);

            //DriverManager.registerDriver(driver);

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").		//db type
                    append("localhost:"). 		//host name
                    append("3306/").			//port
                    append("java_db?").		//db name
                    append("user=root&").		//login
                    append("password=qwerty");		//password

            //Class.forName("com.mysql.jdbc.Driver");


            connection = DriverManager.getConnection(url.toString());


        }

        public User GetUser (String login, String password) throws SQLException
        {

            PreparedStatement statment = connection.prepareStatement("SELECT * from java_db.user Where login = ? and password = ?;");
            statment.setString(1, login);
            statment.setString(2, password);
            User user = new User();
            user.id = -1;
            ResultSet rs = statment.executeQuery();
            while(rs.next())
            {
                user.id = statment.getResultSet().getInt("id");
                user.login = statment.getResultSet().getString("login");
                user.password = statment.getResultSet().getString("password");
            }
            return user;
        }

        public boolean SetUser(String login, String password) throws SQLException
        {

            PreparedStatement statment = connection.prepareStatement("INSERT INTO java_db.user(login, password) Values ( ?, ?);");
            statment.setString(1, login);
            statment.setString(2, password);
            if(!statment.execute())
            {
                return false;
            }
            return true;
        }
        public void TestUser()
        {
            try{SetUser("1","1");}catch(SQLException e){}
        }
    }

