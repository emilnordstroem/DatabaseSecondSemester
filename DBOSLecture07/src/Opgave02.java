import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Opgave02 {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT M.navn, M.mobil FROM Medarbejder AS M");

            while(resultSet.next()){
                System.out.println(
                        resultSet.getString(1)
                                + "\t" +
                                resultSet.getString(2)
                );
            }
            resultSet.close();
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
    }
}
