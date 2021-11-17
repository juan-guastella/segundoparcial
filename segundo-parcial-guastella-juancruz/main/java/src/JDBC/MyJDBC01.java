package src.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBC01 {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            Statement statement = connection.createStatement();
            String consulSelect = "SELECT * FROM estudiante";
            ResultSet pResultSet = statement.executeQuery(consulSelect);

            while(pResultSet.next()){
                System.out.println(pResultSet.getString("id") + " " + pResultSet.getString("nombre") + " " + pResultSet.getString("apellido") + " " + pResultSet.getString("dni"));
            }

        }catch(SQLException e){
            System.out.println(e.toString());

            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException exception){
                    System.out.println(exception.toString());
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    }
}
