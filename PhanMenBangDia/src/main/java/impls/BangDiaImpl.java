package impls;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daos.BangDiaDAO;
import daos.CRUDInterface;
import daos.MyEntityManager;
import entities.BangDia;
import entities.TieuDe;

public class BangDiaImpl implements CRUDInterface<BangDia>, BangDiaDAO {
	private EntityManager em;

	public BangDiaImpl() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean them(BangDia bd) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(bd);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean xoa(BangDia bd) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.remove(em.find(BangDia.class, bd.getMa()));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sua(BangDia bd) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(bd);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BangDia timBangMa(int ma) {
		return em.find(BangDia.class, ma);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BangDia> getDanhSach() {
		return em.createNativeQuery(
				"SELECT ma ,id_khachHang_hienTai ,phi_thue ,phi_tre_han ,so_ngay_duoc_thue ,tinh_trang ,ma_tieu_de FROM bangdia",
				BangDia.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BangDia> timTinhTrangTieuDe(int maTieuDe) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(// t.ma,t.ten,loai,b.ma,b.tinh_trang
				"select * from bangdia b \r\n" + "left join tieude t on b.ma_tieu_de = t.ma\r\n" + "where t.ma ="
						+ maTieuDe,
				BangDia.class);
		List<BangDia> resultList = query.getResultList();
		// resultList.forEach(System.out::println);
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BangDia> dsTieuDeCoBangDiaTrongKho(int maTieuDe) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(// t.ma,t.ten,loai,b.ma,b.tinh_trang
				"select * from bangdia b \r\n" + "left join tieude t on b.ma_tieu_de = t.ma\r\n" + "where t.ma ="
						+ maTieuDe + " AND b.tinh_trang = " + BangDia.TRONG_KHO,
				BangDia.class);
		List<BangDia> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BangDia> dsTieuDeDangDuocThue(int maTieuDe) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(// t.ma,t.ten,loai,b.ma,b.tinh_trang
				"select * from bangdia b \r\n" + "left join tieude t on b.ma_tieu_de = t.ma\r\n" + "where t.ma ="
						+ maTieuDe + " AND b.tinh_trang = " + BangDia.DIA_DANG_THUE,
				BangDia.class);
		List<BangDia> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<BangDia> dsTieuDeDangDuocDat(int maTieuDe) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(// t.ma,t.ten,loai,b.ma,b.tinh_trang
				"select * from bangdia b \r\n" + "left join tieude t on b.ma_tieu_de = t.ma\r\n" + "where t.ma ="
						+ maTieuDe + " AND b.tinh_trang = " + BangDia.DA_DAT,
				BangDia.class);
		List<BangDia> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public int getCountBDTheoKhachHang(int maKH) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(// t.ma,t.ten,loai,b.ma,b.tinh_trang
				"  select * from bangdia bd where id_khachHang_hienTai = "+maKH,
				BangDia.class);
		return query.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BangDia> dsBangDiaDaDatTruocThanhCong(int maKH) {
		return em.createNativeQuery(
				"SELECT * FROM bangdia bd JOIN tieude t ON t.ma = bd.ma_tieu_de JOIN danhsachdattruoc ds ON ds.ma_tieu_de = t.ma WHERE ds.ma_khach_hang = :maKH AND ds.tinh_trang = 0 AND bd.tinh_trang = 2",
				BangDia.class).setParameter("maKH", maKH).getResultList();
	}

	@Override
	public BangDia timBangDiaCoTheThue(int maBangDia) {
		try {
			return (BangDia) em
					.createNativeQuery("SELECT * FROM bangdia bd WHERE bd.tinh_trang = 0 AND bd.ma = :maBangDia",
							BangDia.class)
					.setParameter("maBangDia", maBangDia).getSingleResult();
		} catch (Exception ignored) {
		}
		return null;
	}
}
