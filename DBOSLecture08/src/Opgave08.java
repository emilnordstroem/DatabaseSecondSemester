import java.sql.*;

public class Opgave08 {

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            String sqlStatement =
                    "SELECT M.navn, M.medarbejderNr, SUM(DATEDIFF(HOUR, T.starttid, T.sluttid))\n" +
                    "FROM Tidsregistrering AS T\n" +
                    "INNER JOIN Medarbejder AS M\n" +
                    "\tON T.medarbejderNr = M.medarbejderNr\n" +
                    "\t\tGROUP BY M.navn, M.medarbejderNr";
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sqlStatement,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.printf("%s | %d | %d%n",
                        resultSet.getString(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
            }
            resultSet.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
