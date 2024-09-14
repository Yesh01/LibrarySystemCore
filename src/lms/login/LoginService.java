package lms.login;
import java.util.Scanner;

public class LoginService {

    Scanner myBabyScanner = new Scanner(System.in);

    public void doLogin() {
        System.out.println("Username: ");
        String userName = myBabyScanner.nextLine();

        System.out.println("Passwrod: ");
        String password = myBabyScanner.nextLine();
    }

}
