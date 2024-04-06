/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tisya
 */
public class CourseEntry {

    private String semester;
    private String CourseCode;
    private String description;
    private int seats;

    public CourseEntry(String semester, String CourseCode, String description, int seats) {
        this.semester = semester;
        this.CourseCode = CourseCode;
        this.description = description;
        this.seats = seats;
    }

    public String getDescription() {
        return description;
    }
    
    public String getCourseCode() {
        return CourseCode;
    }
    
    public String getSemester() {
        return semester;
        
    }
    public int getSeats() {
        return seats;
    }

}
