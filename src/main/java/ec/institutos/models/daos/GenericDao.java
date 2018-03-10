package ec.institutos.models.daos;

import java.util.List;

public interface GenericDao<T, ID> {

	public String create(T entity);

	public String update(T entity);

	public String delete(T entity);

	public T find(ID id);

	public List<T> findAll();

	public List<T> findRange(int[] range);

	public Integer count();

}
