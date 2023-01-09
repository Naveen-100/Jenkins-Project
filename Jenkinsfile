pipeline {
    agent any
    stages {
        stage('poll scm') {
            steps {
                git credentialsId: 'jenkins-proj', url: 'git@github.com:Naveen-100/Jenkins-Project.git'
            }
        }
        stage('mvn build') {
            steps {
                sh 'mvn -B -DskipTest clean package'
            }
        }
        stage('mvn test') {
            when {
               expression {stage == 'unittest'}
             }

            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}