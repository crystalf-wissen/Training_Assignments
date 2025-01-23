
class Student implements Comparable
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
    public int compareTo(Object obj)
    {
        Student s = (Student) obj;
        //return new Integer(rollNo).compareTo(s.rollNo);
        return name.compareTo(s.name)*-1;
        //return new Integer(standard).compareTo(s.standard);
    }
}

public class StudentsList
{
    public static void main(String args[])
    {
        //HashSet hs = new HashSet();
        TreeSet hs = new TreeSet();
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