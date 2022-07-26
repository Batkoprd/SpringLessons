package CoreFeatures;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
@Scope(scopeName = "singleton") //if prototype everytime we get object from container it is a new object
public class Doctor implements Staff, BeanNameAware {

    @Value("MBBS")
    private String qualification;

    public Doctor() {
    }

    public Doctor(String qualification) {
        this.qualification = qualification;
    }

    public void assist() {
        System.out.println("Doctor is assisting.");
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("PostConstruct method is called");
    }

    @PreDestroy
    public void destroyMethod() {
        System.out.println("PreDestroy method is called");
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "qualification='" + qualification + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("Set bean name method is called");
    }


}
