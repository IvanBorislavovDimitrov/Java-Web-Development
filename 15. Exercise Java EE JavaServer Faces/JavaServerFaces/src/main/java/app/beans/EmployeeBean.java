package app.beans;

import app.entities.Employee;
import app.repository.EmployeeRepository;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
public class EmployeeBean {


    public void registerUser(Employee employee) {
        employee.setId(EmployeeRepository.employees.size() + 1);

        EmployeeRepository.employees.add(employee);
    }

}
