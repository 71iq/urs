package com.swer348;

import java.io.FileWriter;
import java.time.*;
import java.util.*;

public abstract class Person {

    private static String fn, ln, nm, ct;
    private static LocalDate DoB;
    private String fName;
    private String lName;
    private String phoneNum;
    private String city;
    private LocalDate dob;
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final ArrayList<Faculty> faculty = new ArrayList<>();
    private static final ArrayList<Staff> staff = new ArrayList<>();
    static Scanner sc = Main.getScanner();

    public Person(String fName, String lName, String phoneNum, String city, LocalDate dob) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.city = city;
        this.dob = dob;
    }

    public static void addMember() {
        System.out.println("Enter the desired value: ");
        System.out.println("Enter 1 to add new student: ");
        System.out.println("Enter 2 to add new faculty: ");
        System.out.println("Enter 3 to add new staff: ");
        System.out.println("Enter 0 to go back");
        if (sc.hasNextInt()) {
            int role = sc.nextInt();
            if (role == 1) Person.addStudent();
            if (role == 2) Person.addFaculty();
            if (role == 3) Person.addStaff();
        }
    }

    public static void addPerson() {
        System.out.println("Enter the first name: ");
        fn = sc.next();
        System.out.println("Enter the last name: ");
        ln = sc.next();
        System.out.println("Enter the phone number: ");
        nm = sc.next();
        System.out.println("Enter the city: ");
        ct = sc.next();
        System.out.println("Enter the year of birth");
        int yd = sc.nextInt();
        System.out.println("Enter the month number of birth");
        int md = sc.nextInt();
        System.out.println("Enter the day of birth");
        int dd = sc.nextInt();
        DoB = LocalDate.of(yd, md, dd);
    }

    public static void addStudent() {
        addPerson();
        String studentId = "STU" + (students.size() + 1);
        students.add(new Student(fn, ln, nm, ct, DoB, studentId));
        try {
            FileWriter fw = new FileWriter("inputs/student.txt", true);
            fw.write(fn + "," + ln + "," + nm + "," + ct + "," + DoB.toString() + "," + studentId + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.print("Student " + fn + " " + ln + " has been added successfully\n\n");
    }

    public static void addFaculty() {
        addPerson();
        String facultyId = "FAC" + String.valueOf(faculty.size() + 1);
        try {
            FileWriter fw = new FileWriter("inputs/faculty.txt", true);
            fw.write(fn + "," + ln + "," + nm + "," + ct + "," + DoB.toString() + "," + facultyId + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        faculty.add(new Faculty(fn, ln, nm, ct, DoB, facultyId));
        System.out.print("Fauclty " + fn + " " + ln + " has been added successfully\n\n");
    }

    public static void addStaff() {
        addPerson();
        String staffId = "STA" + String.valueOf(staff.size() + 1);
        try {
            FileWriter fw = new FileWriter("inputs/staff.txt", true);
            fw.write(fn + "," + ln + "," + nm + "," + ct + "," + DoB.toString() + "," + staffId + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        staff.add(new Staff(fn, ln, nm, ct, DoB, staffId));
        System.out.print("Staff " + fn + " " + ln + " has been added successfully\n\n");
    }

    public static boolean studentExists(String id) {
        return students.stream().anyMatch(e -> e.getStudentID().equals(id));
    }

    public static boolean staffExists(String id) {
        return staff.stream().anyMatch(e -> e.getStaffID().equals(id));
    }

    public static boolean FacultyExists(String id) {
        return faculty.stream().anyMatch(e -> e.getFacultyID().equals(id));
    }

    // <editor-fold desc="getters and setters">
    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public static ArrayList<Staff> getStaff() {
        return staff;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Faculty> getFaculty() {
        return faculty;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Name: " + getFName() + " " + getLName() + ", phone Number = " + getPhoneNum() + ", City = " + getCity() + ", Date of Birth = " + getDob();
    }
}