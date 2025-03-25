import java.io.*;
import java.util.Scanner;

public class project2 {
    //public static PrintWriter out;


    public static void main(String[] args) throws FileNotFoundException {

        AllBranches allBranches = new AllBranches();


        String initial_input = args[0];//"/Users/emirhan/IdeaProjects/Assignment2Cmpe250/emirhan5ArkadasSuperPlan/src/large_initial1.txt";
        String output_file_name = args[2];//"/Users/emirhan/IdeaProjects/Assignment2Cmpe250/emirhan5ArkadasSuperPlan/src/print.txt";
        String input_file = args[1];//"/Users/emirhan/IdeaProjects/Assignment2Cmpe250/emirhan5ArkadasSuperPlan/src/large1.txt";
        //String input_file = args[1];


        try {
            PrintStream fileOut = new PrintStream(output_file_name);
            System.setOut(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        // Process initial data
        File initial_file = new File(initial_input);
        Scanner initial_scanner = new Scanner(initial_file);
        //out = new PrintWriter(output_file_name);
        //CREATING CITY AND FILL IT WITH INITIAL INPUT
        while (initial_scanner.hasNextLine()) {
            String line = initial_scanner.nextLine();
            String[] parts = line.strip().split(",");
            String employeeName = parts[2].strip();
            String job = parts[3].strip();
            String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

            allBranches.initialAddEmployee(cityAndDistrict, employeeName, job);

        }
        initial_scanner.close();




        // Process operation commands
        // UPDATE PART
        File input = new File(input_file);
        Scanner input_scanner = new Scanner(input);


        int lineNum = 0;
        while (input_scanner.hasNextLine()) {
            String line = input_scanner.nextLine();

            if (line.startsWith("LEAVE:")) {
                String[] parts = line.substring(6).strip().split(",");
                String employeeName = parts[2].strip();
                String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

                if (allBranches.findBranch(cityAndDistrict) != null) {
                    if( allBranches.findEmployeeByJob(cityAndDistrict,"MANAGER")!=null) {
                        if (allBranches.findBranch(cityAndDistrict).employees.contains(employeeName)) {
                            allBranches.leaveRequest(cityAndDistrict, employeeName);
                        } else {
                            System.out.println("There is no such employee.");
                        }
                    }
                }

            }

            else if (line.startsWith("PERFORMANCE_UPDATE:")) {
                String[] parts = line.substring(19).strip().split(",");
                String employeeName = parts[2].strip();
                String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

                int monthlyScore = Integer.parseInt(parts[3].strip());



                if(allBranches.findBranch(cityAndDistrict) != null) {
                    if(allBranches.findEmployeeByJob(cityAndDistrict,"MANAGER")!=null) {
                        if (allBranches.findBranch(cityAndDistrict).employees.contains(employeeName)) {
                            //refresh promotionPoints, monthlyBonus and overallBonus
                            allBranches.updateEmployeePerformance(cityAndDistrict, employeeName, monthlyScore);

                            allBranches.updateEmployeeJobPosition(cityAndDistrict, employeeName);
                            //dismissal if promotionPoints below 0
                        } else {
                            System.out.println("There is no such employee.");
                        }
                    }
                }

            }

            else if (line.startsWith("ADD:")) {
                String[] parts = line.substring(4).strip().split(",");
                String employeeName = parts[2].strip();
                String job = parts[3].strip();
                String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

                allBranches.addEmployee(cityAndDistrict, employeeName, job);

            }

            else if (line.startsWith("PRINT_MONTHLY_BONUSES:")) {
                String[] parts = line.substring(22).strip().split(",");
                String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

                allBranches.printMonthlyBonuses(cityAndDistrict);

            }

            else if (line.startsWith("PRINT_OVERALL_BONUSES:")) {
                String[] parts = line.substring(22).strip().split(",");
                String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

                allBranches.printOverallBonuses(cityAndDistrict);
            }

            else if (line.startsWith("PRINT_MANAGER:")) {
                String[] parts = line.substring(14).strip().split(",");
                String cityAndDistrict = parts[0].strip() + "," + parts[1].strip();

                allBranches.printManagerEmirhan(cityAndDistrict);
            }

            else if (line.isEmpty()) {

                allBranches.resetMonthlyBonus();
            }
            lineNum++;
        }

        //out.close();
        input_scanner.close();

        }

}


