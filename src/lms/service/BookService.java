package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import lms.database.BookDataAccess;
import lms.model.Book;

public class BookService {

    Scanner myBabyScanner = new Scanner(System.in);  // --> Main Scanner @Hopefully don't forgot your Baby!

    // ----->  Method to [Search] by Serial Number based on Database
    public void searchBySrlNo(Connection connection) throws SQLException {

        System.out.println("[ :> ] Enter Serial No: ");
        int srlNo = myBabyScanner.nextInt();

        BookDataAccess database = new BookDataAccess();
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

        BookDataAccess database = new BookDataAccess();
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

    // ----> Method to Collect the Inputs of New Data to Adding a Book

    public void addBook(Connection connection) throws SQLException {

        System.out.println("                                                   ");
        System.out.println("          * ----- Adding a Book ----- *            ");
        System.out.println("                                                   ");

        System.out.println("[ :> ] Enter the Serial No# of the Book: ");
        int srlNo = myBabyScanner.nextInt();
        myBabyScanner.nextLine();

        System.out.println("[ :> ] Enter a Book Name: ");
        String bookName = myBabyScanner.nextLine();

        System.out.println("[ :> ] Enter an Author Name: ");
        String authorName = myBabyScanner.nextLine();

        System.out.println("[ :> ] Enter a Quantity of Books: ");
        int qty = myBabyScanner.nextInt();

        BookDataAccess bookDataAccess = new BookDataAccess();
        Book existingBokk = bookDataAccess.fetchBookByAuthorOrSerial(connection, authorName, srlNo);

        if(existingBokk != null) {
            System.out.println("[ !! ] Book details exist into our System.");
            System.out.println("[ !! ] Please Add another Book");
            return;
        }

        Book addingBookData = new Book();
        addingBookData.setSrlNo(srlNo);
        addingBookData.setBookName(bookName);
        addingBookData.setAuthorName(authorName);
        addingBookData.setBookQty(qty);

        bookDataAccess.saveBook(connection, addingBookData);

    }

    public void updateBookQty(Connection connection) throws SQLException {

        System.out.println("[ :> ] Enter the Serial No# of the Book: ");
        int srlNo = myBabyScanner.nextInt();

        BookDataAccess dataAccess = new BookDataAccess();
        Book book = dataAccess.getBooksBySrlNo(connection, srlNo);

        if (book == null) {
            System.out.println("[ !! ] Book not Available!");
            return;
        }

        System.out.println("[ :> ] Enter No# of Books to be Added: ");
        int addedQty = myBabyScanner.nextInt();

        Book inputSum = new Book();
        inputSum.setBookQty(book.getBookQty() + addedQty);
        inputSum.setSrlNo(srlNo);

        dataAccess.updateBookQty(connection, inputSum);

    }


    




}
