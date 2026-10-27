package com.pluralsight;

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

        }
    }
}
