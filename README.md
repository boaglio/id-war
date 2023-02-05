# id-war - Oracle Number vs UUID vs ULID

Teste de performance de diferentes tipos de IDs.
 

## Criar ambiente

DROP TABLE CHAVE_COM_NUMBER;

DROP TABLE CHAVE_COM_ULID;

DROP TABLE CHAVE_COM_UUID;

CREATE TABLE CHAVE_COM_NUMBER 
   (ID NUMBER PRIMARY KEY , 
	CLIENTE VARCHAR2(255), 
	TEMPO TIMESTAMP); 
	
CREATE TABLE CHAVE_COM_UUID 
   (ID VARCHAR2(16) PRIMARY KEY , 
	CLIENTE VARCHAR2(255), 
	TEMPO TIMESTAMP);
	
CREATE TABLE CHAVE_COM_ULID 
   (ID VARCHAR2(16) PRIMARY KEY , 
	CLIENTE VARCHAR2(255), 
	TEMPO TIMESTAMP);	

## Remover cache

ALTER TABLE CHAVE_COM_NUMBER nocache;

ALTER TABLE CHAVE_COM_UUID nocache;

ALTER TABLE CHAVE_COM_ULID nocache;

## Consultar tabelas
	
SELECT count(0) FROM CHAVE_COM_ULID;
	
SELECT count(0) FROM CHAVE_COM_UUID;

SELECT count(0) FROM CHAVE_COM_NUMBER;

## Chamadas HTTP usando HTTPie

```
http localhost:8080/api/v1/chave-com-number/122

http localhost:8080/api/v1/stress-test-chave-com-number/200000

http localhost:8080/api/v1/stress-test-chave-uuid/200000

http localhost:8080/api/v1/stress-test-chave-com-ulid/200000
```

## Testes chamando as APIs


### Teste no meu desktop

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

### Teste no meu laptop

| tipo | hits | tempo (ms) |
|-|-|-| 
| Numb | 200.000 | 41059 |
| Numb | 200.000 | 40061 |
| Numb | 200.000 | 40382 |
| Numb | 200.000 | 40329 |
| Numb | 200.000 | 40154 |
| UUID | 200.000 | 40520 |
| UUID | 200.000 | 40218 |
| UUID | 200.000 | 40118 |
| UUID | 200.000 | 41409 |
| UUID | 200.000 | 41946 |
| ULID | 200.000 | 46306 |
| ULID | 200.000 | 43182 |
| ULID | 200.000 | 43897 |
| ULID | 200.000 | 43533 |
| ULID | 200.000 | 41007 |


## Referências

* [Oracle Number](https://www.oracletutorial.com/oracle-basics/oracle-number-data-type/) 
* [UUID](https://en.wikipedia.org/wiki/Universally_unique_identifier) 
* [ULID spec](https://github.com/ulid/spec) - [ULID Java](https://github.com/huxi/sulky/tree/master/sulky-ulid) 
* [CopyOnWriteArrayList](https://www.baeldung.com/java-copy-on-write-arraylist)
* [Caching a table in Memory](https://asktom.oracle.com/pls/apex/f?p=100:11:0::::P11_QUESTION_ID:253415112676)
* []()

