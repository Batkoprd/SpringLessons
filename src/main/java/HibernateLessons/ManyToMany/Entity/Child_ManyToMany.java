package HibernateLessons.ManyToMany.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "children")
public class Child_ManyToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Стратегия генерации случайных значений
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

//    {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH
            , CascadeType.REFRESH, CascadeType.MERGE}, //нет REMOVE потому что из-за каскада ребенок удалится вместе
                                                        // с секцией и наоборот, если удалить ребенка, то секция остается,
                                                        // если удалить секцию, то ребенок может ходить в другие секции
            fetch = FetchType.LAZY)
    @JoinTable(name = "child_section" //Join Table – это таблица, которая отображает связь между строками 2-х других таблиц
            , joinColumns = @JoinColumn(name =  "child_id") // Столбцы Join Table–это Foreign Key, которые ссылаются на Primary Key связываемых таблиц
            , inverseJoinColumns = @JoinColumn(name = "section_id")) //мы прописываем с помощью каких столбцов JoinTable связана с таблицей children и sections
    private List<Section_ManyToMany> sections;
    /*
    В аннотации @JoinTable:
        •Мы прописываем название таблицы, которая выполняет роль Join Table;
        •В joinColumns мы указываем столбец таблицы Join Table,
    который ссылается на Primary Key source таблицы;
        •В inverseJoinColumns мы указываем столбец таблицы Join Table,
    который ссылается на Primary Key target таблицы;
     */
    public Child_ManyToMany() {
    }

    public Child_ManyToMany(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addSectionToChild(Section_ManyToMany section) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        System.out.println("Добавим секцию ребенку.");
        sections.add(section);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Section_ManyToMany> getSections() {
        return sections;
    }

    public void setSections(List<Section_ManyToMany> sections) {
        this.sections = sections;
    }


    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
