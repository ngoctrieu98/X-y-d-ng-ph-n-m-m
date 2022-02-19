package impls;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import daos.CRUDInterface;
import daos.KhachHangDAO;
import daos.MyEntityManager;
import entities.KhachHang;


public class KhachHangImpl implements CRUDInterface<KhachHang>, KhachHangDAO{

	private EntityManager em;

	public KhachHangImpl() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean them(KhachHang kh) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(kh);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean xoa(KhachHang kh) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.remove(em.find(KhachHang.class, kh.getMa()));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(KhachHang kh) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(kh);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public KhachHang timBangMa(int ma) {
		return em.find(KhachHang.class, ma);
	}

	@Override
	public List<KhachHang> getDanhSach() {
		return em.createNativeQuery("Select * FROM khachhang", KhachHang.class).getResultList();
	}

	@Override
	public List<KhachHang> listKhachHangCoPhiTreHan() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * FROM khachhang WHERE phi_tre_han > 0", KhachHang.class).getResultList();
	}
}
