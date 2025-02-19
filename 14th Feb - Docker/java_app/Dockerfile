FROM openjdk:17-alpine

WORKDIR /root/demo

# Download the PostgreSQL JDBC driver
RUN wget https://jdbc.postgresql.org/download/postgresql-42.2.20.jar

# Copy and compile your Java application
COPY EmpManageApp.java .
RUN javac -cp postgresql-42.2.20.jar EmpManageApp.java

# Run the application with the classpath set
CMD ["java", "-cp", ".:postgresql-42.2.20.jar", "EmpManageApp"]
