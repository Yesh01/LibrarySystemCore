package lms;

import java.sql.SQLException;
import lms.login.LoginService;

public class SystemManagement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("------ Welcome to Universitiy's Library! ------");
        System.out.println("[ :> ] Plase do Login --> ");

        LoginService loginService = new LoginService();
        loginService.doLogin();
    }
}
