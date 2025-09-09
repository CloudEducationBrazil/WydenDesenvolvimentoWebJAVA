Criar classe User:

- Atributos
private Long id 
private String nome
private Integer idade

- Constructor
Criar constructor VAZIO
Criar constructor com parâmetros

- Métodos de Acesso
Criar Gets/Setters

- Método de Impressão do Objeto
Criar o método toString

- Método de hashCode do Objeto
Criar o método hashCode e equals

- Por fim vamos mapear, ORM, utilizando as annotations para associar com o BD
@Entities
@TableName(name="tb_user")

Demais annotations para os atributos
- Definir no atributo Id
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

-- Alterar nome de campo
@Column(columnDefinition = "TEXT")