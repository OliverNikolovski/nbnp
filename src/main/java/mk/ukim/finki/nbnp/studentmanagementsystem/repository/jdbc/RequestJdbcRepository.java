package mk.ukim.finki.nbnp.studentmanagementsystem.repository.jdbc;

import mk.ukim.finki.nbnp.studentmanagementsystem.factory.RowMapperFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapperFactory rowMapperFactory;

    public RequestJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate, RowMapperFactory rowMapperFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapperFactory = rowMapperFactory;
    }

    public void createRequestForStudent(Long studentId, Long requestTypeId, String description) {
        String sql = "call create_request_for_student(:studentId, :requestTypeId, :description)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", studentId);
        params.addValue("requestTypeId", requestTypeId);
        params.addValue("description", description);
        jdbcTemplate.update(sql, params);
    }
}
