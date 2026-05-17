# ---------------------------------
# GET DEFAULT VPC
# ---------------------------------
data "aws_vpc" "default" {
  default = true
}

# ---------------------------------
# GET DEFAULT SUBNETS
# ---------------------------------
data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

# ---------------------------------
# SECURITY GROUP
# ---------------------------------
resource "aws_security_group" "kafka_sg" {
  name   = "kafka-learning-sg"
  vpc_id = data.aws_vpc.default.id

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Kafka Broker"
    from_port   = 9092
    to_port     = 9092
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Kafka Controller"
    from_port   = 9093
    to_port     = 9093
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# ---------------------------------
# AMAZON LINUX AMI
# ---------------------------------
data "aws_ami" "amazon_linux" {
  most_recent = true

  owners = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-*-x86_64"]
  }
}

# ---------------------------------
# INSTANCE NAMES
# ---------------------------------
locals {
  instance_names = [
    "broker-1",
    "broker-2",
    "controller-1"
  ]
}

# ---------------------------------
# EC2 INSTANCES
# ---------------------------------
resource "aws_instance" "kafka_nodes" {
  count = 3

  ami           = data.aws_ami.amazon_linux.id
  instance_type = "t2.small"

  subnet_id = data.aws_subnets.default.ids[0]

  vpc_security_group_ids = [
    aws_security_group.kafka_sg.id
  ]

  key_name = var.key_name

  associate_public_ip_address = true

  tags = {
    Name = local.instance_names[count.index]
  }
}