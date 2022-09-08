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
    public Employee save(Employee employee) throws RuntimeException {
        try {
            Connection connection = startConnection();
            Statement stmt = connection.createStatement();
            Integer supervisorId = employee.supervisorId() != null ? employee.supervisorId().value() : null;
            String sql = "INSERT INTO " + EMPLOYEES_TABLE + " (name, supervisor) VALUES (\"" + employee.name().value()
                    + "\", \""
                    + supervisorId + "\");";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.commit();
            connection.close();

            return new Employee(employee.name());
        } catch (Exception e) {
            throw new RuntimeException("Error saving the employee " + employee.name() + " in db. " + e.getMessage());
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
            employee.setId(new EmployeeId(rs.getInt("id")));

            rs.close();
            stmt.close();
            connection.close();

            return employee;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error finding the employee with name " + name.value() + " in the db. " + e.getMessage()
            );
        }
    }

    private Connection startConnection() throws RuntimeException {
        try {
            Connection connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:personio.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Error opening the database. " + e.getMessage());
        }
    }
}