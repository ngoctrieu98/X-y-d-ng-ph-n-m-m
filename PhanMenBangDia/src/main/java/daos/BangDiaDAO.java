package daos;

import java.util.List;

import entities.BangDia;


public interface BangDiaDAO  {
	List<BangDia> timTinhTrangTieuDe(int maTieuDe);
	List<BangDia> dsTieuDeCoBangDiaTrongKho(int maTieuDe);
	List<BangDia> dsTieuDeDangDuocThue(int maTieuDe);
	List<BangDia> dsTieuDeDangDuocDat(int maTieuDe);
	int getCountBDTheoKhachHang(int maKH);
	List<BangDia> dsBangDiaDaDatTruocThanhCong(int maKH);
	BangDia timBangDiaCoTheThue(int maBangDia);
}
