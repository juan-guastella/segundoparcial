package src.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class MyJDBC02 {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            connection.setAutoCommit(false);

            PreparedStatement consulPrep = connection.prepareStatement("INSERT INTO estudiante (dni, nombre, apellido) VALUES (?, ?, ?);");
            consulPrep.setInt(1,40715261);
            consulPrep.setString(2,"Juan Cruz");
            consulPrep.setString(3, "Guastella");
            consulPrep.executeUpdate();

        Statement statement = connection.createStatement();

        // No pude ejecutar el insert, por algun motivo no me lo actualiza ni con consulta preparada ni con un insert normal
            // como el de abajo. No entiendo si esta mal la sintaxis o que es lo que sucede.
       //     statement.executeUpdate("INSERT INTO estudiante (dni, nombre, apellido) VALUES (40715261, 'Juan Cruz', 'Guastella');");

            connection.commit();

            String consulSelect = "SELECT * FROM estudiante";
            ResultSet pResultSet = statement.executeQuery(consulSelect);

            while(pResultSet.next()){
                System.out.println(pResultSet.getString("id") + " " + pResultSet.getString("nombre") + " " + pResultSet.getString("apellido") + " " + pResultSet.getString("dni"));
            }

        } catch(SQLException e){
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
