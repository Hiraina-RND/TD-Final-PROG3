package hei.school.tdfinal.dto;

import java.util.List;
import java.util.Objects;

public class CreateCollectivityDto {
    private String location;
    private List<String> members;
    private Boolean federationApproval;
    private CreateCollectivityStructureDto structure;

    public CreateCollectivityDto(String location, List<String> members, Boolean federationApproval, CreateCollectivityStructureDto structure) {
        this.location = location;
        this.members = members;
        this.federationApproval = federationApproval;
        this.structure = structure;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Boolean getFederationApproval() {
        return federationApproval;
    }

    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }

    public CreateCollectivityStructureDto getStructure() {
        return structure;
    }

    public void setStructure(CreateCollectivityStructureDto structure) {
        this.structure = structure;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateCollectivityDto that = (CreateCollectivityDto) o;
        return Objects.equals(location, that.location) && Objects.equals(members, that.members) && Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, members, structure);
    }
}
