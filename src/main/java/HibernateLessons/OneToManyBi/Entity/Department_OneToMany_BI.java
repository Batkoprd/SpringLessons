package HibernateLessons.OneToManyBi.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments_onetomany")
public class Department_OneToMany_BI {

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

    //{CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}
    @OneToMany(cascade = CascadeType.ALL //Тут связь @OneToMany, потому что в одном департаменте может быть много работников
            , mappedBy = "department" //связь @OneToMany иди в поле department которое есть в классе employee
            , fetch = FetchType.LAZY) //С помощью FetchType мы можем выбрать тип загрузки.
    private List<Employee_OneToMany_BI> emps;
    //Дефолтный тип загрузки FetchType.LAZY, когда мы подгружаем один департамент,
    // то у него может быть много связанных работников, которые мы не хотим сразу получать, поэтому по дефолту LAZY
    // ManyToMany то по дефолту FetchType.LAZY, например у одного кружка может быть много детей и мы не хотим получать
    // этих детей при загрузке кружка
    // OneToOne FetchType.EAGER, когда мы загружаем информацию о работнике у него может быть только одна детальная информация,
    // поэтому при выборе работника подгрузится одна единственная строка с details, поэтому ее можно сразу подгружать с FetchType.EAGER

    /*
        •Eager (нетерпеливая)загрузка –при её использовании связанные сущности загружаются
    сразу вместе с загрузкой основной сущности.
        •Lazy (ленивая)загрузка –при её использовании связанные сущности НЕ загружаются сразу
    вместе с загрузкой основной сущности. Связанные сущности загрузятся только при первом обращении к ним.

        В большинстве случаев при большом количестве связанных сущностей
    целесообразнее использовать Lazy loading по следующим причинам:
        •Lazy загрузка имеет лучший performance по сравнению с Eager загрузкой;
        •Иногда при загрузке основной сущности, нам просто не нужны связанные с ней сущности.
    Поэтому их загрузка –это лишняя работа.

    Например, при Eager загрузке, если нам нужно узнать зарплаты одного департамента,
    то вместе с этой информацией прогрузятся все сотрудники этого департамента.
     */


    public Department_OneToMany_BI() {
    }

    public Department_OneToMany_BI(String departmentName, int maxSalary, int minSalary) {
        this.departmentName = departmentName;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }

    public void addEmployeeToDepartment(Employee_OneToMany_BI employee) {
        if (emps == null) { //проверяем что лист emps создан
            emps = new ArrayList<>();
        }
        emps.add(employee); // добавляем нового работника
        employee.setDepartment(this); // тк это Bi-directional связь, то нужно указать департамент работника (this - объект на котором мы будем вызывать этот метод)
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

    public List<Employee_OneToMany_BI> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee_OneToMany_BI> emps) {
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
