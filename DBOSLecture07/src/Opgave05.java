import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Opgave05 {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Udfyld de nødvendige informationer for tidsregistrering");
            System.out.println("Medarbejder nr:");
            String medarbejderNr = reader.readLine();
            System.out.println("Indsæt opgave nr:");
            String opgaveNr = reader.readLine();
            System.out.println("Dato");
            String dato = reader.readLine();
            System.out.println("Starttidspunkt");
            String starttidspunkt = reader.readLine();
            System.out.println("Sluttidspunkt");
            String sluttidspunkt = reader.readLine();
            System.out.println("Beskrivelse af arbejdet");
            String beskrivelse = reader.readLine();

            String statement = "INSERT INTO Tidsregistrering VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.clearParameters();

            try{
                preparedStatement.setTime(1, Time.valueOf(
                        LocalTime.parse(
                            starttidspunkt,
                            DateTimeFormatter.ofPattern("HH:mm"))
                        )
                );
                preparedStatement.setTime(2, Time.valueOf(
                        LocalTime.parse(
                                sluttidspunkt,
                                DateTimeFormatter.ofPattern("HH:mm"))
                        )
                );
                preparedStatement.setString(3, beskrivelse);
                preparedStatement.setDate(4,
                        Date.valueOf(
                                LocalDate.parse(
                                        dato,
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                )
                        )
                );
                preparedStatement.setInt(5, Integer.parseInt(medarbejderNr));
                preparedStatement.setInt(6, Integer.parseInt(opgaveNr));
            } catch (RuntimeException e) {
                System.out.println("RuntimeException i formatting" + e.getMessage());
            }

            preparedStatement.executeUpdate();
            System.out.println("Ny tidsregistering er registreret");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
