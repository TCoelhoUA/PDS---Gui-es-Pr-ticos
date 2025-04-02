// Sweets

import java.util.Vector;

public class Database {         // Data elements
    private Vector<Employee> employees; // Stores the employees
    
    public Database() {
        employees = new Vector<>();
    }
    
    public boolean addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            return employees.add(employee);
        }
        else {
            System.out.printf("Employee (%d) already exists!\n", employee.getEmpNum());
            return false;
        }
    }
    
    public void deleteEmployee(long emp_num) {
        // Code to delete employee
        for (int i=0; i<employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getEmpNum() == emp_num) {
                employees.remove(i);
                return;
            }
        }
        System.out.printf("Employee (%d) not found!\n", emp_num);
    }
    
    public Employee[] getAllEmployees() {
        // Code to retrieve collection
        Employee[] employees = new Employee[this.employees.size()];

        for (int i=0; i<this.employees.size(); i++) {
            employees[i] = this.employees.get(i);
        }
        
        return employees;
    }
}
