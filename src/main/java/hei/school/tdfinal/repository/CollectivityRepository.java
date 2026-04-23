package hei.school.tdfinal.repository;

import hei.school.tdfinal.entity.*;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class CollectivityRepository {
    Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    public String insert(String location, Boolean federationApproval) {
        String sql = """
            INSERT INTO collectivities (id, location, federation_approval)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            String id = UUID.randomUUID().toString();

            ps.setString(1, id);
            ps.setString(2, location);
            ps.setBoolean(3, federationApproval);

            ps.executeUpdate();

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collectivity findById(String id) {
        String sql = """
            SELECT id, name, number, location, federation_approval
            FROM collectivities
            WHERE id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Collectivity collectivity = new Collectivity();
                collectivity.setId(rs.getString("id"));
                collectivity.setLocation(rs.getString("location"));
                collectivity.setFederation_approval(rs.getBoolean("federation_approval"));

                return collectivity;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByLocationAndStructure(
            String location,
            String presidentId,
            String viceId,
            String treasurerId,
            String secretaryId
    ) {
        String sql = """
        SELECT 1
        FROM collectivities c
        JOIN collectivity_member cm1 ON cm1.collectivity_id = c.id
        JOIN collectivity_member cm2 ON cm2.collectivity_id = c.id
        JOIN collectivity_member cm3 ON cm3.collectivity_id = c.id
        JOIN collectivity_member cm4 ON cm4.collectivity_id = c.id
        WHERE c.location = ?
          AND cm1.member_id = ?
          AND cm1.occupation = 'PRESIDENT'
          AND cm2.member_id = ?
          AND cm2.occupation = 'VICE_PRESIDENT'
          AND cm3.member_id = ?
          AND cm3.occupation = 'TREASURER'
          AND cm4.member_id = ?
          AND cm4.occupation = 'SECRETARY'
        LIMIT 1
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, location);
            ps.setString(2, presidentId);
            ps.setString(3, viceId);
            ps.setString(4, treasurerId);
            ps.setString(5, secretaryId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
