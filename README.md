# MyEvents Teste

Aplicativo implementado para o teste de desenvolvedor Android

# Arquitetura

## <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel">MVVM</a>

Esta arquitetura foi escolhida, pois lida muito com chamadas de API utilizando outras Threads, além de, ter muito muito suporte por parte das bibliotecas do Architecture Component da Google, usando LiveData para receber dados e comunicar as Activities/Fragments (Camada de visualização) sobre as alterações, adicionando toda a lógica de negócio nos ViewModels deixando o código muito mais limpo e organizado.

# Bibliotecas

## <a href="https://square.github.io/retrofit"/>Retrofit</a>

Utilizada para realizar requisições HTTP, muito fácil de implementar e desserializa as respostas em JSON muito rapidamente. É amplamente recomendada pela comunidade e até mesmo pela própria Google.

## <a href="https://dagger.dev/dev-guide/android">Dagger 2</a>

Biblioteca de injeção de dependências muito robusta mantida pela Google, facilita a instanciação de objetos complexos, como View Models, além de ajudar na manutenção e criação de novos objetos.

## <a href="https://kotlinlang.org/docs/reference/coroutines-overview.html">Kotlin Coroutines</a>

Auxilia no gerenciamento de Threads, sendo ideal para operações pesadas, como requisições HTTP, que não podem ficar na Thread Principal, pois podem travar a interface gráfica trazendo uma experiência ruim para o usuário. Com ela, não é necessário o uso de Callbacks, deixando o código muito mais limpo, sequencial e de fácil testagem, se tornando uma escolha perfeita.

## <a href="https://github.com/bumptech/glide">Glide</a>

Utilizada para baixar e mostrar imagens através de URL, dentre todas as bibliotecas, ela se destaca pela velocidade do download das imagens além que trabalhar muito bem com caches de imagens já baixadas.
