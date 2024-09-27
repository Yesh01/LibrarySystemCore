package lms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lms.model.Book;


// ---> Book's Table DB Structured [books]

/*+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| id          | mediumint    | NO   | PRI | NULL    | auto_increment |
| serial_no   | int          | NO   |     | NULL    |                |
| NAME        | varchar(100) | NO   |     | NULL    |                |
| author_name | varchar(100) | NO   |     | NULL    |                |
| qty         | int          | YES  |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+ */

// ---> Current Data in Books Table
/*+----+-----------+----------------------+-------------+------+
| id | serial_no | NAME                 | author_name | qty  |
+----+-----------+----------------------+-------------+------+
|  1 |       101 | Java Language 2050   | Yearu       |  333 |
|  2 |       102 | CShark Language 2076 | Brent       |  404 |
|  3 |       103 | PyGods Language 3088 | Emil        |   98 |
+----+-----------+----------------------+-------------+------+ */

public class BookDataAccess {

    // ---> @Option [ 1 ] --> Search/Retrive by Serial Number

    public Book getBooksBySrlNo(Connection connection, int srlNo) throws SQLException {

        String query = "SELECT * FROM books WHERE serial_no = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, srlNo);

            try(ResultSet rs = ps.executeQuery()) {
                
                // ---> Way for Setter & Getter to Function the Connected in Databses

                if(rs.next()) {

                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("NAME"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setSrlNo(rs.getInt("serial_no"));

                    return book;

                }
            }
        }
        return null;
        
    }

    // ---> @Option [ 2 ] --> Search/Retrive by Author Name
    
    public Book getBooksByAuthorName(Connection connection, String authorName) throws SQLException {

        String query = "SELECT * FROM books WHERE author_name = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, authorName);


            try(ResultSet rs = ps.executeQuery()) {

                if(rs.next()) {

                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("NAME"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setId(rs.getInt("id"));
                    book.setSrlNo(rs.getInt("serial_no"));

                    return book;

                }
            }
        }
        return null;
    }

    //   ----> Method for Checking Validation in Duplication when Inserting new data

    public Book fetchBookByAuthorOrSerial(Connection connection, String authorName, int srlNo) throws SQLException {

        String query = "SELECT * FROM books WHERE author_name = ? OR serial_no = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, authorName);
            ps.setInt(2, srlNo);

            try(ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("NAME"));
                    book.setBookQty(rs.getInt("Qty"));
                    book.setId(rs.getInt("id"));
                    book.setSrlNo(rs.getInt("serial_no"));
                    return book;
                }
            }
        }
        return null;
    }

    //     ----> Method for Official Saving the Collected New Data to Databases

    public void saveBook(Connection connection, Book book) throws SQLException {

        String query = "INSERT INTO books(serial_no, NAME, author_name, qty) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, book.getSrlNo());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthorName());
            ps.setInt(4, book.getBookQty());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("[ :> ] Book Added Succesfully.");
            } else {
                System.out.println("[ !! ] Failed to Add Book");
            }
        }
    }

    // Method for adding a quantity to Database based on serial no# for Quantity Fields
    
    public void updateBookQty(Connection connection, Book book) throws SQLException {

        String query = "UPDATE books SET qty = ? WHERE serial_no = ? ";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
    
            ps.setInt(1, book.getBookQty());
            ps.setInt(2, book.getSrlNo());
    
            int rows = ps.executeUpdate();
    
            if(rows > 0) {

                System.out.println("[ :> ] Book Updated Succesfully!");
            }
            else {
                System.out.println("[ !! ] Failed to Update Book");
            }
        }
    
    }

    // ---> Method for retrieving the data from database connectivity as follow D-A-O Strategy

    public List<Book> displayCurrentBooks(Connection connection) throws SQLException {

        String query = "SELECT * FROM books";

        // --> Using ArrayLists

        List<Book> allBooks = new ArrayList<>();

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            try(ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Book book = new Book();

                    book.setAuthorName(rs.getString("author_name"));
                    book.setBookName(rs.getString("NAME"));
                    book.setBookQty(rs.getInt("qty"));
                    book.setSrlNo(rs.getInt("serial_no"));
                    book.setId(rs.getInt("id"));

                    allBooks.add(book);
                }
            }
        }

        return allBooks;

    }



}





    


