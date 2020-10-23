# -------------------- VPC ---------------------------
resource "aws_vpc" "main" {
  cidr_block       = var.vpc_cidr
  instance_tenancy = "default"
  tags = {
    Name = "SFIA3"
    Team = "4"
  }
}

resource "aws_internet_gateway" "terra_igw" {
  vpc_id = aws_vpc.main.id
  tags = {
    Name = "main"
  }
}

resource "aws_subnet" "public" {
  count = length(var.subnets_cidr_public)
  vpc_id = aws_vpc.main.id
  cidr_block = element(var.subnets_cidr_public,count.index)
  availability_zone = element(var.azs-public,count.index)
  tags = {
    Name = "Public-${count.index+1}"
  }
}

resource "aws_subnet" "private" {
  count = length(var.subnets_cidr_private)
  vpc_id = aws_vpc.main.id
  cidr_block = element(var.subnets_cidr_private,count.index)
  availability_zone = element(var.azs-private,count.index)
  tags = {
    Name = "Private-${count.index+1}"
  }
}

resource "aws_route_table" "public_rt" {
  vpc_id = aws_vpc.main.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.terra_igw.id
  }
  tags = {
    Name = "publicRouteTable"
  }
}

resource "aws_route_table_association" "a" {
  subnet_id      = aws_subnet.public[0].id
  route_table_id = aws_route_table.public_rt.id
}
# ------------------- ^VPC^ --------------------------

