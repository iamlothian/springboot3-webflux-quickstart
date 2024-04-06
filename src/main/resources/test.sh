curl -v -X GET localhost:8080/notes

curl -v -X GET localhost:8080/notes/1

curl -v -X POST localhost:8080/notes -H "Content-Type: application/json" -d '{"note":"This is a note", "createdAt":"2020-01-01T00:00:00Z","lastUpdatedAt":"2020-01-01T00:00:00Z"}'
curl -v -X POST localhost:8080/notes -H "Content-Type: application/json" -d '{"note":"This is a note"}'

curl -v -X PUT localhost:8080/notes/1 -H "Content-Type: application/json" -d '{"version": 0, "note":"This is an edited note"}'
