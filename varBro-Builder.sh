init_database="init"
if [ "$1" = "$init_database" ] ; then
  docker stop varbro varbro-sql
  docker rm varbro varbro-sql
  docker rmi varbro varbro-sql
	docker run --name varbro-sql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=varbro -e MYSQL_USER=varbro-user -e MYSQL_PASSWORD=brovar -d mysql
	sleep 10;
else 
	docker container stop varbro
	docker rm varbro
	docker rmi varbro
	echo y | docker system prune --volumes
fi
mvn package
docker build -t varbro .
docker run -p 8080:8080 --name varbro --link varbro-sql:mysql -d varbro
