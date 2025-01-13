docker compose down
cd ./backend/
.\mvnw clean package
cd ..
docker compose build --no-cache
docker compose up -d