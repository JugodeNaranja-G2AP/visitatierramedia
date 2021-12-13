package services;

public interface GenericLogin<T> {
	
	public T login(String parametro, String password);

}
