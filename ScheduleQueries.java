
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author tisya
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> student = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduleByStudent; 
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static ResultSet resultSet;
    private static PreparedStatement getWaitlistedStudentByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    
    public static void addScheduleEntry(Schedule entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule(semester, studentid, coursecode, status, timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
           
            
            addScheduleEntry.setString(2, entry.getStudentID());
            
           
            addScheduleEntry.setString(3, entry.getCourseCode());
            
            
            addScheduleEntry.setString(4, entry.getStatus());
            
            
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode)   
    {
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and coursecode = ?" );
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                count = resultSet.getInt(1);
            }
                    
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return count;
    }
    
     public static ArrayList<Schedule> getScheduleByStudent(String semester, String studentID) 
    {
        connection = DBConnection.getConnection();
        ArrayList<Schedule> schedule = new ArrayList<Schedule>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select * from app.schedule where semester = ? and studentid = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            
            while(resultSet.next())
            {
                String currstudentID = resultSet.getString("STUDENTID");
                String currsemester = resultSet.getString("SEMESTER");
                String courseCode = resultSet.getString("COURSECODE");
                String status = resultSet.getString("STATUS");
                Timestamp timestamp = resultSet.getTimestamp("TIMESTAMP");
                
                Schedule currentSchedule = new Schedule(currsemester, courseCode, currstudentID, status, timestamp); 
                schedule.add(currentSchedule);
            }
                    
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return schedule;
        
    }

   public static void updateScheduleEntry(String semester, Schedule entry){
        connection = DBConnection.getConnection();
        try
        {
             updateScheduleEntry = connection.prepareStatement("update app.schedule set status = 'S' where semester = ? and coursecode = ? and studentID = ?");
             updateScheduleEntry.setString(1,entry.getSemester());
             updateScheduleEntry.setString(2, entry.getCourseCode());
             updateScheduleEntry.setString(3,entry.getStudentID());
             updateScheduleEntry.executeUpdate();
        }catch (SQLException sqlException)
        {
          sqlException.printStackTrace();
        }
    }
    public static ArrayList<Schedule> getScheduledStudentsByCourse(String semester,String coursecode)
    {
        connection = DBConnection.getConnection();
        ArrayList<Schedule> studentscheduler = new ArrayList<>();
        try
        {
         getScheduledStudentsByCourse = connection.prepareStatement("Select * from app.schedule where semester = ? and coursecode = ?");
         getScheduledStudentsByCourse.setString(1,semester);
         getScheduledStudentsByCourse.setString(2,coursecode);
         resultSet=getScheduledStudentsByCourse.executeQuery();
         while(resultSet.next())
         {
             if (resultSet.getString(4).equals("S")){
                 Schedule Sentry=new Schedule(resultSet.getString(1),resultSet.getString(3),resultSet.getString(2),resultSet.getString(4),resultSet.getTimestamp(5));
                 studentscheduler.add(Sentry);
             }
         }
    }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentscheduler;
        
    
}
public static ArrayList<Schedule> getWaitlistedStudentByCourse(String semester, String coursecode)
    {
        connection = DBConnection.getConnection();
        ArrayList<Schedule> waitlistedstudents= new ArrayList<>();
        try
        {
         getWaitlistedStudentByCourse=connection.prepareStatement("Select * from app.schedule where semester = ? and coursecode = ?");
         getWaitlistedStudentByCourse.setString(1,semester);
         getWaitlistedStudentByCourse.setString(2,coursecode);
         resultSet=getWaitlistedStudentByCourse.executeQuery();
         while(resultSet.next())
         {
             if (resultSet.getString(4).equals("W")){
                 Schedule Sentry=new Schedule(resultSet.getString(1),resultSet.getString(3),resultSet.getString(2),resultSet.getString(4),resultSet.getTimestamp(5));
                 waitlistedstudents.add(Sentry);
             }
         }
    }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistedstudents;
        
    
}

public static void dropStudentScheduleByCourse(String Semester, String StudentID, String coursecode)
{
   connection = DBConnection.getConnection();
   try
   {
      dropStudentScheduleByCourse = connection.prepareStatement("DELETE from app.schedule where semester = ? and studentID = ? and coursecode = ?");
      dropStudentScheduleByCourse.setString(1,Semester);
      dropStudentScheduleByCourse.setString(2,StudentID);
      dropStudentScheduleByCourse.setString(3,coursecode);
    
     
      dropStudentScheduleByCourse.executeUpdate();
      }
   catch(SQLException sqlException)
           {
               sqlException.printStackTrace();
           }
        
      
   }
public static void dropScheduleByCourse(String Semester, String coursecode)
        
{
  connection = DBConnection.getConnection();
  try
  {
      dropScheduleByCourse = connection.prepareStatement("DELETE from app.schedule where semester = ? and  coursecode = ?");
      dropScheduleByCourse.setString(1, Semester);
      dropScheduleByCourse.setString(2, coursecode);
      dropScheduleByCourse.executeUpdate();
   }
  catch(SQLException sqlException)
  {
     sqlException.printStackTrace(); 
  }
  }      
}

