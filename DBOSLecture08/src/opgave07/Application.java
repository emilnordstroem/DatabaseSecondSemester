package opgave07;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Medarbejder> medarbejderListe = hentAlleMedarbejdere();
        for(Medarbejder medarbejder : medarbejderListe){
            System.out.print(medarbejder.toString());
        }
    }

    private static List<Medarbejder> hentAlleMedarbejdere(){
        List<Medarbejder> medarbejderList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            Statement retrieveAlleMedarbejdere = connection.createStatement();
            ResultSet resultSet = retrieveAlleMedarbejdere.executeQuery("SELECT * FROM Medarbejder");

            while(resultSet.next()){
                int nr = resultSet.getInt(1);
                String navn = resultSet.getString(2);
                String stilling = resultSet.getString(3);
                String mobil = resultSet.getString(4);

                medarbejderList.add(new Medarbejder(nr, navn, stilling, mobil));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return medarbejderList;
    }

}
