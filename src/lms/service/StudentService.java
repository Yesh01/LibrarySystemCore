package lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import lms.database.StudentDataAccess;

public class StudentService {

    Scanner myBabyScanner = new Scanner(System.in);

    // ---> Layout to Collect the new Data to Add in Database

    public void addStudent(Connection connection) throws SQLException {


        System.out.println("[ :> ] Enter a Student Name: ");
        String studentName = myBabyScanner.nextLine();

        System.out.println("[ :> ] Enter the Registral Number# for the Student: ");
        String regNum = myBabyScanner.nextLine();

        StudentDataAccess dataAccess = new StudentDataAccess();
        boolean isStudentExist = dataAccess.isRegistered(connection, regNum);

        if(isStudentExist) {
            System.out.println("[ !! ] Failed to Add the Student that had already exist.");
            return;
        }

        dataAccess.registerStudent(connection, studentName, regNum);

    }

    public void displayAllStudents(Connection connection) throws SQLException {

        StudentDataAccess dataAccess = new StudentDataAccess();
        dataAccess.fetchAllRegisteredStudents(connection);

    }



}
