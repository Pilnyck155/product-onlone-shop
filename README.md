То run Docker container please enter in terminal:

sudo docker run --name postgres-4 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=product_shop -d -p 3002:5432 postgres:alpine