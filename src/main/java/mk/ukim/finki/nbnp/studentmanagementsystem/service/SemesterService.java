package mk.ukim.finki.nbnp.studentmanagementsystem.service;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Semester;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.EnrolledSemesterRepository;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final EnrolledSemesterRepository enrolledSemesterRepository;

    public SemesterService(SemesterRepository semesterRepository, EnrolledSemesterRepository enrolledSemesterRepository) {
        this.semesterRepository = semesterRepository;
        this.enrolledSemesterRepository = enrolledSemesterRepository;
    }

    public Optional<Semester> findActiveSemester() {
        return semesterRepository.findByActiveIsTrue();
    }

    public void enrollStudentInSemester(Long studentId, Long semesterId) {
        enrolledSemesterRepository.save(studentId, semesterId, 1L);
    }
}
