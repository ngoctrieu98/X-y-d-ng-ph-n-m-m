package daos;

import java.util.List;

import entities.PhieuThue;

public interface PhieuThueDAO{

	List<PhieuThue> listPhieuThueTheoMaKhachHang(int maKhachHang);

}
