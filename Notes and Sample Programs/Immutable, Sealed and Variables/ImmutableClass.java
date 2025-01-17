import java.io.*;
// final class Student implements Serializable {
//     private final int rollNo;
//     private final String name;
//     private final int standard;

//     public Student(int rn, String n, int s) {
//         rollNo = rn; 
//         name = n;
//         standard = s;
//     }

//     public int getRollNo() {
//         return rollNo;
//     }

//     public String getName() {
//         return name;
//     }

//     public int getStandard() {
//         return standard;
//     }

//     public String toString() {
//         return "Student[Roll No. : "+rollNo+", Name: "+name+", Standard: "+standard+"]";
//     }

//     public int hashCode() {
//         return rollNo;
//     }

//     public boolean equals(Object obj) {
//         Student s1= (Student ) obj;
//         if((this.rollNo==s1.rollNo) && (this.name.equals(s1.name)) && (this.standard==s1.standard)) {
//             return true;
//         } else {
//             return false;
//         }
//     }
// }

// record Student(int rollNo,String name, int standard) { }

record Student(int rollNo, String name, int standard) {
    static String schoolName = "TWS";
    Student() {
        this(22,"Raj",3);
    }
    Student(int rollNo, String name, int standard) {
        if(rollNo<=0)
            throw new NullPointerException();
        this.rollNo = rollNo;
        this.name = name;
        this.standard = standard;
    }

    public static void display() {
        System.out.println("School Name:"+schoolName);
    }

    public boolean equals(Object obj) {
        if(this.rollNo==((Student)obj).rollNo)
            return true;
        else
            return false;
    }

    public int hashCode() {
        return this.rollNo;
    }
}

public class ImmutableClass {
    public static void main(String[] args) {
        Student s1 = new Student(101, "John", 10);
        Student s2 = new Student(101, "John", 10);
        Student s3 = new Student();

        System.out.println(s1); 
        System.out.println(s2);    
        System.out.println(s3);    

        System.out.println(s1.hashCode()); 
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
        
        System.out.println(s1.equals(s2)); 

        s1.display();
        s2.display();
        s3.display();
    }
}