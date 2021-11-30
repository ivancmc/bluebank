<img src="Arquivos/img/logo.png"  alt="Logo Bluebank" title="Logo Bluebank"></img>
<h1 align="center" id="topo">Olá, seja bem vindo(a) a API Bluebank</h1>
Esse projeto consiste em uma API para gerenciar as transações de um banco fictício utilizando Java, MySQL e AWS. Efetuamos a aplicação por meio de Spring Boot, usando Maven com CRUD e dividimos as funções em microserviços. Para o banco de dados utilizamos o MySql com deploy no Docker e pipeline em Jenkins, e por fim, fizemos uso da AWS como servidor para manter a aplicação por meio da instância EC2.

<h3>Sumário</h3>
<ul>
	<a href="https://storyset.com/work">
		<img src="Arquivos/img/Scrum board.gif" min-width="350px" max-width="350px" width="350px" align="right"  alt="Work illustrations by Storyset" title="Work illustrations by Storyset">
	</a>
	<li><a href="#requisitos">Requisitos e Funcionalidades</a></li>
	<ul>
		<li><a href="#RQEntrega">Requisitos de Entrega</a></li>
        <li><a href="#RQFuncionais">Requisitos Funcionais</a></li>
        <li><a href="#endpoints">Endpoints</a></li>
        <ul>
            <li><a href="#postman">Postman</a></li>
            <li><a href="#swagger">Swagger</a></li>
        </ul>
    </ul>
    <li><a href="#desenvolvimento">Processo de Desenvolvimento</a></li>
    <ul>
        <li><a href="#jira">Quadro Kanban</a></li>
        <li><a href="#testes">Execução dos Testes</a></li>
    </ul>
    <li><a href="#tecnologias">Tecnologias e Ferramentas</a></li>
    <li><a href="#licenca">Licença</a></li>
    <li><a href="#agradecimentos">Agradecimentos</a></li>
    <li><a href="#squad">Squad DezKTop</a></li>
</ul>

<h1 align="center" id="requisitos">Requisitos e Funcionalidades</h1>
<h3 id="RQEntrega">Requisitos de Entrega</h3>

- [ ] O código deve ser entregue em um repositório no Github.
- [ ] A API deve ser disponibilizada em ambiente AWS com EC2 e em Beanstalk.
- [ ] A aplicação deve ter um pipeline em Jenkins ou no Aws Build.
- [ ] A aplicação precisa ser configurada no API Gateway da AWS.
- [ ] A aplicação precisa ter no mínimo um endpoint de SNS para cadastro de emails e verificação automática.
- [ ] A aplicação precisa ter no mínimo um Lambda.
- [ ] Liste os endpoints no README.md
- [ ] O Banco deve ser entregue em script SQL junto ao repositório.

<h3 id="RQFuncionais">Requisitos Funcionais</h3>
Os requisitos funcionais desta API giram em torno de um CRUD entre clientes e suas respectivas contas bancárias, logo, este sistema possibilita:

- O cadastro de clientes - Incluindo dados pessoais e informações para contato.
- A listagem de clientes - Você pode optar por listar todos os clientes existentes na base de dados ou buscar um em específico.
- A atualização de dados dos clientes.
- A exclusão de um cliente, e consequentemente de sua conta bancária.
- O histórico de transações entre contas.

<h3 id="endpoints">Endpoints</h3>
Essa seção encontra-se em desenvolvimento.

<h3 id="postman">Postman</h3>
Essa seção encontra-se em desenvolvimento.

<h3 id="swagger">Swagger</h3>
Essa seção encontra-se em desenvolvimento.

<p align="right"><a href="#topo">Você pode voltar ao topo clicando aqui ↑</a></p>
<h1 id="desenvolvimento" align="center">Desenvolvimento</h1>
<h3 id="jira">Quadro Kanban</h3>
Para a organização do projeto, utilizamos o <a href="https://www.atlassian.com/br/software/jira">Jira</a> e criamos um quadro <a href="https://www.atlassian.com/agile/kanban">Kanban</a>. A formatação do quadro utilizada pelo grupo foi de "To Do, Doing, Testing, Done":
<img src="Arquivos/img/jira/BBP_Columns.png" align="center" alt="Quadro Kanban"></img><br>

Já no jira, nas configurações do projeto, ativamos o recurso de <a href="https://support.atlassian.com/jira-software-cloud/docs/enable-the-backlog/">"Lista de Pendências / Priorização"</a>, que fornece um espaço exclusivo para planejar e priorizar o trabalho, e o recurso de <a href="https://www.atlassian.com/agile/scrum/sprints">"Sprints"</a> que permite o planejamento e conclusão do trabalho em unidades de tempo fixas. Assim, nos restava apenas planejar a sprint já que todas as histórias e tarefas criadas iam diretamente para o backlog do projeto.
<img src="Arquivos/img/jira/BBP_Backlog.png" alt="Backlog"></img><br>

