package HibernateLessons.OneToManyUni.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments_onetomany")
public class Department_OneToMany_UNI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "min_salary")
    private int minSalary;
/*
При использовании связи One-to-Many в аннотации @JoinColumn name
будет ссылаться на Foreign Key не из source, а из target таблицы.
 */
    @OneToMany(cascade = CascadeType.ALL //Департамент относится к сотрудникам OneToMany
                ,fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id") //departments это source table, а employees target table. department_id это foreignkey из таблицы employees
    private List<Employee_OneToMany_UNI> emps;


    public Department_OneToMany_UNI() {
    }

    public Department_OneToMany_UNI(String departmentName, int maxSalary, int minSalary) {
        this.departmentName = departmentName;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }

    public void addEmployeeToDepartment(Employee_OneToMany_UNI employee) {
        if (emps == null) {
            emps = new ArrayList<>();
        }
        emps.add(employee);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public List<Employee_OneToMany_UNI> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee_OneToMany_UNI> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", maxSalary=" + maxSalary +
                ", minSalary=" + minSalary +
                '}';
    }
}
