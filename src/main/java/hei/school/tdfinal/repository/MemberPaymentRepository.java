package hei.school.tdfinal.repository;

import hei.school.tdfinal.entity.MemberPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPaymentRepository extends JpaRepository<MemberPayment, Long> {
}