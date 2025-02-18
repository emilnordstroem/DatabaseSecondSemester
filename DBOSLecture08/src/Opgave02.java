import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Opgave02 {

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] userInput = promptUser(reader);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Opgave VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            int index = 0;
            for(int parameter = 1; parameter <= userInput.length + 1; parameter++){
                switch (parameter) {
                    case 1:
                        preparedStatement.setInt(parameter, nextAvailableNr(connection));
                        break;
                    case 2:
                        preparedStatement.setString(parameter, userInput[index]);
                        break;
                    case 3:
                        preparedStatement.setInt(parameter, Integer.parseInt(userInput[index]));
                        break;
                    case 4, 5:
                        preparedStatement.setDate(parameter,
                                Date.valueOf(
                                        LocalDate.parse(
                                                userInput[index],
                                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                        )
                                ));
                        break;
                    case 6:
                        preparedStatement.setString(parameter, userInput[index]);
                        break;
                    case 7:
                        preparedStatement.setInt(parameter, Integer.parseInt(userInput[index]));
                        break;
                    case 8:
                        preparedStatement.setInt(parameter, Integer.parseInt(userInput[index]));
                        break;
                    default:
                        System.out.println("Default");
                        break;
                }
                index++;
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] promptUser(BufferedReader reader) throws IOException {
        String[] prompt = {"Beskrivelse: højest 50 karaktere", "Prioitet: 1 til 5",
                "forventet start", "Deadline", "Status: 'To do', 'Igang', 'Færdig', 'Checked'",
                "Projekt nr", "Opgavetype"};
        String[] input = new String[prompt.length];

        for(int promptNo = 0; promptNo < prompt.length; promptNo++){
            System.out.println(prompt[promptNo]);
            input[promptNo] = reader.readLine();
        }
        return input;
    }

    private static int nextAvailableNr(Connection connection){
        try{
            String sqlFindOpgaveNr = "SELECT opgaveNr FROM Opgave";
            Statement statement = connection.createStatement();
            ResultSet opgaveNr = statement.executeQuery(sqlFindOpgaveNr);
            int highest = 0;
            while(opgaveNr.next()) {
                highest = opgaveNr.getInt(1);
            }
            return highest + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
