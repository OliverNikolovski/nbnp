-- 1. приказ на сите студенти на факултетот (корисници со улога STUDENT)
-- vreme: 70ms
create view students_view as
select distinct on (u.id) u.id                 as id,
                          p.first_name         as first_name,
                          p.middle_name        as middle_name,
                          p.last_name          as last_name,
                          p.embg               as embg,
                          p.birth_date         as birth_date,
                          p.gender             as gender,
                          p.address            as address,
                          p.personal_email     as email,
                          p.phone_number       as phone_number,
                          c.country            as country,
                          c2.city              as city,
                          n.nationality        as nationality,
                          u.year_of_enrollment as year_of_enrollment,
                          u.indeks             as indeks,
                          u.faculty_email      as faculty_email
from users u
         join user_role ur on u.id = ur.user_id
         join role r on ur.role_id = r.id
         join person p on u.person_id = p.id
         join country c on p.country_id = c.id
         join city c2 on p.city_id = c2.id
         join nationality n on p.nationality_id = n.id
where r.name = 'STUDENT';

-- 2. приказ на сите професори на факултетот (корисници со улога PROFESSOR)
-- vreme: 70 ms
create view professors_view as
select distinct on (u.id) u.id             as id,
                          p.first_name     as first_name,
                          p.middle_name    as middle_name,
                          p.last_name      as last_name,
                          p.embg           as embg,
                          p.birth_date     as birth_date,
                          p.gender         as gender,
                          p.address        as address,
                          p.personal_email as email,
                          p.phone_number   as phone_number,
                          c.country        as country,
                          c2.city          as city,
                          n.nationality    as nationality,
                          u.faculty_email  as faculty_email
from users u
         join user_role ur on u.id = ur.user_id
         join role r on ur.role_id = r.id
         join person p on u.person_id = p.id
         join country c on p.country_id = c.id
         join city c2 on p.city_id = c2.id
         join nationality n on p.nationality_id = n.id
where r.name = 'PROFESSOR';

-- 3. приказ на сите администратори на факултетот (корисници со улога ADMINISTRATOR)
-- vreme: 70 ms
create view administrators_view as
select distinct on (u.id) u.id             as id,
                          p.first_name     as first_name,
                          p.middle_name    as middle_name,
                          p.last_name      as last_name,
                          p.embg           as embg,
                          p.birth_date     as birth_date,
                          p.gender         as gender,
                          p.address        as address,
                          p.personal_email as email,
                          p.phone_number   as phone_number,
                          c.country        as country,
                          c2.city          as city,
                          n.nationality    as nationality,
                          u.faculty_email  as faculty_email
from users u
         join user_role ur on u.id = ur.user_id
         join role r on ur.role_id = r.id
         join person p on u.person_id = p.id
         join country c on p.country_id = c.id
         join city c2 on p.city_id = c2.id
         join nationality n on p.nationality_id = n.id
where r.name = 'ADMINISTRATOR';

-- 4. групирање на предметите според бројот на положени испити и подредување во опаѓачки редослед (9 sec)
-- vreme: 4 sec
create view subjects_by_times_passed_view as
select s.id as subject_id, s.name as subject_name, count(*) as number_of_passed_exams
from student_exam se
         join exam e on se.exam_id = e.id
         join subject s on e.subject_id = s.id
where se.grade > 5
group by 1, 2
order by 3 desc;

-- 5. приказ на сите студенти со нивната насока, тип на студии, година на започнување и завршување на студиите
-- 80 ms
-- optimizirano na okolu 100ms so trganje na students_view
create view students_program_study_type_view as
select u.id          as id,
       pe.first_name as ime,
       pe.last_name  as prezime,
       p.name        as nasoka,
       st.type       as tip_na_studii,
       sp.start_date as start_date,
       sp.end_date   as end_date,
       ss.finished   as finished
from users u
         join user_role ur on ur.user_id = u.id
         join person pe on pe.id = u.person_id
         join student_program sp on sp.user_id = u.id
         join program p on p.id = sp.program_id
         join student_studytype ss on u.id = ss.user_id
         join study_type st on ss.study_type_id = st.id
where ur.role_id = 1;

-- 6. приказ на сите професори заедно со соодветните предмети кои ги предаваат
-- vreme: 70 ms
create view professors_subjects_view as
select u.id                               as professor_id,
       p.first_name || ' ' || p.last_name as professor_name,
       s.id                               as subject_id,
       s.name                             as subject_name
from users u
         join professor_subject ps on u.id = ps.professor_id
         join subject s on ps.subject_id = s.id
         join person p on u.person_id = p.id
         join user_role ur on ur.user_id = u.id
         join role r on r.id = ur.role_id;

-- 7. приказ на сите предмети заедно со насоката и типот на студии во кои припаѓа соодветниот предмет
-- vreme: 60 ms
create view subjects_view as
select s.id              as predmet_id,
       s.name            as predmet,
       s.code            as kod,
       s.credits         as krediti,
       s.semester_number as broj_semestar,
       p.name            as nasoka,
       st.type           as tip_na_studii
