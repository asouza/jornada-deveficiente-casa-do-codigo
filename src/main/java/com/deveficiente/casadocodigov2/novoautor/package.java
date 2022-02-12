/*
 * Analise da funcionalidade pelo olhar das regras da arquitetura em camadas descritas
 * por Bob Marting
 * 
 * Independent of Frameworks - Quase (depende do metadado do framework)
 * Testable. The business rules can be tested without the UI, Database... - Sim(só mockar entitymanager)
 * Independent of UI - Não(sim)
 * Independent of Database - Sim (agora depende de uma interface do próprio projeto)
 * Independent of any external agency - Sim (só depende do repositorio que é uma abstracao do projeto)
 * 
 * Principios:
 * 
 * 1 - Segue a The Dependency Rule - Não(quebrou recebendo o objeto de request de fora) 
 */