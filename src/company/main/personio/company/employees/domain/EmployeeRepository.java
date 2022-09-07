package personio.company.employees.domain;


public interface EmployeeRepository {
    public void save(Employee employee);

    public Employee search(EmployeeId id);

    public Employee searchByName(EmployeeName name);
}
