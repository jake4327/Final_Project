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
                git clone -b aws-test https://github.com/jake4327/Final_Project.git
                cd Final_Project
                '''
            }
        }

        stage('Install Docker') {
            steps {
                sh '''
                curl https://get.docker.com | sudo bash
                sudo usermod -aG docker $(whoami)
                sudo apt update
                sudo apt install -y curl jq
                version=$(curl -s https://api.github.com/repos/docker/compose/releases/latest | jq -r '.tag_name')
                sudo curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
                sudo chmod +x /usr/local/bin/docker-compose
                sudo chmod 666 /var/run/docker.sock
                '''
            }
        }
        
        // stage('Build  images') {
        //     steps {
        //             script{
        //                         sh '''
        //                         cd Final_Project
        //                         docker build . -t jstoneqa/sfia-3-backend
        //                         docker push jstoneqa/sfia-3-backend
        //                         '''
        //                     }
        //                 }                     
        //     }
        // }

        stage('ssh into NEXUS and push to private repo') {
            steps {
                    sh ''' 
                    ssh ubuntu@10.0.3.249 <<EOF
                    docker login -u admin -p password to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80
                    docker pull jstoneqa/sfia-3-backend
                    docker tag jstoneqa/sfia-3-backend to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80/jstoneqa/sfia-3-backend
                    docker push to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80/jstoneqa/sfia-3-backend
EOF           
                    '''                   
            }
        }
        
        stage('Deploy using docker') {
            steps {
                sh "docker run -d -p 5001:5001 --name sfia3-sb apanj/sfia3"
            }
        }
    }
}
