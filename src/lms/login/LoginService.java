package lms.login;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import lms.database.DatabaseService;
import lms.database.LoginDB;

public class LoginService {

    Scanner myBabyScanner = new Scanner(System.in);

    public void doLogin() throws ClassNotFoundException, SQLException {
        System.out.println("[ :> ] Username: ");
        String userName = myBabyScanner.nextLine();

        System.out.println("[ :> ] Passwrod: ");
        String password = myBabyScanner.nextLine();

        try (Connection connection = DatabaseService.getConnection()) {

            LoginDB loginDB = new LoginDB();
            String userType = loginDB.doLogin(connection, userName, password);

            if(userType == null) {
                System.out.println("[ !! ] Invalid User!");
                return;
            }

            System.out.println("[ :> ] Login Success");
            System.out.println("[ :> ] You Logged in as a " + userType);
            System.out.println("[ :> ] Please Select the Options below.");

            
            if(userType.equals("admin")){
                System.out.println(" . ");
            }
            else {
                System.out.println(" . ");
            }




        }
    }

}
