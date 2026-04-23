package hei.school.tdfinal.service;

import hei.school.tdfinal.dto.CreateMembershipFeeDto;
import hei.school.tdfinal.dto.MembershipFeeResponseDto;
import hei.school.tdfinal.entity.Collectivity;
import hei.school.tdfinal.entity.FrequencyTypeEnum;
import hei.school.tdfinal.entity.MembershipFee;
import hei.school.tdfinal.entity.MembershipFeeStatusEnum;
import hei.school.tdfinal.exception.BadRequestException;
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

    public List<MembershipFeeResponseDto> saveCollectivityMembershipFees(
            String collectivityId,
            List<CreateMembershipFeeDto> membershipFeeDtos
    ) {
        Collectivity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new NotFoundException("Collectivity not found.");
        }

        return membershipFeeDtos.stream()
                .map(dto -> {
                    if (
                            dto.getAmount() < 0 ||
                            dto.getFrequency() == null
                    ) {
                        throw new BadRequestException("Either unrecognized frequency or amount under 0");
                    }

                    MembershipFee saved = membershipFeeRepository.insert(
                            collectivityId,
                            dto.getEligibleFrom(),
                            dto.getFrequency(),
                            dto.getAmount(),
                            dto.getLabel(),
                            MembershipFeeStatusEnum.ACTIVE
                    );

                    return toDto(saved);
                })
                .collect(Collectors.toList());
    }
}