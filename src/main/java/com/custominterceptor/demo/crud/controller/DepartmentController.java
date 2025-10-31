package com.custominterceptor.demo.crud.controller;

import com.custominterceptor.demo.crud.entity.Department;
import com.custominterceptor.demo.crud.error.DepartmentNotFoundException;
import com.custominterceptor.demo.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/getAll")
    public List<Department> getAllDepartment(){
        return departmentService.getAll();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById( @PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }


    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Data Deleted Successfully";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId,@RequestBody Department department){
        return departmentService.updateDepartmentById(departmentId, department);
    }
}
