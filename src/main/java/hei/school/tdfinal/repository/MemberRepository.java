package hei.school.tdfinal.repository;

import hei.school.tdfinal.entity.GenderEnum;
import hei.school.tdfinal.entity.Member;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberRepository {
    Connection connection;

    public MemberRepository(Connection connection) {
        this.connection = connection;
    }

    public Member findById(String id) {
        String sql = """
            SELECT id,
                   first_name,
                   last_name,
                   birth_date,
                   gender,
                   address,
                   profession,
                   phone_number,
                   email
            FROM member
            WHERE id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Member member = new Member();

                member.setId(rs.getString("id"));
                member.setFirstName(rs.getString("first_name"));
                member.setLastName(rs.getString("last_name"));
                member.setBirthDate(rs.getDate("birth_date").toLocalDate());
                member.setGender(GenderEnum.valueOf(rs.getString("gender")));
                member.setAdress(rs.getString("address"));
                member.setProfession(rs.getString("profession"));
                member.setPhoneNumber(rs.getString("phone_number"));
                member.setEmail(rs.getString("email"));

                return member;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
