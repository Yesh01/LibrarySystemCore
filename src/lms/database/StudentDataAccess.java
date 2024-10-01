package lms.database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lms.model.BookingDetails;
import lms.model.Recoords;

// ---> Student's Table DB Structured [students]

// +----------+--------------+------+-----+---------+----------------+
// | Field    | Type         | Null | Key | Default | Extra          |
// +----------+--------------+------+-----+---------+----------------+
// | id       | mediumint    | NO   | PRI | NULL    | auto_increment |
// | std_name | varchar(100) | NO   |     | NULL    |                |
// | reg_num  | varchar(100) | NO   |     | NULL    |                |
// +----------+--------------+------+-----+---------+----------------+



public class StudentDataAccess {

    // --->  Method for Offcial Insert the new Data Student to Databases

    public void registerStudent(Connection connection, String studentName, String regNum ) throws SQLException {

        String query = "INSERT INTO students (std_name, reg_num) VALUES (?, ?) ";

        try(PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, studentName);
            ps.setString(2, regNum);

            int rows = ps.executeUpdate();

            if(rows > 0) {
                System.out.println("[ :> ] Student Succesfully Registered");
            }
            else {
                System.out.println("[ !! ] Failed to Register!");
            }
        }

    }

    // ---> Method for Checkig the Registration Number of the Student. [for Avoiding Duplication Error]
    // ---> Common Logic to Ask for System [Is Student is Exist? Y/N]

    public boolean isRegistered(Connection connection, String regNum) throws SQLException {
        
        String query = "SELECT * FROM students WHERE reg_num = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1, regNum);

            try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }

    // --->  Focus in Student ID for Flow in Saving Booking Details :0

    public int getStudentIdByRegNo(Connection connection, String regNum) throws SQLException {

        String query = "SELECT * FROM students WHERE reg_num = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, regNum);

            try(ResultSet rs = ps.executeQuery()) {

                if(rs.next()){
                    return rs.getInt(1); // Difference from Prev Method here u Old Man!
                }
            }
        }

        return 0;
    }

    public void fetchAllRegisteredStudents(Connection connection) throws SQLException {

        String query = "SELECT * FROM students";

            System.out.println("                                                      ");
            System.out.println("             --- Students Information ---             ");
            System.out.println("                                                      ");
            System.out.println("|----------|----------------------|------------------|");
            System.out.println("|   IDs#   |     Student Name     |     Reg No#      |");
            System.out.println("|----------|----------------------|------------------|");

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {

                        System.out.printf("| %-8s | %-20s | %-16s |\n",

                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                        
                        System.out.println("|----------|----------------------|------------------|");


                        
                    }
                }
            }
        }


        // --> Final Phase to Save Booking Details in DB

        public void saveRecoords(Connection connection, int stdId, int bookId, int qty) throws SQLException {

            String query = "INSERT INTO booking_details(std_id, book_id, qty) VALUES(?, ?, ?)";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, stdId);
                ps.setInt(2, bookId);
                ps.setInt(3, qty);

                int row = ps.executeUpdate();

                if(row > 0) {
                    System.out.println("[ :> ] Booking Details Added Succesfully");
                }
                else {
                    System.out.println("[ !! ] Failed to Add Booking Details");
                }
            }
        }

        // --> Implementation of Join Relationship 

        public List<BookingDetails>  getRecordsId(Connection connection, int stdId) throws SQLException {

            String query = "SELECT * FROM booking_details bd "
                            + "INNER JOIN books b ON b.id = bd.books_id "
                            + "WHERE bd_id = ? ";

            List<BookingDetails> bookList = new ArrayList<>();

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, stdId);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {

                    /*booking details
                     *Name
                     *bookName
                     authorname
                     book id
                     std id
                     srl no
                     qty
                     id
                     */

                     BookingDetails bookingDetails = new BookingDetails();
                     bookingDetails.setBookName(rs.getString("NAME"));
                     bookingDetails.setAuthorName(rs.getString("author_name"));
                     bookingDetails.setBookId(rs.getInt("book_id"));
                     bookingDetails.setStudentId(rs.getInt("std_id"));
                     bookingDetails.setSrlNo(rs.getInt("serial_no"));
                     bookingDetails.setQty(rs.getInt("qty"));
                     bookingDetails.setId(rs.getInt("id"));

                     bookList.add(bookingDetails);

                }
                
            }

            return bookList;

        }

        // ---> Delete here

        public void deleteBookingDeteails(Connection connection, int id) throws SQLException {

            String query = "DELETE FROM booking_details WHERE id = ? ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

        }






    


    }

 