Note que:
- O painel <b>MVP (Minimum Viable Product) 5</b> indica a sprint que está sendo executada no momento. Seu período de duração foi do dia 24 ao dia 29 de novembro.
- O painel do <b>Backlog</b>, indica as tarefas que ainda serão realizadas em uma Sprint futura.
- Há um indicador de priorização em cada card. Os status de priorização são definidos por: :arrow_double_up: Highest, :arrow_up_small: High, <a href="https://www.flaticon.com/br/autores/freepik" title="Freepik"><img src="Arquivos/img/igual.png" align="center"></img></a> Medium, :arrow_down_small: Low, :arrow_double_down: Lowest

Procuramos criar as tarefas e histórias o mais completas possíveis, e quando necessário, adicionavamos os detalhes da na descrição do card.
<img src="Arquivos/img/jira/BBP_HistoriaCrudCliente.png" alt="Historia Crud Cliente"></img>
<img src="Arquivos/img/jira/BBP_TarefaCrudCliente.png" alt="Tarefa Crud Cliente"></img>
<img src="Arquivos/img/jira/BBP_TarefaExtrato.png" alt="Tarefa Extrato"></img>

Note que:
- Há relações entre histórias e tarefas. Nos cars de história, as tarefas "filhas" dessa história estão vinculadas, e portanto são mostradas na descição da mesma.
- Há descrições extras em alguns cards.
- Os responsáveis pela tarefa são indicados nos cards na categoria "Responsáveis".

E por último, todas as tarefas e histórias estão vinculadas ao épico **_"Eu enquanto cliente gostaria de abrir uma conta no banco para que eu possa efetuar transações financeiras"_**
<img src="Arquivos/img/jira/BBP_Roteiro.png" alt="epico"></img>

<h2 id="testes">Execução dos Testes</h2>
Criação dos testes, utilizamos JUnit
Essa sessão encontra-se em andamento.

<h3 id="resultados">Análise dos Resultados</h3>
Essa sessão encontra-se em andamento.

<p align="right"><a href="#topo">Você pode voltar ao topo clicando aqui ↑</a></p>
<h3 class="subTitulo" id="tecnologias">Ferramentas e tecnologias utilizadas</h3>
<div id="tecnologias" style="display: inline_block" align="center"><br>
    <a href="https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html">
        <img align="center" alt="Java" title="Java SE 11" height="80px" width="90px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-plain.svg"/>
    </a>
    <a href="https://spring.io/tools#suite-three">
        <img align="center" alt="Spring-Boot-Tools" title="Spring Boot Tools" height="80px" width="90px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg"/>
    </a>
    <a href="https://www.mysql.com/">
        <img align="center" alt="MySql" title="MySql" height="80px" width="90" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg"/>
    </a>
    <a href="https://aws.amazon.com/pt/?nc2=h_lg">
        <img align="center" alt="AWS" title="Amazom Web Services (AWS)" height="80px" width="90px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/amazonwebservices/amazonwebservices-original.svg"/>
    </a>
    <a href="https://www.docker.com/get-started">
    <img align="center" alt="Docker" title="Docker" height="90px" width="100px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg"/> 
    </a>
    <a href="https://www.jenkins.io/download/">
        <img align="center" alt="Jenkins" title="Jenkins" height="80px" width="90px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg"/>
    </a>
        <a href="https://www.atlassian.com/br/software/jira">
        <img align="center" alt="Jira" title="Jira" height="80" width="90" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jira/jira-original.svg"/>
    </a>
    <a href="https://www.postman.com/downloads/">
        <img align="center" alt="Postman" title="Postman" height="80px" width="90px" src="Arquivos/img/postman-icon.svg"/>
    <a href="https://git-scm.com/">
        <img align="center" alt="Git" title="Git" height="80px" width="90px" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg"/>
    </a>
    <a href="https://www.phpmyadmin.net/downloads/">
        <img align="center" alt="PhpMyAdmin" title="PhpMyAdmin" height="150px" width="160px" src="Arquivos/img/PhpMyAdmin.svg"/>
    </a>
    <a href="https://www.apachefriends.org/pt_br/index.html">
        <img align="center" alt="Xampp" title="Xampp" height="80" width="90" src="Arquivos/img/xampp.svg"/>
    </a>
</div>

<h3 id="licenca">Liçenca</h3>
Este projeto está sob a licença MIT. Você pode achar mais informações relacionadas a essa licença no arquivo <a href="LICENSE">LICENSE</a>, contido neste repositório.

