import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Opgave06 {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Opret medarbejder");
            System.out.println("Navn");
            String navn = input.readLine();
            System.out.println("Stillingsbetegnelse");
            String stilling = input.readLine();
            System.out.println("Mobil");
            String mobil = input.readLine();

            String sqlOpretMedarbejder = "INSERT INTO Medarbejder VALUES" +
                    "(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlOpretMedarbejder);
            preparedStatement.clearParameters();


            preparedStatement.setInt(1, nextAvailableNr(connection));
            preparedStatement.setString(2, navn);
            preparedStatement.setString(3, stilling);
            preparedStatement.setString(4, mobil);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int nextAvailableNr(Connection connection){
        try{
            String sqlFindMedarbejderNr = "SELECT M.medarbejderNr FROM Medarbejder AS M";
            Statement statement = connection.createStatement();
            ResultSet medarbejderNr = statement.executeQuery(sqlFindMedarbejderNr);
            int highest = 0;
            while(medarbejderNr.next()) {
                highest = medarbejderNr.getInt(1);
            }
            return highest + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
