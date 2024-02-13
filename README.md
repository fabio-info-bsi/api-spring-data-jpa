# api-spring-data-jpa
Um exemplo de API REST com Spring Boot e Spring Data Jpa

## Usou/Criou:
- Spring Data com JPA
- Classes `CrudRepository`e `Repository`, @Id, @GeneratedValue, @Modifying and @Query
- Lombok (@Slf4j, @AllArgsConstructor, @Getter, @Setter, @Data)
- @Service, @Component, @Controller, @RestController, @RestControllerAdvice, @RequestMapping
- ResponseEntity
- Configs Logback
- H2 Database (`schema.sql` , `data.sql`)
- @Transactional

## Atenção
> Ao implementar métodos que "alteram" dados, se faz necessário utilizar `@Modifying`.

> É interessante utilizar a abordagem de definir query por meio da `@Query` para que a aplicação não precise converter o método em query (Spring Data convetion)
