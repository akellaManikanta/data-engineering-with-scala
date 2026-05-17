output "public_ips" {
  value = {
    for idx, instance in aws_instance.kafka_nodes :
    local.instance_names[idx] => instance.public_ip
  }
}

output "private_ips" {
  value = {
    for idx, instance in aws_instance.kafka_nodes :
    local.instance_names[idx] => instance.private_ip
  }
}