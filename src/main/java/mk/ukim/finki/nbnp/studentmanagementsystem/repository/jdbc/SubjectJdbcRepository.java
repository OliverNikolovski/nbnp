package mk.ukim.finki.nbnp.studentmanagementsystem.repository.jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubjectJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SubjectJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void enrollSubjectsForStudent(Long studentId, Long semesterId, List<Long> subjectIds) {
        String subjectIdsString = subjectIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",", "{", "}"));
        String sql = "call enroll_student_in_subjects(:studentId, :semesterId, :subjectIds::bigint[])";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId)
                .addValue("semesterId", semesterId)
                .addValue("subjectIds", subjectIdsString);
        jdbcTemplate.update(sql, params);
    }
}
