import Model.Teacher;
import Model.Student;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Appication {
    public static void main(String[] args){
      AbstractApplicationContext context;
      context = new ClassPathXmlApplicationContext("Config/TeacherBean.xml");
        Teacher teacher = (Teacher) context.getBean("teacher");
        System.out.println(teacher);
    }
}
