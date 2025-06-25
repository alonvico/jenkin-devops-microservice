pipeline {
    // agent any
    agent { docker { image 'maven:3.6.3' } } //maven:3.9.10
    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
                echo "Build"
            }
        }
        stage('Test') {
             steps {
                 echo "Test"
             }
        }
        stage('Intregration Test') {
             steps {
                  echo "Intregration Test"
             }
        }
    }

    post {
        always {
            echo "Im awesome. I run always"
        }
        success {
            echo "I run when you are successful"
        }
        failure {
            echo "I run when you fail"
        }
    }
}