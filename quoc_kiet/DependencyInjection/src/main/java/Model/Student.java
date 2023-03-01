package Model;

public class Student {
    private String name;
    private float mark;
    public Student(){}

    public Student(String name){this.name = name;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public float getMark(){return mark;}

    public void setMark(float mark){this.mark = mark;}

    public String toString(){
        return "Student{" + "Name+" + name + '}';
    }
}
