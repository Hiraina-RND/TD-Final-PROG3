package hei.school.tdfinal.dto;

import hei.school.tdfinal.entity.FrequencyTypeEnum;

import java.time.LocalDate;
import java.util.Objects;

public class CreateMembershipFeeDto {
    private LocalDate eligibleFrom;
    private FrequencyTypeEnum frequency;
    private Double amount;
    private String label;

    public CreateMembershipFeeDto(LocalDate eligibleFrom, FrequencyTypeEnum frequency, Double amount, String label) {
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateMembershipFeeDto that = (CreateMembershipFeeDto) o;
        return Objects.equals(eligibleFrom, that.eligibleFrom) && frequency == that.frequency && Objects.equals(amount, that.amount) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eligibleFrom, frequency, amount, label);
    }
}
