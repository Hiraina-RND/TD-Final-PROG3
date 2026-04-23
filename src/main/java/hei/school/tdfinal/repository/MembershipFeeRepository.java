package hei.school.tdfinal.repository;

import hei.school.tdfinal.entity.FrequencyTypeEnum;
import hei.school.tdfinal.entity.MembershipFee;
import hei.school.tdfinal.entity.MembershipFeeStatusEnum;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public MembershipFee insert(
            String collectivity_id,
            LocalDate eligibleFrom,
            FrequencyTypeEnum frequency,
            Double amount,
            String label,
            MembershipFeeStatusEnum status
    ) {
        String sql = """
        insert into membership_fees
        (id, eligible_from, frequency, amount, label, status, collectivity_id)
        values (?, ?, ?::frequencyTypeEnum, ?, ?, ?::statusTypeEnum, ?)
        returning id
        """;
        MembershipFee membershipFee = new MembershipFee();

        try {
            String generatedId = "MSF-" + UUID.randomUUID().toString();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, generatedId);
            preparedStatement.setDate(2, Date.valueOf(eligibleFrom));
            preparedStatement.setString(3, frequency.name());
            preparedStatement.setDouble(4, amount);
            preparedStatement.setString(5, label);
            preparedStatement.setString(6, status.name());
            preparedStatement.setString(7, collectivity_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                membershipFee = findById(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return membershipFee;
    }

    public MembershipFee findById(String id) {
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
            where msf.id = ?
            """;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new MembershipFee(
                        resultSet.getString("id"),
                        resultSet.getDate("eligible_from").toLocalDate(),
                        FrequencyTypeEnum.valueOf(resultSet.getString("frequency")),
                        resultSet.getDouble("amount"),
                        resultSet.getString("label"),
                        MembershipFeeStatusEnum.valueOf(resultSet.getString("status")),
                        resultSet.getString("collectivity_id")
                );
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
