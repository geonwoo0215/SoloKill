pipeline {
  agent any

  environment {
            PATH = "/opt/gradle/gradle-7.6.1/bin:$PATH"
        }

  stages {
    stage('Git Checkout') {
              steps {
                  checkout scm
              }
    }
     stage('Create Mysql Docker Container') {
              steps {
                  sh 'docker run -d -p 3305:3306 --env MYSQL_DATABASE=${TEST_DATABASE} --env MYSQL_ROOT_PASSWORD=${TEST_DATASOURCE_PASSWORD} mysql:8.0.31'
              }
     }

     stage('Create Redis Docker Container') {
              steps {
                  sh 'docker run -d -p 6379:6379 --name localhost redis'
              }
     }

    stage('Test') {
        steps {

            sh 'gradle test'
        }
      }



    stage('Build') {
      steps {
           sh 'gradle clean build --exclude-task test'
      }
    }
  }
}