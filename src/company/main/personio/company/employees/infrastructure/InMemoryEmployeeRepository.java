package personio.company.employees.infrastructure;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeId;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {
    private HashMap<EmployeeId, Employee> employees = new HashMap<>();

    @Override
    public void save(Employee employee){
        this.employees.put(employee.id(), employee);
    }

    @Override
    public Optional<Employee> searchByName(EmployeeName name) {
        return Optional.ofNullable(employees.get(name));
    }
}