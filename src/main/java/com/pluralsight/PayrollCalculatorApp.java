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

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(outputFile));

            String input;
            boolean firstEmployee = true;

            buffReader.readLine();

            if (isJson) {
                buffWriter.write("[\n");
            } else {
                buffWriter.write("id|name|gross pay\n");
            }

            while ((input = buffReader.readLine()) != null) {
                if (input.trim().isEmpty()) {
                    continue;
                }

                String[] tokens = input.split("\\|");

                int id = Integer.parseInt(tokens[0].trim());
                String name = tokens[1].trim();
                double hoursWorked = Double.parseDouble(tokens[2].trim());
                double payRate = Double.parseDouble(tokens[3].trim());

                Employee employee = new Employee(id, name, hoursWorked, payRate);

                if (isJson) {
                    if (!firstEmployee) {
                        buffWriter.write(",\n");
                    }
                    buffWriter.write(String.format(Locale.US,
                            "  { \"id\": %d, \"name\" : \"%s\", \"grossPay\" : %.2f }",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay()));
                } else{
                    buffWriter.write(String.format(Locale.US" %d|%s|%.2f\n",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay()));
                }
                firstEmployee = false;
            }

            if (isJson) {
                buffWriter.write("\n}\n");
            }

            buffReader.close();
            buffWriter.close();
            System.out.println("Payroll written to " + outputFile);
        } catch (IOException e){
            System.out.println("ERROR: An unexpected error occurred");
            e.printStackTrace();
        }

        scanner.close();
    }
}