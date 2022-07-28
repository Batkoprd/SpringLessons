package HibernateLessons.OneToOneBi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees_onetoone")
public class Employee_OneToOne_BI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "salary")
    private int salary;

    @OneToOne(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    // OneToOne FetchType.EAGER, когда мы загружаем информацию о работнике у него может быть только одна детальная информация,
    // поэтому при выборе работника подгрузится одна единственная строка с details, поэтому ее можно сразу подгружать с FetchType.EAGER
    @JoinColumn(name = "details_id") // связь между классами
    private Detail_OneToOne_BI empDetail;

    public Employee_OneToOne_BI() {
    }

    public Employee_OneToOne_BI(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Detail_OneToOne_BI getEmpDetail() {
        return empDetail;
    }

    public void setEmpDetail(Detail_OneToOne_BI empDetail) {
        this.empDetail = empDetail;
    }
}

