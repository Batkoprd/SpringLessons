package HibernateLessons.OneToManyBi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees_onetomany")
public class Employee_OneToMany_BI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private int salary;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
//    @ManyToOne(cascade = CascadeType.ALL,   //Много работников может работать в одном департаменте
                fetch = FetchType.EAGER)     // ManyToOne у каждого из множества работников может быть только один департамент, поэтому когда мы подгружаем работника нет проблем подгрузить одну строку департамента
    //В @ManyToOne ForeignKey всегда находится в таблице Many, в данном случае employees
    @JoinColumn(name = "department_id") // В аннотации @JoinColumn всегда прописываем ForeignKey, в данном случае он находится в таблице employees
    private Department_OneToMany_BI department;

    public Department_OneToMany_BI getDepartment() {
        return department;
    }

    public void setDepartment(Department_OneToMany_BI department) {
        this.department = department;
    }

    public Employee_OneToMany_BI() {
    }

    public Employee_OneToMany_BI(String name, String surname, int salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                '}';
    }
}


