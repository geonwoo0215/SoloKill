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

            // 컨테이너가 실행될 때까지 대기
            sh "docker wait ${containerName}"

            // 컨테이너 로그 출력
            sh "docker logs ${containerName}"
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