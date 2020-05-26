SELECT * FROM assemblies.agenda;
SELECT * FROM assemblies.agenda WHERE created_at  <= NOW() - INTERVAL 1 MINUTE;

/*DELETE FROM  assemblies.agenda;*/

SELECT DATE_FORMAT(create_at, '%m/%d/%Y %H:%i') as data_formatada FROM assemblies.agenda ;

SELECT DATE_FORMAT(create_at, '%d/%m/%Y') FROM assemblies.agenda;