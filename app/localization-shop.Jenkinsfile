pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'MyDockerAccount'
        DOCKERHUB_REPO = 'juanvalenzuela101/localization-shop_v1_2026'
        DOCKER_IMAGE_TAG = 'latest'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -f app/pom.xml clean install' // sh for linux and ios
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -f app/pom.xml test'
                
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn -f app/pom.xml jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                dir('app') {
                    sh """
                    mvn clean verify sonar:sonar "-Dsonar.projectKey=translations" "-Dsonar.token=$SONAR_TOKEN"
                    """
                }
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                dir('app') {
                    script {
                        docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                dir('app') {
                    script {
                        docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                            docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                        }
                    }
                }
            }
        }
    }
}


