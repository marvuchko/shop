# Performe SonarQube code analysis
./mvnw clean test jacoco:report
./mvnw sonar:sonar -Dsonar.host.url=http://127.0.0.1:28004