-- site semestri vo koisto e zapisan studentot
create or replace function get_semesters_for_student(p_student_id bigint)
    returns setof enrolled_semesters_view as
$$
begin
    return query
        select *
        from enrolled_semesters_view
        where student_id = p_student_id
        order by semester_id desc;
end;
$$ language plpgsql;


create or replace function get_subjects_in_semester_for_student(p_student_id bigint, p_semester_id bigint)
    returns setof enrolled_subjects_view as
$$
begin
    return query
        select distinct on (subject_id) *
        from enrolled_subjects_view
        where student_id = p_student_id
          and semester_id = p_semester_id;
end;
$$ language plpgsql;

create or replace function get_all_passed_exams_for_student(p_student_id bigint)
    returns setof exams_view as
$$
begin
    return query
        select distinct on (subject_id, date) *
        from exams_view
        where student_id = p_student_id
          and grade > 5;
end;
$$ language plpgsql;

-- site predmeti sto gi ima zapisano studentot vo odreden vremenski interval
create or replace function get_subjects_for_student(p_student_id bigint, p_semester_start_date date default null,
                                                    p_semester_end_date date default null)
    returns setof enrolled_subjects_view as
$$
begin
    -- ako start_date i end_date se dvete null, togash filtriraj samo po student_id
    if p_semester_start_date is null and p_semester_end_date is null then
        return query
            select *
            from enrolled_subjects_view
            where student_id = p_student_id;
    elsif p_semester_start_date is null then -- ako samo start_date e null, filtriraj po student_id i end_date
        return query
            select *
            from enrolled_subjects_view
            where student_id = p_student_id
              and semester_end_date <= p_semester_end_date;
    elsif p_semester_end_date is null then -- ako samo end_date e null, filtriraj po student_id i start_date
        return query
            select *
            from enrolled_subjects_view
            where student_id = p_student_id
              and semester_start_date >= p_semester_start_date;
    else -- filtriraj po site parametri
        return query
            select *
            from enrolled_subjects_view
            where student_id = p_student_id
              and semester_start_date >= p_semester_start_date
              and semester_end_date <= p_semester_end_date;
    end if;
end;
$$ language plpgsql;

-- licni informacii za korisnik
create or replace function get_personal_info_for_user(p_user_id bigint)
    returns setof user_personal_info_view as
$$
begin
    return query
        select *
        from user_personal_info_view
        where id = p_user_id;
end;
$$ language plpgsql;

create or replace function get_all_requests_for_student(p_student_id bigint)
    returns setof request_request_type as
$$
begin
    return query
        select *
        from request_request_type
        where user_id = p_student_id
        order by id desc;
end;
$$ language plpgsql;


-- kreira konkreten tip na baranje od student
create or replace procedure create_request_for_student(p_student_id bigint, p_request_type_id bigint, p_description text)
    language plpgsql
as
$$
begin
    insert into request (user_id, request_type_id, request_date, status, description)
    values (p_student_id, p_request_type_id, current_timestamp, 'PENDING', p_description);
end;
$$;

-- site predmeti vo odredena ispitna sesija podredeni po datum
create or replace function get_exams_in_session_ordered_by_date(p_exam_session_id bigint)
    returns setof exam as
$$
begin
    return query
        select e.*,
               s.name       as subject_name,
               p.first_name as professor_first_name,
               p.last_name  as professor_last_name
        from exam e
                 join subject s on s.id = exam.subject_id
                 join professors_view p on p.id = exam.professor_id
        where e.exam_session_id = p_exam_session_id
        order by e.exam_date;
end;
$$ language plpgsql;

-- go postavuva prosekot na student
create or replace function set_average_grade_for_student()
    returns trigger as
$$
declare
    v_average    double precision;
    v_student_id int;
begin
    if (tg_op = 'INSERT' or tg_op = 'UPDATE') then
        v_student_id = new.student_id;
    elsif (tg_op = 'DELETE') then
        v_student_id = old.student_id;
    end if;

    select avg(grade)
    into v_average
    from student_exam
    where student_id = v_student_id;

    update users
    set average_grade = v_average
    where id = v_student_id;

    return null;
