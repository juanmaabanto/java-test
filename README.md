# java-test
Esta es una aplicación de test

## Requisitos

- Java 11
- Maven 3.8+
- Docker (si quiere ejecutar en contenedr)

## Ejecución Local

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/juanmaabanto/java-test.git
   cd java-test
   ```

2. **Compilar y ejecutar**:

   ```bash
   mvn clean install
   ```

   ```bash
   mvn spring-boot:run
   ```

3. **Usando Docker**:
   ```bash
   docker build -t java-test .
   ```

   ```bash
   docker run -p 8080:8080 java-test
   ```
   
4. **Uso**:
La aplicación estara disponible en http://localhost:8080
El swagger estara disponible en http://localhost:8080/swagger-ui/index.html

Hay una colección de postman en la raíz del directorio para probar (test/postman_collection.json)