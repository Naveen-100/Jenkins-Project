pipeline{
    agent any
    stages{
        stage('git Konnect'){
            steps{
                git credentialsId: 'jnks-pvt', url: 'git@github.com:Naveen-100/Jenkins-Project.git'
            }
        }
        stage('mvn build') {
            steps {
                sh 'mvn -B -DskipTest clean package'
            }
        }
        stage('mvn test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('checkstyle') {
            steps {
                sh 'mvn checkstyle:checkstyle'
                recordIssues(tools: [checkStyle(pattern: '**/checkstyle-result.xml')])

            }
        }
        stage('code coverage') {
            steps {
                jacoco()

            }
        }
        stage('sonarQube'){
            steps{
                sh 'mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=jenkins-capstone \
                    -Dsonar.host.url=http://137.135.113.220:9000 \
                    -Dsonar.login=sqp_b8e9faf84f7c80451924a1dca6fc82d8dff495ca'
            }
        }
        stage('nexus'){
            steps{
                script {
                            pom = readMavenPom file: "pom.xml";
                            filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                            echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                            artifactPath = filesByGlob[0].path;
                            
                            nexusArtifactUploader artifacts: [[artifactId: pom.artifactId, classifier: '', file: artifactPath, type: pom.packaging, type: 'war']], credentialsId: 'nexus-key', groupId: pom.artifactId, nexusUrl: '20.163.228.142:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: pom.version
                }
            }
        }
    }
}