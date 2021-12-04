CREATE DATABASE `bluebank` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `agendamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT NULL,
  `transacao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9wvvbhkdmevgg3m901fyuy7fg` (`transacao_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `cliente` (
  `cpf` varchar(11) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `senha` varchar(4) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `conta` (
  `numero_conta` varchar(10) NOT NULL,
  `agencia` varchar(6) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `cliente_cpf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`numero_conta`),
  KEY `FK9gye6g3vk7w430iyrdynyogh` (`cliente_cpf`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `conta_externa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agencia` varchar(255) DEFAULT NULL,
  `banco` varchar(255) DEFAULT NULL,
  `cnpj` varchar(255) DEFAULT NULL,
  `conta` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `transacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` int(11) NOT NULL,
  `conta_destino` varchar(255) DEFAULT NULL,
  `conta_origem` varchar(255) DEFAULT NULL,
  `momento` datetime DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `conta_externa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoh9siqwyxr594cc3vq6wjux0k` (`conta_externa`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
