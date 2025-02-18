import java.sql.*;

public class Opgave01 {

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://LENOVO-THINKPAD\\SQLExpress;databaseName=tidsregistrering;user=sa;password=131202;"
            );

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Opgave");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println(
                        resultSet.getString(1)
                                + " \t " +
                        resultSet.getString(2)
                                + " \t " +
                        resultSet.getString(3)
                                + " \t " +
                        resultSet.getString(4)
                                + " \t " +
                        resultSet.getString(5)
                                + "\t" +
                        resultSet.getString(6)
                                + " \t " +
                        resultSet.getString(7)
                                + " \t " +
                        resultSet.getString(8)
                );
            }
            resultSet.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
