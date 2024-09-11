package com.example.JasperTable.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.JasperTable.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

@RestController
public class StudentController
{

    //Get All Students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        List<Student> allStudent = new ArrayList<>();
        long roll = 101;
        int j = 1;
        String name = "Student" + j;
        String Addres = "Varansi";
        String gender = "Male";
        int age = 24;

        Student s1 = new Student(101L,"Tanmay","Varanasi","Male",24);
        for(int i = 1;i<=100;i++){
            if(i%2 == 0){
                gender = "Female";
            }else{
                gender = "Male";
            }
            Student s = new Student(roll++,name+j++,Addres,gender,age);
            allStudent.add(s);
        }
        return allStudent;
    }

    //Generate Report
    @GetMapping("/students/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return exportReport(format);
    }
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path = "D:\\Study\\Report";

        List<Student> list = getAllStudents();

        File file = ResourceUtils.getFile("classpath:reports\\StudentReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        Map<String, Object> parameters  = new HashMap<>();
        parameters.put("createdBy", "Tanmay Singh");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path +"\\Student_File.pdf");
        return "Report generated in path :"+ path;

    }

}
