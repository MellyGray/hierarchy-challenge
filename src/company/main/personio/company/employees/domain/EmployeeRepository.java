package personio.company.employees.domain;

import java.util.Optional;

public interface EmployeeRepository {
    public void save(Employee employee);

    Optional<Employee> searchByName(EmployeeName name);
}
