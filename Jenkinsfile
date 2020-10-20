pipeline {
    agent any
    stages {
        stage('Update Jenkins Server') {
            steps {
                sh '''
                sudo -tt apt update
                '''
            }
        }
        stage('Clone repo') {
            steps {
                sh '''
                git clone https://github.com/jake4327/Final_Project.git
                '''
            }
        }
        stage('Build docker images') {
            steps {
                script{
                     cd Final_Project
                     image = docker.build("alexcarley98/sfia3springboot")
                     }
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