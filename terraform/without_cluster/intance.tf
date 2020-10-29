
resource "aws_instance" "jenkins" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = "final_project"
  subnet_id              = aws_subnet.private[0].id
  vpc_security_group_ids = [aws_security_group.private-sg.id]
  tags                   = { Name = "jenkins" }
}
resource "aws_instance" "artifact-repo" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = "final_project"
  subnet_id              = aws_subnet.private[0].id
  vpc_security_group_ids = [aws_security_group.private-sg.id]
  tags                   = { Name = "artifact-repo" }

}
resource "aws_instance" "test" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = "final_project"
  subnet_id              = aws_subnet.private[0].id
  vpc_security_group_ids = [aws_security_group.private-sg.id]
  tags                   = { Name = "test" }
}

resource "aws_instance" "bastion-host" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = "final_project"
  associate_public_ip_address = true
  subnet_id              = aws_subnet.public[0].id
  vpc_security_group_ids = [aws_security_group.bastion-host-sg.id]
  tags                   = { Name = "bastion-host" }
}



