package daos;

import java.util.List;

import entities.ChiTietPhieuThue;


public interface ChiTietPhieuThueDAO{
	public List<ChiTietPhieuThue> getDanhSachByKhachHangID(int maKH);

	public List<ChiTietPhieuThue> getDanhSachByKhachHangIDVaDiaID(int maKH, int diaID);

	List<ChiTietPhieuThue> listChiTietPhieuThueTheoPhieuThue(int maPhieuThue);
	List<ChiTietPhieuThue> getDSNoByKhachHangID(int maKH);

	List<ChiTietPhieuThue> getDanhSachByKhachHangIDDangThue(int maKH);

	List<ChiTietPhieuThue> getDanhSachByKhachHangIDVaDiaIDDangThue(int maKH, int diaID);
}
