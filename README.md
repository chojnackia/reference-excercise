# reference-excercise
### Skrypt do kompilacji i uruchomienia
run.bat 
### Baza danych
http://www.localhost:8080/h2-console/

username: sa

password: password

### Przykładowe wywołania usług:

##### Dodanie rezerwacji:

curl -X POST www.localhost:8080/bookings/
   -H 'Content-Type: application/json'
   -d '{
    "checkInDate": "2021-08-01",
    "checkOutDate": "2021-08-05",
    "tenantName": "tweet",
    "apartmentName": "Szmaragdowy"
}'

##### Pobranie rezerwacji dla zadanego najemcy (nazwa)
curl -X GET www.localhost:8080/bookings/tenants/morty25
   -H 'Content-Type: application/json'
  
##### Pobranie rezerwacji dla danego obiektu
curl -X GET www.localhost:8080/bookings/szmaragdowy
   -H 'Content-Type: application/json'

##### Zmiana rezerwacji
curl -X PUT www.localhost:8080/bookings/
   -H 'Content-Type: application/json'
   -d '{
    "checkInDate": "2021-06-04",
    "checkOutDate": "2021-06-07",
    "tenantName": "maven32",
    "apartmentName": "Szmaragdowy"
}
