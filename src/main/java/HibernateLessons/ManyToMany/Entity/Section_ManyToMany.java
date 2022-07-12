package HibernateLessons.ManyToMany.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "section")
public class Section_ManyToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//    {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable(name = "child_section" //Join Table – это таблица, которая отображает связь между строками 2-х других таблиц
            , joinColumns = @JoinColumn(name =  "section_id") // Столбцы Join Table–это Foreign Key, которые ссылаются на Primary Key связываемых таблиц
            , inverseJoinColumns = @JoinColumn(name = "child_id")) // Для связи с таблицей sections JoinTable будет использовать столбец section_id, а для связи с таблицей children child_id.
    private List<Child_ManyToMany> childList;

    public Section_ManyToMany() {
    }

    public Section_ManyToMany(String name) {
        this.name = name;
    }

    public void addChildToSection(Child_ManyToMany child) {
        if (childList == null) {
            childList = new ArrayList<>();
        }
        System.out.println("Добавим ребенка в секцию.");
        childList.add(child);
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

    public List<Child_ManyToMany> getChildList() {
        return childList;
    }

    public void setChildList(List<Child_ManyToMany> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
