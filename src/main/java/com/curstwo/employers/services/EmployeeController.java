package com.curstwo.employers.services;

import com.curstwo.employers.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String hello(){
        return "Сервер работает!";
    }

    @GetMapping("/add")
    public Object addEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.addEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/remove")
    public Object deleteEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @ResponseStatus
    @GetMapping("/find")
    public Object findEmployee(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.findEmployee(firstName, lastName);
    }
}
