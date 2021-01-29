//package com.example.demo.CSVHelper;
//
//import com.example.demo.entity.Salaries;
//import org.apache.commons.csv.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class CSVHelper {
//    public static String TYPE = "text/csv";
//    static String[] HEADERs = { "emp_no", "salary", "from_date", "to_date" };
//
//    public static boolean hasCSVFormat(MultipartFile file) {
//        if (TYPE.equals(file.getContentType())
//                || file.getContentType().equals("application/vnd.ms-excel")) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static List<Salaries> csvToTutorials(InputStream is) {
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
//
//            List<Salaries> salariesList = new ArrayList<>();
//
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//            for (CSVRecord csvRecord : csvRecords) {
//                Salaries salaries = new Salaries();
//                        Long.parseLong(csvRecord.get("Id")),
//                        csvRecord.get("Title"),
//                        csvRecord.get("Description"),
//                        Boolean.parseBoolean(csvRecord.get("Published"));
//
//                        salaries.setEmp_no(csvRecord.get("emp_no"));
//                        salaries.setSalary(csvRecord.get("salary"));
//                        salaries.setFrom_date(csvRecord.get("from_date"));
//                        salaries.setEmp_no(csvRecord.get("emp_no"));
//
//
//                salariesList.add(salaries);
//            }
//
//            return salariesList;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//    }
//
//    public static ByteArrayInputStream tutorialsToCSV(List<DeveloperTutorial> developerTutorialList) {
//        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//            for (DeveloperTutorial developerTutorial : developerTutorialList) {
//                List<String> data = Arrays.asList(
//                        String.valueOf(developerTutorial.getId()),
//                        developerTutorial.getTitle(),
//                        developerTutorial.getDescription(),
//                        String.valueOf(developerTutorial.isPublished())
//                );
//
//                csvPrinter.printRecord(data);
//            }
//
//            csvPrinter.flush();
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//        }
//    }
//}