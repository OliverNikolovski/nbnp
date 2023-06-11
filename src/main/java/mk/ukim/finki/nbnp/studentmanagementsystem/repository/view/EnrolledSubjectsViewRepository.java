package mk.ukim.finki.nbnp.studentmanagementsystem.repository.view;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSubjectsView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSubjectsViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EnrolledSubjectsViewRepository extends JpaRepository<EnrolledSubjectsView, EnrolledSubjectsViewId> {
    @Query(nativeQuery = true,
            value = "SELECT get_subjects_for_student(:p_student_id, :p_semester_start_date, :p_semester_end_date);")
    List<EnrolledSubjectsView> getAllByStudentId(@Param("p_student_id") Long studentId,
                                                 @Param("p_semester_start_date") LocalDate semesterStartDate,
                                                 @Param("p_semester_end_date") LocalDate semesterEndDate);
}