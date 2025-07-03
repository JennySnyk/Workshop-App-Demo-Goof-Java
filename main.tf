provider "aws" {
  region = "us-east-1"
}

# Vulnerable S3 bucket with public read access
resource "aws_s3_bucket" "vulnerable_bucket" {
  bucket = "my-vulnerable-s3-bucket-for-demo"
  acl    = "public-read"
}

# Unencrypted SQS queue
resource "aws_sqs_queue" "unencrypted_queue" {
  name = "my-unencrypted-queue"
}
