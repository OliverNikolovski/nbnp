package mk.ukim.finki.nbnp.studentmanagementsystem.repository.view;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.view.UserPersonalInfoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserPersonalInfoViewRepository extends JpaRepository<UserPersonalInfoView, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM get_personal_info_for_user(:p_user_id);")
    Optional<UserPersonalInfoView> getPersonalInfoById(@Param("p_user_id") @NonNull Long userId);
}