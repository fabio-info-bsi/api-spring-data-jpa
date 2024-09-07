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
- Testes:
  - Unit
  - Integration
  - BDD - Behavior Driven Development (Desenvolvimento Orientado a Comportamento)
    - classe `org.mockito.BDDMockito.*`.
      - given() & willReturn()  
  - @Order() para métodos e classes
    - @TestClassOrder(ClassOrderer.OrderAnnotation.class)
    - @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  - @MockBean, @Mock, @InjectMocks

## Atenção
> Ao implementar métodos que "alteram" dados, se faz necessário utilizar `@Modifying`.

> É interessante utilizar a abordagem de definir query por meio da `@Query` para que a aplicação não precise converter o método em query (Spring Data convetion)

> @MockBean somente usado em Spring.
