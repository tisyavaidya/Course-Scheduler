/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Timestamp;
/**
 *
 * @author tisya
 */
public class Schedule {
    private String semester; 
    private String CourseCode; 
    private String StudentID;
    private String status;
    private Timestamp timestamp;
    
      
    
    public Schedule() //Constructor
    {
        
    }
    

    public Schedule(String semester, String CourseCode, String StudentID, String status, Timestamp timestamp)  
    {
        setSemester (semester);
        setCoursecode (CourseCode);
        setStudentID (StudentID);
        setStatus (status);
        setTimestamp (timestamp);
            
    }
    
    private void setSemester(String semester) {
            this.semester = semester;
    }

    private void setCoursecode(String CourseCode) {
            this.CourseCode = CourseCode;
    }

    private void setStudentID(String StudentID) {
            this.StudentID = StudentID;
    }

    private void setStatus(String status) {
            this.status = status;
    }

    private void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
    }
   
    
    public String getSemester()
    {
        return semester;
    }
    
    public String getCourseCode()
    {
        return CourseCode;
    }
    
    public String getStudentID()
    {
       return StudentID;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    Timestamp getCurrentTimestamp() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
