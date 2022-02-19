package impls;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import daos.CRUDInterface;
import daos.MyEntityManager;
import daos.PhieuThueDAO;
import entities.PhieuThue;

public class PhieuThueImpl implements CRUDInterface<PhieuThue>, PhieuThueDAO {
	private EntityManager em;

	public PhieuThueImpl() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean them(PhieuThue pt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(pt);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean xoa(PhieuThue pt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.remove(em.find(PhieuThue.class, pt.getMa()));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(PhieuThue pt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(pt);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public PhieuThue timBangMa(int ma) {
		return em.find(PhieuThue.class, ma);
	}

	@Override
	public List<PhieuThue> getDanhSach() {
		return em.createNativeQuery("Select * FROM phieuthue", PhieuThue.class).getResultList();
	}

	@Override
	public List<PhieuThue> listPhieuThueTheoMaKhachHang(int maKhachHang) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * from phieuthue where ma_khach_hang = "+maKhachHang,PhieuThue.class).getResultList();
	}
}
