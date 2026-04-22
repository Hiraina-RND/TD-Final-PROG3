package hei.school.tdfinal.controller;

import hei.school.tdfinal.exception.NotFoundException;
import hei.school.tdfinal.service.MembershipFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
