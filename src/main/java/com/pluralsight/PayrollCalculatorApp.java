package com.pluralsight;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class PayrollCalculatorApp {
    public static  void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the name of the file to process: ");
        String inputFile = scanner.nextLine().trim();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFile = scanner.nextLine().trim();

        boolean isJson = outputFile.toLowerCase().endsWith(".json");

        try (BufferedReader buffReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(outputFile))) {

            buffReader.readLine();

            buffWriter.write(isJson ? "[\n" : "id|name|gross pay\n");

            boolean firstEmployee = true;
            String line;
            while ((line = buffReader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                Employee employee = parseEmployee(line);
                employeeData(employee);
                printSummary(employee);

                if (isJson) {
                    if (!firstEmployee) {
                        buffWriter.write(",\n");
                    }
                    buffWriter.write(toJson(employee));
                } else {
                    buffWriter.write(toCsv(employee));
                }
                firstEmployee = false;
            }
            if (isJson) {
                buffWriter.write("\n]\n");
            }
            System.out.println("Payroll written to " + outputFile);
        } catch (IOException e){
            System.out.println("ERROR: Could not process the files" +  e.getMessage());
        }
    }

    private static  Employee parseEmployee(String line) {
        String[] tokens = line.split("\\|");

        int id = Integer.parseInt(tokens[0].trim());
        String name = tokens[1].trim();
        double hoursWorked = Double.parseDouble(tokens[2].trim());
        double payRate = Double.parseDouble(tokens[3].trim());

        return new Employee(id, name, hoursWorked, payRate);
    }

    private static void  employeeData(Employee employee) {
        if (employee.getEmployeeId() < 0) {
            employee.setEmployeeId(Math.abs(employee.getEmployeeId()));
        }
        employee.setName(employee.getName().trim());
        if (employee.getHoursWorked() < 0) {
            employee.setHoursWorked(0);
        }
        if (employee.getPayRate() < 0) {
            employee.setPayRate(0);
        }
    }

    private static void  printSummary(Employee employee) {
        System.out.printf(Locale.US, "%-18s %5.1f hrs @ $%.2f%n",
                employee.getName(), employee.getHoursWorked(), employee.getPayRate());
    }

    private static String toCsv(Employee employee) {
        return String.format(Locale.US, "%d|%s|%.2f%n",
                employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
    }

    private static String toJson(Employee employee) {
        return String.format(Locale.US, "  { \"id\": %d, \"name\" : \"%s\", \"grossPay\" : %.2f }",
                employee.getEmployeeId(), employee.getName(), employee.getGrossPay());


    }
}
