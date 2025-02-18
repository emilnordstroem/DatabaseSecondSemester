import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Opgave03 {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            BufferedReader inLine = new BufferedReader(new InputStreamReader(System.in)); // Alternative to Scanner
            System.out.print("Indtast navn på medarbejder: ");
            String userInput = inLine.readLine();

            String sql = "SELECT M.mobil FROM Medarbejder AS M WHERE M.navn = ?"; // preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userInput);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println((e.getMessage()));
        }
    }
}
