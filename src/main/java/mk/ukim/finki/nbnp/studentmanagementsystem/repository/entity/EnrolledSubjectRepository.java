package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.EnrolledSubject;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.EnrolledSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EnrolledSubjectRepository extends JpaRepository<EnrolledSubject, EnrolledSubjectId> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true,
            value = "CALL enroll_student_in_subject(:p_student_id, :p_semester_id, :p_subject_id);")
    void save(@Param("p_student_id") Long studentId,
                @Param("p_semester_id") Long semesterId,
                @Param("p_subject_id") Long subjectId);
}