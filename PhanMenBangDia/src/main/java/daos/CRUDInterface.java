package daos;

import java.util.List;

public interface CRUDInterface<T> {

	public boolean them(T t);
	public boolean xoa(T t);
	public boolean sua(T t);
	public T timBangMa(int ma);
	public List<T> getDanhSach();
}
