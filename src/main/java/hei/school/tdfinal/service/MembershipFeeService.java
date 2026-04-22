package hei.school.tdfinal.service;

import hei.school.tdfinal.dto.MembershipFeeResponseDto;
import hei.school.tdfinal.entity.MembershipFee;
import hei.school.tdfinal.repository.MembershipFeeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MembershipFeeService {

    private final MembershipFeeRepository membershipFeeRepository;

    public MembershipFeeService(MembershipFeeRepository membershipFeeRepository) {
        this.membershipFeeRepository = membershipFeeRepository;
    }

    public List<MembershipFeeResponseDto> findMembershipFeesByCollectivityId(String collectivityId) {

        return membershipFeeRepository
                .findCollectivityMembershipFees(collectivityId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private MembershipFeeResponseDto toDto(MembershipFee fee) {
        return new MembershipFeeResponseDto(
                fee.getId(),
                fee.getEligibleFrom(),
                fee.getFrequency(),
                fee.getAmount(),
                fee.getLabel(),
                fee.getStatus()
        );
    }
}