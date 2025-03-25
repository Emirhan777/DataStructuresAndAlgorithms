

public class AllBranches {
    public static final int INITIAL_SIZE = 31;

    public Branch[] branchArray;
    public int numberOfUsedPlaces;
    public int tableSize;

    AllBranches() {
        this(INITIAL_SIZE);
    }

    AllBranches(int tableSize) {
        createNewEmptyTable(tableSize);
        emptyTable();
    }

    public void createNewEmptyTable(int arraySize) {
        branchArray = new Branch[nextPrime(arraySize)];
    }

    public void emptyTable() {
        numberOfUsedPlaces = 0;
        for (int i = 0; i < branchArray.length; i++) {
            branchArray[i] = null;
        }
    }

    public boolean addBranch(Branch branch) {
        int usedIndex = findIndex(branch.cityAndDistrict);
        if (isUsed(usedIndex)) {
            return false;
        }

        if (branchArray[usedIndex] == null) {
            numberOfUsedPlaces++;
        }
        branchArray[usedIndex] = branch;
        tableSize++;

        if (numberOfUsedPlaces > branchArray.length / 2) {
            rehash();
        }
        return true;

    }

    public int findIndex(String cityAndDistrict) {
        int initial = 1;
        int hashedIndex = findHashedIndex(cityAndDistrict);

        while (branchArray[hashedIndex] != null && !branchArray[hashedIndex].cityAndDistrict.equals(cityAndDistrict)) {
            hashedIndex += initial;
            initial += 2;
            if (hashedIndex >= branchArray.length) {
                hashedIndex -= branchArray.length;
            }
        }

        return hashedIndex;
    }

    public boolean containsBranch(String cityAndDistrict) {
        int usedIndex = findIndex(cityAndDistrict);
        return isUsed(usedIndex);
    }

    public boolean isUsed(int currentPos) {
        return branchArray[currentPos] != null && branchArray[currentPos].isUsed;
    }

    public int findHashedIndex(String cityAndDistrict) {
        int hashedIndex = cityAndDistrict.hashCode();
        hashedIndex = hashedIndex % branchArray.length;
        if (hashedIndex < 0) {
            hashedIndex += branchArray.length;
        }
        return hashedIndex;
    }

    public void rehash() {
        Branch[] oldBranchArray = branchArray;
        createNewEmptyTable(2 * oldBranchArray.length);
        numberOfUsedPlaces = 0;
        tableSize = 0;
        for (Branch branch : oldBranchArray) {
            if (branch != null && branch.isUsed) {
                addBranch(branch);
            }
        }
    }

    public static int nextPrime(int currentPrime) {
        if (currentPrime % 2 == 0) {
            currentPrime++;
        }
        while (!checkIfPrimeNumber(currentPrime)) {
            currentPrime += 2;
        }
        return currentPrime;
    }

