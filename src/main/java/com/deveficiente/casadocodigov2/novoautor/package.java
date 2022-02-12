/*
 * Analise da funcionalidade pelo olhar das regras da arquitetura em camadas descritas
 * por Bob Marting
 * 
 * Independent of Frameworks - Não (depende da JPA e também do Spring)
 * Testable. The business rules can be tested without the UI, Database... - Sim(só mockar entitymanager)
 * Independent of UI - Não(só funciona por enquanto se for via web)
 * Independent of Database - Sim (agora depende de uma interface do próprio projeto)
 * Independent of any external agency - Não (depende do banco de dados, dado que está acoplada a JPA)
 */