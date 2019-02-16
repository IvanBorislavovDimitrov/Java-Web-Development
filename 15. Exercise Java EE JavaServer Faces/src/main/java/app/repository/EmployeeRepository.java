package app.repository;

import app.entities.Employee;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class EmployeeRepository {

    public static List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        EmployeeRepository.employees = employees;
    }

    public void remove(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    public double totalMoneyNeeded() {
        return employees.stream()
                .mapToDouble(employee -> employee.getSalary())
                .sum();
    }

    public double averageSalary() {
        return employees.stream()
                .mapToDouble(employees -> employees.getSalary())
                .average()
                .orElse(0);
    }
}
