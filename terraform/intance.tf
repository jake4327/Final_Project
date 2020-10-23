
resource "aws_instance" "jenkins" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "jenkins" }
}
resource "aws_instance" "artifact-repo" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "artifact-repo" }

}
resource "aws_instance" "test" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "test" }
}
resource "aws_instance" "bastion-host" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "test" }
}

/*
resource "aws_key_pair" "default" {
  key_name   = "final_project"
  public_key = file("~/.ssh/id_rsa.pub")
}*/
