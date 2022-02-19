package daos;

import java.util.List;

import entities.DanhSachDatTruoc;


public interface DanhSachDatTruocDAO  {
	List<DanhSachDatTruoc> getDanhSachKHDatTruoc();
	List<DanhSachDatTruoc> getDanhSachKHDatTruocDangChoDia();
	DanhSachDatTruoc timTheoTieuDeVaKhachHang(int maTieuDe, int maKhachHang);
	List<DanhSachDatTruoc> timDSDTTheoMaTieuDe(int maTieuDe);
}
