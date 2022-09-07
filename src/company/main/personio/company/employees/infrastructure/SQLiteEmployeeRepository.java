package personio.company.employees.infrastructure;

import java.sql.*;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeId;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;

@Repository
public final class SQLiteEmployeeRepository implements EmployeeRepository {
    private static final String EMPLOYEES_TABLE = "employees";

    @Override
    public void save(Employee employee) {
        try {
            Connection connection = startConnection();
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO " + EMPLOYEES_TABLE + " (NAME, SUPERVISOR) " +
                    "VALUES (" + employee.name() + ", " + employee.supervisorId() + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException("Error saving the employee in db");
        }
    }

    @Override
    public Employee search(EmployeeId id) throws RuntimeException {
        try {
            Connection connection = startConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + EMPLOYEES_TABLE + " WHERE id=\"" + id.value() + "\";");
            
            Employee employee = new Employee(new EmployeeName(rs.getString("name")));

            employee.setSupervisorId(new EmployeeId(rs.getInt("supervisor")));

            rs.close();
            stmt.close();
            connection.close();

            return employee;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            throw new RuntimeException("Error finding the employee with id " + id.value() + " in the db");
        }
    }

    @Override
    public Employee searchByName(EmployeeName name) throws RuntimeException {
        try {
            Connection connection = startConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + EMPLOYEES_TABLE + " WHERE name=\"" + name.value() + "\";");
            
            Employee employee = new Employee(new EmployeeName(rs.getString("name")));

            employee.setSupervisorId(new EmployeeId(rs.getInt("supervisor")));

            rs.close();
            stmt.close();
            connection.close();

            return employee;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            throw new RuntimeException("Error finding the employee with name " + name.value() + " in the db");
        }
    }

    private Connection startConnection() throws Exception {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:personio.db");
        connection.setAutoCommit(false);
        System.out.println("Opened database successfully");

        return connection;
    }
}