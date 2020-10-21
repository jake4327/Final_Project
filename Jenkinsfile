pipeline {
    agent any
    stages {
        stage('Update Jenkins Server') {
            steps {
                sh '''
                sudo apt update
                '''
            }
        }
        stage('Clone repo') {
            steps {
                sh '''
                rm -rf Final_Project
                git clone https://github.com/jake4327/Final_Project.git
                cd Final_Project

                '''
            }
        }
        stage('Install Docker') {
            steps {
                sh '''
                curl https://get.docker.com | sudo bash
                sudo usermod -aG docker $(whoami)
                sudo chmod 666 /var/run/docker.sock
                sudo apt update
                '''
            }
        }

        stage('Build docker images') {
            steps {
                script{
                     sh "docker pull apanj/sfia3"
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
                sh "docker run -d -p 5001:5001 --name sfia3springboot apanj/sfia3"
            }
        }
    }
}