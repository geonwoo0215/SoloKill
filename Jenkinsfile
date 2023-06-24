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