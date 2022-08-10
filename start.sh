docker stop  $(docker ps | grep -w simple-api-with-spring | head -n 1  | awk '{print $1;}')
mvn clean install package
docker build -t simple-api-with-spring -f Dockerfile .
docker run --env-file ./envfile -d --rm -p 8088:8088 --name simple-api-with-spring simple-api-with-spring
docker commit  $(docker ps | grep -w simple-api-with-spring | head -n 1  | awk '{print $1;}')  aphiwe2020/simple-api-with-spring:$1
docker push aphiwe2020/simple-api-with-spring:$1
kubectl apply -f deployments/deployment.yaml