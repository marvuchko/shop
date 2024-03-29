# Rebuild the project
./mvnw clean install

# Stop the containers in case they have been up and running
docker-compose down

# Start the containers
docker-compose up --build

# Check your browser:
# REST API documentation:     http://localhost:28003/api/swagger-ui.html
# H2 database administration: http://localhost:28003/api/h2
#                             database: shop
#                             username: marko
#                             password: marko
# SonarQube:                  http://localhost:28004
#                             username: admin
#                             password: admin