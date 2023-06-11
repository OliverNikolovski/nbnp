package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(nativeQuery = true, value = "SELECT get_exams_in_session_ordered_by_date(:p_exam_session_id);")
    List<Exam> getAllByExamSession(@Param("p_exam_session_id") Long examSessionId);
}