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
        stage('checkstyle') {
            when {
                expression {stage == 'checkstyle'}
            }
            steps {
                sh 'mvn checkstyle:checkstyle'
                recordIssues(tools: [checkStyle(pattern: '**/checkstyle-result.xml')])

            }
        }
        stage('code coverage') {
            when {
                expression {stage == 'codecoverage'}
            }
            steps {
                jacoco()

            }
        }
        stage('sonar') {
            when {
                expression {stage == 'sonar'}
            }
            steps {
                sh 'mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=jenkins-project \
                    -Dsonar.host.url=http://20.232.22.143:9000 \
                    -Dsonar.login=sqp_d20d9122f841fbaf3b1831ce5231496954b1db1d'
            }
        }
    }
}
