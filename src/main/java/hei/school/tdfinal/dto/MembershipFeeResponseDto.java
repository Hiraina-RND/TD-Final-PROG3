package hei.school.tdfinal.dto;

import hei.school.tdfinal.entity.FrequencyTypeEnum;
import hei.school.tdfinal.entity.MembershipFeeStatusEnum;

import java.time.LocalDate;
import java.util.Objects;

public class MembershipFeeResponseDto {
    private String id;
    private LocalDate eligibleFrom;
    private FrequencyTypeEnum frequency;
    private Double amount;
    private String label;
    private MembershipFeeStatusEnum status;

    public MembershipFeeResponseDto(String id, LocalDate eligibleFrom, FrequencyTypeEnum frequency, Double amount, String label, MembershipFeeStatusEnum status) {
        this.id = id;
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
        this.status = status;
    }

    public MembershipFeeResponseDto() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MembershipFeeResponseDto that = (MembershipFeeResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(eligibleFrom, that.eligibleFrom) && frequency == that.frequency && Objects.equals(amount, that.amount) && Objects.equals(label, that.label) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eligibleFrom, frequency, amount, label, status);
    }
}
