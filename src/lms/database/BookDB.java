package lms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lms.model.Book;


// TABLE --> books

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

public class BookDB {

    // ---> @Option [ 1 ] --> Search/Retrive by Serial Number

    public Book getBooksBySrlNo(Connection connection, int srlNo) throws SQLException {

        String query = "SELECT * FROM books WHERE serial_no = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, srlNo);

            try(ResultSet rs = ps.executeQuery()) { // ---> Way for Setter & Getter to Function the Connected in Databses

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

}
