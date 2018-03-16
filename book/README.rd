Antes de compilar os dados de conexão com o banco devem ser informados no arquivo application.properties localizado em src/main/resources.

As propriedades abaixo devem ser alteradas informando a base mysql que será usada

#jdbc:mysql://<server>:<port>/<database_name>
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

Para compilar o projeto utilizar o maven com o comando abaixo:

	mvn clean package

Para executar o projeto utilizar o comando abaixo: 

	java -jar target\book-0.0.1-SNAPSHOT.jar