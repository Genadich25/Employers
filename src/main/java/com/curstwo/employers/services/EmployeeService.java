package com.curstwo.employers.services;

import com.curstwo.employers.Employee;
import com.curstwo.employers.exceptions.BadRequest;
import com.curstwo.employers.exceptions.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.BadAttributeValueExpException;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpTimeoutException;
import java.rmi.NotBoundException;

@Service
public class EmployeeService {
    private Employee[] employees = new Employee[]{
            new Employee("Петр", "Иванович"),
            new Employee(null, null),
            new Employee("Григорий", "Жилин"),
            new Employee("Николай", "Сергеев"),
            new Employee("Анастасия", "Игнатьева")
    };


    public Object addEmployee(String firstName, String lastName){
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getFirstName() == null && employees[i].getLastName() == null) {
                employees[i].setFirstName(firstName);
                employees[i].setLastName(lastName);
                return employees[i];
            }
            if (employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastName)) {
                throw new BadRequest();
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public Object deleteEmployee(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].getFirstName() != null || employees[i].getLastName() != null) {
                    if (employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastName)) {
                        return employees[i];
                    }
                }
            }
        }
        throw new NotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object findEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastName)) {
                return employees[i];
            }
        }
        if (firstName == null && lastName == null){
            throw new NotFound();
        }
        throw new NotFound();
    }
}
