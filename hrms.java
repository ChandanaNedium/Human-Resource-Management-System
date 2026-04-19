import java.util.*;

// Employee Class
class Employee {
    int id;
    String name, designation, department, contact;

    Employee(int id, String name, String designation, String department, String contact) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.contact = contact;
    }

    public String toString() {
        return id + " | " + name + " | " + designation + " | " + department + " | " + contact;
    }
}

// Leave Class
class Leave {
    int empId;
    String type, startDate, endDate, status;

    Leave(int empId, String type, String startDate, String endDate) {
        this.empId = empId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
    }

    public String toString() {
        return empId + " | " + type + " | " + startDate + " to " + endDate + " | Status: " + status;
    }
}

public class hrms {

    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Employee> employees = new HashMap<>();
    static HashMap<Integer, String> attendance = new HashMap<>();
    static List<Leave> leaves = new ArrayList<>();

    static String adminUser = "admin";
    static String adminPass = "1234";

    public static void main(String[] args) {

        // Login System
        System.out.print("Enter Username: ");
        String user = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (!user.equals(adminUser) || !pass.equals(adminPass)) {
            System.out.println("Invalid Login!");
            return;
        }

        System.out.println("Login Successful!");

        while (true) {
            System.out.println("\n--- HRMS MENU ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Mark Attendance");
            System.out.println("6. View Attendance");
            System.out.println("7. Apply Leave");
            System.out.println("8. Approve/Reject Leave");
            System.out.println("9. View Leaves");
            System.out.println("10. Search Employee");
            System.out.println("11. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: updateEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5: markAttendance(); break;
                case 6: viewAttendance(); break;
                case 7: applyLeave(); break;
                case 8: manageLeave(); break;
                case 9: viewLeaves(); break;
                case 10: searchEmployee(); break;
                case 11: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Add Employee
    static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        if (employees.containsKey(id)) {
            System.out.println("Employee ID already exists!");
            return;
        }

        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Designation: ");
        String des = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Contact: ");
        String contact = sc.nextLine();

        employees.put(id, new Employee(id, name, des, dept, contact));
        System.out.println("Employee Added Successfully!");
    }

    // View Employees
    static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for (Employee e : employees.values()) {
            System.out.println(e);
        }
    }

    // Update Employee
    static void updateEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        sc.nextLine();
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        employees.get(id).name = name;
        System.out.println("Employee Updated Successfully!");
    }

    // Delete Employee
    static void deleteEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        employees.remove(id);
        System.out.println("Employee Deleted Successfully!");
    }

    // Mark Attendance
    static void markAttendance() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Status (Present/Absent/Leave): ");
        String status = sc.nextLine();

        attendance.put(id, status);
        System.out.println("Attendance Marked!");
    }

    // View Attendance
    static void viewAttendance() {
        if (attendance.isEmpty()) {
            System.out.println("No attendance records!");
            return;
        }

        for (Map.Entry<Integer, String> entry : attendance.entrySet()) {
            System.out.println("Emp ID: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Apply Leave
    static void applyLeave() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        sc.nextLine();
        System.out.print("Leave Type: ");
        String type = sc.nextLine();
        System.out.print("Start Date: ");
        String start = sc.nextLine();
        System.out.print("End Date: ");
        String end = sc.nextLine();

        leaves.add(new Leave(id, type, start, end));
        System.out.println("Leave Applied Successfully!");
    }

    // Approve/Reject Leave (FIXED)
    static void manageLeave() {
        if (leaves.isEmpty()) {
            System.out.println("No leave requests available!");
            return;
        }

        for (int i = 0; i < leaves.size(); i++) {
            System.out.println(i + ". " + leaves.get(i));
        }

        System.out.print("Select Leave Index: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 0 || index >= leaves.size()) {
            System.out.println("Invalid index!");
            return;
        }

        System.out.print("Approve or Reject: ");
        String status = sc.nextLine();

        leaves.get(index).status = status;
        System.out.println("Leave Updated Successfully!");
    }

    // View Leaves
    static void viewLeaves() {
        if (leaves.isEmpty()) {
            System.out.println("No leave records found!");
            return;
        }

        for (Leave l : leaves) {
            System.out.println(l);
        }
    }

    // Search Employee
    static void searchEmployee() {
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Employee e : employees.values()) {
            if (e.name.equalsIgnoreCase(name)) {
                System.out.println(e);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Employee not found!");
        }
    }
}

//leave index - 0,1 etc.
