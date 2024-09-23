package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import lms.database.BookDB;
import lms.model.Book;

public class BookService {

    Scanner myBabyScanner = new Scanner(System.in);

    // ----->  Method to [Search] by Serial Number based on Database
    public void searchBySrlNo(Connection connection) throws SQLException {

        System.out.println("[ :> ] Enter Serial No: ");
        int srlNo = myBabyScanner.nextInt();

        BookDB database = new BookDB();
        Book book = database.getBooksBySrlNo(connection, srlNo); // ----> Databases Getter Void

        if (book != null) {

            // ---->  Printing the Output for Retrieved Data By [Serial Name] Inputs
            System.out.println("                                                ");
            System.out.println("      * ------- Book Details -------- *         ");
            System.out.println("                                                ");
            System.out.println("[ :> ] Serial No: " + book.getSrlNo() + "[ :> ] Book Name: " + book.getBookName() + " [ :> ] Author Name: " + book.getAuthorName());
        
        } else {
            System.out.println("[ !! ] No Book for Serial No#: " + srlNo + " Not Found.");
        }
    }

    // ---->  Method to [Search] by Author Name based on Database
    public void searchByAuthorName(Connection connection) throws SQLException {

        System.out.println("[ :> ] Enter Author Name: ");
        String authorName = myBabyScanner.nextLine();

        BookDB database = new BookDB();
        Book book = database.getBooksByAuthorName(connection, authorName); // ---> Databases Getter Void too

        if (book != null) {

            // ---->  Printing the Output for Retrieved Data By [Author Name] Inputs
            System.out.println("                                                ");
            System.out.println("      * ------- Book Details -------- *         ");
            System.out.println("                                                ");
            System.out.println("[ :> ] Serial No: " + book.getSrlNo() + " [ :> ] Book Name: " + book.getBookName() + " [ :> ] Author Name: " + book.getAuthorName());
            System.out.println("                                                ");

        } else {
            System.out.println("[ !! ] No Book for Author Name: " + authorName + " Not Found.");
        }
    }

}
