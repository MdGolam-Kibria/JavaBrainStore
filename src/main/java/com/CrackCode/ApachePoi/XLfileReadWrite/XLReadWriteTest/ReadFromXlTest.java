package com.CrackCode.ApachePoi.XLfileReadWrite.XLReadWriteTest;

import com.CrackCode.ApachePoi.XLfileReadWrite.ContentReadListener;
import com.CrackCode.ApachePoi.XLfileReadWrite.ExcelFileReader;
import com.CrackCode.designPattern.Prototype.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReadFromXlTest {

    @GetMapping("/readStudentListFromXlFile")
    public List<Student> readDataFromXlFile() throws IOException, InvalidFormatException {
        List<Student> studentList = new ArrayList<>();


        File file = new File("C:\\Users\\golam.kibria5290\\Downloads\\student-information.xls");
        InputStream inputStream = new FileInputStream(file);
        ExcelFileReader xlReader = new ExcelFileReader(inputStream);

        final byte NAME = 0;
        final byte EMAIL = 1;
        final byte PHONE = 2;
        final byte ADDRESS = 3;


        xlReader.setContentReadListener(4/*Number of cell like here {name,email,phone,address} total 4 that's why pass 4 here*/, new ContentReadListener() {
            @Override
            public void afterRowRead(int rowIndex, String[] values) {
                if (rowIndex == 0) {
                    return;
                }
                Student student = new Student();
                student.setName(values[NAME]);//name index in XL file.
                student.setEmail(values[EMAIL]);//email index in XL file.
                student.setPhone(values[PHONE]);
                student.setAddress(values[ADDRESS]);
                studentList.add(student);
            }

            @Override
            public String afterCellRead(int rowIndex, int cellIndex, String value) {
                return value;
            }
        });


        xlReader.execute(0);//now execute the configuration

        return studentList;
    }


}
