package SpringMVC;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller //аннотация @Controller это специальный @Component, поэтому когда будет происходить сканирование пакетов
// в файле applicationContext, поэтому при сканировании (поиске компонентов) контроллер тоже будет найден
@RequestMapping("/employee") // @RequestMapping, указанный для Controller-а связывает URL (адрес) со всеми его методами
// Если будет два контроллера с одинаковыми URL методов, то при загрузке на сервер возникнет ошибка Ambiguous mapping,
// для этого используем аннотацию @RequestMapping на самом контроллере
public class MyController {

    @RequestMapping("/") //@RequestMapping связывает URL (адрес)с методом контроллера.
    // в скобках мы указываем при каком адресе должен срабатывать метод, а соответственно отображаться view
    public String showFirstView() {
        return "first-view";
    }

    @RequestMapping("/askDetails")
    public String askEmployeeDetails(Model model) {

//        Можно добавить дефолтные значения форм
//        Employee emp = new Employee();
//        emp.setName("Default");
//        emp.setSurname("Default");
//        emp.setSalary(666);

        model.addAttribute("employee", new Employee());

        return "ask-emp-details-view";
    }

//    @RequestMapping("/showDetails")
//    public String showEmpDetails() {
//        return "show-emp-details-view";
//    }

//    @RequestMapping("/showDetails")
//    public String showEmpDetails(HttpServletRequest request, Model model) { //HttpServletRequest содержит информацию о httprequestе
//
//        String empName = request.getParameter("employeeName"); // с помощью метода getParameter мы получаем значение поля employeeName
//        empName = "Mr. " + empName; // изменяем имя и добавляем в качестве атрибута в модель
//        model.addAttribute("nameAttribute", empName); // модель это контейнер для каких-угодно данных,
//        // мы добавляем этот атрибут в модель, чтобы в дальнейшем могли эту информацию извлечь из модели и отобразить
//        // в shop-emp-details-view
//        model.addAttribute("description", " - employee");
//        // в методе addAttribute() второй параметр типа Object это означает, что мы можем передавать в модель все что угодно.
//
//        return "show-emp-details-view";
//    }


//    @RequestMapping("/showDetails")
//    public String showEmpDetails(@RequestParam("employeeName") String empName,
//                                 Model model) {
////      теперь нам нет необходимости получать значение empName с помощью HttpServletRequest,
////      благодаря аннотации @RequestParam изъятие значения employeeName происходит за кулисами
//
//       // При работе с формами, аннотация @RequestParam позволяет
//       // нам связывать поле формы с параметром метода из Controller-а
//
//        empName = "Mr. " + empName;
//        model.addAttribute("nameAttribute", empName);
//
//        return "show-emp-details-view";
//    }

    @RequestMapping("/showDetails")
    public String showEmpDetails(@Valid @ModelAttribute("employee") Employee emp,
                                 BindingResult bindingResult) {
        //При работе с формами аннотация @ModelAttribute параметре метода Controller-а даёт доступ к конкретному атрибуту Модели

        //получаем доступ к полям атрибута и меняем их значения в show-emp-details-view
//        String name = emp.getName();
//        emp.setName("Mr " + name);
//
//        int salary = emp.getSalary();
//        emp.setSalary(salary*10);
//
//        @Valid означает, что атрибут должен пройти процесс валидации
//        BindingResult содержит результат этой валидации

        System.out.println("Длина фамилии: " + emp.getSurname().length());
        //с аннотацией @NotNull Surname превращается в пустую строку с длиной строки 0, поэтому валидация не работает
        // @NotEmpty принимает на вход просто пробелы на место фамилии, поэтому нужно использовать @NotBlank
        //@NotEmpty–поле не должно быть пустым; @NotBlank–поле не должно быть пустыми не должно быть заполнено только пробелами

        if(bindingResult.hasErrors()) { // если валидация не прошла, то показывает форму заполнение, если прошла, то информацию о работнике
            return "ask-emp-details-view";
        } else {
            return "show-emp-details-view";
        }
    }
}
