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
        stage('Unit and Integration Testing') {
            steps {
                    script{
                                sh '''
                                ssh ubuntu@10.0.3.69  <<EOF
                                rm -rf Final_Project
                                git clone -b development https://github.com/jake4327/Final_Project.git
                                cd Final_Project
                                mvn test >> test.txt
EOF
                                '''
                            }
                        }                     
        }

        stage('SSH into NEXUS and Build Backend image') {
            steps {
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                rm -rf Final_Project
                git clone -b build https://github.com/jake4327/Final_Project.git
                cd Final_Project
EOF
                '''
            }
        }
        
        stage('SSH into NEXUS and Build Frontend image') {
            steps {
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                rm -rf Final_Project
                git clone -b react https://github.com/jake4327/Final_Project.git
                cd Final_Project
                docker build -t jstoneqa/sfia-3-frontend .
EOF
                '''
            }
        }

        stage('SSH into NEXUS and push backend to private repo'){
            steps{
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                docker login -u admin -p password to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80
                docker tag jstoneqa/sfia-3-backend to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80/jstoneqa/sfia-3-backend
                docker push to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80/jstoneqa/sfia-3-backend
EOF
                '''
            }
        }

        stage('SSH into NEXUS and push frontend to private repo'){
            steps{
                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                docker login -u admin -p password to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80
                docker tag jstoneqa/sfia-3-frontend to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80/jstoneqa/sfia-3-frontend
                docker push to-AR-8082-ac14aea09fe210ef.elb.us-east-2.amazonaws.com:80/jstoneqa/sfia-3-frontend
EOF
                '''
            }
        }

        stage ('Kubectl'){
          steps {
              sh '''
              ssh ubuntu@10.0.3.249 <<EOF
              git checkout kubernetes
              kubectl get pods
              kubectl apply -f Final_Project/K8S
EOF
              '''
          }
        }
        
        stage('SSH into NEXUS and deploy images') {
            steps {

                sh '''
                ssh ubuntu@10.0.3.249 <<EOF
                docker run -d -p 5001:5001 --name sfia3-backend  jstoneqa/sfia-3-backend
                sleep 20
                docker run -d -p 3000:3000 --name sfia3-frontend  jstoneqa/sfia-3-frontend
EOF
                '''
                sh "docker run -d -p 5001:5001 --name sfia3  jstoneqa/sfia-3-backend"
            }
        }
    }
}
