INSERT INTO bluebank.cliente (cpf,data_nascimento,email,endereco,nome,rg,senha,telefone) VALUES
	 ('40680153098','1985-11-17 00:00:00','alana@gmail.com','Maceio','Alana Amaral','483761126','2294','3488-4483'),
	 ('46466174042','1978-04-03 00:00:00','vinicius@gmail.com','Joao Pessoa','Vinicius Silva','408112396','5276','29284845'),
	 ('60799848069','1988-02-05 00:00:00','carla@gmail.com','Maceio','Carla Braga','409810721','8632','2638-9359'),
	 ('64973227013','2001-10-12 00:00:00','Pedro@gmail.com','Rio de Janeiro','Pedro Araujo','178377727','1995','23132550'),
	 ('84214772008','1996-07-20 00:00:00','leticia@gmail.com','Sao paulo','Leticia Souza','363104604','3790','32208108');

INSERT INTO bluebank.conta (numero_conta,agencia,saldo,cliente_cpf) VALUES
	 ('0779408491','0001',0.00,'40680153098'),
	 ('1224422408','0001',0.00,'46466174042'),
	 ('2559090598','0001',0.00,'84214772008'),
	 ('6165203858','0001',0.00,'60799848069'),
	 ('8540667195','0001',0.00,'64973227013');