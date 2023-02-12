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

http localhost:8080/api/v1/stress-test-chave-com-uuid/200000

http localhost:8080/api/v1/stress-test-chave-com-ulid/200000
```

## Testes chamando as APIs

Carga: 100.000 registros/tabela

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

| tipo | hits | tempo (s) |
|-|-|-| 
| Numb | 200.000 | 41,059 |
| Numb | 200.000 | 40,061 |
| Numb | 200.000 | 40,382 |
| Numb | 200.000 | 40,329 |
| Numb | 200.000 | 40,154 |
| UUID | 200.000 | 40,520 |
| UUID | 200.000 | 40,218 |
| UUID | 200.000 | 40,118 |
| UUID | 200.000 | 41,409 |
| UUID | 200.000 | 41,946 |
| ULID | 200.000 | 46,306 |
| ULID | 200.000 | 43,182 |
| ULID | 200.000 | 43,897 |
| ULID | 200.000 | 43,533 |
| ULID | 200.000 | 41,007 |

## Segundo teste

Carga: 1.000.000 registros/tabela

### Database 

Rotina melhorada para limpar o cache do Oracle:

```
SQL> 
DECLARE
  n_counter NUMBER := 10000;
begin
  WHILE n_counter > 1
  LOOP
   APEX_UTIL.PAUSE(1);
   execute immediate 'ALTER SYSTEM FLUSH SHARED_POOL';
   n_counter := n_counter - 1;
  END LOOP;
END;
/
```

### Database 1.000.000 linhas/tabela

| tipo | hits | tempo (s) |
|-|-|-| 
| Numb |   500.000 |  91,608 |
| Numb |   500.000 |  98,500 |
| Numb |   500.000 |  74,366 |
| Numb | 2.000.000 | 485,504 |
| Numb | 2.000.000 | 494,008 |
| Numb | 2.000.000 | 467,540 |
| UUID |   500.000 | 116,248 |
| UUID |   500.000 |  88,803 |
| UUID |   500.000 |  88,557 |
| UUID | 2.000.000 | 441,392 |
| UUID | 2.000.000 | 440,073 |
| UUID | 2.000.000 | 405,196 |
| ULID |   500.000 |  98,633 |
| ULID |   500.000 |  78,329 |
| ULID |   500.000 |  84,237 |
| ULID | 2.000.000 | 423,239 |
| ULID | 2.000.000 | 423,199 |
| ULID | 2.000.000 | 480,033 |


## Referências

* [Oracle Number](https://www.oracletutorial.com/oracle-basics/oracle-number-data-type/) 
* [UUID](https://en.wikipedia.org/wiki/Universally_unique_identifier) 
* [ULID spec](https://github.com/ulid/spec) - [ULID Java](https://github.com/huxi/sulky/tree/master/sulky-ulid) 
* [CopyOnWriteArrayList](https://www.baeldung.com/java-copy-on-write-arraylist)
* [Caching a table in Memory](https://asktom.oracle.com/pls/apex/f?p=100:11:0::::P11_QUESTION_ID:253415112676)
* [Measure Elapsed Time in Java](https://www.baeldung.com/java-measure-elapsed-time)
* [IDs : Integer Vs UUID Vs ULID](https://www.solwey.com/posts/ids-integer-vs-uuid-vs-ulid)

