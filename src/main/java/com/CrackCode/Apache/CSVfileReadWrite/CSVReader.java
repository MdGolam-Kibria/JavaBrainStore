package com.CrackCode.Apache.CSVfileReadWrite;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
    @TODO Need this dependency
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-csv</artifactId>
        <version>1.5</version>
    </dependency>
 */
public class CSVReader {

    private static final String SAMPLE_CSV_FILE_PATH = "./sample.csv";//--expected Readable CSV File Name

    public static void main(String[] args) throws IOException {
        /**
         * @TODO Read and print CSV data with Headers to console
         */
        //here get Data From Csv File using valid file name
        CSVParser csvDataWithHeaders = new CSVReader().getCsvDataListWithHeaders(SAMPLE_CSV_FILE_PATH);
        //Now print
        printCSVdata(csvDataWithHeaders);







        System.out.println("\n\n\n\n\n\n\n\n\n-----------------------------Now Read Data Without Headers----------------------------------------\n\n\n\n\n\n\n\n\n\n\n");





        /**
         * @TODO Read and print CSV data without Headers to console
         */
        //here get Data From Csv File using valid file name
        CSVParser csvDataWithoutHeaders = new CSVReader().getCsvDataListWithoutHeaders(SAMPLE_CSV_FILE_PATH);
        //Now print
        printCSVdata(csvDataWithoutHeaders);

    }

    public CSVParser getCsvDataListWithHeaders(String fileName) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader("Name", "Email", "Phone", "Address")//those are headers name.
                .withIgnoreHeaderCase()
                .withTrim());
        return csvParser;
    }

    public CSVParser getCsvDataListWithoutHeaders(String fileName) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());
        return csvParser;
    }



    //only for print data
    public static void printCSVdata(CSVParser csvRecords){
        for (CSVRecord csvRecord :csvRecords ) {
            // Accessing values by the names assigned to each column
            String name = csvRecord.get("Name");
            String email = csvRecord.get("Email");
            String phone = csvRecord.get("Phone");
            String address = csvRecord.get("Address");

            System.out.println("Record No - " + csvRecord.getRecordNumber());
            System.out.println("---------------");
            System.out.println("Name : " + name);
            System.out.println("Email : " + email);
            System.out.println("Phone : " + phone);
            System.out.println("Address : " + address);
            System.out.println("---------------\n\n");
        }
    }
}
