public class HashMapForEmployees {
    private static int INITIAL_SIZE = 1543;

    private Employee[] employeeArray;
    private int numberOfUsedPlaces;


    //initialization construction methods
    HashMapForEmployees() {
        this(INITIAL_SIZE);
    }

    HashMapForEmployees(int tableSize) {
        createNewEmptyTable(tableSize);
        numberOfUsedPlaces = 0;
        for (int i = 0; i < employeeArray.length; i++) {
            employeeArray[i] = null;
        }
    }

    HashMapForEmployees(Employee[] employeeArray, int size) {
        createNewEmptyTable(size);

    }

    private void createNewEmptyTable(int arraySize) {
        employeeArray = new Employee[nextPrimeToBeUsed(arraySize)];
    }


    public boolean addEmployeeToHashTable(Employee employee) {
        int index = giveIndex(employee.name);
        if (returnIfIndexIsUsed(index)) {
            return false;
        }

        if (employeeArray[index] == null) {
            numberOfUsedPlaces++;
        }
        employeeArray[index] = employee;


        if (numberOfUsedPlaces > employeeArray.length / 2) {
            rehash();
        }
        return true;
    }

    public Employee returnEmployeeObject(String name) {
        int initial = 1;
        int usedIndex = findHashedIndex(name);

        while (employeeArray[usedIndex] != null && !employeeArray[usedIndex].name.equals(name)) {
            usedIndex += initial;
            initial += 2;
            if (usedIndex >= employeeArray.length) {
                usedIndex -= employeeArray.length;
            }
        }

        return employeeArray[usedIndex];
    }


    public void removeEmployeeFromHashMap(Employee employee) {
        int employeeIndex = giveIndex(employee.name);
        if (returnIfIndexIsUsed(employeeIndex)) {
            employeeArray[employeeIndex].isUsed = false;

        }

    }



    private int giveIndex(String employeeName) {
        int employeeIndex = findHashedIndex(employeeName);
        int initial = 1;

        while (employeeArray[employeeIndex] != null && !employeeArray[employeeIndex].name.equals(employeeName)) {
            employeeIndex += initial;
            initial += 2;
            if (employeeIndex >= employeeArray.length) {
                employeeIndex -= employeeArray.length;
            }
        }

        return employeeIndex;
    }

    public boolean contains(String employeeName) {
        int usedIndex = giveIndex(employeeName);
        return returnIfIndexIsUsed(usedIndex);
    }

    private boolean returnIfIndexIsUsed(int employeeIndex) {
        return employeeArray[employeeIndex] != null && employeeArray[employeeIndex].isUsed;
    }

    private int findHashedIndex(String name) {
        int hashedIndex = name.hashCode();
        hashedIndex = hashedIndex % employeeArray.length;
        if (hashedIndex < 0) {
            hashedIndex += employeeArray.length;
        }
        return hashedIndex;
    }

    private void rehash() {
        Employee[] oldArrayToBeRenewed = employeeArray;
        createNewEmptyTable(2 * oldArrayToBeRenewed.length);
        numberOfUsedPlaces = 0;

        for (Employee employee : oldArrayToBeRenewed) {
            if (employee != null && employee.isUsed) {
                addEmployeeToHashTable(employee);
            }
        }
    }
































    private static int nextPrimeToBeUsed(int usedPrimeNumber) {
        if (usedPrimeNumber % 2 == 0) {
            usedPrimeNumber++;
        }
        while (!checkIfPrimeNumber(usedPrimeNumber)) {
            usedPrimeNumber += 2;
        }
        return usedPrimeNumber;
    }

    private static boolean checkIfPrimeNumber(int number) {
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


}
