package mk.ukim.finki.nbnp.studentmanagementsystem.repository.view;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSemestersView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.EnrolledSemestersViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrolledSemestersViewRepository extends JpaRepository<EnrolledSemestersView, EnrolledSemestersViewId> {
    @Query(nativeQuery = true, value = "SELECT get_semesters_for_student(:p_student_id);")
    List<EnrolledSemestersView> getAllByStudentId(@Param("p_student_id") Long studentId);
}