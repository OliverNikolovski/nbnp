alter table request
    add column description text;

alter table semester
    add column active boolean default false;

update semester
set active = true
where id = (select id
            from semester
            order by id desc
            limit 1);
