package hei.school.tdfinal.service;

import hei.school.tdfinal.dto.CollectivityResponseDto;
import hei.school.tdfinal.dto.CollectivityResponseStructureDto;
import hei.school.tdfinal.dto.CreateCollectivityDto;
import hei.school.tdfinal.dto.CreateMemberPaymentDto;
import hei.school.tdfinal.entity.Member;
import hei.school.tdfinal.entity.MemberPayment;
import hei.school.tdfinal.entity.OccupationEnum;
import hei.school.tdfinal.exception.BadRequestException;
import hei.school.tdfinal.exception.NotFoundException;
import hei.school.tdfinal.repository.CollectivityMemberRepository;
import hei.school.tdfinal.repository.CollectivityRepository;
import hei.school.tdfinal.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final CollectivityMemberRepository collectivityMemberRepository;

    public CollectivityService(
            CollectivityRepository collectivityRepository,
            MemberRepository memberRepository,
            CollectivityMemberRepository collectivityMemberRepository
    ) {
        this.collectivityRepository = collectivityRepository;
        this.memberRepository = memberRepository;
        this.collectivityMemberRepository = collectivityMemberRepository;
    }

    @Transactional
    public List<CollectivityResponseDto> save(List<CreateCollectivityDto> dtos) {

        List<CollectivityResponseDto> responses = new ArrayList<>();
        Set<String> requestKeys = new HashSet<>();

        List<ValidatedCollectivity> validated = new ArrayList<>();

        for (CreateCollectivityDto dto : dtos) {

            if (dto.getStructure() == null) {
                throw new BadRequestException("Structure is required");
            }

            String president = dto.getStructure().getPresident();
            String vice = dto.getStructure().getVicePresident();
            String treasurer = dto.getStructure().getTreasurer();
            String secretary = dto.getStructure().getSecretary();

            if (president == null || vice == null || treasurer == null || secretary == null) {
                throw new BadRequestException("All structure roles must be assigned");
            }

            List<String> structure = List.of(president, vice, treasurer, secretary);

            Set<String> structureSet = new HashSet<>(structure);
            if (structureSet.size() != 4) {
                throw new BadRequestException("Duplicate members are not allowed in structure");
            }

            structureSet.forEach(this::validateMember);

            Set<String> juniors = new HashSet<>();

            for (String memberId : dto.getMembers()) {

                if (memberRepository.findById(memberId) == null) {
                    throw new NotFoundException("Member not found: " + memberId);
                }

                if (structureSet.contains(memberId)) {
                    throw new BadRequestException("Member cannot be both structure and junior: " + memberId);
                }

                if (!juniors.add(memberId)) {
                    throw new BadRequestException("Duplicate junior member not allowed: " + memberId);
                }
            }

            String requestKey = dto.getLocation()
                    + "-" + president
                    + "-" + vice
                    + "-" + treasurer
                    + "-" + secretary;

            if (!requestKeys.add(requestKey)) {
                throw new BadRequestException("Duplicate collectivity in request body");
            }

            if (collectivityRepository.existsByLocationAndStructure(
                    dto.getLocation(),
                    president,
                    vice,
                    treasurer,
                    secretary
            )) {
                throw new BadRequestException("Collectivity already exists");
            }

            validated.add(new ValidatedCollectivity(dto, structure, juniors));
        }

        for (ValidatedCollectivity v : validated) {

            String president = v.structure().get(0);
            String vice = v.structure().get(1);
            String treasurer = v.structure().get(2);
            String secretary = v.structure().get(3);

            String collectivityId =
                    collectivityRepository.insert(v.dto().getLocation(), v.dto().getFederationApproval());

            collectivityMemberRepository.insert(collectivityId, president, OccupationEnum.PRESIDENT);
            collectivityMemberRepository.insert(collectivityId, vice, OccupationEnum.VICE_PRESIDENT);
            collectivityMemberRepository.insert(collectivityId, treasurer, OccupationEnum.TREASURER);
            collectivityMemberRepository.insert(collectivityId, secretary, OccupationEnum.SECRETARY);

            for (String memberId : v.juniors()) {
                collectivityMemberRepository.insert(collectivityId, memberId, OccupationEnum.JUNIOR);
            }

            List<Member> members = v.juniors().stream()
                    .map(memberRepository::findById)
                    .toList();

            Map<OccupationEnum, String> structureIds =
                    collectivityMemberRepository.findStructure(collectivityId);

            CollectivityResponseStructureDto structure =
                    new CollectivityResponseStructureDto(
                            memberRepository.findById(structureIds.get(OccupationEnum.PRESIDENT)),
                            memberRepository.findById(structureIds.get(OccupationEnum.VICE_PRESIDENT)),
                            memberRepository.findById(structureIds.get(OccupationEnum.TREASURER)),
                            memberRepository.findById(structureIds.get(OccupationEnum.SECRETARY))
                    );

            responses.add(
                    new CollectivityResponseDto(
                            collectivityId,
                            v.dto().getLocation(),
                            structure,
                            members
                    )
            );
        }

        return responses;
    }

    private void validateMember(String memberId) {
        if (memberRepository.findById(memberId) == null) {
            throw new NotFoundException("Member not found: " + memberId);
        }
    }

    public List<MemberPayment> createMemberPayments(String id, List<CreateMemberPaymentDto> dtos) {
        return List.of();
    }

    record ValidatedCollectivity(
            CreateCollectivityDto dto,
            List<String> structure,
            Set<String> juniors
    ) {}
}