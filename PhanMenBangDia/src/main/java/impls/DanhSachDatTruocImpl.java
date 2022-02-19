package impls;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import daos.CRUDInterface;
import daos.DanhSachDatTruocDAO;
import daos.MyEntityManager;
import entities.DanhSachDatTruoc;


public class DanhSachDatTruocImpl implements CRUDInterface<DanhSachDatTruoc>,DanhSachDatTruocDAO {

	private EntityManager em;

	public DanhSachDatTruocImpl() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean them(DanhSachDatTruoc dt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(dt);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean xoa(DanhSachDatTruoc dt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.remove(em.find(DanhSachDatTruoc.class, dt.getMa()));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(DanhSachDatTruoc dt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(dt);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Không tìm được mã kiểu này
	 */
	@Override
	public DanhSachDatTruoc timBangMa(int ma) {
		return null;
	}

	@Override
	public List<DanhSachDatTruoc> getDanhSach() {
		return em.createQuery("FROM danhsachdattruoc", DanhSachDatTruoc.class).getResultList();
	}

	@Override
	public List<DanhSachDatTruoc> getDanhSachKHDatTruoc() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * FROM danhsachdattruoc WHERE tinh_trang <> 0 Order by ngay_dat_truoc  ASC", DanhSachDatTruoc.class).getResultList();

	}

	@Override
	public DanhSachDatTruoc timTheoTieuDeVaKhachHang(int maTieuDe, int maKhachHang) {
		// TODO Auto-generated method stub
		try {
			return (DanhSachDatTruoc) em.createNativeQuery("Select * FROM danhsachdattruoc WHERE ma_khach_hang = "+maKhachHang+" AND ma_tieu_de = "+maTieuDe, DanhSachDatTruoc.class).getSingleResult();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<DanhSachDatTruoc> getDanhSachKHDatTruocDangChoDia() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * FROM danhsachdattruoc ", DanhSachDatTruoc.class).getResultList();

	}

	@Override
	public List<DanhSachDatTruoc> timDSDTTheoMaTieuDe(int maTieuDe) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * FROM danhsachdattruoc Where ma_tieu_de = "+maTieuDe+" Order by ngay_dat_truoc  ASC", DanhSachDatTruoc.class).getResultList();

	}
	
	

}
