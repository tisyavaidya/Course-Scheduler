/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tisya
 */
public class StudentEntry {

    private String StudentID; 
    private String FirstName; 
    private String LastName;
          
    
    public StudentEntry(StudentEntry StudentID1) //Constructor
    {
        
    }
    

    public StudentEntry(String StudentID, String FirstName, String LastName)  
    {
        
        setStudentID (StudentID);
        setFirstName (FirstName);
        setLastName (LastName);
            
    }
    
    private void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    private void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    private void setLastName(String LastName) {
        this.LastName = LastName;
    }
    
     public String getFirstName() 
    {
        return FirstName;
    }
    
    public String getLastName()  
    {
        return LastName;
    }
    public String getStudentID() 
    {
       return StudentID;
    }
    
    public String toString(){
        return LastName + ", "+FirstName;
    }
}
