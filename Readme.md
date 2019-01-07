
Build and Deploy locally
-----------------------------

1. Open a command prompt and navigate to the root directory of this microservice.
2. Type this command to build and execute the application:
mvn clean package
3. Run the application With Debug 
java -jar -agentlib:jdwp=transport=dt_socket,address=9009,server=y,suspend=n target/demo-thorntail.jar


4. Test de Application with the following endpoints:

http://localhost:8080/livro/listar
http://localhost:8080/livro/atualizar
http://localhost:8080/livro/cadastrar
http://localhost:8080/livro/deletar

5. Here anothers endpoint:

http://localhost:8080/node
http://localhost:8080/heap
http://localhost:8080/threads
http://localhost:8080/health

http://localhost:8080/console

http://localhost:8080/app/diskSpace

http://localhost:9990/management
