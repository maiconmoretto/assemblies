SELECT * FROM assemblies.agenda;

SELECT 
*
 FROM assemblies.agenda
WHERE assemblies.agenda.created_at +  INTERVAL 420 SECOND >= NOW() 
--                        2020-05-29 12:20:00 =>  2020-05-29 12:14:00
and assemblies.agenda.id = 1;

SELECT * FROM assemblies.agenda a WHERE a.id = 1 and a.created_at + INTERVAL a.duration SECOND >= NOW();
SELECT * FROM assemblies.agenda a WHERE a.id = 1 and a.created_at + INTERVAL 6000 SECOND >= NOW();
/*DELETE FROM  assemblies.agenda;*/

SELECT DATE_FORMAT(create_at, '%m/%d/%Y %H:%i') as data_formatada FROM assemblies.agenda ;

SELECT DATE_FORMAT(create_at, '%d/%m/%Y') FROM assemblies.agenda;