end;
$$ language plpgsql;

-- triger za presmetuvanje na prosekot pri dodavanje/menuvanje/brisenje na ispit za student
create or replace trigger update_average_grade_for_student
    after insert or update or delete
    on student_exam
    for each row
execute function set_average_grade_for_student();

-- go postavuva brojot na osvoeni krediti na student
create or replace function set_total_credits_for_student()
    returns trigger as
$$
declare
    v_total_credits int;
    v_student_id    int;
begin
    if (tg_op = 'INSERT' or tg_op = 'UPDATE') then
        v_student_id = new.student_id;
    elsif (tg_op = 'DELETE') then
        v_student_id = old.student_id;
    end if;

    select sum(s.credits)
    into v_total_credits
    from student_exam se
             join exam e on se.exam_id = e.id
             join subject s on e.subject_id = s.id
    where student_id = v_student_id
      and se.grade > 5;

    update users
    set total_credits = v_total_credits
    where id = v_student_id;

    return null;
end;
$$ language plpgsql;

-- triger za presmetuvanje na brojot na krediti pri dodavanje/menuvanje/brisenje na ispit za student
create or replace trigger update_total_credits_for_student
    after insert or update or delete
    on student_exam
    for each row
execute function set_total_credits_for_student();

-- proverka dali ispitot e vo ramki na ispitnata sesija
create or replace function check_exam_validity()
    returns trigger as
$$
declare
    v_session_start_date date;
    v_session_end_date   date;
begin
    select start_date, end_date
    into v_session_start_date, v_session_end_date
    from exam_session
    where id = new.exam_session_id;

    if new.exam_date not between v_session_start_date and v_session_end_date then
        raise exception 'Exam cannot take place outside of exam session dates.';
    end if;

    return new;
end;
$$ language plpgsql;

-- triger za proverka na validnosta na ispit
create or replace trigger exam_validity
    before insert or update
    on exam
    for each row
execute function check_exam_validity();

-- site predmeti sto gi predava profesorot
create or replace function get_subjects_for_professor(p_professor_id bigint)
    returns setof professors_subjects_view as
$$
begin
    if not exists(select *
                  from professors_view
                  where id = p_professor_id) then
        raise exception 'The user is not a professor.';
    end if;

    return query
        select *
        from professors_subjects_view
        where professor_id = p_professor_id;
end;
$$ language plpgsql;

-- dali studentot e redoven
create or replace function is_regular_student(p_student_id bigint)
    returns boolean as
$$
declare
    v_regular_status_id int;
begin
    select id
    into v_regular_status_id
    from semester_status
    where status = 'REDOVEN';

    if exists(select *
              from enrolled_semester es
                       join semester s on es.semester_id = s.id
              where es.student_id = p_student_id
                and current_date between s.start_date and s.end_date
                and es.semester_status_id = v_regular_status_id) then
        return true;
    end if;

    return false;
end
$$ language plpgsql;

-- vnesuvanje na detali specificni za student
create or replace procedure insert_student_details(p_user_id bigint)
    language plpgsql
as
$$
begin
    update users u
    set year_of_enrollment = extract(year from current_date)::int
    where u.id = p_user_id;

    update users u
    set indeks = year_of_enrollment % 100 || to_char(u.id, 'FM0000000')
    where u.id = p_user_id;
end;
$$;

-- vnesuvanje na korisnik
create or replace procedure insert_person_user(p_first_name varchar, p_last_name varchar, p_birthdate date,
                                               p_embg varchar, p_gender varchar,
                                               p_city_id bigint, p_country_id bigint, p_nationality_id bigint,
                                               p_address varchar(255),
                                               p_phone_number varchar(255), p_personal_email varchar(255),
                                               p_password varchar, p_resume varchar, p_role varchar)
    language plpgsql
as
$$
declare
    v_person_id     bigint;
    v_user_id       bigint;
    v_faculty_email varchar(255);
    v_role_id       bigint;
