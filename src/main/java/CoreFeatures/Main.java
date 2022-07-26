package CoreFeatures;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // ApplicationContext extends BeanFactory so there is more features
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContextCoreFeatures.xml");

        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);

        //we are getting objects from spring container
        /*
        Using property in xml is setter injection
        Using constructor in xml is constructor injection
         */
        Doctor doctor = context.getBean(Doctor.class);
        doctor.assist();
        System.out.println(doctor);

//        Nurse nurse = (Nurse) context.getBean("nurse");
//        nurse.assist();
    }
}
