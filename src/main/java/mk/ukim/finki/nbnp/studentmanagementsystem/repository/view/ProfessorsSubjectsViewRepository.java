package mk.ukim.finki.nbnp.studentmanagementsystem.repository.view;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.ProfessorsSubjectsView;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.ProfessorsSubjectsViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorsSubjectsViewRepository extends JpaRepository<ProfessorsSubjectsView, ProfessorsSubjectsViewId> {
    @Query(nativeQuery = true, value = "SELECT get_subjects_for_professor(:p_professor_id);")
    List<ProfessorsSubjectsView> getAllByProfessorId(@Param("p_professor_id") Long professorId);
}