select now() from dual;

insert into company (company_name,created_date,modified_date) values('배달의민족', now(), now());
insert into company (company_name,created_date,modified_date) values('카카오', now(), now());

insert into job_posting (company_id,compensation,created_date,job_detail,job_position,modified_date,technologies_used)
values (1, 100000, now(), '배달의 민족에서 백엔드 주니어 개발자를 채용', '백엔드 개발자', now(), 'Python');
insert into job_posting (company_id,compensation,created_date,job_detail,job_position,modified_date,technologies_used)
values (1, 200000, now(), '배달의 민족에서 백엔드 주니어 개발자를 채용2', '백엔드 개발자2', now(), 'Java');
insert into job_posting (company_id,compensation,created_date,job_detail,job_position,modified_date,technologies_used)
values (1, 300000, now(), '배달의 민족에서 백엔드 주니어 개발자를 채용3', '백엔드 개발자3', now(), 'Javascript');



