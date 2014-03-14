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
import Database.DAO.*;
import sun.plugin2.main.server.ResultHandler;
//import java.sql.Connection;
//import  java.sql.DriverManager;
//import java.sql.PreparedStatement;



public class ORM {

    public UserDAO CreateDAO()
    {
        UserDAO userDAO = new UserDAO();
        return userDAO;
    }

}
