package com.sg.classmodeling;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patri
 */
public class School2 {
    private int enrollment;
    private int numberOfTeachers;
    private String[] coursesOffered;
    private String sportsNickname;
    private String clubsOffered;
    private Student[] studentRoster;
    
    public void enrollStudent(Student student){
        
    }
    public void unenrollStudent(Student student){
        
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public String[] getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(String[] coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public String getSportsNickname() {
        return sportsNickname;
    }

    public void setSportsNickname(String sportsNickname) {
        this.sportsNickname = sportsNickname;
    }

    public String getClubsOffered() {
        return clubsOffered;
    }

    public void setClubsOffered(String clubsOffered) {
        this.clubsOffered = clubsOffered;
    }

    public Student[] getStudentRoster() {
        return studentRoster;
    }

    public void setStudentRoster(Student[] studentRoster) {
        this.studentRoster = studentRoster;
    }
    
    
}
