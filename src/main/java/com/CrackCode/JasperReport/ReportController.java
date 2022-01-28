package com.CrackCode.JasperReport;

import com.CrackCode.Apache.CSVfileReadWrite.CSVReader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @TODO need below maven
 * <dependency>
 * 			<groupId>net.sf.jasperreports</groupId>
 * 			<artifactId>jasperreports</artifactId>
 * 			<version>6.9.0</version>
 * 		</dependency>
 *
 * 	1st create a #.jrxml file using @TODO JsoftStudio OR iReport for generate a design
 * 	then using the {.jrxml}  file you can  follow this approach.
 */
@RestController
public class ReportController {

    @GetMapping("/jasparReport/{expectation}")//here expectation = html/pdf
    public String GenerateReport(@PathVariable("expectation") String expectation) throws IOException, JRException {

        /**
         * 1st get Data From A CSV File
         */
        String file = "C:\\kibria\\interviewQuestion\\src\\main\\resources\\datasource\\Employees.csv";
        CSVReader csvReader = new CSVReader();
        CSVParser csvDataWithoutHeaders = csvReader.getCsvDataListWithoutHeaders(file);
        List<Employee> employeeList = getEmployeeList(csvDataWithoutHeaders);

        String jrxFilePath = "classpath:templates/employees.jrxml";//pdf design file path
        //Now create the JasperReport using this data dynamically
        if (!Objects.equals(expectation,"html")){
            generatedPdfReport(jrxFilePath,employeeList,"employeeList");
        }else {
            generatedHTMLReport(jrxFilePath,employeeList,"employeeList");
        }
        return "Report Successfully Generated";
    }

    /**
     * Method For Generate Jasper Report dynamically
     */
    public void generatedPdfReport(String jrxmlFilePath,List<Employee> data,String outPutFileName) throws FileNotFoundException, JRException {
        File reportFile = ResourceUtils.getFile(jrxmlFilePath);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Golam Kibria");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "./" + outPutFileName+".pdf");
    }

    /**
     * Method For Generate Jasper Report dynamically
     */
    public void generatedHTMLReport(String jrxmlFilePath,List<Employee> data,String outPutFileName) throws FileNotFoundException, JRException {
        File reportFile = ResourceUtils.getFile(jrxmlFilePath);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Golam Kibria");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "./" + outPutFileName+".html");
    }

    private List<Employee> getEmployeeList(CSVParser csvDataWithoutHeaders) {
        List<Employee> list = new ArrayList<>();
        int i = 1;
        for (CSVRecord currentCsvData : csvDataWithoutHeaders) {
            Employee employee = new Employee();
            employee.setId(i+1);
            employee.setName(""+currentCsvData.get(0));
            employee.setEmail(""+currentCsvData.get(1));
            employee.setMobile(""+currentCsvData.get(2));
            employee.setSalary(""+currentCsvData.get(3));
            list.add(employee);
        }
        return list;
    }
}
