package mk.ukim.finki.nbnp.studentmanagementsystem.factory;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RowMapperFactory {

    public RowMapper<UserPersonalInfoView> getUserPersonalInfoViewRowMapper() {
        // rs - resultSet (objektot od baza)
        // rowNum - redniot broj na redot vo tabelata vo baza
        return (rs, rowNum) -> {
            UserPersonalInfoView view = new UserPersonalInfoView();
            view.setId(rs.getLong("id"));
            view.setFirstName(rs.getString("first_name"));
            view.setMiddleName(rs.getString("middle_name"));
            view.setLastName(rs.getString("last_name"));
            view.setEmbg(rs.getString("embg"));
            view.setCity(rs.getString("city"));
            view.setCountry(rs.getString("country"));
            view.setNationality(rs.getString("nationality"));
            view.setAddress(rs.getString("address"));
            view.setGender(rs.getString("gender"));
            view.setPhoneNumber(rs.getString("phone_number"));
            view.setPersonalEmail(rs.getString("personal_email"));
            view.setBirthDate(rs.getDate("birth_date").toLocalDate());
            view.setFacultyEmail(rs.getString("faculty_email"));
            view.setIndeks(rs.getString("indeks"));
            view.setResume(rs.getString("resume"));
            view.setYearOfEnrollment(rs.getInt("year_of_enrollment"));
            view.setAverageGrade(rs.getDouble("average_grade"));
            view.setTotalCredits(rs.getInt("total_credits"));
            return view;
        };
    }

    public RowMapper<EnrolledSemestersView> getEnrolledSemestersViewRowMapper() {
        return (rs, rowNum) -> {
            EnrolledSemestersView view = new EnrolledSemestersView();
            EnrolledSemestersViewId id = new EnrolledSemestersViewId();
            id.setStudentId(rs.getLong("student_id"));
            id.setSemesterId(rs.getLong("semester_id"));
            view.setId(id);
            view.setType(rs.getString("type"));
            view.setStatus(rs.getString("status"));
            view.setStartDate(rs.getDate("start_date").toLocalDate());
            view.setEndDate(rs.getDate("end_date").toLocalDate());
            return view;
        };
    }

    public RowMapper<EnrolledSubjectsView> getEnrolledSubjectsViewRowMapper() {
        return (rs, rowNum) -> {
            EnrolledSubjectsView view = new EnrolledSubjectsView();
            EnrolledSubjectsViewId id = new EnrolledSubjectsViewId();
            id.setStudentId(rs.getLong("student_id"));
            id.setSubjectId(rs.getLong("subject_id"));
            id.setSemesterId(rs.getLong("semester_id"));
            view.setId(id);
            view.setProfessorId(rs.getLong("professor_id"));
            view.setCode(rs.getString("code"));
            view.setCredits(rs.getInt("credits"));
            view.setSubject(rs.getString("subject"));
            view.setSemesterNumber(rs.getInt("semester_number"));
            view.setProgram(rs.getString("program"));
            view.setStudyType(rs.getString("study_type"));
            view.setProfFirstName(rs.getString("prof_first_name"));
            view.setProfLastName(rs.getString("prof_last_name"));
            view.setSemesterType(rs.getString("semester_type"));
            view.setSemesterStartDate(rs.getDate("semester_start_date").toLocalDate());
            view.setSemesterEndDate(rs.getDate("semester_end_date").toLocalDate());
            return view;
        };
    }

    public RowMapper<ExamsView> getExamsViewRowMapper() {
        return (rs, rowNum) -> {
            ExamsView view = new ExamsView();
            view.setExamId(rs.getLong("exam_id"));
            view.setStudentId(rs.getLong("student_id"));
            view.setStudentIndeks(rs.getString("student_indeks"));
            view.setCode(rs.getString("code"));
            view.setSubject(rs.getString("subject"));
            view.setSubjectId(rs.getLong("subject_id"));
            view.setSession(rs.getString("session"));
            view.setDate(rs.getDate("date").toLocalDate());
            view.setSemester(rs.getInt("semester"));
            view.setCredits(rs.getInt("credits"));
            view.setWinterOrSummer(rs.getString("winter_summer"));
            view.setGrade(rs.getInt("grade"));
            view.setProfessorId(rs.getLong("professor_id"));
            view.setProfFirstName(rs.getString("prof_first_name"));
            view.setProfLastName(rs.getString("prof_last_name"));
            return view;
        };
    }

    public RowMapper<RequestsView> getRequestViewRowMapper() {
        return (rs, rowNum) -> {
            RequestsView view = new RequestsView();
            view.setId(rs.getLong("id"));
            view.setUserId(rs.getLong("user_id"));
            view.setRequestTypeId(rs.getLong("request_type_id"));
            view.setRequestDate(rs.getDate("request_date").toLocalDate());
            view.setStatus(rs.getString("status"));
            view.setName(rs.getString("name"));
            return view;
        };
    }

}
