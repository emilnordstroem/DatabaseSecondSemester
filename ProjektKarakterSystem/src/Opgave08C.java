import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Opgave08C {
    // Gruppe 9: Emil, Henrik, Sammi, Govher

    public static void main(String[] args) {
        findStudentByExamAndTerm();
    }

    private static void findStudentByExamAndTerm() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=KarakterSystem;user=sa;password=131202;"
            );

            /*
            Vær obs. på at eksamensId 1 (termin S2024) indeholde en studerende to gang.
            Dette er fordi den studerende har en karakter fra re-eksamen samme termin.
            Derfor har vi valgt at inkludere dato for eksamen i tredje kolonne for bedre forståelse.
             */
            PreparedStatement queryStudentsFromExam = connection.prepareStatement(
                    /* SP kan findes i besvarelse (opgave 4-7) SQL fil, og skal creates inden
                    systemet køres */
                    "EXEC resultaterFraEksamenITermin ?, ?"
            );

            List<String> userInputForInsert = systemRequestUserInput();
            for (int inputIndex = 1; inputIndex < userInputForInsert.size(); inputIndex++) {
                queryStudentsFromExam.setString(inputIndex, userInputForInsert.get(inputIndex));
            }

            ResultSet resultSet = queryStudentsFromExam.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s | %s | %s%n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDate(3)
                );
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            /*
            Ikke benyttet fejlkode med constraint fra SQL
             */
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> systemRequestUserInput(){
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        List<String> userInputArrayList = new ArrayList<>();
        String[] promptToUser = {
                "Udfyld følgende: [tryk ENTER]",
                "Eksamens navn:",
                "Termin: Eks. V2025 eller S2025"
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