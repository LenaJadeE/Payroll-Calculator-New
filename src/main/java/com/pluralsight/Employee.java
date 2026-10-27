package com.pluralsight;

public class Employee {
    private static final double RegularHours = 40;
    private static final double OvertimeMultiplier = 1.5;

    private int employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    public  Employee(int employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getGrossPay() {
        if (hoursWorked <= RegularHours) {
            return hoursWorked * payRate;
        }
        double overtimeHours = hoursWorked - RegularHours;
        return (RegularHours * payRate) + (overtimeHours * payRate * OvertimeMultiplier);
    }
}


// EXERCISE 3
//  Create a new Java application named payroll-calculator. You will reademployee data from a .csv file and generate the payroll information for each employee.
//  For this exercise read the file using the BufferedReader.
//  The employees.csv file in DataFiles.zip will contain several lines of employee
//     data in the form:
//          id|name|hours-worked|pay-rate
//      For example:
//          10|Dana Wyatt|52.5|12.50
//          20|Ezra Aiden|17|16.75
//          30|Brittany Thibbs|40|16.50
//          40|Zephaniah Hughes|2|10.00
//  Step 1
//      Create an Employee class as described below:
//      Private data members (attributes): - employeeId - name - hoursWorked - payRate
//          Methods: - parameterized constructor - getEmployeeId() and getters/setters for other attributes - getGrossPay() that calculates and returns that employee's gross pay based on their hours worked and pay rate
//  Step 2
//      To make the main program work, you will need to:
//      1. Open the file with FileReader and wrap it in a BufferedReader
//      2. Read each line of text
//      3. Split it into individual fields using the | character as the delimiter
//      4. Copy the values from the tokens array into variables that match the data and then use them to create a new Employee object
//      5. Display the employee using a printf and by calling the employee's getEmployeeId(), getName(), and getGrossPay() methods
//      6. Repeat for each line in the input file
//  Step 3
//      1. Ensure all your changes are committed and pushed to GitHub
//      2. Send your repository URL to your Instructor
//
//  EXERCISE 1
//      Continue working on the payroll-calculator program.
//      Rather than displaying your payroll report to the screen, write it to a .csv file in the following format.
//          id|name|gross pay
//          111|Cameron Tay|3277.65
//          222|James Tee|2150.00
//      Prompt the user for the name of a file to read and process, then prompt them for thename of the payroll file to create.
//          Enter the name of the file to process: employees.csv
//          Enter the name of the payroll file to create: payroll-sept-2023.csv
//          When your program finishes running, open the new file to view the results.
//  BONUS: If the user chooses specifies a .json extension write the data as JSON instead of csv.
//      For example:
//          Enter the name of the file to process: employees.csv
//          Enter the name of the payroll file to create: payroll-sept-2023.json
//          payroll-sept-2023.json
//  [ { "id": 111, "name" : "Cameron Tay", "grossPay" : 3277.65 },
//  { "id": 222, "name" : "James Tee", "grossPay" : 2150.00 }