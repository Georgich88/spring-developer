#!/bin/bash
sudo docker run -it -d --name redis-bitly-demo -p 6379:6379 redis:latest
sudo docker ps
sudo docker
sudo docker exec -it redis-bitly-demo sh