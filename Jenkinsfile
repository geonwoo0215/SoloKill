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

    stage('Create Test Database') {

        steps{
            sh 'docker run -d -p 3305:3306 --env MYSQL_DATABASE="${env.TEST_DATABASE}" --env MYSQL_ROOT_PASSWORD="${env.TEST_DATASOURCE_PASSWORD}" mysql:8.0.31'
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