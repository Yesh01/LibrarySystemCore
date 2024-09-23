package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import lms.database.BookDB;
import lms.model.Book;

public class BookService {

    Scanner myBabyScanner = new Scanner(System.in);

    // ----->  Method to search by Serial Number based on Database
    public void searchBySrlNo(Connection connection) throws SQLException {

        System.out.println("[ :> ] Enter Serial No: ");
        int srlNo = myBabyScanner.nextInt();

        BookDB database = new BookDB();
        Book book = database.getBooksBySrlNo(connection, srlNo); // ---> Databases Getter Void

        if (book != null) {

            System.out.println("      ---- Book Details ----         ");
            System.out.println("Serial No: " + book.getSrlNo() + "Book Name: " + book.getBookName() + "Author Name: " + book.getAuthorName());
        
        } else {
            System.out.println("No Book for Serial No#: " + srlNo + " Found.");
        }
    }

    // Method -->
    public void searchByAuthorName(Connection connection) throws SQLException {

        System.out.println("Enter Author Name: ");
        myBabyScanner.nextLine();

        String authorName = myBabyScanner.nextLine();

        BookDB database = new BookDB();
        Book book = database.getBooksByAuthorName(connection, authorName);

        if (book != null) {

            System.out.println("      ---- Book Details ----         ");
            System.out.println("Serial No: " + book.getSrlNo() + "Book Name: " + book.getBookName() + "Author Name: " + book.getAuthorName());
        
        } else {
            System.out.println("No Book for Author Name: " + authorName + " Found.");
        }
    }

}
