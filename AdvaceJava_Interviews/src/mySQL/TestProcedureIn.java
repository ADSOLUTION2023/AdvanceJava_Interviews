package mySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TestProcedureIn {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb",
            "root",
            "root"
        );

        CallableStatement call = conn.prepareCall("CALL empIn(?)");

        call.setInt(1, 2); // dept_id = 2

        ResultSet rs = call.executeQuery();

        while (rs.next()) {
            System.out.println("Id: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Salary: " + rs.getInt("salary"));
            System.out.println("Department Id: " + rs.getInt("dept_id"));
            System.out.println("----------------");
        }

        rs.close();
        call.close();
        conn.close();
    }
}