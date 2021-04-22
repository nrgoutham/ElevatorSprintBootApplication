# Elevator design Using REST

This Project uses SpringBoot and Rest Design to implement below Use cases.

- Add a user
- Update a user - change name, modify buildings belongs to
- Find a building for a user
- Get status of all elevators in a building for a user
- User summons an elevator
- User select a floor

### results Folder

This folder has jpg files from Postman for all the usecases mentioned above.

### docker Folder

docker Folder has elevator_design.yml to spin up Postgres docker container to run application in localhost.

### migration module

Migration module uses Flyway database migration to initially create Tables and few test data. Resources -> migration
folder has versioned sql files and if any changes required can be added as next version (Example - V3_***.sql)
This module generates migration.jar which can be run either in local or aws postgres instance without restarting
Service.

To run migration for testing in local

- execute PostgresMigrationToolTest under test folder so that it runs migration successfully or execute jar file
  manually in local or mvn clean install will do the same job.

### service module

This module has controller,model,dto,dao and service folders.

Currently this project is set to execute in localhost port:8080

This Project can be executed in aws by following below steps.

- create EC2 instance, Install java 11
- create RDS -> Postgres database
- add postgres jdbc url in service->PostgresConfig and in migration->PostgresProperties files
- Run mvn clean install to generate war file
- Add war file generated in service-> target and jar file from migration -> target folder in S3 bucket
- connect EC2 instance from localmachine and wget war file from s3 bucket
- Run java -jar 'migration jar file name' to create tables and initial data for testing
- Run java -jar 'war file name' to run application

I have created RDS -> Postgres database and EC2 instance with application running and it can be tested with below URLs

GET- http://ec2-3-250-231-234.eu-west-1.compute.amazonaws.com:8080/api/v1/users

