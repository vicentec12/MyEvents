# MyEvents Teste

Aplicativo implementado para o teste de desenvolvedor Android

# Arquitetura

## <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel">MVVM</a>

Esta arquitetura foi escolhida, pois lida muito com chamadas de API utilizando outras Threads, além de, ter muito muito suporte por parte das bibliotecas do Architecture Component da Google, usando LiveData para receber dados e comunicar as Activities/Fragments (Camada de visualização) sobre as alterações, adicionando toda a lógica de negócio nos ViewModels deixando o código muito mais limpo e organizado.

# Bibliotecas

## <a href="https://square.github.io/retrofit"/>Retrofit</a>

Utilizada para realizar requisições HTTP, sendo muito fácil de implementar, além de desserializar as respostas em JSON muito rapidamente. É amplamente recomendada pela comunidade e até mesmo pela própria Google.

## <a href="https://dagger.dev/dev-guide/android">Dagger 2</a>

Biblioteca de injeção de dependências muito robusta mantida pela Google, facilita a instanciação de objetos complexos, como View Models, além de ajudar na manutenção e criação de novos objetos.

## <a href="https://kotlinlang.org/docs/reference/coroutines-overview.html">Kotlin Coroutines</a>

Auxilia no gerenciamento de Threads, sendo ideal para operações pesadas, como requisições HTTP, que não podem ficar na Thread Principal, pois podem travar a interface gráfica trazendo uma experiência ruim para o usuário. Com ela, não é necessário o uso de Callbacks, deixando o código muito mais limpo, sequencial e de fácil testagem, se tornando uma escolha perfeita.

## <a href="https://github.com/bumptech/glide">Glide</a>

Utilizada para baixar e mostrar imagens através de URL, dentre todas as bibliotecas, ela se destaca pela velocidade do download das imagens além que trabalhar muito bem com caches de imagens já baixadas.

## <a href="https://site.mockito.org/">Mockito</a>

Usado para criar objetos "falsos" de objetos existentes para ajudar nos testes sem precisar utilizar os reais, fazendo com que se tenha mais controle na hora de realizar um teste.

# Imagens do Aplicativo
<img src="https://github.com/vicentec12/MyEvents/blob/master/app/src/main/assets/img1.jpg" alt="Imagem 1" width="360"> <img src="https://github.com/vicentec12/MyEvents/blob/master/app/src/main/assets/img2.jpg" alt="Imagem 2" width="360">

<img src="https://github.com/vicentec12/MyEvents/blob/master/app/src/main/assets/img3.jpg" alt="Imagem 3" width="360"> <img src="https://github.com/vicentec12/MyEvents/blob/master/app/src/main/assets/img4.jpg" alt="Imagem 4" width="360">

<img src="https://github.com/vicentec12/MyEvents/blob/master/app/src/main/assets/img5.jpg" alt="Imagem 5" width="360">

