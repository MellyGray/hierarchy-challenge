package personio.company.employees.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
    public Employee save(Employee employee);

    public Employee search(EmployeeId id);

    public Employee searchByName(EmployeeName name);
}
