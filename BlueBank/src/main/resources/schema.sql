CREATE TABLE `cliente` (
  `cpf` varchar(11) NOT NULL,
  `data_nascimento` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `senha` varchar(4) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `conta` (
  `numero_conta` varchar(255) NOT NULL,
  `agencia` varchar(255) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `cliente_cpf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`numero_conta`),
  KEY `FK9gye6g3vk7w430iyrdynyogh` (`cliente_cpf`),
  CONSTRAINT `FK9gye6g3vk7w430iyrdynyogh` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;