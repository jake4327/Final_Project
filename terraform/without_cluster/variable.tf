variable "aws_region" {
  default = "us-east-2"
}

variable "vpc_cidr" {
  default = "10.0.0.0/16"
}

variable "azs" {
  type = list
  default = ["us-east-2a", "us-east-2b"]
}

variable "user" {
  default = "root"
}

variable "password" {
  default = "password"
}

variable "subnets_cidr_public" {
  type = list
  default = ["10.0.1.0/24", "10.0.2.0/24"]
}

variable "subnets_cidr_private" {
  type = list
  default = ["10.0.3.0/24"]
}

variable "ami-id" {
  default = "ami-0ebc8f6f580a04647"
}

variable "instance-type" {
  default = "t2.micro"
}

variable "pem-key" {
  default = "final_project"
}