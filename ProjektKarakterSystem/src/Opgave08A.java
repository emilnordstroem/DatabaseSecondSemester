import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Opgave08A {
    // Gruppe 9: Emil, Henrik, Sammi, Govher

    public static void main(String[] args) {
        indsætBedømmelse();
    }

    private static void indsætBedømmelse() {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=KarakterSystem;user=sa;password=131202;"
            );

            PreparedStatement insertExamAttempt = connection.prepareStatement(
                    "INSERT INTO Bedømmelse (afviklingsId, studerendeId, forsøg, karakter) VALUES (?, ?, ?, ?)"
            );

            List<String> userInputForInsert = systemRequestUserInput();
            for(int inputIndex = 1; inputIndex < userInputForInsert.size(); inputIndex++){
                if(inputIndex == 4){
                    insertExamAttempt.setString(inputIndex, userInputForInsert.get(inputIndex));
                }
                insertExamAttempt.setInt(inputIndex, Integer.parseInt(userInputForInsert.get(inputIndex)));
            }

            insertExamAttempt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            if(e.getErrorCode() == 547){
                String inputError = ""; // Where the error occurred
                switch (e.getMessage()) {
                    case "afviklingsIdCheck":
                        inputError = "Afviklings ID";
                        break;
                    case "studerendeIdCheck":
                        inputError = "Studerende ID";
                        break;
                    case "forsøgCheck":
                        inputError = "Forsøg";
                        break;
                    case "karakterCheck":
                        inputError = "Karakter";
                        break;
                }
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
                "Afviklings ID:",
                "Studerendes ID:",
                "Eksamens forsøg:",
                "Karakter"
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