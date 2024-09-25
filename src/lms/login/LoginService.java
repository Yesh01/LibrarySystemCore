package lms.login;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import lms.database.DatabaseService;
import lms.database.LoginDataAccess;
import lms.service.BookService;

public class LoginService {

    Scanner myBabyScanner = new Scanner(System.in);

    // Log-in Session --->
    public void doLogin() throws ClassNotFoundException, SQLException {
        System.out.println("[ :> ] Username: ");
        String userName = myBabyScanner.nextLine();
        System.out.println("[ :> ] Passwrod: ");
        String password = myBabyScanner.nextLine();

        // Connection to Database Service Class --->
        try (Connection connection = DatabaseService.getConnection()) {

            LoginDataAccess loginDB = new LoginDataAccess();
            String userType = loginDB.doLogin(connection, userName, password);

            if(userType == null) {
                System.out.println("[ !! ] Invalid User!");
                return;
            }

            System.out.println("[ :> ] Login Success");
            System.out.println("[ :> ] You Logged in as a " + userType);
            System.out.println("[ :> ] Please Select the Options below.");

            
            if(userType.equals("admin")){
                displayAdminMenu(connection); // -----> Admin Flow
            }
            else {
                System.out.println(" . "); // Display Student Related Menu
            }
        }
    }
    public void displayAdminMenu(Connection connection) throws SQLException {

        int choice;
        BookService bookService = new BookService();

        do {
            //                       ----> Admin's Main Menu
            System.out.println("                                        ");
            System.out.println("   [ 1. ] Search a Book                 ");
            System.out.println("   [ 2. ] Add a New Book                ");
            System.out.println("   [ 3. ] Upgrade Quantity of a Book    ");
            System.out.println("   [ 4. ] Show All Books                ");
            System.out.println("   [ 5. ] Register Student              ");
            System.out.println("   [ 6. ] Show All Registered Students  ");
            System.out.println("   [ 7. ] Exit From Application         ");
            System.out.println("                                        ");

            System.out.println("[ :> ] Please Enter Your Choice: ");
            choice = myBabyScanner.nextInt();

            //    ----> Functionalities for Options
            
            switch (choice) {
                case 1:
                    searchBook(connection);
                    break;
                case 2:
                    bookService.addBook(connection);
                    break;
                case 3:
                    bookService.updateBookQty(connection);
                    break;
                case 4:
                    bookService.displayCurrentBooks(connection);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("[ !! ] Exiting... ");
                    break;
                default:
                    System.out.println("[ !! ] Please Select a Valid Option");
            }
            
        } while (choice != 7);
    }

    //  ----->  Sub-Options for Search from [Search a Book Button]

    private void searchBook(Connection connection) throws SQLException {

        BookService bookService = new BookService();

        System.out.println("                                          ");
        System.out.println("   [ 1. ] Search w/ Book Serial No.       ");
        System.out.println("   [ 2. ] Search w/ Book's Author Name.   ");
        System.out.println("                                          ");

        System.out.println("[ :> ] Please Enter Your Choice: ");
        int choice = myBabyScanner.nextInt();

        switch (choice) {
            case 1:
                bookService.searchBySrlNo(connection);
                break;
            case 2:
                bookService.searchByAuthorName(connection);
                break;
            default:
                break;
        }

    }




}
