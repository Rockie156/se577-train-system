FROM maven:3.6.2
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.skip=true --quiet
ENTRYPOINT java -jar /app/target/TrainDemo-0.0.1-SNAPSHOT.jar