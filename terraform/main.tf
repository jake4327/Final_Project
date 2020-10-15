provider "aws" {
  version                 = "~> 2.0"
  region                  = "eu-west-1"
  shared_credentials_file = "~/.aws/credentials"
}

resource "aws_instance" "jenkins" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "jenkins" }
}
resource "aws_instance" "test" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "test" }

}
resource "aws_key_pair" "defualt" {
  key_name   = "example"
  public_key = file("~/.ssh/id_rsa.pub")
}
resource "aws_db_instance" "default" {
  allocated_storage      = 20
  storage_type           = "gp2"
  engine                 = "mysql"
  engine_version         = "5.7"
  instance_class         = "db.t2.micro"
  name                   = "users"
  username               = var.user
  password               = var.password
  skip_final_snapshot    = "true"
  publicly_accessible    = "true"
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
}