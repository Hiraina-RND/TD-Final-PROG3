package hei.school.tdfinal.entity;

import java.util.Objects;

public class CollectivityMember {
    private Integer id;
    private String member_id;
    private String collectivity_id;
    private OccupationEnum occupation;

    public CollectivityMember(Integer id, String member_id, String collectivity_id, OccupationEnum occupation) {
        this.id = id;
        this.member_id = member_id;
        this.collectivity_id = collectivity_id;
        this.occupation = occupation;
    }

    public CollectivityMember() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCollectivity_id() {
        return collectivity_id;
    }

    public void setCollectivity_id(String collectivity_id) {
        this.collectivity_id = collectivity_id;
    }

    public OccupationEnum getOccupation() {
        return occupation;
    }

    public void setOccupation(OccupationEnum occupation) {
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CollectivityMember that = (CollectivityMember) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
