provider "aws" {
  version                 = "~> 2.0"
  region                  = "eu-west-2b"
  shared_credentials_file = "~/.aws/credentials"
}

resource "aws_instance" "jenkins" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-a713e7ed"]
  tags                   = { Name = "jenkins" }
}
resource "aws_instance" "test" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-a713e7ed"]
  tags                   = { Name = "test" }

}
resource "aws_key_pair" "default" {
  key_name   = "aws-test"
  public_key = file("~/.ssh/id_rsa.pub")
}