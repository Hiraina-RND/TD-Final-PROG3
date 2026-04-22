package hei.school.tdfinal.service;

import hei.school.tdfinal.dto.MembershipFeeResponseDto;
import hei.school.tdfinal.entity.Collectivity;
import hei.school.tdfinal.entity.MembershipFee;
import hei.school.tdfinal.exception.NotFoundException;
import hei.school.tdfinal.repository.CollectivityRepository;
import hei.school.tdfinal.repository.MembershipFeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembershipFeeService {

    private final MembershipFeeRepository membershipFeeRepository;
    private final CollectivityRepository collectivityRepository;

    public MembershipFeeService(
            MembershipFeeRepository membershipFeeRepository,
            CollectivityRepository collectivityRepository
    ) {
        this.membershipFeeRepository = membershipFeeRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public List<MembershipFeeResponseDto> findMembershipFeesByCollectivityId(String collectivityId) {

        List<MembershipFee> fees = membershipFeeRepository.findCollectivityMembershipFees(collectivityId);
        Collectivity collectivity = collectivityRepository.findById(collectivityId);

        if (collectivity == null) {
            throw new NotFoundException("Collectivity not found.");
        }

        return fees
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