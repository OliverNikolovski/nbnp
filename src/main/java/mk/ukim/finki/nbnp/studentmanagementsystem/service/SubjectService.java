package mk.ukim.finki.nbnp.studentmanagementsystem.service;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.Subject;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.request.EnrollSubjectsForStudentRequest;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.EnrolledSubjectRepository;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.SubjectRepository;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.jdbc.SubjectJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectJdbcRepository subjectJdbcRepository;
    private final EnrolledSubjectRepository enrolledSubjectRepository;

    public SubjectService(SubjectRepository subjectRepository, SubjectJdbcRepository subjectJdbcRepository, EnrolledSubjectRepository enrolledSubjectRepository) {
        this.subjectRepository = subjectRepository;
        this.subjectJdbcRepository = subjectJdbcRepository;
        this.enrolledSubjectRepository = enrolledSubjectRepository;
    }

    public void enrollStudentInSubject(Long studentId, Long semesterId, Long subjectId) {
        this.enrolledSubjectRepository.save(studentId, semesterId, subjectId);
    }

    public List<Subject> getSubjectsBySemesterType(String semesterType) {
        return subjectRepository.findBySemester(semesterType);
    }

    public void enrollSubjectsForStudent(EnrollSubjectsForStudentRequest request) {
        subjectJdbcRepository.enrollSubjectsForStudent(request.studentId, request.semesterId, request.subjectIds);
    }
}
