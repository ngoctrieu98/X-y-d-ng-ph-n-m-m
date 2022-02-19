package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "danhsachdattruoc")
public class DanhSachDatTruoc implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DA_XAC_NHAN = 0;

	public static final int CHUA_XAC_NHAN = 1;

	public static final int DANG_CHO = 2;
	
	@EmbeddedId
	private DanhSachDatTruocId ma;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("maKhachHang")
	@JoinColumn(name = "ma_khach_hang")
	private KhachHang khachHang;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("maTieuDe")
	@JoinColumn(name = "ma_tieu_de")
	private TieuDe tieuDe;

	@Column(name = "ngay_dat_truoc")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date ngayDatTruoc;

	@Column(name = "tinh_trang")
	private int tinhTrang;

	public DanhSachDatTruoc() {
		super();
	}

	public DanhSachDatTruoc(KhachHang khachHang, TieuDe tieuDe, Date ngayDatTruoc,
			int tinhTrang) {
		super();
		this.khachHang = khachHang;
		this.tieuDe = tieuDe;
		this.ngayDatTruoc = ngayDatTruoc;
		this.tinhTrang = tinhTrang;
		this.ma = new DanhSachDatTruocId(tieuDe.getMa(), khachHang.getMa());
	}
	

	public DanhSachDatTruoc(DanhSachDatTruocId ma) {
		super();
		this.ma = ma;
	}

	public DanhSachDatTruocId getMa() {
		return ma;
	}

	public void setMa(DanhSachDatTruocId ma) {
		this.ma = ma;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public TieuDe getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(TieuDe tieuDe) {
		this.tieuDe = tieuDe;
	}

	public Date getNgayDatTruoc() {
		return ngayDatTruoc;
	}

	public void setNgayDatTruoc(Date ngayDatTruoc) {
		this.ngayDatTruoc = ngayDatTruoc;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "DanhSachDatTruoc [ma=" + ma + ", khachHang=" + khachHang + ", tieuDe=" + tieuDe + ", ngayDatTruoc="
				+ ngayDatTruoc + ", tinhTrang=" + tinhTrang + "]";
	}
}
