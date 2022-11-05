# WS Cars

#### O aplicativo Ws Cars foi criado para atender um desafio proposto pela WS Work Sistemas. Ele consiste na criação de uma base de leads interessados em carros. Programaticamente o aplicativo consta com: MVVM + Clean + Retrofit + Koin + Room e muito mais! Além disso, existe uma rotina de envio dos leads para o banco de dados remoto.

## 🎯 Tomada de Decisões
`Arquiteturas`: Decidi usar MvvM + Clean neste projeto para seguir os padrões utlizados atualmente no mercado de trabalho. Deixando-o mais limpo, de fácil manutenção, de fácil entendimento e facilmente testável. <br><br>
`Dados do Usuário`: Para adquirir os dados do usuário, decidi ter mais um fragment onde ele pode colocar seu nome e e-mail para ser contactado futuramente. <br><br>
`Tema de cores do Aplicativo`: Em relação as cores do aplicativo, eu optei por utilizar as cores do site da WS Work Sistemas, responsável pela criação deste desafio. <br><br>
`Validação dos dados do Usuário`: Para evitar o envio de dados inconsistentes para a API, o usuário só consegue se tornar um Lead quando seus dados forem validados como "válidos" . <br><br>
`Rotina de envio de Leads`: Na primeira tela do aplicativo é disparado a funcionalidade de envio de leads que consiste em: Buscar no banco de dados local a lista de leads salva > Checar se essa lista não está vazia para evitar envios de listas vazias para a Api > Em caso da listar não estar vazia é feito o envio desses leads para a Api > Em caso de sucesso do envio de leads, o banco de dados local é limpo. <br>

## :iphone: Layouts do Projeto
![layouts-ws-cars](https://user-images.githubusercontent.com/109977155/200141335-430be63d-a030-491a-a462-722afe2a4c0a.png)

## :pencil2: Organização das Pastas
![pastas-ws-cars](https://user-images.githubusercontent.com/109977155/200142022-2933136e-05ba-4af9-854b-80440c7facf2.png)

## 🔨 Funcionalidades do projeto
`Demonstrar interesse em um carro (se tornar um Lead)` <br>

## ✔️ Técnicas e tecnologias utilizadas
`Kotlin`: linguagem utilizada no projeto. <br>
`ViewBinding`: fazer binding de view. <br>
`Flow`: fazer atualizações da tela em tempo real. <br>
`Courutines`: usado para colocar ações demoradas em um thread secundária. <br>
`Git`: usado para versionar o código no github. <br>
`Room`: usado para persistência de dados em banco de dados interno com SQLite. <br>
`Retrofit`: usado para se comunicar com a API. <br>
`Koin`: usado para injeção de dependência. <br>
`Arquitetura Clean + MvvM`: projeto foi feito totalmente nas duas arquiteturas. <br>
`Fragments`: Esse aplicativo é um SAP (Single Activity App)  <br>
`Navigation Component`: Utilizado para navegação entre fragments. <br>
`State Pattern` <br>
`Observer Pattern`

## 🛠️ Abrir e rodar o projeto
Após baixar o projeto, você pode abrir com o Android Studio. Para isso, na tela de launcher clique em:
Open an Existing Project (ou alguma opção similar) Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo
antes de procurá-lo) Por fim clique em OK, O Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as 
tasks, você pode executar o App.

## 🎥 Showcase do aplicativo
https://user-images.githubusercontent.com/109977155/200141699-68faf44c-c564-4f89-b727-544994cb38c2.mp4

