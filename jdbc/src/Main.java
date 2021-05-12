import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/authordatabase";
        String user = "root";
        String password = "";

        Connection myConn = null;
        PreparedStatement myStmt = null;

        Scanner scanner = null;

        try {
            // 0. read user input from command line: authorId, authorName, authorlastname  and authoremail
            scanner = new Scanner(System.in);


            System.out.print("Enter ID: ");
            String AuthorId = scanner.nextLine();

            System.out.print("Enter  Name: ");
            String authorName = scanner.nextLine();

            System.out.print("Enter  Lastame: ");
            String authorLastName = scanner.nextLine();

            System.out.print("Enter  Email: ");
            String authorEmail = scanner.nextLine();

            // 1. Get a connection to database
            myConn = DriverManager.getConnection(url, user, password);

            // 2. Create a statement
            String sql = "insert into authors "
                    + " (AuthorId, authorName, authorLastname, authorEmail)" + " values ( ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set param value
            myStmt.setString(1, AuthorId);
            myStmt.setString(2, authorName);
            myStmt.setString(3, authorLastName);
            myStmt.setString(4, authorEmail);

            // 3. Execute SQL query
            myStmt.executeUpdate();

            System.out.println("Insert complete.");

            Statement mySt = myConn.createStatement();

            ResultSet myRs = mySt.executeQuery("select * from authors");

            while (myRs.next()) {

                System.out.println(myRs.getString("authorId") + "," +myRs.getString("authorName") + ", " + myRs.getString("authorLastname") + ", " + myRs.getString("authorEmail"));

            }





        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }

            if (scanner != null) {
                scanner.close();
            }
        }
    }

}