begin
    insert into person(address, phone_number, personal_email, first_name, last_name, birth_date, embg, gender, city_id,
                       country_id, nationality_id)
    values (p_address, p_phone_number, p_personal_email, p_first_name, p_last_name, p_birthdate, p_embg, p_gender,
            p_city_id, p_country_id, p_nationality_id);

    select id
    into v_person_id
    from person
    where embg = p_embg;

    select lower(p_first_name) || '.' || lower(p_last_name) || '.' || p_embg || '@finki.ukim.mk'
    into v_faculty_email;

    insert into users(person_id, password, resume, faculty_email)
    values (v_person_id, p_password, p_resume, v_faculty_email);

    select id
    into v_user_id
    from users
    where person_id = v_person_id;

    if p_role = 'STUDENT' then
        call insert_student_details(v_user_id);
    end if;

    select id
    into v_role_id
    from role
    where name = p_role;

    insert into user_role(user_id, role_id)
    values (v_user_id, v_role_id);
end;
$$;

-- insertiranje na ispit za student
create or replace procedure student_exam_insert(p_student_id bigint, p_exam_id bigint, p_grade int, p_prof_id bigint)
    language plpgsql
as
$$
declare
    v_subject_id bigint;
    v_prof_id    bigint;
begin
    select subject_id
    into v_subject_id
    from exam
    where id = p_exam_id;

    select professor_id
    into v_prof_id
    from enrolled_subject
    where student_id = p_student_id
      and subject_id = v_subject_id;

    if v_prof_id <> p_prof_id then
        raise exception 'Not authorized';
    end if;

    insert into student_exam(student_id, exam_id, grade)
    values (p_student_id, p_exam_id, p_grade);
    commit;
end;
$$;

-- vnesuvanje na semestar
create or replace procedure insert_semester(p_type varchar, p_start_date date, p_end_date date)
    language plpgsql
as
$$
begin
    insert into semester(type, start_date, end_date)
    values (p_type, p_start_date, p_end_date);
end;
$$;

-- zapisuvanje na student vo semestar
create or replace procedure enroll_student_in_semester(p_student_id bigint, p_semester_id bigint,
                                                       p_semester_status_id bigint)
    language plpgsql
as
$$
begin
    insert into enrolled_semester(student_id, semester_id, semester_status_id)
    values (p_student_id, p_semester_id, p_semester_status_id);
end;
$$;



-- zapisuvanje na predmet od strana na student
create or replace procedure enroll_student_in_subject(p_student_id bigint, p_semester_id bigint, p_subject_id bigint)
    language plpgsql
as
$$
declare
    v_professor_id bigint;
begin
    if not exists(select *
                  from enrolled_semester
                  where student_id = p_student_id
                    and semester_id = p_semester_id) then
        raise exception 'Student must be enrolled in semester before being enrolled in subjects.';
    end if;

    select professor_id
    into v_professor_id
    from professor_subject
    where subject_id = p_subject_id
    order by random()
    limit 1;

    insert into enrolled_subject(student_id, semester_id, subject_id, professor_id)
    values (p_student_id, p_semester_id, p_subject_id, v_professor_id);
end;
$$;

create or replace procedure enroll_student_in_subjects(
    p_student_id bigint,
    p_semester_id bigint,
    p_subject_ids bigint[]
)
    language plpgsql
as
$$
begin
    if array_length(p_subject_ids, 1) > 5 then
        raise exception 'You cannot enroll more than 5 subjects.';
    end if;

    call enroll_student_in_subject(p_student_id, p_semester_id, p_subject_ids[1]);
    call enroll_student_in_subject(p_student_id, p_semester_id, p_subject_ids[2]);
    call enroll_student_in_subject(p_student_id, p_semester_id, p_subject_ids[3]);
    call enroll_student_in_subject(p_student_id, p_semester_id, p_subject_ids[4]);
    call enroll_student_in_subject(p_student_id, p_semester_id, p_subject_ids[5]);
end;
$$;
