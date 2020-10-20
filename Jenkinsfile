pipeline {
    agent any
    stages {
        stage('Update Jenkins Server') {
            steps {
                sh "sudo apt update"
            }
        }
        stage('Build docker images') {
            steps {
                image = docker.build("alexcarley98/sfia3springboot")
            }
        }
//         stage('Put docker images in artifact repo') {
//             steps {
//                 //docker push IMAGE_NAME
//             }
//         }
//         stage('Run tests') {
//             steps {
//                 //ssh into TESTVM << EOF
//                 //          ????
//                 //EOF
//             }
//         }
        stage('Deploy using docker') {
            steps {
                sh "docker run -d -p 8080:8080 --name sfia3springboot alexcarley98/sfia3springboot"
            }
        }
    }
}