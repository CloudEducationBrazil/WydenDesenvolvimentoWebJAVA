-- API REST - por recurso
@RestController
@RequestMapping(value = "/users")
public class UserController {

-- Componente  injeção de dependência      
@Autowired
public UserRepository userRepository;
	
-- endpoint Método Get - Consultar
@GetMapping
public List<UserDTO> getAllUser(){}

-- endpoint Método Get por Id	
@GetMapping(value="/{id}")
public User getByUserId(@PathVariable Long id){}

-- endpoint Médodo Post - Incluir por Id	
@PostMapping
public void createUser (@RequestBody UserDTO body) {}

-- endpoint Método Alterar	
@PutMapping(value = "/{idUser}/replacement")
public void updateUser (@PathVariable Long idUser, @RequestBody UserDTO body) {}

-- endpoint Método Deletar	
@DeleteMapping(value = "/{idUser}")
public void deleteUser (@PathVariable Long idUser) {}

