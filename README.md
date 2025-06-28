📚 BookFinder API Console App

Aplicación de consola en Java con Spring Boot para buscar libros desde una API externa, almacenarlos en una base de datos evitando duplicados, y realizar consultas específicas sobre libros y autores guardados.
📌 Índice

    Descripción

    Características

    Tecnologías Usadas

    Cómo Funciona

    Ejemplo de Uso

    Autor

📝 Descripción

Esta aplicación permite buscar libros a través de una API pública (como Gutendex), y almacenar los resultados en una base de datos utilizando JPA. Los libros y autores se guardan de forma organizada, evitando duplicados. Toda la interacción se realiza desde la consola.
✅ Características

    🔍 Búsqueda de libros desde API externa.

    🧠 Persistencia inteligente:

        No guarda libros ya existentes.

        Evita duplicar autores; si ya existen, asocia el libro al autor correspondiente.

    📚 Relación uno a muchos:

        Un autor puede tener muchos libros.

        Un libro tiene un único autor.

    🗃️ Base de datos relacional usando JPA.

    🌐 Operaciones funcionales con lambda, streams y records.

    🧾 DTOs para exponer solo los datos relevantes.

    🧪 Funcionalidades disponibles desde la consola:

        Buscar libros.

        Listar libros guardados.

        Listar autores guardados.

        Listar autores vivos en un año determinado.

        Listar libros por idioma.

🛠️ Tecnologías Usadas

    Java 17+

    Spring Boot

    Spring Data JPA

    Hibernate

    API pública (como Gutendex)

    H2 / MySQL / PostgreSQL (según configuración)

    Maven

    Programación funcional con Streams y Lambdas

⚙️ Cómo Funciona

    El usuario busca un libro.

    Se hace una consulta a la API externa.

    Se mapean los datos con clases record.

    Antes de guardar:

        Se verifica si el libro ya existe.

        Se verifica si el autor ya existe.

        Si el autor existe, se asocia el libro a él.

    Se guarda solo si es nuevo.

    Las consultas posteriores se basan únicamente en los datos guardados en la base de datos.

💻 Ejemplo de Uso

Elija la opcion a traves de su numero:
1 - Buscar libro por titulo
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año 
5 - Buscar libros por idioma
0 - Salir

👤 Autor

Albeiro Manuel Baena Tovar
💼 Estudiante de Java con Spring Boot en Alura Latam
