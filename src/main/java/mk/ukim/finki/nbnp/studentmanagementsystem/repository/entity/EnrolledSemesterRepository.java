package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.EnrolledSemester;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.EnrolledSemesterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolledSemesterRepository extends JpaRepository<EnrolledSemester, EnrolledSemesterId> {
    @Query(nativeQuery = true,
            value = "CALL enroll_student_in_semester(:p_student_id, :p_semester_id, :p_semester_status_id);")
    void save(@Param("p_student_id") Long studentId,
                @Param("p_semester_id") Long semesterId,
                @Param("p_semester_status_id") Long semesterStatusId);
}