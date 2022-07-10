package HibernateLessons.OneToOneUni.Entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee_OneToOne_UNI {
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

    @OneToOne(cascade = CascadeType.ALL//связь с таблицей details
            ,fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id") // details_id это foreign key используется для создания связи между двумя таблицами
                                        // ссылается на Primary key таблицы details
                                    // foreign key не может содержать информацию, которой нет в столбце на который он ссылается
    /*
    Cascade операций –это выполнение операции не только для Entity, на котором операция вызывается,
    но и на связанных с ним Entity. Например, если мы удаляем работника, то и details для этого работника
    тоже не нужны, т.е. мы удаляем работника и его details. Но это подходит не для всех типов данных
    Если мы сохраним работника, то сохраним и связанные с ним details
     */
    private Detail_OneToOne_UNI empDetail;

    public Employee_OneToOne_UNI() {
    }

    public Employee_OneToOne_UNI(String name, String surname, String department, int salary) {
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

    public Detail_OneToOne_UNI getEmpDetail() {
        return empDetail;
    }

    public void setEmpDetail(Detail_OneToOne_UNI empDetail) {
        this.empDetail = empDetail;
    }
}

