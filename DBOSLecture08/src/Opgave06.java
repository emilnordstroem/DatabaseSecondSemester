import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Opgave06 {

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] userInput = promptUser(reader);

            PreparedStatement opretTidsregistrering = connection.prepareStatement(
                    "INSERT INTO Tidsregistrering (starttid, sluttid, beskrivelseAfArbejde, datoRegistering, medarbejderNr, opgaveNr) VALUES(?, ?, ?, ?, ?, ?)"
            );
            opretTidsregistrering.setTime(1, Time.valueOf(LocalTime.parse(userInput[0], DateTimeFormatter.ofPattern("HH:mm"))));
            opretTidsregistrering.setTime(2, Time.valueOf(LocalTime.parse(userInput[1], DateTimeFormatter.ofPattern("HH:mm"))));
            opretTidsregistrering.setString(3, userInput[2]);
            opretTidsregistrering.setDate(4, Date.valueOf(LocalDate.now()));
            opretTidsregistrering.setInt(5, Integer.parseInt(userInput[3]));
            opretTidsregistrering.setInt(6, Integer.parseInt(userInput[4]));

            opretTidsregistrering.executeUpdate();

            opretTidsregistrering.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] promptUser(BufferedReader reader) {
        String[] prompt = {"Starttid:", "Sluttid:", "Beskrivelse af arbejdet:", "Medarbejder nr:", "Opgaven nr:"};
        String[] input = new String[prompt.length];
        try{
            for(int promptNo = 0; promptNo < prompt.length; promptNo++){
                System.out.println(prompt[promptNo]);
                input[promptNo] = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("promptUser(BufferedReader reader)");
            throw new RuntimeException(e);
        }
        return input;
    }
}