# Awesome Music

Questo progetto è una semplice applicazione backend per la gestione delle prenotazioni di una sala prove, 
sviluppata come prima iterazione senza interfaccia grafica.

L’applicazione espone alcune API REST che permettono di creare una prenotazione, recuperarla tramite 
un codice e gestire la coda delle prenotazioni da parte di un amministratore (approvazione o rifiuto).

Il progetto è sviluppato in Java 17 con Spring Boot e utilizza Spring Web, Spring Data JPA, Lombok, Validation. 
Per semplicità è stato utilizzato un database H2 in-memory.

La versione Java utilizzata è la 17.

Una volta avviata, l’applicazione è disponibile su http://localhost:8080.  
La console H2 è accessibile su http://localhost:8080/h2-console 
utilizzando come JDBC URL `jdbc:h2:mem:awesomemusic`, user `sa` e password vuota.

Nel progetto è inclusa anche una collection Postman con le principali chiamate API, 
disponibile nella cartella `Collection postman`.

