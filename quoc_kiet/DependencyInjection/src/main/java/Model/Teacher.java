package Model;

public class Teacher {
    private Student student;

    public Teacher(Student student){ this.student=student;}

    public Student getStudent(){ return student;}

    public void setStudent(Student student){ this.student=student;}

    public String toString(){
        return "Teacher{" + "student+" + student + '}';
    }
}
