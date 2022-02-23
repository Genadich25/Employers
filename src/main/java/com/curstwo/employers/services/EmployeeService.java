package com.curstwo.employers.services;

import com.curstwo.employers.Employee;
import com.curstwo.employers.exceptions.BadRequestException;
import com.curstwo.employers.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private Employee[] employees = new Employee[3];

    public Object addEmployee(String firstName, String lastName){
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employee.equals(employees[i])) {
                throw new BadRequestException();
            }
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return employees[i];
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public Object deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] != null) {
                    if (employees[i].equals(employee)) {
                        employees[i] = null;
                        return employee;
                    }
                }
            }
        throw new NotFoundException();
    }

    public Object findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].equals(employee)) {
                return employees[i];
            }
        }
        throw new NotFoundException();
    }
}
