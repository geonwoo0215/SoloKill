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

     stage('Prepare DB') {
               steps {
                 script {
                   // 도커로 DB 컨테이너 실행
                   sh "docker run --name=${env.TEST_DATABASE} -e MYSQL_DATABASE=${env.TEST_DATABASE} -e MYSQL_ROOT_PASSWORD=${env.TEST_DATASOURCE_PASSWORD} -p 3306:3306 -d mysql:5.7"
                 }
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