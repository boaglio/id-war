# id-war
Teste de performance de diferentes tipos de IDs

# Criar ambiente

DROP TABLE CHAVE_COM_NUMBER ;

DROP TABLE CHAVE_COM_ULID  ;

DROP TABLE CHAVE_COM_UUID  ;

CREATE TABLE CHAVE_COM_NUMBER 
   (ID NUMBER PRIMARY KEY , 
	CLIENTE VARCHAR2(255), 
	TEMPO TIMESTAMP) 
	
CREATE TABLE CHAVE_COM_UUID 
   (ID VARCHAR2(16) PRIMARY KEY , 
	CLIENTE VARCHAR2(255), 
	TEMPO TIMESTAMP) 
	
CREATE TABLE CHAVE_COM_ULID 
   (ID VARCHAR2(16) PRIMARY KEY , 
	CLIENTE VARCHAR2(255), 
	TEMPO TIMESTAMP) 	

# Remover cache

ALTER TABLE CHAVE_COM_NUMBER nocache;

ALTER TABLE CHAVE_COM_UUID nocache;

ALTER TABLE CHAVE_COM_ULID nocache;

# Consultar tabelas
	
SELECT count(0) FROM CHAVE_COM_ULID;
	
SELECT count(0) FROM CHAVE_COM_UUID;

SELECT count(0) FROM CHAVE_COM_NUMBER ;

# Chamadas HTTP usando HTTPie

```
http http://localhost:8080/api/v1/stress-test-chave-com-number/50000

http http://localhost:8080/api/v1/stress-test-chave-uuid/50000

http http://localhost:8080/api/v1/stress-test-chave-com-ulid/50000
```

# Testes chamando as APIs

| tipo | hits | tempo (ms) |
|-|-|-| 
| Numb | 200.000 | 21652 |
| Numb | 200.000 | 21654 |
| Numb | 200.000 | 21174 |
| UUID | 200.000 | 22273 |
| UUID | 200.000 | 19961 |
| UUID | 200.000 | 19192 |
| ULID | 200.000 | 20200 |
| ULID | 200.000 | 37583 |
| ULID | 200.000 | 39487 |
