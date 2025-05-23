# ToDoList
To Do List using Java

Este é um aplicativo de lista de tarefas desenvolvido em Java utilizando o WindowBuilder para a construção da interface gráfica (GUI). Ele permite adicionar, remover, visualizar, salvar e carregar tarefas por meio de um arquivo .csv, utilizando técnicas de persistência de dados com ArrayList, BufferedWriter e BufferedReader.

Objetivo
Criar um aplicativo funcional e simples para gerenciamento de tarefas que ensina o uso de:

Interface gráfica com WindowBuilder.

Estrutura de dados ArrayList<String>.

Manipulação de arquivos .csv com persistência de dados.

Organização de código utilizando métodos auxiliares.

Boas práticas com try-with-resources.

Funcionalidades
Funcionalidades Principais
Adicionar nova tarefa.

Remover tarefa selecionada.

Exibir todas as tarefas na interface gráfica.

Salvar tarefas em um arquivo .csv.

Carregar tarefas a partir de um arquivo .csv.

Componentes da Interface
Campo de texto para digitar uma nova tarefa.

Botões: Adicionar, Remover, Salvar, Carregar e Editar.

Lista (JList) para exibição das tarefas atuais.

Persistência de Dados
As tarefas são armazenadas temporariamente em um ArrayList<String>.

Salvamento das tarefas em arquivo CSV com FileWriter e BufferedWriter.

Leitura de tarefas com FileReader e BufferedReader ou Scanner.

Utilização de try-with-resources para garantir o fechamento seguro de arquivos.



Maneira que o aplicativo funciona:

Na pasta "main" fica minha classe Application, que é onde foi criado toda a interface e sets iniciais das coisas, como botões visíveis ou não, entre outros. 
Para os botões Inserir, Carregar e Salvar, foram feitas funções para fazer os processos em classes separadas, por organização.

As classes separadas fazem as lógicas de inserir, carregar e salvar. Elas também chamam funções de "Show" que mudam o que está visível ou não, além de abrir
o explorador de arquivos lá.