pipeline {
  agent any

  environment {
            PATH = "/opt/gradle/gradle-7.6.1/bin:$PATH"
            TEST_DATABASE = credentials('TEST_DATABASE')
            TEST_DATASOURCE_PASSWORD = credentials('TEST_DATASOURCE_PASSWORD')
        }

  stages {
    stage('Git Checkout') {
              steps {
                  checkout scm
              }
    }

    stage('Create Test Database') {

        steps{
            sh 'docker run -d -p 3306:3306 --env MYSQL_DATABASE=${TEST_DATABASE} --env MYSQL_ROOT_PASSWORD=${TEST_DATASOURCE_PASSWORD} mysql:8.0.31'
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