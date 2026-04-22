package hei.school.tdfinal.dto;

import java.util.Objects;

public class CreateCollectivityStructureDto {
    private String president;
    private String vicePresident;
    private String treasurer;
    private String secretary;

    public CreateCollectivityStructureDto(String president, String vicePresident, String treasurer, String secretary) {
        this.president = president;
        this.vicePresident = vicePresident;
        this.treasurer = treasurer;
        this.secretary = secretary;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getVicePresident() {
        return vicePresident;
    }

    public void setVicePresident(String vicePresident) {
        this.vicePresident = vicePresident;
    }

    public String getTreasurer() {
        return treasurer;
    }

    public void setTreasurer(String treasurer) {
        this.treasurer = treasurer;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateCollectivityStructureDto that = (CreateCollectivityStructureDto) o;
        return Objects.equals(president, that.president) && Objects.equals(vicePresident, that.vicePresident) && Objects.equals(treasurer, that.treasurer) && Objects.equals(secretary, that.secretary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(president, vicePresident, treasurer, secretary);
    }
}
