pipeline {
    agent any
    stages {
        stage('Update Jenkins Server') {
            steps {
                sudo apt update
            }
        }
        stage('Build docker images') {
            steps {
                //docker-compose --build
            }
        }
        stage('Put docker images in artifact repo') {
            steps {
                //docker push IMAGE_NAME
            }
        }
        stage('Pull images && Run tests') {
            steps {
                //ssh into TESTVM << EOF
                //          ????
                //EOF
            }
        }
        stage('Deploy to EKS') {
            steps {
                //ssh into TESTVM << EOF
                //          ????
                //EOF
            }
        }
    }
}