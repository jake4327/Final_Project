resource "aws_db_instance" "production" {
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
resource "aws_db_instance" "test" {
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
