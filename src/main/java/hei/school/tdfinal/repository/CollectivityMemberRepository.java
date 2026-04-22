package hei.school.tdfinal.repository;

import hei.school.tdfinal.entity.OccupationEnum;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CollectivityMemberRepository {
    private final Connection connection;

    public CollectivityMemberRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(String collectivityId, String memberId, OccupationEnum occupation) {

        String sql = """
            INSERT INTO collectivity_member (collectivity_id, member_id, occupation)
            VALUES (?, ?, ?::occupation_type_enum)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, collectivityId);
            ps.setString(2, memberId);
            ps.setString(3, occupation.name());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findMemberIdsByCollectivityAndOccupation(String collectivityId, OccupationEnum occupation) {
        String sql = """
        SELECT member_id
        FROM collectivity_member
        WHERE collectivity_id = ?
        AND occupation = ?::occupation_type_enum
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, collectivityId);
            ps.setString(2, occupation.name());

            ResultSet rs = ps.executeQuery();

            List<String> ids = new ArrayList<>();

            while (rs.next()) {
                ids.add(rs.getString("member_id"));
            }

            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<OccupationEnum, String> findStructure(String collectivityId) {

        String sql = """
            SELECT member_id, occupation
            FROM collectivity_member
            WHERE collectivity_id = ?
            AND occupation IN ('PRESIDENT', 'VICE_PRESIDENT', 'TREASURER', 'SECRETARY')
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, collectivityId);
            ResultSet rs = ps.executeQuery();

            Map<OccupationEnum, String> structure = new HashMap<>();
            while (rs.next()) {
                OccupationEnum occupation =
                        OccupationEnum.valueOf(rs.getString("occupation"));

                String memberId = rs.getString("member_id");

                structure.put(occupation, memberId);
            }

            return structure;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
