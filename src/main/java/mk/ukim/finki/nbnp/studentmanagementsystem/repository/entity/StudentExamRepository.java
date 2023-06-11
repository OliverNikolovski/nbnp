package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {
    @Query(nativeQuery = true, value = "CALL student_exam_insert(:p_student_id, :p_exam_id, :p_grade);")
    void save(@Param("p_student_id") Long studentId,
              @Param("p_exam_id") Long examId,
              @Param("p_grade") Integer grade);
}