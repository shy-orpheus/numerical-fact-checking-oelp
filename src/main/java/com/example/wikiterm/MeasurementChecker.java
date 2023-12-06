package com.example.wikiterm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeasurementChecker {

    // Function to check if the webpage content confirms the input measurement within a certain deviation
    public static boolean confirmMeasurement(String websiteContent, String inputMeasurement, double allowedDeviation) {
        // Regular expression pattern to detect measurements
        String regex = "\\b(\\d{1,3}(?:,\\d{3})*(?:\\.\\d+)?|\\d+(?:\\.\\d+)?)(\\s*(km|meters|m|metres|cm|mi|miles|ft|feet|foot|yards|yd|mile|kilometre|kilometer|yard))?\\b"; // Adjust this pattern to suit your measurement formats
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(websiteContent);

        double parsedInputMeasurement = parseMeasurement(inputMeasurement);

        // Find and compare measurements
        while (matcher.find()) {
            String measurement = matcher.group(1).replaceAll(",", ""); // Remove commas if present
            String webpageUnit = matcher.group(2);

           //System.out.println(measurement);

            double webpageMeasurement = parseMeasurement(measurement);
            double convertedWebpageMeasurement = UnitConverter.convertToMeters(webpageMeasurement, webpageUnit);
            //System.out.println(webpageMeasurement);

            //System.out.println(convertedWebpageMeasurement);

            // Check if the webpage measurement is within the allowed deviation
            if (Math.abs(convertedWebpageMeasurement - parsedInputMeasurement) <= allowedDeviation) {
                return true;
            }
        }
        return false; // No matching measurement found or none within the allowed deviation
    }

    // Function to parse measurement values
    private static double parseMeasurement(String measurement) {
        return Double.parseDouble(measurement.replaceAll("[^\\d.]", ""));
    }

    public static void main(String[] args) {
        String sampleMeasurement = "9000 meter";
        double deviation = 200.0; // Allowed deviation
        int timeout = 10000; // You can adjust the timeout as needed
        JsoupCrawlUtil crawlUtil = new JsoupCrawlUtil();
        stops stop_word = new stops();
        //  String pageContent1 = crawlUtil.readPage("", timeout);
        String websiteContent = crawlUtil.readPage("https://byjus.com/question-answer/what-is-the-height-above-sea-level-of-mt-everest-8848-m7848-m8048-m9848-m/", timeout);
       //String websiteContent = "9km";
        boolean isConfirmed = confirmMeasurement(websiteContent, sampleMeasurement, deviation);
        System.out.println("Measurement confirmed: " + isConfirmed);
    }
}