from program p
         join program_subject ps on p.id = ps.program_id
         join study_type st on p.study_type_id = st.id
         join subject s on ps.subject_id = s.id;

-- 8. приказ на сите студенти со нивниот остварен просек, сортирани според просекот
-- 2s 500ms
create view students_average_grades_view as
select se.student_id                      as id,
       s.indeks                           as indeks,
       s.first_name || ' ' || s.last_name as ime_prezime,
       avg(se.grade)::numeric(4, 2)       as prosek
from student_exam se
         join students_view s on se.student_id = s.id
group by se.student_id, s.indeks, s.first_name, s.last_name
order by prosek desc, indeks;

-- 9. приказ на студентите со вкупниот број на освоени кредити по студент
-- vreme: 3 sec
create view students_credits_view as
select sv.id          as id,
       sv.indeks      as indeks,
       sv.first_name  as ime,
       sv.last_name   as prezime,
       sum(s.credits) as krediti
from subject s
         join exam e on s.id = e.subject_id
         join student_exam se on e.id = se.exam_id
         join students_view sv on se.student_id = sv.id
where se.grade > 5
group by sv.id, sv.indeks, sv.first_name, sv.last_name
order by krediti desc, indeks;

-- 10. приказ на бројот на студенти во секој тип на студии (додипломски, магистерски, докторски)
-- vreme: 200ms
create view grouped_by_study_type_view as
select st.id             as id,
       st.type           as tip_na_studii,
       count(ss.user_id) as broj_na_studenti
from study_type st
         left join student_studytype ss on st.id = ss.study_type_id
group by st.id, st.type
order by broj_na_studenti desc;

-- 11. сите испратени молби/барања
-- vreme: 70 ms
create view request_request_type as
select r.*, rt.name
from request r,
     request_type rt
where r.request_type_id = rt.id;

-- 12. сите испити
-- vreme: 2s 500ms
-- after index idx_student_exam_student_id: 80ms
create view exams_view as
select st.id             as student_id,
       st.indeks         as student_indeks,
       s.code            as code,
       s.name            as subject,
       s.id              as subject_id,
       es.session        as session,
       e.exam_date       as date,
       s.semester_number as semester,
       s.credits         as credits,
       s.semester        as winter_summer,
       se.grade          as grade,
       u.id              as professor_id,
       p.first_name      as prof_first_name,
       p.last_name       as prof_last_name,
       e.id              as exam_id
from exam e
         join exam_session es on es.id = e.exam_session_id
         join student_exam se on e.id = se.exam_id
         join users st on st.id = se.student_id
         join subject s on e.subject_id = s.id
         join users u on u.id = e.professor_id
         join person p on p.id = u.person_id;

-- 13. сите запишани семестри
-- vreme: 70ms
create view enrolled_semesters_view as
select s.id          as semester_id,
       s.type        as type,
       s.start_date  as start_date,
       s.end_date    as end_date,
       ss.status     as status,
       es.student_id as student_id
from semester s
         join enrolled_semester es on es.semester_id = s.id
         join semester_status ss on ss.id = es.semester_status_id;

-- 14. сите запишани предмети
-- vreme: 100ms
create view enrolled_subjects_view as
select es.student_id    as student_id,
       es.subject_id    as subject_id,
       es.professor_id  as professor_id,
       sem.id           as semester_id,
       sv.kod           as code,
       sv.krediti       as credits,
       sv.predmet       as subject,
       sv.broj_semestar as semester_number,
       sv.nasoka        as program,
       sv.tip_na_studii as study_type,
       p.first_name     as prof_first_name,
       p.last_name      as prof_last_name,
       sem.type         as semester_type,
       sem.start_date   as semester_start_date,
       sem.end_date     as semester_end_date
from enrolled_subject es
         join subjects_view sv on sv.predmet_id = es.subject_id
         join semester sem on sem.id = es.semester_id
         join users u on u.id = es.professor_id
         join person p on p.id = u.person_id;

-- 15. лични информации за корисници
create view user_personal_info_view as
select u.id                 as id,
       p.first_name         as first_name,
       p.middle_name        as middle_name,
       p.last_name          as last_name,
       p.embg               as embg,
       c.city               as city,
       co.country           as country,
       n.nationality        as nationality,
       p.address            as address,
       p.gender             as gender,
       p.phone_number       as phone_number,
       p.personal_email     as personal_email,
       p.birth_date         as birth_date,
       u.faculty_email      as faculty_email,
       u.indeks             as indeks,
       u.resume             as resume,
       u.year_of_enrollment as year_of_enrollment,
       u.average_grade      as average_grade,
       u.total_credits      as total_credits
from person p
         join users u on p.id = u.person_id
         join city c on p.city_id = c.id
         join country co on p.country_id = co.id
         join nationality n on p.nationality_id = n.id;

