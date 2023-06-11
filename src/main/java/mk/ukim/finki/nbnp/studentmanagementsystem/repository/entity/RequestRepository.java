package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(nativeQuery = true, value = "SELECT create_request_for_student(:p_student_id, :p_request_type_id)")
    Request save(@Param("p_student_id") Long studentId,
                   @Param("p_request_type_id") Long requestTypeId);
}