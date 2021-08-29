wget -q $1 -O docker-stack-user.yml
echo "Deploying new user service stack"
docker stack rm user
docker stack deploy --compose-file docker-stack-user.yml --with-registry-auth user