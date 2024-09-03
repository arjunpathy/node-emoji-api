pipeline {
    agent any

    environment {
        // Define environment variables if needed
        DOCKER_IMAGE = "node-emoji-api" // Name of your Docker image
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/arjunpathy/node-emoji-api.git' 
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                script {
                    // Stop and remove the old container if it's running
                    sh "docker stop ${DOCKER_IMAGE} || true"
                    sh "docker rm ${DOCKER_IMAGE} || true"
                    
                    // Run the new Docker container
                    dockerImage.run("-p 3000:3000 --name ${DOCKER_IMAGE}")
                }
            }
        }
    }

    post {
        success {
            echo "Deployment successful!"
        }
        failure {
            echo "Deployment failed!"
        }
    }
}
