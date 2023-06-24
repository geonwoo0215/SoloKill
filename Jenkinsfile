pipeline {
  agent any

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