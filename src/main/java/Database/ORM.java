package Database; /**
 * Created by alexey on 11.03.14.
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sun.plugin2.main.server.ResultHandler;
//import java.sql.Connection;
//import  java.sql.DriverManager;
//import java.sql.PreparedStatement;



public class ORM {

    private static Connection connection = null;
    //private Statement statement = null;
    //private static PreparedStatement statment = null;
    private static Driver driver;

    private void connect()
    {

            driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/feedback?" + "user=root&password=qwerty");*/
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

    public Integer GetUser(String login, String password)
    {

        PreparedStatement statment = connection.prepareStatement("SELECT id from java_db.user Where login = ? and password = ?;");
        statment.setString(1, login);
        statment.setString(2, password);
        Integer id = -1;
        ResultSet rs = statment.executeQuery();
        if(rs.next())
        {
            id = Integer.parseInt(statment.getResultSet().toString());
        }
        return id;
    }

    public boolean SetUser(String login, String password)
    {

        PreparedStatement statment = connection.prepareStatement("INSERT INTO java_db.user(login, password) Values ( ?, ?;");
        statment.setString(1, login);
        statment.setString(2, password);
        if(!statment.execute())
        {
            return false;
        }
        return true;
    }
}
