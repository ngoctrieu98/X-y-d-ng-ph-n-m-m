package impls;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import daos.CRUDInterface;
import daos.ChiTietPhieuThueDAO;
import daos.MyEntityManager;
import daos.PhieuThueDAO;
import entities.ChiTietPhieuThue;
import entities.PhieuThue;

public class ChiTietPhieuThueImpl implements CRUDInterface<ChiTietPhieuThue>, ChiTietPhieuThueDAO {
	private EntityManager em;

	public ChiTietPhieuThueImpl() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean them(ChiTietPhieuThue ctpt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(ctpt);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean xoa(ChiTietPhieuThue ctpt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.remove(em.find(ChiTietPhieuThue.class, ctpt.getMa()));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(ChiTietPhieuThue ctpt) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(ctpt);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ChiTietPhieuThue timBangMa(int ma) {
		return em.find(ChiTietPhieuThue.class, ma);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChiTietPhieuThue> getDanhSach() {
		return em.createNativeQuery("SELECT * FROM chitietphieuthue", ChiTietPhieuThue.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChiTietPhieuThue> getDanhSachByKhachHangID(int maKH) {
		return em.createNativeQuery(
				"SELECT chitietphieuthue.ma_bang_dia ,chitietphieuthue.ma_phieu_thue ,chitietphieuthue.ngay_tra ,chitietphieuthue.tinh_trang FROM chitietphieuthue INNER JOIN phieuthue ON chitietphieuthue.ma_phieu_thue = phieuthue.ma  WHERE phieuthue.ma_khach_hang=? ",
				ChiTietPhieuThue.class).setParameter(1, maKH).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChiTietPhieuThue> getDanhSachByKhachHangIDDangThue(int maKH) {
		return em.createNativeQuery(
				"SELECT chitietphieuthue.ma_bang_dia ,chitietphieuthue.ma_phieu_thue ,chitietphieuthue.ngay_tra ,chitietphieuthue.tinh_trang FROM chitietphieuthue INNER JOIN phieuthue ON chitietphieuthue.ma_phieu_thue = phieuthue.ma  WHERE phieuthue.ma_khach_hang=? AND chitietphieuthue.tinh_trang = 0",
				ChiTietPhieuThue.class).setParameter(1, maKH).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChiTietPhieuThue> getDanhSachByKhachHangIDVaDiaID(int maKH, int diaID) {
		return em.createNativeQuery(
				"SELECT chitietphieuthue.ma_bang_dia ,chitietphieuthue.ma_phieu_thue ,chitietphieuthue.ngay_tra ,chitietphieuthue.tinh_trang FROM chitietphieuthue INNER JOIN phieuthue ON chitietphieuthue.ma_phieu_thue = phieuthue.ma  WHERE phieuthue.ma_khach_hang=? AND chitietphieuthue.ma_bang_dia=?",
				ChiTietPhieuThue.class).setParameter(1, maKH).setParameter(2, diaID).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChiTietPhieuThue> getDanhSachByKhachHangIDVaDiaIDDangThue(int maKH, int diaID) {
		return em.createNativeQuery(
				"SELECT chitietphieuthue.ma_bang_dia ,chitietphieuthue.ma_phieu_thue ,chitietphieuthue.ngay_tra ,chitietphieuthue.tinh_trang FROM chitietphieuthue INNER JOIN phieuthue ON chitietphieuthue.ma_phieu_thue = phieuthue.ma  WHERE phieuthue.ma_khach_hang=? AND chitietphieuthue.ma_bang_dia=? AND chitietphieuthue.tinh_trang = 0",
				ChiTietPhieuThue.class).setParameter(1, maKH).setParameter(2, diaID).getResultList();
	}
	@Override
	public List<ChiTietPhieuThue> listChiTietPhieuThueTheoPhieuThue(int maPhieuThue) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("Select * from chitietphieuthue where ma_phieu_thue =" +maPhieuThue,ChiTietPhieuThue.class).getResultList();
	}
	@Override
	public List<ChiTietPhieuThue> getDSNoByKhachHangID(int maKH) {
		return em.createNativeQuery(
				"SELECT chitietphieuthue.ma_bang_dia ,chitietphieuthue.ma_phieu_thue ,chitietphieuthue.ngay_tra ,chitietphieuthue.tinh_trang FROM chitietphieuthue\r\n" + 
				" INNER JOIN phieuthue ON chitietphieuthue.ma_phieu_thue = phieuthue.ma  \r\n" + 
				" INNER JOIN bangdia ON chitietphieuthue.ma_bang_dia = bangdia.ma\r\n" + 
				" WHERE phieuthue.ma_khach_hang= ? AND chitietphieuthue.tinh_trang = 1 \r\n" + 
				" Order by bangdia.phi_tre_han ASC",
				ChiTietPhieuThue.class).setParameter(1, maKH).getResultList();
	}


}
