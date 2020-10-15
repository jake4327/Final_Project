provider "aws" {
  version                 = "~> 2.0"
  region                  = "eu-west-1"
  shared_credentials_file = "~/.aws/credentials"
}

# ----------------------------------------------------
# ----------------------------------------------------

# -------------------- VPC ---------------------------
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
  tags = {
    Name = "SFIA3"
    Team = "4"
  }
}

data "aws_internet_gateway" "default" {
  filter {
    name   = "attachment.vpc-id"
    values = [var.vpc_id]
  }
}

data "aws_network_acls" "example" {
  vpc_id = var.vpc_id
}
# ------------------- ^VPC^ --------------------------

# ----------------------------------------------------
# ----------------------------------------------------

# --------------- Public subnet ----------------------
resource "aws_subnet" "public" {
  vpc_id     = data.aws_security_group.cluster.vpc_id
  cidr_block = "10.0.1.0/24"
  tags = {
    Name = "Public Subnet"
  }
}

data "aws_security_group" "cluster" {
  id = var.sg_cluster
}

data "aws_security_group" "bastion_sg" {
  id = var.sg_bastion_host

}

data "aws_nat_gateway" "default" {
  subnet_id = aws_subnet.public.id
}

# Bastion host to gain access to the login page.
resource "aws_instance" "bastion_host" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  tags                   = { Name = "Bastion Host" }
  associate_public_ip_address = true

}

#Jenkins machine
resource "aws_instance" "jenkins" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "Jenkins" }
}


# -------------- ^Public subnet^ ---------------------

# ----------------------------------------------------
# ----------------------------------------------------

# ---------------- Private subnet --------------------
resource "aws_subnet" "private" {
  vpc_id     = data.aws_security_group.RDS_Jenkins_sg.vpc_id
  cidr_block = "10.0.2.0/24"
  tags = {
    Name = "Private subnet"
  }
}

data "aws_security_group" "RDS_Jenkins_sg" {
  id = var.sg_Jenkins_RDS
}

#Test server
resource "aws_instance" "test" {
  ami                    = var.ami-id
  instance_type          = var.instance-type
  key_name               = var.pem-key
  vpc_security_group_ids = ["sg-09584f38b14387fe5"]
  tags                   = { Name = "Test" }

}

# RDS instance for production
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

# ---------------- ^Private subnet^ ------------------

# ----------------------------------------------------
# ----------------------------------------------------

# Key pair - will make new key pair id_rsa when apply
resource "aws_key_pair" "default" {
  key_name   = "example"
  public_key = file("~/.ssh/id_rsa.pub")
}

