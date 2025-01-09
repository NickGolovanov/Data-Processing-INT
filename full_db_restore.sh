#!/bin/bash

CONTAINER_NAME="db" # Docker container name for PostgreSQL

# Load environment variables from .env file
if [ -f .env ]; then
  export $(cat .env | grep -v '^#' | xargs)
else
  echo "Error: .env file not found!"
  exit 1
fi

# Check if required environment variables are set
if [ -z "$DB_USER" ] || [ -z "$DB_PASSWORD" ] || [ -z "$DB_NAME" ]; then
  echo "Error: One or more required environment variables are missing in the .env file!"
  exit 1
fi

# Check if the user provided a backup file path
if [ $# -ne 1 ]; then
  echo "Usage: $0 <full_path_to_backup_file.sql>"
  exit 1
fi

BACKUP_FILE="$1"

# Check if the backup file exists
if [ ! -f "$BACKUP_FILE" ]; then
  echo "Error: Backup file '$BACKUP_FILE' does not exist."
  exit 1
fi

# Prompt user for confirmation
echo "You are about to restore the database '$DB_NAME' using backup file '$BACKUP_FILE'."
read -p "Are you sure you want to proceed? (yes/no): " CONFIRM
if [ "$CONFIRM" != "yes" ]; then
  echo "Restore operation cancelled."
  exit 0
fi

# Restore the database
echo "Restoring database '$DB_NAME' from backup file '$BACKUP_FILE'..."
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
  docker exec -i $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME < "$BACKUP_FILE"
else
  echo "Error: Docker container '$CONTAINER_NAME' is not running."
  exit 1
fi

# Check restore status
if [ $? -eq 0 ]; then
  echo "Database '$DB_NAME' restored successfully from '$BACKUP_FILE'."
else
  echo "Error: Failed to restore the database."
  exit 1
fi
