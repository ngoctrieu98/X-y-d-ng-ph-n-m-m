package service;

import entities.KhachHang;
import impls.KhachHangImpl;

public class NoService {

	KhachHangImpl khDAO;

	public NoService() {
		khDAO = new KhachHangImpl();
	}

	public void tinhNo(int maKhachHang, int noMoi) {
		KhachHang kh = khDAO.timBangMa(maKhachHang);
		int tongNo = kh.getPhiTreHan();
		tongNo += noMoi;
		kh.setPhiTreHan(tongNo);
		khDAO.sua(kh);
	}
	public void huyNo(int maKhachHang, int noCu) {
		KhachHang kh = khDAO.timBangMa(maKhachHang);
		int tongNo = kh.getPhiTreHan();
		tongNo -= noCu;
		kh.setPhiTreHan(tongNo);
		khDAO.sua(kh);
	}


	public int xemNo(int maKhachHang) {
		return khDAO.timBangMa(maKhachHang).getPhiTreHan();
	}
}
