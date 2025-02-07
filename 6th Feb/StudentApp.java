import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

class Fees{
    private float totalFees;
    private float feesPaid;
    private float feesPending;

    public Fees(float totalFees,float feesPaid){
        this.totalFees = totalFees;
        this.feesPaid = feesPaid;
        feesPending = totalFees - feesPaid;
    }
    public String toString(){
        return "Total: "+totalFees+" Fees paid: "+feesPaid+" Fees Pending: "+feesPending;
    }
    public float getFeesPaid(){
        return feesPaid;
    }
    public float getFeesPending(){
        return feesPending;
    }
}
class Student{
    private int rollNo;
    private String name;
    private int age;
    private int standard;
    private String school;
    private String gender;
    private float percentage;
    private Fees fees;

    public Student(int rollNo, String name, int age, int standard, String school, String gender, float percentage, Fees fees){
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.standard = standard;
        this.school = school;
        this.gender = gender;
        this.percentage = percentage;
        this.fees = fees;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getStandard() {
        return standard;
    }
    public String getGender() {
        return gender;
    }
    public Float getPercentage() {
        return percentage;
    }
    public String getSchool() {
        return school;
    }
    public Fees getFees() {
        return fees;
    }

    public String toString() {
        return "Roll No: " + rollNo + "\n" +
               "Name: " + name + "\n" +
               "Age: " + age + "\n" +
               "Standard: " + standard + "\n" +
               "School: " + school + "\n" +
               "Gender: " + gender + "\n" +
               "Percentage: " + percentage + "\n" +
               "Fees: " + fees;
    }
    
}
public class StudentApp {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "Sanju", 18, 12, "ABC School", "Male", 85.5f, new Fees(8000, 4000)));
        list.add(new Student(2, "Alice", 17, 11, "XYZ School", "Female", 90.2f, new Fees(7500, 3000)));
        list.add(new Student(3, "Bob", 19, 12, "PQR School", "Male", 78.3f, new Fees(8000, 4500)));
        list.add(new Student(4, "Charlie", 18, 10, "XYZ School", "Male", 88.7f, new Fees(7000, 3500)));
        list.add(new Student(5, "David", 17, 11, "ABC School", "Male", 82.4f, new Fees(7600, 3800)));
        list.add(new Student(6, "Eva", 16, 10, "XYZ School", "Female", 91.5f, new Fees(7000, 1000)));
        list.add(new Student(7, "Frank", 15, 10, "PQR School", "Male", 76.2f, new Fees(7300, 2000)));
        list.add(new Student(8, "Grace", 18, 12, "ABC School", "Female", 89.1f, new Fees(8000, 4000)));
        list.add(new Student(9, "Hannah", 16, 11, "XYZ School", "Female", 84.3f, new Fees(7600, 3600)));
        list.add(new Student(10, "Ivy", 17, 10, "PQR School", "Female", 80.0f, new Fees(7200, 2000)));
        list.add(new Student(11, "Jack", 19, 12, "ABC School", "Male", 77.9f, new Fees(8100, 4100)));
        list.add(new Student(12, "Kelly", 18, 12, "XYZ School", "Female", 85.5f, new Fees(7400, 3700)));
        list.add(new Student(13, "Liam", 15, 10, "PQR School", "Male", 79.3f, new Fees(7000, 3500)));
        list.add(new Student(14, "Mia", 16, 11, "ABC School", "Female", 92.4f, new Fees(7300, 3300)));
        list.add(new Student(15, "Nina", 18, 12, "XYZ School", "Female", 81.2f, new Fees(8000, 5000)));
        list.add(new Student(16, "Oliver", 17, 11, "PQR School", "Male", 86.5f, new Fees(7500, 4000)));
        list.add(new Student(17, "Paul", 19, 12, "ABC School", "Male", 89.3f, new Fees(8200, 4100)));
        list.add(new Student(18, "Quincy", 18, 11, "XYZ School", "Male", 93.2f, new Fees(7600, 3800)));
        list.add(new Student(19, "Rose", 16, 10, "PQR School", "Female", 85.7f, new Fees(7100, 3000)));
        list.add(new Student(20, "Sam", 15, 10, "ABC School", "Male", 78.4f, new Fees(7200, 2800)));
        list.add(new Student(21, "Tina", 19, 12, "XYZ School", "Female", 92.9f, new Fees(8000, 4200)));
        list.add(new Student(22, "Ursula", 18, 12, "PQR School", "Female", 87.0f, new Fees(7800, 3900)));
        list.add(new Student(23, "Vince", 17, 11, "ABC School", "Male", 88.8f, new Fees(7500, 3500)));
        list.add(new Student(24, "Wendy", 19, 12, "XYZ School", "Female", 83.2f, new Fees(8200, 4500)));
        list.add(new Student(25, "Xander", 18, 12, "PQR School", "Male", 90.1f, new Fees(7800, 3800)));

        int totalStudents = list.size();

        System.out.println("How many students in each standard?");
        Map<Integer,Long> studentsByStandard = list.stream().collect(Collectors.groupingBy(Student::getStandard,Collectors.counting()));
        studentsByStandard.forEach((standard,count)->System.out.println("Standard "+standard+" : "+count));

        System.out.println("How many students male & female students?");
        Map<Boolean,Long> studentsByGender = list.stream().collect(Collectors.partitioningBy(s -> s.getGender().equals("Female"),Collectors.counting()));
        studentsByGender.forEach((isFemale, count) -> {
            if (isFemale) {
                System.out.println("Female: " + count);
                System.out.println("Male: " + (totalStudents-count));
            } else {
                System.out.println("Male: " + count);
                System.out.println("Female: " + (totalStudents-count));
            }
        });
        
        System.out.println("How many students have failed and pass - WHOLE UNIVERSITY");
        Map<Boolean, Long> passedStudents = list.stream().collect(Collectors.partitioningBy(s->s.getPercentage()>40f,Collectors.counting()));
        passedStudents.forEach((hasPassed, count) -> {
            if (hasPassed) {
                System.out.println("Passed: " + count);
            } else {
                System.out.println("Failed: " + count);
            }
        });

        List<Student> sortedStudents = list.stream().sorted((s1, s2) -> Float.compare(s2.getPercentage(), s1.getPercentage()))
        .collect(Collectors.toList());

        Map<String, List<Student>> groupedBySchool = sortedStudents.stream()
        .collect(Collectors.groupingBy(Student::getSchool));

        System.out.println("How many students have failed and pass - SCHOOL WISE");
        Map<String,Map<Boolean, Long>> m1 = list.stream().collect(Collectors.groupingBy(Student::getSchool,Collectors.collectingAndThen(Collectors.partitioningBy(s->s.getPercentage()>40f,Collectors.counting()), passFail -> passFail)));
        m1.forEach((school,count)->{
            System.out.println("School "+school+" : ");
            System.out.println("Passed: " + count.get(true));
            System.out.println("Failed: " + count.get(false));
        });


        System.out.println("Top 3 students (Whole university)");
        List<Student> toppersUni = sortedStudents.stream()
        .limit(3).collect(Collectors.toList());
        System.out.println(toppersUni);

        System.out.println("Top scorer school wise");

        groupedBySchool.forEach((school, students) -> {
            Student topScorer = students.get(0);
            System.out.println("Top scorer in " + school + ": " + topScorer.getName() + " with percentage: " + topScorer.getPercentage());
        });

        System.out.println("Average age of male & female students");
        Map<String,Double> avgAgeByGender = list.stream().collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge)));
        System.out.println(avgAgeByGender);

        System.out.println("Total Fees Collected School Wise:");
        Map<String, Double> totalFeesCollectedBySchool = list.stream()
        .collect(Collectors.groupingBy(Student::getSchool, Collectors.summingDouble(s -> s.getFees().getFeesPaid())));

        totalFeesCollectedBySchool.forEach((school, total) -> System.out.println(school + ": " + total));

        System.out.println("Total Fees Pending School Wise:");
        Map<String, Double> totalFeesPendingBySchool = list.stream()
        .collect(Collectors.groupingBy(Student::getSchool, Collectors.summingDouble(s -> s.getFees().getFeesPending())));

        totalFeesPendingBySchool.forEach((school, total) -> System.out.println(school + ": " + total));

        System.out.println("Total Students in university are: "+totalStudents);
    }
}