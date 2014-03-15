package Database.DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("java_db?").
                    append("user=root&").
                    append("password=qwerty");

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

        public void SetUser(String login, String password) throws SQLException
        {
            PreparedStatement statment = connection.prepareStatement("INSERT INTO java_db.user(login, password) Values ( ?, ?);");
            statment.setString(1, login);
            statment.setString(2, password);
            statment.execute();

        }
    }

