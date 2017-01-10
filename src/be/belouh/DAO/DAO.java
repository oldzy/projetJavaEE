package be.belouh.DAO;

import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection c = ConnectionDB.getInstance();

	public abstract boolean create(T obj);

	public abstract boolean delete(T obj);

	public abstract boolean update(T obj);

	public abstract Object find(Object id);
}
