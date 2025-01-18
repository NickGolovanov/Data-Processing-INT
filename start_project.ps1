cd ./backend/
.\mvnw clean package
cd ..
docker compose up --build -d
