package mk.ukim.finki.nbnp.studentmanagementsystem.repository.jdbc;

import mk.ukim.finki.nbnp.studentmanagementsystem.factory.RowMapperFactory;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapperFactory rowMapperFactory;

    public UserJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate, RowMapperFactory rowMapperFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapperFactory = rowMapperFactory;
    }

    public Optional<UserPersonalInfoView> getPersonalInfoForUser(Long id) {
        RowMapper<UserPersonalInfoView> rowMapper = rowMapperFactory.getUserPersonalInfoViewRowMapper();
        String sql = "SELECT * FROM get_personal_info_for_user(:p_user_id)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("p_user_id", id);
        try {
            UserPersonalInfoView view = jdbcTemplate.queryForObject(sql, params, rowMapper);
            return Optional.ofNullable(view);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public List<EnrolledSemestersView> getSemestersForStudent(Long id) {
        RowMapper<EnrolledSemestersView> rowMapper = rowMapperFactory.getEnrolledSemestersViewRowMapper();
        String sql = "SELECT * FROM get_semesters_for_student(:p_student_id);";
        MapSqlParameterSource params = new MapSqlParameterSource("p_student_id", id);
        return jdbcTemplate.query(sql, params, rowMapper);
    }

    public List<EnrolledSubjectsView> getSubjectsInSemesterForStudent(Long studentId, Long semesterId) {
        RowMapper<EnrolledSubjectsView> rowMapper = rowMapperFactory.getEnrolledSubjectsViewRowMapper();
        String sql = "SELECT * FROM get_subjects_in_semester_for_student(:p_student_id, :p_semester_id);";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("p_student_id", studentId)
                .addValue("p_semester_id", semesterId);
        return jdbcTemplate.query(sql, params, rowMapper);
    }

    public List<ExamsView> getAllPassedExamsForStudent(Long studentId) {
        RowMapper<ExamsView> rowMapper = rowMapperFactory.getExamsViewRowMapper();
        String sql = "SELECT * FROM get_all_passed_exams_for_student(:studentId);";
        MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
        return jdbcTemplate.query(sql, params, rowMapper);
    }

    public List<RequestsView> getAllRequestsForStudent(Long studentId) {
        RowMapper<RequestsView> rowMapper = rowMapperFactory.getRequestViewRowMapper();
        String sql = "SELECT * FROM get_all_requests_for_student(:studentId);";
        MapSqlParameterSource params = new MapSqlParameterSource("studentId", studentId);
        return jdbcTemplate.query(sql, params, rowMapper);
    }

}
