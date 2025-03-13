import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Opgave08B {
    // Gruppe 9: Emil, Henrik, Sammi, Govher

    public static void main(String[] args) {
        indsætEksamensAfvikling();
    }

    private static void indsætEksamensAfvikling() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=KarakterSystem;user=sa;password=131202;"
            );

            PreparedStatement insertExamToHold = connection.prepareStatement(
                    "INSERT INTO Afvikling (eksamensId, termin, startDato, slutDato) VALUES (?, ?, ?, ?)"
            );

            List<String> userInputForInsert = systemRequestUserInput();
            for (int inputIndex = 1; inputIndex < userInputForInsert.size(); inputIndex++) {
                switch (inputIndex) {
                    case 1:
                        insertExamToHold.setInt(inputIndex, Integer.parseInt(userInputForInsert.get(inputIndex)));
                        break;
                    case 2:
                        insertExamToHold.setString(inputIndex, userInputForInsert.get(inputIndex));
                        break;
                    case 3, 4:
                        insertExamToHold.setDate(inputIndex, Date.valueOf(
                                LocalDate.parse(userInputForInsert.get(inputIndex)))
                        );
                        break;
                    default:
                }
            }

            insertExamToHold.executeUpdate();

        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParseException. Format: 2026-06-10 not 2026-6-10");
            System.out.println(e.getParsedString());
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            if(e.getErrorCode() == 547){
                String inputError = switch (e.getMessage()) {
                    case "eksamenCheck" -> "Eksamens ID";
                    case "terminCheck" -> "Termin";
                    case "datoCheck" -> "Start-/Slutdato";
                    default -> "";
                    // Where the error occurred
                };
                System.out.printf("Fejl i indtastet %s",
                        inputError);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> systemRequestUserInput(){
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        List<String> userInputArrayList = new ArrayList<>();
        String[] promptToUser = {
                "Udfyld følgende: [tryk ENTER]",
                "Eksamens ID:",
                "Termin: Eks. V2025 eller S2025",
                "Startdato: yyyy-mm-dd",
                "Slutdato: yyyy-mm-dd"
        };
        try {
            for(String prompt : promptToUser){
                System.out.println(prompt);
                userInputArrayList.add(inputReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userInputArrayList;
    }

}