<h3 id="agradecimentos">Agradecimentos</h3>
Agradecemos ao Banco Pan e a Gama Academy pela iniciativa do projeto Pan Academy, que possibilitou uma nova jornada de conhecimento a todos os selecionados. Aos aos professores, por nos acompanhar e instruir nessa jornada. A todos os mentores que dedicaram um pouco do seu tempo compartilhando conhecimentos e experiências conosco. Aos nossos colegas de classe. E por último, aos membros da nossa Squad, sem nenhum de nós, esse projeto não seria possível.

<h3 id="squad">Nós somos a Squad DezKTop...</h3>
Você pode ver mais acerca do nosso desenvolvimento através dos repositórios contidos no GitHub e nos contatar pelo linkedin. Agradecemos desde já o tempo que você dedicou a leitura deste arquivo.😄
<br><br>
	
<a href="https://storyset.com/work">
   <img src="Arquivos/img/developer.svg" min-width="350px" max-width="350px" width="350px" align="right"  alt="Work illustrations by Storyset">
</a>
<table width="40%">
    <tr>
        <td>
            <img src="Arquivos/img/squad/ivan.jpeg" alt="Foto Ivan" height="70px" style="border-radius:10px">
        </td>
        <td align="center">
            <a href="https://github.com/ivancmc">
                <img src="https://img.shields.io/badge/%20Ivan%20%20%20%20%20%20%20%20%20Cardoso%20-10?style=social&logo=github&logoColor=006A8A" width="150px;" alt="GitHub" />
            </a>
        </td>
        <td align="center">
            <a href="https://www.linkedin.com/in/ivancmc/" target="_blank" alt="Linkedin">
                <img src="https://img.shields.io/badge/%20Ivan%20%20%20%20%20%20%20%20%20Cardoso%20-0077B5?style=social&logo=linkedin&logoColor=00C6FF&" width="150px;" alt="Linkedin" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <img src="Arquivos/img/squad/luana.jpeg" alt="Foto Luana" height="70px" style="border-radius:10px">
        </td>
        <td align="center">
            <a href="https://github.com/LuanaSantosNascimento">
                <img src="https://img.shields.io/badge/Luana%20Nascimento-10?style=social&logo=github&logoColor=006A8A" width="150px;" alt="GitHub" />
            </a>
        </td>
        <td align="center">
            <a href="https://www.linkedin.com/in/luana-nascimento-853240221/" target="_blank" alt="Linkedin">
                <img src="https://img.shields.io/badge/Luana%20Nascimento-0077B5?style=social&logo=linkedin&logoColor=00C6FF" width="150px;" alt="Linkedin" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <img src="Arquivos/img/squad/lucas.png" alt="Foto Lucas" height="70px" style="border-radius:10px">
        </td>
        <td align="center">
            <a href="https://github.com/lucas-rl">
                <img src="https://img.shields.io/badge/Lucas%20Rodrigues-10?style=social&logo=github&logoColor=006A8A" width="150px;" alt="GitHub" />
            </a>
        </td>
        <td align="center">
            <a href="https://www.linkedin.com/in/lucas-rodrigues-l/" target="_blank" alt="Linkedin">
                <img src="https://img.shields.io/badge/Lucas%20Rodrigues-0077B5?style=social&logo=linkedin&logoColor=00C6FF" width="150px;" alt="Linkedin" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <img src="Arquivos/img/squad/mayckon.jpeg" alt="Foto Mayckon" height="70px" style="border-radius:10px">
        </td>
        <td align="center">
            <a href="https://github.com/Mayckon-Prado">
                <img src="https://img.shields.io/badge/Mayckon%20%20%20%20Prado-10?style=social&logo=github&logoColor=006A8A" width="150px;" alt="GitHub" />
            </a>
        </td>
        <td align="center">
            <a href="https://www.linkedin.com/in/mayckon-morian-marqui-prado-a9195571/" target="_blank" alt="Linkedin">
                <img src="https://img.shields.io/badge/Mayckon%20%20%20%20%20Prado-0077B5?style=social&logo=linkedin&logoColor=00C6FF" width="150px;" alt="Linkedin" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <img src="Arquivos/img/squad/wendel.jpeg" alt="Foto Wendel" height="70px" style="border-radius:10px">
        </td>
        <td align="center">
            <a href="https://github.com/dantaswendel">
                <img src="https://img.shields.io/badge/%20Wendel%20%20%20%20%20Dantas%20%20-10?style=social&logo=github&logoColor=006A8A" width="150px;" alt="GitHub" />
            </a>
        </td>
        <td align="center">
            <a href="https://www.linkedin.com/in/wendel-dantas-lima-5104b0166/" target="_blank" alt="Linkedin">
                <img src="https://img.shields.io/badge/%20Wendel%20%20%20%20%20%20Dantas%20%20-0077B5?style=social&logo=linkedin&logoColor=00C6FF" width="150px;" alt="Linkedin" />
            </a>
        </td>
</table>
<p align="right"><a href="#topo">Você pode voltar ao topo clicando aqui ↑</a></p>
