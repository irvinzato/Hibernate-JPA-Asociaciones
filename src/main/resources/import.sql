INSERT INTO clientes (id, nombre, apellido, forma_pago, creado_en, editado_en) VALUES (1,'Mauricio','Rivera','debito',NULL,NULL),(2,'Angeles','Lopez','credito',NULL,NULL),(3,'Jade','Aketza','paypal',NULL,NULL),(5,'Nombre modificado2','Apellido modificado','paypal',NULL,NULL),(6,'Mauricio','Rivera','debito',NULL,NULL),(7,'Marcos','Gomez','credito',NULL,NULL),(8,'Mauro','Rosales','paypal',NULL,NULL),(9,'Adan','Paraiso','debito',NULL,NULL),(10,'Lubu','Sanz','paypal',NULL,NULL),(11,'Luci','Perez','paypal',NULL,NULL),(12,'Luna','Guzman','credito',NULL,NULL),(13,'Irving','Banderley','paypal',NULL,NULL),(14,'Raiden','Acaros','paypal','2022-09-06 23:35:24','2022-09-06 23:38:50'),(15,'Sasaki','Coyiro','debito','2022-09-06 23:52:39','2022-09-06 23:55:03');
INSERT INTO alumnos (id, nombre, apellido) VALUES (1,"Joana","Dimas"),(2,"Laura","Cervantes");
INSERT INTO cursos (id, titulo, profesor) VALUES (1,"Docker","Ruben"),(2,"FlexBox","Dalto"),(3,"React","Teresa");
INSERT INTO direcciones (calle, numero) VALUES ("Humores", 135),("Juarez", 23);
INSERT INTO clientes_direcciones (id_cliente, id_direccion) VALUES (1,1),(1,2);
INSERT INTO clientes_detalles (id, prime, puntos_acumulados, id_cliente_detalle) VALUES (1, 1, 5000, 1);