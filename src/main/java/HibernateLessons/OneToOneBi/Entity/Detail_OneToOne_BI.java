package HibernateLessons.OneToOneBi.Entity;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class Detail_OneToOne_BI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "city")
    private  String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "empDetail", //Мы уже прописали @JoinColumn в классе Employee, мы говорим, что связь уже налажена, поищи ее в поле empDetail класса Employee
              fetch = FetchType.EAGER )
    private Employee_OneToOne_BI employee;

    public Detail_OneToOne_BI() {
    }

    public Detail_OneToOne_BI(String city, String phoneNumber, String email) {
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee_OneToOne_BI getEmployee() {
        return employee;
    }

    public void setEmployee(Employee_OneToOne_BI employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
