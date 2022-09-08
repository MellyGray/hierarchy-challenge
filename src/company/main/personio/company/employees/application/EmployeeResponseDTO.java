package personio.company.employees.application;

public final class EmployeeResponseDTO {
    private String name;
    private Integer supervisorId;

    public EmployeeResponseDTO(String name, Integer supervisorId) {
        this.name = name;
        this.supervisorId = supervisorId;
    }

    public String name() {
        return name;
    }

    public Integer supervisorId() {
        return supervisorId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        EmployeeResponseDTO other = (EmployeeResponseDTO) obj;
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
