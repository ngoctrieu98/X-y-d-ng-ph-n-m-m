package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phieuthue")
public class PhieuThue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ma;

	@Column(name = "ngay_thue")
	private Date ngayThue;

	@Column(name = "tong_tien")
	private int tongTien;

	@JoinColumn(name = "ma_khach_hang")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private KhachHang maKhachHang;

	public PhieuThue() {
		super();
	}

	public PhieuThue(Date ngayThue, KhachHang maKhachHang, int tongTien) {
		super();
		this.ngayThue = ngayThue;
		this.maKhachHang = maKhachHang;
		this.tongTien = tongTien;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public Date getNgayThue() {
		return ngayThue;
	}

	public void setNgayThue(Date ngayThue) {
		this.ngayThue = ngayThue;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public KhachHang getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(KhachHang maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	@Override
	public String toString() {
		return "PhieuThue [ma=" + ma + ", ngayThue=" + ngayThue + ", tongTien=" + tongTien + ", maKhachHang="
				+ maKhachHang + "]";
	}
}
