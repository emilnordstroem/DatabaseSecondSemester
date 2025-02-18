import java.sql.*;

public class Opgave04 {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("EXEC ManagementStudio");

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString(1)
                                + "\t" +
                                resultSet.getString(2));
            }
            statement.close();
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
    }
}