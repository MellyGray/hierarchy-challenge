package personio.company.employees.domain;


public final class Employee {
    private EmployeeName name;
    private EmployeeId id;
    private EmployeeId supervisorId;

    public Employee(EmployeeName name) {
        this.name = name;
    }

    public EmployeeName name() {
        return name;
    }

    public EmployeeId id() {
        return id;
    }

    public EmployeeId supervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(EmployeeId supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((supervisorId == null) ? 0 : supervisorId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (supervisorId == null) {
            if (other.supervisorId != null)
                return false;
        } else if (!supervisorId.equals(other.supervisorId))
            return false;
        return true;
    }
}
