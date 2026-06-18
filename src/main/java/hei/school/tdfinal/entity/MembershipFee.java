package hei.school.tdfinal.entity;

import java.time.LocalDate;
import java.util.Objects;

public class MembershipFee {
    private String id;
    private LocalDate eligibleFrom;
    private FrequencyTypeEnum frequency;
    private Double amount;
    private String label;
    private MembershipFeeStatusEnum status;
    private String collectivityId;

    public MembershipFee(String id, LocalDate eligibleFrom, FrequencyTypeEnum frequency, Double amount, String label, MembershipFeeStatusEnum status, String collectivity) {
        this.id = id;
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
        this.status = status;
        this.collectivityId = collectivity;
    }

    public MembershipFee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getEligibleFrom() {
        return eligibleFrom;
    }

    public void setEligibleFrom(LocalDate eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
    }

    public FrequencyTypeEnum getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyTypeEnum frequency) {
        this.frequency = frequency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MembershipFeeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MembershipFeeStatusEnum status) {
        this.status = status;
    }

    public String getCollectivity() {
        return collectivityId;
    }

    public void setCollectivity(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MembershipFee that = (MembershipFee) o;
        return Objects.equals(id, that.id) && Objects.equals(eligibleFrom, that.eligibleFrom) && frequency == that.frequency && Objects.equals(amount, that.amount) && Objects.equals(label, that.label) && status == that.status && Objects.equals(collectivityId, that.collectivityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eligibleFrom, frequency, amount, label, status, collectivityId);
    }
}
