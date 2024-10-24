#!/bin/bash
localstack status services
awslocal sqs create-queue --queue-name queue
awslocal s3 mb s3://bucket
