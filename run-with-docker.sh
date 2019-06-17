# Rebuild the project
./mvnw clean install

# Stop the containers in case they have been up and running
docker-compose down

# Start the system
docker-compose up

# Performe SonarQube code analysis
./mvnw sonar:sonar -Dsonar.host.url=http://127.0.0.1:28004

# Check your browser:
# REST API documentation:     http://localhost:28003/api/swagger-ui.html
# H2 database administration: http://localhost:28003/api/h2
#                             username: marko
#                             password: marko
# SonarQube:                  http://localhost:28004