SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Banco de dados: `bluebank`
--
CREATE DATABASE IF NOT EXISTS bluebank;
-- --------------------------------------------------------

--
-- Estrutura da tabela `agendamento`
--

CREATE TABLE `agendamento` (
  `id` bigint(20) NOT NULL,
  `data` varchar(255) DEFAULT NULL,
  `transacao_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpf` varchar(11) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `senha` varchar(4) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`cpf`, `data_nascimento`, `email`, `endereco`, `nome`, `rg`, `senha`, `telefone`) VALUES
('40680153098', '1985-10-07', 'alana@gmail.com', 'Maceio', 'Alana Amaral', '483761126', '2294', '34884483'),
('46466174042', '1995-05-07', 'vinicius@gmail.com', 'Joao Pessoa', 'Vinicius Silva', '408112396', '5276', '29284845'),
('60799848069', '1977-12-25', 'carla@gmail.com', 'Maceio', 'Carla Braga', '409810721', '8632', '26389359'),
('64973227013', '1989-04-17', 'Pedro@gmail.com', 'Rio de Janeiro', 'Pedro Araujo', '178377727', '1995', '23132550'),
('84214772008', '1988-08-08', 'leticia@gmail.com', 'Sao paulo', 'Leticia Souza', '363104604', '3790', '32208108');

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE `conta` (
  `numero_conta` varchar(10) NOT NULL,
  `agencia` varchar(6) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `cliente_cpf` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `conta`
--

INSERT INTO `conta` (`numero_conta`, `agencia`, `saldo`, `cliente_cpf`) VALUES
('0957690925', '0001', '3000.00', '40680153098'),
('2585362243', '0001', '9000.00', '64973227013'),
('4674409991', '0001', '7500.00', '84214772008'),
('7155996152', '0001', '6000.00', '60799848069'),
('7624893092', '0001', '500.00', '46466174042');

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta_externa`
--

CREATE TABLE `conta_externa` (
  `id` bigint(20) NOT NULL,
  `agencia` varchar(255) DEFAULT NULL,
  `banco` varchar(255) DEFAULT NULL,
  `cnpj` varchar(255) DEFAULT NULL,
  `conta` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `transacao`
--

CREATE TABLE `transacao` (
  `id` bigint(20) NOT NULL,
  `categoria` int(11) NOT NULL,
  `conta_destino` varchar(255) DEFAULT NULL,
  `conta_origem` varchar(255) DEFAULT NULL,
  `momento` datetime DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `conta_externa` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `agendamento`
--
ALTER TABLE `agendamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9wvvbhkdmevgg3m901fyuy7fg` (`transacao_id`);

--
-- Índices para tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`);

--
-- Índices para tabela `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`numero_conta`),
  ADD KEY `FK9gye6g3vk7w430iyrdynyogh` (`cliente_cpf`);

--
-- Índices para tabela `conta_externa`
--
ALTER TABLE `conta_externa`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `transacao`
--
ALTER TABLE `transacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoh9siqwyxr594cc3vq6wjux0k` (`conta_externa`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agendamento`
--
ALTER TABLE `agendamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `conta_externa`
--
ALTER TABLE `conta_externa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `transacao`
--
ALTER TABLE `transacao`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `agendamento`
--
ALTER TABLE `agendamento`
  ADD CONSTRAINT `FK9wvvbhkdmevgg3m901fyuy7fg` FOREIGN KEY (`transacao_id`) REFERENCES `transacao` (`id`);

--
-- Limitadores para a tabela `conta`
--
ALTER TABLE `conta`
  ADD CONSTRAINT `FK9gye6g3vk7w430iyrdynyogh` FOREIGN KEY (`cliente_cpf`) REFERENCES `cliente` (`cpf`);

--
-- Limitadores para a tabela `transacao`
--
ALTER TABLE `transacao`
  ADD CONSTRAINT `FKoh9siqwyxr594cc3vq6wjux0k` FOREIGN KEY (`conta_externa`) REFERENCES `conta_externa` (`id`);
COMMIT;
