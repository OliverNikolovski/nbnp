package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    @Query(nativeQuery = true, value = "CALL insert_semester(:p_type, :p_start_date, :p_end_date)")
    void save(@Param("p_type") String type,
              @Param("p_start_date") LocalDate startDate,
              @Param("p_end_date") LocalDate endDate);
}