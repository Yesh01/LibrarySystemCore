package lms;

import java.sql.SQLException;
import lms.login.LoginService;

public class SystemManagement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException { 

        System.out.println("                                               ");
        System.out.println("   ---- Welcome to University's Library! ----   ");
        System.out.println("                                        ");

        System.out.println("[ :> ] Please do Login -->    ");

        LoginService loginService = new LoginService();
        loginService.doLogin();
    }
}

// @Finished [Small Project][Learning Built-In Project]
// @Temporary Done
// @Yesh01 --> Author


