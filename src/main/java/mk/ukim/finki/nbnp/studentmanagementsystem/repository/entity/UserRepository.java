package mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT is_regular_student(:p_student_id);")
    boolean isRegularStudent(@Param("p_student_id") Long userId);

    @Query(nativeQuery = true,
            value = "SELECT insert_person_user(:p_first_name, :p_last_name, :p_birthdate, :p_embg, :p_gender, :p_city_id, :p_country_id, :p_nationality_id, :p_address, :p_phone_number, :p_personal_email, :p_password, :p_resume, :p_role);")
    void save(@Param("p_first_name") String firstName,
                @Param("p_last_name") String lastName,
                @Param("p_birthdate") LocalDate birthDate,
                @Param("p_embg") String embg,
                @Param("p_gender") String gender,
                @Param("p_city_id") Long cityId,
                @Param("p_country_id") Long countryId,
                @Param("p_nationality_id") Long nationalityId,
                @Param("p_address") String address,
                @Param("p_phone_number") String phoneNumber,
                @Param("p_personal_email") String personalEmail,
                @Param("p_password") String password,
                @Param("p_resume") String resume,
                @Param("p_role") String role);
}