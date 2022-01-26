package com.CrackCode.interviewQuestion.ApachePoi;

import com.CrackCode.interviewQuestion.designPattern.Prototype.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
public class WriteToXlTest {
    @GetMapping("/writeStudentInfoTOxLFile")
    public String WriteDataToXlFile(HttpServletResponse response) throws Exception {
        String[] headers = {
                "Name",
                "Email",
                "Phone",
                "Address"
        };

        List<Student> studentList =  Arrays.asList(new Student("Golam Kibria","golamkibria.java@gmail.com","01531921892","Tejgaon Industrial Area"));


        List<Object[]> rows = new ArrayList<>();//for store row data
        studentList.forEach(student -> {//add row data here
            Object[] objectArray = new Object[5];
            objectArray[0] = student.getName();
            objectArray[1] = student.getEmail();
            objectArray[2] = student.getPhone();
            objectArray[3] = student.getAddress();
            rows.add(objectArray);
        });

        //now prepare the xl file info
        response.setContentType("application/xls");
        response.setHeader("Content-Disposition", "attachment; filename=\"student-information.xls"+ "\"");
        response.setCharacterEncoding("UTF-8");
        /**
         * Now Write Data TO XL file
         */
        writeApprovedTransactionToResponse(response, headers, rows);

        return "Congrats!";
    }

    /**
     * @Method For write TO XL file
     */
    public void writeApprovedTransactionToResponse(HttpServletResponse response, String[] headers, List<Object[]> rows) throws Exception {
        try {

            ExcelFileWriter xlWriter = new ExcelFileWriter();
            xlWriter.setContentReadListener(rows.get(0).length, new ContentReadListener() {
                @Override
                public void afterRowRead(int rowIndex, String[] values) {

                }

                @Override
                public String afterCellRead(int rowIndex, int cellIndex, String value) {
                    return value;
                }
            });

            xlWriter.execute("Exported Data", headers, rows);

            xlWriter.toResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Fail to create ApprovedTransaction excel file");
        }
    }
}
