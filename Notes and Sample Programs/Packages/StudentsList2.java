import java.util.*;

class Student
{
    int rollNo;
    String name;
    int standard;
    
    public Student(int rn, String n, int s)
    {
        rollNo = rn;
        name = n;
        standard = s;
    }
    public String toString()
    {
        System.out.println("RollNo: "+rollNo);
        System.out.println("Name: "+name);
        System.out.println("Standard: "+standard);
        return "";
    }

    public boolean equals(Object obj)
    {
        Student s = (Student) obj;
        if((this.rollNo==s.rollNo) && (this.standard == s.standard))
            return true;
        else   
            return false;
    }
    public int hashCode()
    {
        return standard;
    }
}

class RollNoSorterDesc implements Comparator<Student>
{
    public int compare(Student s1, Student s2)
    {
        return new Integer(s1.rollNo).compareTo(s2.rollNo) * -1;
    }
}

class NameSorter implements Comparator<Student>
{
    public int compare(Student s1, Student s2)
    {
        return s1.name.compareTo(s2.name);
    }
}

public class StudentsList2
{
    public static void main(String args[])
    {
        HashSet hs = new HashSet(new RollNoSorterDesc());
        //TreeSet hs = new TreeSet(new RollNoSorterDesc());
        hs.add(new Student(11,"Pintu",5));
        hs.add(new Student(12,"Sanju",2));
        hs.add(new Student(13,"Chintu",4));
        hs.add(new Student(14,"Manju",7));
        hs.add(new Student(15,"Rita",3));
        hs.add(new Student(16,"Sita",4));

        Iterator i = hs.iterator();
        while(i.hasNext())
        {
            System.out.println(i.next());
        }
    }
}