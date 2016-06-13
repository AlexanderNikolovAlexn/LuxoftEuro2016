package com.samodeika.dao;

import com.samodeika.entity.Player;
import com.samodeika.utils.DateUtils;

import java.sql.*;
import java.util.List;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class PlayerDaoImpl implements PlayerDao{

    private final String insertStmt = "INSERT INTO euro2016(name, bio, is_photo, special_player, position, player_number," +
            "caps, goals_scored, club, league, birthdate, match_rating1, match_rating2, match_rating3) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String truncateTbl = "TRUNCATE TABLE euro2016";

    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/alex", "root", "root");
    }

    public void truncateTable() {
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(truncateTbl, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean savePlayer(Player player) {
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, player.getName());
            ps.setString(2, player.getBio());
            ps.setInt(3, (player.isPhoto() ? 1 : 0));
            ps.setString(4, player.getSpecialPlayer());
            ps.setString(5, player.getPosition());
            ps.setInt(6, player.getNumber());
            ps.setInt(7, player.getCaps());
            ps.setInt(8, player.getGoalsScored());
            ps.setString(9, player.getClub());
            ps.setString(10, player.getLeague());
            ps.setDate(11, DateUtils.convertJavaDateToSqlDate(player.getBirthDate()));
            ps.setDouble(12, player.getMatchRating1());
            ps.setDouble(13, player.getMatchRating2());
            ps.setDouble(14, player.getMatchRating3());
            ps.execute();
            /*
            //In case we want to take auto generated value

            ResultSet rs = ps.getGeneratedKeys();
            Long key = null;
            if(rs != null && rs.next()) {
                key = rs.getLong(1);
            }
            */
            return true;
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean savePlayers(List<Player> players) {
        try (Connection c = getConnection()) {
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
            for (Player player : players) {
                ps.setString(1, player.getName());
                ps.setString(2, player.getBio());
                ps.setInt(3, (player.isPhoto() ? 1 : 0));
                ps.setString(4, player.getSpecialPlayer());
                ps.setString(5, player.getPosition());
                ps.setInt(6, player.getNumber());
                ps.setInt(7, player.getCaps());
                ps.setInt(8, player.getGoalsScored());
                ps.setString(9, player.getClub());
                ps.setString(10, player.getLeague());
                ps.setDate(11, DateUtils.convertJavaDateToSqlDate(player.getBirthDate()));
                ps.setDouble(12, player.getMatchRating1());
                ps.setDouble(13, player.getMatchRating2());
                ps.setDouble(14, player.getMatchRating3());
                ps.addBatch();
            }
            ps.executeBatch();
            c.commit();
            return true;
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
