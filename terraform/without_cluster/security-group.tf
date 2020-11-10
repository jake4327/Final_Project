resource "aws_security_group" "bastion-host-sg" {
  name        = "all-ssh-devs-only"
  description = "Allow TLS inbound traffic"
  vpc_id      = aws_vpc.final_project.id

  ingress {
    description = "SSH from devs"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.final_project.cidr_block, "92.40.182.224/32", "82.16.246.244/32", "109.146.211.95/32", "81.107.84.61/32"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "bastion-host"
  }
}

resource "aws_security_group" "public-sg" {
  name        = "allow_tls"
  description = "Allow TLS inbound traffic"
  vpc_id      = aws_vpc.final_project.id

  ingress {
    description = "HTTP open port"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.final_project.cidr_block, "0.0.0.0/0"]
  }

  ingress {
    description = "open for testing spring boot"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.final_project.cidr_block, "0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "spring-boot-port"
  }
}

resource "aws_security_group" "private-sg" {
  name        = "private-sub-sg"
  description = "Allows traffic on 8080 for jenkins"
  vpc_id      = aws_vpc.final_project.id

  ingress {
    description = "Jenkins"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.final_project.cidr_block,"92.40.189.117/32","82.16.246.244/32", "109.146.211.95/32", "81.107.84.61/32"]
  }

  ingress {
    description = "SSH other VMs in VPC"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [aws_vpc.final_project.cidr_block]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "allow jenkins"
  }
}
