package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import lms.database.BookDataAccess;
import lms.database.StudentDataAccess;
import lms.model.Book;
import lms.model.BookingDetails;

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

    // ----> Method to Collect the the request of changes [Adding Qty] then proceed in DOA Functionality

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

    // Method for printing all the data as reuseable code functionality

    public void displayCurrentBooks(Connection connection) throws SQLException {

        BookDataAccess dataAccess = new BookDataAccess();
        List<Book> allBooks = dataAccess.displayCurrentBooks(connection);
        
        System.out.println("                                                                                                   ");
        System.out.println("                                      --- Library Information ---                                  ");
        System.out.println("                                                                                                   ");
        System.out.println("|-------------------|-------------------------------------|------------------------|--------------|");
        System.out.println("| Book Serial No#   |               Book Name             |       Author Name      |   Quantity   |");
        System.out.println("|-------------------|-------------------------------------|------------------------|--------------|");
        
        for (Book book : allBooks) {

            System.out.printf("| %-17s | %-35s | %-22s | %-12s |\n",

                              book.getSrlNo(),
                              book.getBookName(),
                              book.getAuthorName(),
                              book.getBookQty());
        }
        
        System.out.println("|-------------------|-------------------------------------|------------------------|--------------|");
    }

    /*               Check-In || Check-Out                */
    // ---> Borrow & Return Buisness Logic Flow Here [ !! ]
    

    /*                 [Check-IN]                    */

    public void returnBook(Connection connection) throws SQLException {

        /*
         *  [ 1 ]  Input For Reg No# for Verification
         *  [ 2 ]  After Verification Search by Reg No# Will Displayed Book Details
         *         that assigned lists for specific student after retrieved too the ID
         *         of the Registered Student
         *  [ 3 ]  Input Book Serial Numnber for Choices
         *  [ 4 ]  Using the [Stream API Filtered] recommended to the selected book in
         *         Booking Details in the lists of borrowed books maybe else not found will be null
         *  [ 5 ]  Update +1 the qty of Books in Database.
         *  [ 6 ]  Delete for returned book
         *
         */

        // :p

        StudentDataAccess dataAccess = new StudentDataAccess(); // --> not yet

        System.out.println("[ :> ] Enter Registration Number: ");
        String regNum = myBabyScanner.nextLine();

        boolean isExists = dataAccess.isRegistered(connection, regNum); // [ 2 ]

        if (!isExists) {
            System.out.println("[ !! ] Student is not Registered");
            System.out.println("[ !! ] Please Register Firsts to the Admin NU-ITSO");
            return;
        }

        int id = dataAccess.getStudentIdByRegNo(connection, regNum);
        List<BookingDetails> recordsList = dataAccess.getRecordsId(connection, id);
        
        // --> To Show Displayed Lists Here

        recordsList.stream().forEach(booking -> System.out.println(
            String.format("%-10d %-30s %-30s",
                            booking.getSrlNo(),
                            booking.getAuthorName(),
                            booking.getBookName())
        ));

        // --> Stream API Usage

        System.out.println("[ :> ] Enter Serial No# of Book to be Check-In: ");
        int srlNo = myBabyScanner.nextInt();

        BookingDetails matchingBooking = recordsList.stream()
                    .filter(booking -> booking.getSrlNo() == srlNo)
                    .findAny()
                    .orElse(null);


        // --> Connectionz

        BookDataAccess dBookDataAccess = new BookDataAccess();
        Book book = dBookDataAccess.getBooksBySrlNo(connection, srlNo);
        book.setBookQty(book.getBookQty() + 1);

        dBookDataAccess.updateBookQty(connection, book);
        dataAccess.deleteBookingDeteails(connection, matchingBooking.getId());
        
        // --> Update Qty
        // --> Also Delete as last
        // --> Continuation Tommorow After Sunday Service

    }

    /*                 [Check-OUT]                 */

    public void borrowBook(Connection connection) throws SQLException {

        // :0

        // Same flow in Check-in. But more likely is opposite

        StudentDataAccess dataAccess = new StudentDataAccess();

        System.out.println("[ :> ] Enter Registration Number: ");
        String regNum = myBabyScanner.nextLine();

        boolean isExists = dataAccess.isRegistered(connection, regNum); // [ 2 ]

        if (!isExists) {
            System.out.println("[ !! ] Student is not Registered");
            System.out.println("[ !! ] Please Register Firsts to the Admin NU-ITSO");
            return;
        }

        displayCurrentBooks(connection);

        System.out.println("[ :> ] Serial Number of the Book to Check-Out: ");
        int srlNo = myBabyScanner.nextInt();

        BookDataAccess dBookDataAccess = new BookDataAccess();
        Book book = dBookDataAccess.getBooksBySrlNo(connection, srlNo);

        if(book == null) {
            System.out.println("Books is Not Available");
            return;
        }

        book.setBookQty(book.getBookQty() - 1);

        int id = dataAccess.getStudentIdByRegNo(connection, regNum);

        dataAccess.saveRecoords(connection, id, book.getId(), 1);
        dBookDataAccess.updateBookQty(connection, book);

        
    }


}