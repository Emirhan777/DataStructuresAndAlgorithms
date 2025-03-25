public class Employee {
    String name;
    String job;

    int promotionPoints;

    boolean isUsed;


    Employee(String name, String job) {
        this.name = name;
        this.job = job;
        this.isUsed = true;
    }

    @Override
    public String toString() {
        return name + " (" + job + ")" ;
    }



    void updatePerformance(int monthlyScore) {

        this.promotionPoints += monthlyScore / 200; // Will decrease if negative
        // Bonus remains unchanged for negative scores

    }
}