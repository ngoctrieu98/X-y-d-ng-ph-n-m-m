package impls;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
//import javax.rmi.CORBA.Tie;

import daos.CRUDInterface;
import daos.MyEntityManager;
import daos.TieuDeDAO;
import entities.BangDia;
import entities.TieuDe;


public class TieuDeImpl implements CRUDInterface<TieuDe>, TieuDeDAO {

	private EntityManager em;

	public TieuDeImpl() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean them(TieuDe td) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(td);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean xoa(TieuDe td) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();

			List<TieuDe> ds = em.createNativeQuery(
					"SELECT ma ,id_khachHang_hienTai ,phi_thue ,phi_tre_han ,so_ngay_duoc_thue ,tinh_trang ,ma_tieu_de FROM bangdia WHERE ma_tieu_de = ?",
					TieuDe.class).setParameter(1, td.getMa()).getResultList();

			if (ds.size() != 0)
				return false;
			em.remove(em.find(TieuDe.class, td.getMa()));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(TieuDe td) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(td);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TieuDe timBangMa(int ma) {
		return em.find(TieuDe.class, ma);
	}

	@Override
	public TieuDe timBangTen(String ten) {
		return (TieuDe) em.createNativeQuery("SELECT ma ,loai ,ten FROM tieude WHERE ten LIKE ?", TieuDe.class)
				.setParameter(1, ten).getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TieuDe> getDanhSach() {
		return em.createNativeQuery("Select * FROM tieude", TieuDe.class).getResultList();
	}

}
