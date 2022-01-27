package com.CrackCode.ApachePoi.CSVfileReadWrite;

import com.CrackCode.designPattern.Prototype.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
    @TODO Need this dependency
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-csv</artifactId>
        <version>1.5</version>
    </dependency>
 */
public class CSVWriter {
    private static final String SAMPLE_CSV_FILE = "./sample.csv";//--expected CSV File Name

    public static void main(String[] args) throws IOException {
        String[] headers = {"Name", "Email", "Phone","Address"};//for headers
        //now prepare data
        List<Student> dataList = new ArrayList<>();
        dataList.add(new Student("kibria","k@gmail.com","01431921892","dhaka bangladesh"));
        dataList.add(new Student("anika","anika@gmail.com","01631921892","Naogaon"));
        //Now send data to write CSV File
        new CSVWriter().writeDataTCsv(SAMPLE_CSV_FILE,headers, dataList);
    }


    /**
     * @TODO Method For Write Data to CSV file
     * @param fileName
     * @param headers
     * @param rowsDataList
     * @throws IOException
     */
    public void writeDataTCsv(String fileName, String[] headers,List<Student> rowsDataList) throws IOException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader(headers/*Add headers here*/));

            //now set data to print
            rowsDataList.forEach(student -> {
                try {
                    csvPrinter.printRecord(student.getName(), student.getEmail(), student.getPhone(), student.getAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            csvPrinter.flush(); //Finally, flash the operation

        }catch (Exception e){
            throw e;
        }
    }

}
