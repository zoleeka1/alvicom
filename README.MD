Alvicom számára tesztfeladat megoldása
- első paraméter futtatáskor a generálandó tesztállomány helye (alapértelmezetten, ha nem adjuk meg: c:/Temp/json)
- második paraméter egy egész szám, mely a generálandó tranzakciók számát adja meg (alapértelmezetten, ha nem adjuk meg: 1000)

A program egyszer fut le, és leáll az összes érték feldolgozása után, 10 soronként számlánként megjelennek a tranzakciók.

Futtatás: java -jar alvicom-dz-jar.jar "c:/Temp" 20

Deák Zoltán / zoleeka1@gmail.com