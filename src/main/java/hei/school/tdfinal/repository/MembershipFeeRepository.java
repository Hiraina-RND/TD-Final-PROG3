package hei.school.tdfinal.repository;

import hei.school.tdfinal.entity.FrequencyTypeEnum;
import hei.school.tdfinal.entity.MembershipFee;
import hei.school.tdfinal.entity.MembershipFeeStatusEnum;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MembershipFeeRepository {
    private final Connection connection;

    public MembershipFeeRepository(Connection connection) {
        this.connection = connection;
    }

    public List<MembershipFee> findCollectivityMembershipFees(String id) {
        List<MembershipFee> membershipFees = new ArrayList<>();
        String sql = """
                select
                    msf.id,
                    msf.eligible_from,
                    msf.frequency,
                    msf.amount,
                    msf.label,
                    msf.status,
                    msf.collectivity_id
                from membership_fees msf
                where msf.collectivity_id = ?
                """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                membershipFees.add(
                        new MembershipFee(
                                resultSet.getString("id"),
                                resultSet.getDate("eligible_from").toLocalDate(),
                                FrequencyTypeEnum.valueOf(resultSet.getString("frequency")),
                                resultSet.getDouble("amount"),
                                resultSet.getString("label"),
                                MembershipFeeStatusEnum.valueOf(resultSet.getString("status")),
                                resultSet.getString("collectivity_id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return membershipFees;
    }
}
