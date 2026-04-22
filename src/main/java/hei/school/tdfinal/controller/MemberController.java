package hei.school.tdfinal.controller;

import hei.school.tdfinal.dto.CreateMemberPaymentDto;
import hei.school.tdfinal.entity.MemberPayment;
import hei.school.tdfinal.service.CollectivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class MemberController {
    private final CollectivityService service;

    @PostMapping("/members/{id}/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MemberPayment> createPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentDto> dtos) {
        return service.createMemberPayments(id, dtos);
    }
}