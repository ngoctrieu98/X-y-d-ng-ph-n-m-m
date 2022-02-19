package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "bangdia")
public class BangDia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int TRONG_KHO = 0;

	public static final int DIA_DANG_THUE = 1;

	public static final int DA_DAT = 2;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ma;

	@Column(name = "phi_thue")
	private int phiThue;

	@Column(name = "phi_tre_han")
	private int phiTreHan;

	@Column(name = "so_ngay_duoc_thue")
	private int soNgayDuocThue;

	@Column(name = "tinh_trang")
	private int tinhTrang;

	@JoinColumn(name = "ma_tieu_de")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private TieuDe maTieuDe;
	
	private int id_khachHang_hienTai;

	public BangDia() {
		super();
	}

	public BangDia( int phiThue, int phiTreHan, int soNgayDuocThue, int tinhTrang, TieuDe maTieuDe) {
		super();
		this.phiThue = phiThue;
		this.phiTreHan = phiTreHan;
		this.soNgayDuocThue = soNgayDuocThue;
		this.tinhTrang = tinhTrang;
		this.maTieuDe = maTieuDe;
	}
	
	
	public BangDia(int phiThue, int phiTreHan, int soNgayDuocThue, int tinhTrang, TieuDe maTieuDe,
			int id_khachHang_hienTai) {
		super();
		this.phiThue = phiThue;
		this.phiTreHan = phiTreHan;
		this.soNgayDuocThue = soNgayDuocThue;
		this.tinhTrang = tinhTrang;
		this.maTieuDe = maTieuDe;
		this.id_khachHang_hienTai = id_khachHang_hienTai;
	}

	public int getId_khachHang_hienTai() {
		return id_khachHang_hienTai;
	}

	public void setId_khachHang_hienTai(int id_khachHang_hienTai) {
		this.id_khachHang_hienTai = id_khachHang_hienTai;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	
	public int getPhiThue() {
		return phiThue;
	}

	public void setPhiThue(int phiThue) {
		this.phiThue = phiThue;
	}

	public int getPhiTreHan() {
		return phiTreHan;
	}

	public void setPhiTreHan(int phiTreHan) {
		this.phiTreHan = phiTreHan;
	}

	public int getSoNgayDuocThue() {
		return soNgayDuocThue;
	}

	public void setSoNgayDuocThue(int soNgayDuocThue) {
		this.soNgayDuocThue = soNgayDuocThue;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public TieuDe getMaTieuDe() {
		return maTieuDe;
	}

	public void setMaTieuDe(TieuDe maTieuDe) {
		this.maTieuDe = maTieuDe;
	}

	@Override
	public String toString() {
		return "BangDia [ma=" + ma + ", phiThue=" + phiThue + ", phiTreHan=" + phiTreHan
				+ ", soNgayDuocThue=" + soNgayDuocThue + ", tinhTrang=" + tinhTrang + ", maTieuDe=" + maTieuDe + "]";
	}
}
