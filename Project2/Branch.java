import java.util.ArrayList;
import java.util.LinkedList;

public class Branch {
    String branchName;
    public String cityAndDistrict;

    int monthlyBonus;
    int totalBonus;

    boolean isUsed;

    HashMapForEmployees employees;
    ArrayList<Employee> employeeList;
    LinkedList<Employee> cookToManagaerPromotionQueue;


    //Branch initialisation:


    Branch(String cityAndDistrict) {
        this.cityAndDistrict = cityAndDistrict;
        String strArray[] = cityAndDistrict.split(",");
        this.branchName = strArray[1];
        this.isUsed = isUsed;
        this.employeeList = new ArrayList<>();


        this.employees = new HashMapForEmployees();
        this.isUsed = true;
        this.monthlyBonus = 0;
        this.totalBonus = 0;
        this.cookToManagaerPromotionQueue = new LinkedList<>();

    }


    public void addEmployee(String employeeName, String job) {
        Employee employee = new Employee(employeeName, job);
        employees.addEmployeeToHashTable(employee);
        employeeList.add(employee);

    }


    //CHECKERS FOR EACH job FOR LEAVE COMMAND



}


