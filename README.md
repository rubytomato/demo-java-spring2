# Spring Boot 2.0 Rest API application

Development environment

* OpenJDK 10.0.2
* Spring Boot 2.0.6
* MySQL CE 5.7.19
* Maven 3.5.4

```text
openjdk version "10.0.2" 2018-07-17
OpenJDK Runtime Environment 18.3 (build 10.0.2+13)
OpenJDK 64-Bit Server VM 18.3 (build 10.0.2+13, mixed mode)
```

## compile

```text
mvn clean package
```

## run

### executable jar

```text
java -jar .\target\demo.jar
```

Specify a profile

```text
java -jar -Dspring.profiles.active=dev .\target\demo.jar
```

### spring boot maven plugin

```text
mvn spring-boot:run
```

Specify a profile

```text
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Memo API

#### get

```text
curl -v "http://localhost:9000/app/memo/1"
```

```text
curl -v "http://localhost:9000/app/memo/list"
```

```text
curl -v "http://localhost:9000/app/memo/list?page=0&size=5"
```

#### post

```text
curl -v -H "Content-Type:application/json" -d "{\"title\": \"new memo title\", \"description\": \"new memo description\", \"done\": false }" -X POST "http://localhost:9000/app/memo"
```

or

```text
curl -v -H "Content-Type:application/json" -d @new_memo.json -X POST "http://localhost:9000/app/memo"
```

new_memo.json

```json
{
    "title": "new memo title",
    "description": "new memo description",
    "done": false
}
```

#### put

```text
curl -v -H "Content-Type:application/json" -d "{\"title\": \"update memo title\", \"description\": \"update memo description\", \"done\": false }" -X PUT "http://localhost:9000/app/memo/1"
```

#### delete

```text
curl -v -X DELETE "http://localhost:9000/app/memo/1"
```

### Customer API

#### get

```text
curl -v "http://localhost:9000/app/customer/1"
```

#### put

```text
curl -v -H "Content-Type:application/json" -X PUT "localhost:9000//app/customer/1" -d "{\"nickName\": \"new_nickname\"}"
```


## database

**database**

resources/scripts/sql/create_database.sql

```sql
CREATE DATABASE IF NOT EXISTS demo_db
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;
```

**user**

resources/scripts/sql/create_user.sql

```sql
CREATE USER IF NOT EXISTS 'demo_user'@'localhost'
  IDENTIFIED BY 'demo_pass'
  PASSWORD EXPIRE NEVER;

GRANT ALL ON demo_db.* TO 'demo_user'@'localhost';
```

### Using Memo API

**table**

resources/scripts/sql/create_memo_table.sql

switch to demo_db.

```sql
DROP TABLE IF EXISTS memo;

CREATE TABLE IF NOT EXISTS memo (
  id BIGINT AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  done BOOLEAN NOT NULL DEFAULT FALSE,
  updated TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET = utf8mb4,
COLLATE utf8mb4_general_ci;
```

**create test data**

resources/scripts/sql/insert_memo_data.sql

```sql
INSERT INTO memo (id, title, description, done, updated) VALUES
  (1, 'memo shopping', 'memo1 description', false, '2018-01-04 12:01:00'),
  (2, 'memo job', 'memo2 description', false, '2018-01-04 13:02:10'),
  (3, 'memo private', 'memo3 description', false, '2018-01-04 14:03:21'),
  (4, 'memo job', 'memo4 description', false, '2018-01-04 15:04:32'),
  (5, 'memo private', 'memo5 description', false, '2018-01-04 16:05:43'),
  (6, 'memo travel', 'memo6 description', false, '2018-01-04 17:06:54'),
  (7, 'memo travel', 'memo7 description', false, '2018-01-04 18:07:05'),
  (8, 'memo shopping', 'memo8 description', false, '2018-01-04 19:08:16'),
  (9, 'memo private', 'memo9 description', false, '2018-01-04 20:09:27'),
  (10,'memo hospital', 'memoA description', false, '2018-01-04 21:10:38')
;
```

### using Customer API 

**table**

resources/scripts/sql/v2_schema.sql
resources/scripts/sql/v2_constraint.sql

```sql
show tables;

+-------------------+
| Tables_in_demo_db |
+-------------------+
| category          |
| customer          |
| customer_order    |
| customer_review   |
| item              |
| item_stock        |
| location          |
| memo              |
+-------------------+
8 rows in set (0.03 sec)
```

**create test data**

resources/scripts/sql/v2_proc_create_test_data.sql

compile

```sql
source v2_proc_create_test_data.sql
```

This process takes about 90 - 120 minutes.

```sql
call proc_create_test_data();
```

results

```text
demo_user@localhost [demo_db] > select count(*) from customer;
+----------+
| count(*) |
+----------+
|   250000 |
+----------+
1 row in set (0.13 sec)

demo_user@localhost [demo_db] > select count(*) from customer_order;
+----------+
| count(*) |
+----------+
|  2805940 |
+----------+
1 row in set (2.53 sec)

demo_user@localhost [demo_db] > select count(*) from customer_review;
+----------+
| count(*) |
+----------+
|   131880 |
+----------+
1 row in set (0.03 sec)
```
