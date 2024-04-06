/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tisya
 */

public class StudentQueries {
    private static Connection connection;
    private static ArrayList<String> student = new ArrayList<String>();
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.studententry(studentID, FirstName, LastName) values (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            
            addStudent.setString(2, student.getFirstName());
           
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> student = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select * from app.studententry");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                String studentID = resultSet.getString("STUDENTID");
                String FirstName = resultSet.getString("FIRSTNAME");
                String LastName = resultSet.getString("LASTNAME");
                var currentStudent = new StudentEntry(studentID, FirstName, LastName); 
                student.add(currentStudent);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
        
    }
    
    public static StudentEntry getStudent(String StudentID){
        connection = DBConnection.getConnection();
        StudentEntry id = null;
        try 
        {
            getStudent = connection.prepareStatement("Select StudentID, FirstName, LastName from app.studententry where StudentID = ?");
            getStudent.setString(1,StudentID);
            resultSet = getStudent.executeQuery();
            
            while (resultSet.next()) {
                id = new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return id;
    }

    public static void dropStudent(String StudentID)
    {
      connection = DBConnection.getConnection();
      
      try
      {
          dropStudent = connection.prepareStatement("DELETE FROM APP.STUDENTENTRY WHERE STUDENTID = ?");
          dropStudent.setString(1,StudentID);
          dropStudent.executeUpdate();
      }
      catch(SQLException sqlException)
      {
          sqlException.printStackTrace();
      }
      
      
     
    }
}
