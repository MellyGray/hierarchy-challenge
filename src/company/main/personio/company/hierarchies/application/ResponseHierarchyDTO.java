package personio.company.hierarchies.application;


public final class ResponseHierarchyDTO {
    private String hierarchy;

    public ResponseHierarchyDTO(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String hierarchy() {
        return hierarchy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hierarchy == null) ? 0 : hierarchy.hashCode());
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
        ResponseHierarchyDTO other = (ResponseHierarchyDTO) obj;
        if (hierarchy == null) {
            if (other.hierarchy != null)
                return false;
        } else if (!hierarchy.equals(other.hierarchy))
            return false;
        return true;
    }
    
}
