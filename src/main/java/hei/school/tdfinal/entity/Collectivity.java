package hei.school.tdfinal.entity;

import java.util.List;
import java.util.Objects;

public class Collectivity {
    private String id;
    private String name;
    private String location;
    private Boolean federation_approval;
    private List<CollectivityMember> collectivityMembers;

    public Collectivity(String id, String name, String location, Boolean federation_approval, List<CollectivityMember> collectivityMembers) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.federation_approval = federation_approval;
        this.collectivityMembers = collectivityMembers;
    }

    public Collectivity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getFederation_approval() {
        return federation_approval;
    }

    public void setFederation_approval(Boolean federation_approval) {
        this.federation_approval = federation_approval;
    }

    public List<CollectivityMember> getCollectivityMembers() {
        return collectivityMembers;
    }

    public void setCollectivityMembers(List<CollectivityMember> collectivityMembers) {
        this.collectivityMembers = collectivityMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Collectivity that = (Collectivity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
