# -------------------- VPC ---------------------------
resource "aws_vpc" "final_project" {
  cidr_block           = var.vpc_cidr
  enable_dns_hostnames = true
  tags = {
    Name = "SFIA3"
    Team = "4"
  }
}

resource "aws_internet_gateway" "terra_igw" {
  vpc_id = aws_vpc.final_project.id
  tags = {
    Name = "final_project"
  }
}

resource "aws_eip" "nat" {
    vpc = true
}

resource "aws_subnet" "public" {
  count             = length(var.subnets_cidr_public)
  vpc_id            = aws_vpc.final_project.id
  cidr_block        = element(var.subnets_cidr_public, count.index)
  availability_zone = element(var.azs, count.index)
  tags = {
    Name = "Public-${count.index + 1}"
  }
}

resource "aws_subnet" "private" {
  count             = length(var.subnets_cidr_private)
  vpc_id            = aws_vpc.final_project.id
  cidr_block        = element(var.subnets_cidr_private, count.index)
  availability_zone = element(var.azs, count.index)
  tags = {
    Name = "Private-${count.index + 1}"
  }
}

resource "aws_route_table" "public_rt" {
  vpc_id = aws_vpc.final_project.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.terra_igw.id
  }
  tags = {
    Name = "publicRouteTable"
  }
}

resource "aws_nat_gateway" "gw" {
  allocation_id = aws_eip.nat.id
  subnet_id = aws_subnet.public[0].id
  tags = {
    Name = "gw NAT"
  }
  depends_on = [aws_internet_gateway.terra_igw]
}

resource "aws_route_table" "private_rt" {
  vpc_id = aws_vpc.final_project.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_nat_gateway.gw.id
  }
  tags = {
    Name = "privateRouteTable"
  }
}

resource "aws_route_table_association" "a" {
  subnet_id      = aws_subnet.public[0].id
  route_table_id = aws_route_table.public_rt.id
}

resource "aws_route_table_association" "b" {
  subnet_id      = aws_subnet.public[1].id
  route_table_id = aws_route_table.public_rt.id
}

resource "aws_route_table_association" "c" {
  subnet_id      = aws_subnet.private[0].id
  route_table_id = aws_route_table.private_rt.id
}
# ------------------- ^VPC^ --------------------------

