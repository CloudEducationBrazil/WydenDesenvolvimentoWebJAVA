public interface BaseDAO {
	public void save(Object obj);
	public void update(Object obj);
	public void delete(int id);
	public Object getById(int id);
	public List<Object> getAll();
}