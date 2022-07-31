-- setup
use field_agent_test;
set sql_safe_updates = 0;
call set_known_good_state();
set sql_safe_updates = 1;
-- 

select * 
from agency_agent;



-- delete aa from agency_agent aa
-- left join security_clearance sc on sc.security_clearance_id = aa.security_clearance_id
-- where sc.security_clearance_id is null;

set sql_safe_updates = 0;

delete sc from security_clearance sc 
left join agency_agent aa on aa.security_clearance_id = sc.security_clearance_id 
where aa.security_clearance_id is null and sc.security_clearance_id = 1;
-- https://stackoverflow.com/questions/32809725/delete-from-sql-table-where-id-is-found-from-conditions-applied-to-another-table

set sql_safe_updates = 1;


-- DELETE 
-- FROM table2
-- WHERE  ID IN (SELECT t1.subid
--               FROM   table1 t1
--               WHERE  t1.name = 'name1') 

-- DELETE FROM security_clearance sc
-- WHERE  sc.security_clearance_id NOT IN (SELECT aa.security_clearance_id
--               FROM   agency_agent aa
--               WHERE  aa.security_clearance_id = 2 );


-- DELETE FROM agency_agent aa 
-- WHERE aa.security_clearance_id = 2;

-- DELETE FROM security_clearance sc 
-- WHERE sc.security_clearance_id = 1 NOT IN (SELECT distinct aa.security_clearance_id FROM agency_agent aa );


-- ===========================Alias
-- data

insert into alias values
	(1, 'Hazel C Sauven', 'Mr. Potato', 1),
    (2, 'Claudian C O\'Lynn', 'Mrs. Potato', 2);
select * from alias;