package database;

import java.util.Date;

import daos.CRUDInterface;
import entities.BangDia;
import entities.ChiTietPhieuThue;
import entities.DanhSachDatTruoc;
import entities.KhachHang;
import entities.PhieuThue;
import entities.TieuDe;
import impls.BangDiaImpl;
import impls.ChiTietPhieuThueImpl;
import impls.DanhSachDatTruocImpl;
import impls.KhachHangImpl;
import impls.PhieuThueImpl;
import impls.TieuDeImpl;

public class TestDB {
	public static void main(String[] args) {
		// CREATE
		CRUDInterface<KhachHang> khDAO = new KhachHangImpl();
		CRUDInterface<PhieuThue> ptDAO = new PhieuThueImpl();
		CRUDInterface<TieuDe> tdDAO = new TieuDeImpl();
		CRUDInterface<DanhSachDatTruoc> dsdtDAO = new DanhSachDatTruocImpl();
		CRUDInterface<ChiTietPhieuThue> ctptDAO = new ChiTietPhieuThueImpl();
		CRUDInterface<BangDia> bdDAO = new BangDiaImpl();
		
		KhachHang kh1 = new KhachHang("01245963901", "Lê Thiên Tân", 0, "TP HCM");
		KhachHang kh2 = new KhachHang("01245963902", "Trần Hoàng Nam", 0, "TP HCM");
		KhachHang kh3 = new KhachHang("01245963903", "Nguyễn Thi Phượng", 0, "TP HCM");
		KhachHang kh4 = new KhachHang("0798455595", "Trần Thế Duy", 0, "TP HCM");
		KhachHang kh5 = new KhachHang("01245963908", "Tạ Minh Hiếu", 0, "TP HCM");
		KhachHang kh6 = new KhachHang("01245963904", "Trần Thu Hà", 0, "TP HCM");
		
		

		khDAO.them(kh1);
		khDAO.them(kh2);
		khDAO.them(kh3);
		khDAO.them(kh4);
		khDAO.them(kh5);
		khDAO.them(kh6);

		TieuDe td1 = new TieuDe("Biệt Động Sài Gòn", TieuDe.GAME);
		TieuDe td2 = new TieuDe("Cánh Đồng Hoang", TieuDe.PHIM);
		TieuDe td3 = new TieuDe("War Of Thone", TieuDe.GAME);
		TieuDe td4 = new TieuDe("Cuộc chiến ở Moskva", TieuDe.PHIM);
		TieuDe td5 = new TieuDe("Tom and cherry", TieuDe.GAME);
		TieuDe td6 = new TieuDe("Hiệp Sĩ Bóng Đêm", TieuDe.PHIM);
		TieuDe td7 = new TieuDe("The Conjuring (2013)", TieuDe.GAME);
		TieuDe td8 = new TieuDe("Warcraft", TieuDe.GAME);
		TieuDe td9 = new TieuDe("Thế Thân - Avatar", TieuDe.PHIM);
		
		
		tdDAO.them(td1);
		tdDAO.them(td2);
		tdDAO.them(td3);
		tdDAO.them(td4);	
		tdDAO.them(td5);
		tdDAO.them(td6);
		tdDAO.them(td7);
		tdDAO.them(td8);
		tdDAO.them(td9);
		
		BangDia bd1 = new BangDia(3000, 4000, 1, BangDia.TRONG_KHO, td1);
		BangDia bd2 = new BangDia(3000, 4000, 3, BangDia.TRONG_KHO, td1);
		BangDia bd3 = new BangDia(3000, 4000, 1, BangDia.TRONG_KHO, td1);
		BangDia bd4 = new BangDia(3000, 4000, 3, BangDia.TRONG_KHO, td2);
		BangDia bd5 = new BangDia(3000, 4000, 3, BangDia.TRONG_KHO, td2);
		BangDia bd6 = new BangDia(3000, 4000, 3, BangDia.TRONG_KHO, td3);
		BangDia bd7 = new BangDia(3000, 4000, 3, BangDia.TRONG_KHO, td4);
		
		bdDAO.them(bd1);
		bdDAO.them(bd2);
		bdDAO.them(bd3);
		bdDAO.them(bd4);
		bdDAO.them(bd5);
		bdDAO.them(bd6);
		bdDAO.them(bd7);
		
	}
}
