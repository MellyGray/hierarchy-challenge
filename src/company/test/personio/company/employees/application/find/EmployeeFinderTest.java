package personio.company.employees.application.find;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personio.company.employees.domain.Employee;
import personio.company.employees.domain.EmployeeId;
import personio.company.employees.domain.EmployeeName;
import personio.company.employees.domain.EmployeeRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.atLeastOnce;

public class EmployeeFinderTest {
    private EmployeeFinder finder;
    private EmployeeRepository repository;

    @BeforeEach
    protected void setUp() {
        repository = mock(EmployeeRepository.class);
        finder = new EmployeeFinder(repository);
    }

    @Test
    void find_employee_by_id() {
        Employee employee = new Employee(new EmployeeName("Nick"));
        EmployeeId employeeId = new EmployeeId(1);
        employee.setId(employeeId);
        employee.setSupervisorId(new EmployeeId(3));

        FindEmployeeDTO request = new FindEmployeeDTO(employeeId.value());

        finder.find(request);

        verify(repository, atLeastOnce()).search(employeeId);
    }
}

