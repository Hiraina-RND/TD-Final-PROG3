package hei.school.tdfinal.dto;

import hei.school.tdfinal.entity.Member;

import java.util.List;

public class CollectivityResponseDto {
    private String id;
    private String location;
    private CollectivityResponseStructureDto structure;
    private List<Member> members;

    public CollectivityResponseDto(String id, String location, CollectivityResponseStructureDto structure, List<Member> members) {
        this.id = id;
        this.location = location;
        this.structure = structure;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CollectivityResponseStructureDto getStructure() {
        return structure;
    }

    public void setStructure(CollectivityResponseStructureDto structure) {
        this.structure = structure;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
