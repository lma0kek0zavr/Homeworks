import java.util.Arrays;

public class EmployeeBook {
    private Employee[] employees; 

    private EmployeeSalaryComparator salaryComparator = new EmployeeSalaryComparator();

    public EmployeeBook(int bookCapacity) {
        employees = new Employee[bookCapacity];
    }

    public boolean add(String firstName, String middleName, String lastName, int department, int salary) { 
        Employee employee = new Employee(firstName, middleName, lastName, department, salary);  
        for(int i = 0; i < employees.length; i++) {
            if(employees[i] == null) {
                employees[i] = employee;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int employeeId) {
        for (int i = 0; i <= employees.length; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                employees[i] = null;
                return true;
            }
        } 
        return false;
    }

    public void getAllEmployees() { 
        for(Employee employee : employees) {
            if (employee == null) { 
                System.out.println("[Id: null; Full name: null; Department: null; Salary: null]");
                continue;
            }
            System.out.println(employee.toString());
        }
    }

    public int calculateTotalSalary() {
        int totalSalary = 0;
        for(Employee employee : employees) {
            if (employee == null) { 
                continue;
            }
            totalSalary += employee.getSalary();
        }
        return totalSalary;
    }

    public int calculateAverageSalary() { 
        int totalSalary = calculateTotalSalary();

        int averageSalary = totalSalary / employees.length;
        return averageSalary;
    }

    public String getEmployeeWithMinimalSalary() {
        Employee[] temp = new Employee[employees.length];
        System.arraycopy(employees, 0, temp, 0, employees.length);

        Arrays.sort(temp, salaryComparator);
        return temp[0].toString();
    }

    public String getEmployeeWithMaximalSalary() { 
        Employee[] temp = new Employee[employees.length];
        System.arraycopy(employees, 0, temp, 0, employees.length);

        Arrays.sort(temp, salaryComparator);
        return temp[temp.length - 2].toString();
    }

    public void printAllEmployeesFullName() { 
        for(Employee employee : employees) { 
            if (employee == null) { 
                System.out.println("Null");
                continue;
            }
            System.out.println(employee.getFullName());
        }
    }

    public void indexSalary(int indexationPercentage) {
        for(Employee employee : employees) { 
            if (employee == null) { 
                continue;
            }
            employee.setSalary((employee.getSalary() * indexationPercentage / 100) + employee.getSalary());
        }
    }

    public void getInfoByDepartment(int department) {
        int departmentSize = 0;

        for(Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getDepartment() == department) { 
                departmentSize += 1;
            }
        }

        Employee[] employeesByDepartment = new Employee[departmentSize];

        for(Employee employee : employees) {
            if (employee == null) continue;

            if (employee.getDepartment() == department) { 
                employeesByDepartment[employeesByDepartment.length - departmentSize] = employee;
                departmentSize -= 1;
            }
        }

        for(Employee employee : employeesByDepartment) { 
            System.out.printf("\n[Id: %d; Full name %s; Salary: %d]",
            employee.getEmployeeId(),
            employee.getFullName(),
            employee.getSalary());
        }
    }
} 
