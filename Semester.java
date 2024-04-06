/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tisya
 */
public class Semester {
    private String semester;
          
    
    public Semester() //Constructor
    {
        
    }
    

    public Semester(String semester)  
    {
        
        setSemester (semester);
            
    }
    
    private void setSemester(String semester) {
        this.semester = semester;
    }
    
    public String getSemester()
    {
        return semester;
    }
}