    public static boolean checkIfPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;

    }

    public Branch findBranch(String cityAndDistrict) {
        int initial = 1;
        int branchIndex = findHashedIndex(cityAndDistrict);

        while (branchArray[branchIndex] != null && !branchArray[branchIndex].cityAndDistrict.equals(cityAndDistrict)) {
            branchIndex += initial;
            initial += 2;
            if (branchIndex >= branchArray.length) {
                branchIndex -= branchArray.length;
            }
        }

        return branchArray[branchIndex];
    }



    public boolean checkBranch(String cityAndDistrict) {
        return containsBranch( cityAndDistrict);

    }















    public void addEmployee(String cityAndDistrict, String employeeName, String job) {
        Branch branch = findBranch(cityAndDistrict);

        if(numberOfThatJobsInThatBranch(cityAndDistrict, job)==1){
            Employee employeeToGo = findEmployeeByJob(cityAndDistrict, job);
            if(employeeToGo.promotionPoints<= -5){
                System.out.println(employeeToGo.name + " is dismissed from branch: " + branch.branchName + ".");
                branch.employees.removeEmployeeFromHashMap(employeeToGo);
                branch.employeeList.remove(employeeToGo);
            }
            else if(employeeToGo.promotionPoints>=3 && employeeToGo.job.equals("CASHIER")){
                System.out.println(employeeToGo.name + " is promoted from " + "Cashier" + " to "+ "Cook.");
                employeeToGo.job="COOK";
                employeeToGo.promotionPoints-=3;
            }

            else if (employeeToGo.promotionPoints>=10 && employeeToGo.job.equals("COOK")) {
                Employee employeeToGoManager = findEmployeeByJob(cityAndDistrict, "MANAGER");
                //System.out.println("PROMOTİON POİNTS OF MANAGER: "+employeeToGo.promotionPoints);
                if(employeeToGoManager.promotionPoints<= -5){
                    System.out.println(employeeToGoManager.name + " is dismissed from branch: " + branch.branchName + ".");
                    branch.employees.removeEmployeeFromHashMap(employeeToGoManager);
                    branch.employeeList.remove(employeeToGoManager);


                    System.out.println(employeeToGo.name + " is promoted from " + "Cook" + " to "+ "Manager.");
                    branch.cookToManagaerPromotionQueue.remove(employeeToGo);
                    employeeToGo.job="MANAGER";
                    employeeToGo.promotionPoints-=10;
                }
            }


        }


        branch.addEmployee(employeeName, job);
    }

    public void initialAddEmployee(String cityAndDistrict, String employeeName, String job) {
        Branch branch;
        if (!checkBranch(cityAndDistrict)) {
            branch = new Branch(cityAndDistrict);
            addBranch(branch);

        } else {
            branch = findBranch(cityAndDistrict);
        }
        branch.addEmployee(employeeName, job);
    }


    public void leaveRequest(String cityAndDistrict, String employeeName){
        //System.out.print("LineNum:" + lineNum + "   ");

        Branch branch = findBranch(cityAndDistrict);
        Employee employee = branch.employees.returnEmployeeObject(employeeName);



        if(employee.job.equals("MANAGER")){
            checkCookQueue(cityAndDistrict);
            if(!branch.cookToManagaerPromotionQueue.isEmpty() && checkThereAreMoreThanOneOfThatJob(cityAndDistrict,"COOK")){
                System.out.println(employeeName + " is leaving from branch: " + branch.branchName + ".");
                branch.employees.removeEmployeeFromHashMap(employee);
                branch.employeeList.remove(employee);


                Employee employeeToBePromoted = branch.cookToManagaerPromotionQueue.poll();
                System.out.println(employeeToBePromoted.name + " is promoted from " + "Cook" + " to "+ "Manager.");
                employeeToBePromoted.job="MANAGER";
                employeeToBePromoted.promotionPoints-=10;

            }

            else if (employee.promotionPoints>-5) {
                findBranch(cityAndDistrict).monthlyBonus += 200 ;
                findBranch(cityAndDistrict).totalBonus += 200 ;
            }

        }

        else {
            if (checkThereAreMoreThanOneOfThatJob(cityAndDistrict, employee.job)) {
                System.out.println(employeeName + " is leaving from branch: " + branch.branchName + ".");
                branch.employees.removeEmployeeFromHashMap(employee);
                branch.employeeList.remove(employee);
            }
            else if( employee.promotionPoints>-5) {
                findBranch(cityAndDistrict).monthlyBonus += 200 ;
                findBranch(cityAndDistrict).totalBonus += 200 ;

            }
            checkCookQueue(cityAndDistrict);
        }
    }

    public int numberOfThatJobsInThatBranch(String cityAndDistrict, String jobTitle) {
        int count = 0;

        for (Employee p : findBranch(cityAndDistrict).employeeList) {
            if (p.job.equals(jobTitle)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkThereAreMoreThanOneOfThatJob(String cityAndDistrict, String jobTitle) {
        int count = 0;

        for (Employee employee : findBranch(cityAndDistrict).employeeList) {
            if (employee.job.equals(jobTitle)) {
                count++;
            }
        }
        return count>1;
    }




    public void updateEmployeePerformance(String cityAndDistrict, String employeeName, int monthlyScore) {
        Employee employee = findBranch(cityAndDistrict).employees.returnEmployeeObject( employeeName);

        employee.updatePerformance(monthlyScore);

        if (monthlyScore >= 0 ) {
            findBranch(cityAndDistrict).monthlyBonus += (monthlyScore % 200) ;
            findBranch(cityAndDistrict).totalBonus += (monthlyScore % 200) ;
        }
    }




    public void updateEmployeeJobPosition(String cityAndDistrict, String employeeName){
        Branch branch = findBranch(cityAndDistrict);
        Employee employee = branch.employees.returnEmployeeObject(employeeName);
        String job=employee.job;

        if(job.equals("COURIER")){
            if(employee.promotionPoints<=-5){
                if(checkThereAreMoreThanOneOfThatJob( cityAndDistrict, job )){
                    System.out.println(employee.name + " is dismissed from branch: " + branch.branchName + ".");
                    branch.employees.removeEmployeeFromHashMap(employee);
                    branch.employeeList.remove(employee);
                }
            }
        }



        else if(job.equals("CASHIER")){
            if(employee.promotionPoints<=-5){
                if(checkThereAreMoreThanOneOfThatJob( cityAndDistrict, job )){
                    System.out.println(employee.name + " is dismissed from branch: " + branch.branchName + ".");
                    branch.employees.removeEmployeeFromHashMap(employee);
                    branch.employeeList.remove(employee);
                }
            }


            else if (employee.promotionPoints >= 3) {
                if(checkThereAreMoreThanOneOfThatJob( cityAndDistrict, job )){
                    if(numberOfThatJobsInThatBranch(cityAndDistrict, "COOK")==1){
                        Employee employeeToGo = findEmployeeByJob(cityAndDistrict, "COOK");
                        if(employeeToGo.promotionPoints<= -5){
                            System.out.println(employeeToGo.name + " is dismissed from branch: " + branch.branchName + ".");
                            branch.employees.removeEmployeeFromHashMap(employeeToGo);
                            branch.employeeList.remove(employeeToGo);
                        }
                    }

                    // here cashier is promoted to cook


                    // and if there is one cook waiting to be promoted and manager can leave:
                    if(numberOfThatJobsInThatBranch(cityAndDistrict,"COOK")==1){
                        Employee employeeToGo = findEmployeeByJob(cityAndDistrict, "COOK");
                        if (employeeToGo.promotionPoints>=10 ) {
                            Employee employeeToGoManager = findEmployeeByJob(cityAndDistrict, "MANAGER");
                            //System.out.println("PROMOTİON POİNTS OF MANAGER: "+employeeToGo.promotionPoints);
                            if(employeeToGoManager.promotionPoints<= -5){
                                System.out.println(employeeToGoManager.name + " is dismissed from branch: " + branch.branchName + ".");
                                branch.employees.removeEmployeeFromHashMap(employeeToGoManager);
                                branch.employeeList.remove(employeeToGoManager);


                                System.out.println(employeeToGo.name + " is promoted from " + "Cook" + " to "+ "Manager.");
                                branch.cookToManagaerPromotionQueue.remove(employeeToGo);
                                employeeToGo.job="MANAGER";
                                employeeToGo.promotionPoints-=10;
                            }
                        }
                    }
                    //cook become manager and manager is dismissed


                    System.out.println(employeeName + " is promoted from " + "Cashier" + " to "+ "Cook.");
                    employee.job="COOK";
                    employee.promotionPoints-=3;

                }
            }
        }




        else if(job.equals("COOK")){

            checkCookQueue(cityAndDistrict);


            if(employee.promotionPoints<=-5){
                if(checkThereAreMoreThanOneOfThatJob(cityAndDistrict, job )){
                    System.out.println(employee.name + " is dismissed from branch: " + branch.branchName + ".");
                    branch.employees.removeEmployeeFromHashMap(employee);
                    branch.employeeList.remove(employee);
                }
            }

            else if (employee.promotionPoints >= 10) {
                addToQueue(employee, cityAndDistrict);
                if(checkThereAreMoreThanOneOfThatJob( cityAndDistrict, job )){

                    Employee employeeToGo = findEmployeeByJob(cityAndDistrict, "MANAGER");
                    if(employeeToGo.promotionPoints<= -5){
                        System.out.println(employeeToGo.name + " is dismissed from branch: " + branch.branchName + ".");
                        branch.employees.removeEmployeeFromHashMap(employeeToGo);
                        branch.employeeList.remove(employeeToGo);


                        System.out.println(employeeName + " is promoted from " + "Cook" + " to "+ "Manager.");
                        employee.job="MANAGER";
                        branch.cookToManagaerPromotionQueue.remove(employee);
                        employee.promotionPoints-=10;
                    }
                }
            }
        }



        else if(job.equals("MANAGER")){
            if(employee.promotionPoints<=-5){
                //System.out.println(employeeName+" will be FİRED");
                if(!branch.cookToManagaerPromotionQueue.isEmpty() && checkThereAreMoreThanOneOfThatJob(cityAndDistrict, "COOK")){
                    System.out.println(employeeName + " is dismissed from branch: " + branch.branchName + ".");
                    branch.employees.removeEmployeeFromHashMap(employee);
                    branch.employeeList.remove(employee);

                    Employee employeeToBePromoted = branch.cookToManagaerPromotionQueue.poll();
                    System.out.println(employeeToBePromoted.name + " is promoted from " + "Cook" + " to "+ "Manager.");
                    employeeToBePromoted.job="MANAGER";
                    employeeToBePromoted.promotionPoints-=10;

                }
            }
        }


    }














    public Employee findEmployeeByJob(String cityAndDistrict, String job) {
        Branch branch = findBranch(cityAndDistrict);

        for (Employee p : branch.employeeList) {
            if (p.job.equals(job)) {
                return p;
            }
        }

        return null;
    }
















    public void checkCookQueue(String cityAndDistrict) {
        Branch branch= findBranch(cityAndDistrict);

        for (int i = 0; i < branch.cookToManagaerPromotionQueue.size(); i++) {
            Employee employee = branch.cookToManagaerPromotionQueue.get(i);
            if (employee.promotionPoints < 10) {
                branch.cookToManagaerPromotionQueue.remove(i);
                i--; // Decrement i because the list size has decreased
            }
        }



    }
























    public void addToQueue( Employee employee, String cityAndDistrict ){
        Branch branch=findBranch(cityAndDistrict);
        if(branch.cookToManagaerPromotionQueue.contains(employee)){
            //nothing
        }
        else{
            branch.cookToManagaerPromotionQueue.add(employee);
            //System.out.println(employee.name + " is added to the QUEUE");
        }


    }





    public void printMonthlyBonuses(String cityAndDistrict) {
        Branch branch = findBranch(cityAndDistrict);

        int monthlyBonusByHash = branch.monthlyBonus;
        System.out.println("Total bonuses for the " + branch.branchName + " branch this month are: " + monthlyBonusByHash);
    }



    public void printOverallBonuses(String cityAndDistrict) {
        Branch branch = findBranch(cityAndDistrict);

        int totalBonusByHash = branch.totalBonus;
        System.out.println("Total bonuses for the " + branch.branchName + " branch are: " + totalBonusByHash);
    }



    public void printManagerEmirhan(String cityAndDistrict) {
        Branch branch=findBranch(cityAndDistrict);


        for (Employee employee : branch.employeeList) {
            if (employee.job.equals("MANAGER")) {
                System.out.println("Manager of the " + branch.branchName + " branch is " + employee.name + ".");
                return;
            }
        }
    }


























    public void resetMonthlyBonus(){
        for(Branch branch: branchArray){
            if(branch == null){
                continue;
            }
            branch.monthlyBonus = 0;
        }
    }
}
