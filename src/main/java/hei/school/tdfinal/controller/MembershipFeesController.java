package hei.school.tdfinal.controller;

import hei.school.tdfinal.dto.CreateMembershipFeeDto;
import hei.school.tdfinal.exception.BadRequestException;
import hei.school.tdfinal.exception.NotFoundException;
import hei.school.tdfinal.service.MembershipFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembershipFeesController {
    MembershipFeeService membershipFeeService;

    public MembershipFeesController(MembershipFeeService membershipFeeService) {
        this.membershipFeeService = membershipFeeService;
    }

    @GetMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<?> findCollectivityMembershipFees(
            @PathVariable String id
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(membershipFeeService.findMembershipFeesByCollectivityId(id));
        } catch (NotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<?> saveCollectivityMembershipFees(
            @PathVariable String id,
            @RequestBody List<CreateMembershipFeeDto> membershipFees
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(membershipFeeService.saveCollectivityMembershipFees(id, membershipFees));
        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
