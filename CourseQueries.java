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
public class CourseQueries {
    private static PreparedStatement getAllCourses;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet resultSet;
    private static PreparedStatement getCourseSeats;
    private static Connection connection;
    private static ArrayList<String> course = new ArrayList<String>();
    private static PreparedStatement addCourse;
    private static PreparedStatement dropCourse;
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.courseentry(semester, coursecode, description, seats) values (?, ?, ?, ?)");
            addCourse.setString(1, course.getSemester());
            
            addCourse.setString(2, course.getCourseCode());
            
            addCourse.setInt(4, course.getSeats());
            
            addCourse.setString(3, course.getDescription());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<CourseEntry> getAllCourses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> course = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select * from app.courseentry where semester = ?");
            getAllCourses.setString(1, semester);
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                String currsemester = resultSet.getString("SEMESTER");
                String description = resultSet.getString("DESCRIPTION");  
                String courseCode = resultSet.getString("COURSECODE");
                int seats = resultSet.getInt("SEATS");                             
                CourseEntry currentCourse = new CourseEntry(currsemester, courseCode, description, seats); 
                course.add(currentCourse);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return course;
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester) 
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.courseentry where semester = ?");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
        
    }
    
     public static int getCourseSeats(String semester, String courseCode)  
    {
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select seats from app.courseentry where semester = ? and coursecode = ?");                    
            getCourseSeats.setString(1, semester);
            getCourseSeats.setString(2, courseCode);
            resultSet = getCourseSeats.executeQuery();
            
            while (resultSet.next()) {
                seats = resultSet.getInt(1);
            }
            
            return seats;
                    
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return seats;
    }

    public static void dropCourse(String semester , String coursecode)
   {
     connection = DBConnection.getConnection();
     try {
         dropCourse.setString(2, coursecode);
         dropCourse = connection.prepareStatement("delete from app.courseentry where semester=? and coursecode=?");
         dropCourse.setString(1, semester);
         dropCourse.executeUpdate();
     }
     catch(SQLException sqlException)
     {
       sqlException.printStackTrace();
     }
       
   }
    
    
}
