package com.hexaware.mainmod;

import com.hexaware.dao.InsuranceServiceImpl;
import com.hexaware.entities.Policy;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) throws ParseException {
        InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createPolicy(insuranceService, scanner);
                    break;
                case 2:
                    getPolicyById(insuranceService, scanner);
                    break;
                case 3:
                    getAllPolicies(insuranceService);
                    break;
                case 4:
                    updatePolicy(insuranceService, scanner);
                    break;
                case 5:
                    deletePolicy(insuranceService, scanner);
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void createPolicy(InsuranceServiceImpl insuranceService, Scanner scanner) throws ParseException {
        // Code to read policy details from the user and create a Policy object
        // Then call insuranceService.createPolicy(policy) to create the policy in the database

    	
    	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//    	Date startDate = df.parse();
    	
        Policy policy = new Policy();
        System.out.println("Coverage Amount: ");
        policy.setCoverageAmount(scanner.nextDouble());
        System.out.println("Enter EndDate: ");
        policy.setEndDate(df.parse(scanner.next()));
        System.out.println("Enter PolicyNumber ");
        policy.setPolicyNumber(scanner.next());
        System.out.println("Enter PolicyType ");
        policy.setPolicyType(scanner.next());
        System.out.println("Enter PremiumAmount ");
        policy.setPremiumAmount(scanner.nextInt());
        System.out.println("Enter StartDate ");
        policy.setStartDate(df.parse(scanner.next()));
        // Code to set attributes of the policy object from user input
        insuranceService.createPolicy(policy);
        System.out.println("Policy created successfully!");
    }

    private static void getPolicyById(InsuranceServiceImpl insuranceService, Scanner scanner) {
        // Code to read policy ID from the user and call insuranceService.getPolicy(policyId)
        // Then print the details of the retrieved policy

        System.out.println("Enter Policy ID:");
        int policyId = scanner.nextInt();
        Policy policy = insuranceService.getPolicy(policyId);
        if (policy != null) {
            System.out.println("Policy Details:");
            System.out.println(policy.toString());
        } else {
            System.out.println("Policy not found!");
        }
    }

    private static void getAllPolicies(InsuranceServiceImpl insuranceService) {
        List<Policy> policies = insuranceService.getAllPolicies();
        // Code to print details of all policies in the list

        System.out.println("All Policies:");
        for (Policy policy : policies) {
            System.out.println(policy.toString());
        }
    }

    private static void updatePolicy(InsuranceServiceImpl insuranceService, Scanner scanner) {
        // Code to read updated policy details from the user and create an updated Policy object
        // Then call insuranceService.updatePolicy(updatedPolicy) to update the policy in the database

        System.out.println("Enter Policy ID to update:");
        int policyId = scanner.nextInt();
        Policy policy = insuranceService.getPolicy(policyId);

    	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        if (policy != null) {
            // Code to update attributes of the policy object from user input
            System.out.println("Want to change policyNumber: (If no then press n)");
            String str = scanner.next();
            if(!str.equals("n")) {
            	policy.setPolicyNumber(str);
            }
            System.out.println("Want to change policy_type: (If no then press n)");
            str = scanner.next();
            if(!str.equals("n")) {
            	policy.setPolicyType(str);
            }
            System.out.println("Want to change premium_amount: (If no then press 0)");
            double val = scanner.nextInt();
            if(val!=0) {
            	policy.setPremiumAmount(val);
            }
            System.out.println("Want to change start_date: (If no then press n)");
            str = scanner.next();
            if(!str.equals("n")) {
            	try {
					policy.setStartDate(df.parse(str));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            System.out.println("Want to change end_date: (If no then press n)");
            str = scanner.next();
            if(!str.equals("n")) {
            	try {
					policy.setEndDate(df.parse(str));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        	insuranceService.updatePolicy(policy);
            System.out.println("Policy updated successfully!");
        } else {
            System.out.println("Policy not found!");
        }
    }

    private static void deletePolicy(InsuranceServiceImpl insuranceService, Scanner scanner) {
        // Code to read policy ID from the user and call insuranceService.deletePolicy(policyId)
        // Then print a message indicating whether the deletion was successful or not

        System.out.println("Enter Policy ID to delete:");
        int policyId = scanner.nextInt();
        boolean isDeleted = insuranceService.deletePolicy(policyId);

        if (isDeleted) {
            System.out.println("Policy deleted successfully!");
        } else {
            System.out.println("Policy not found or deletion failed!");
        }
    }
}


