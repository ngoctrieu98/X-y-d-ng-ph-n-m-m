package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chitietphieuthue")
public class ChiTietPhieuThue implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int DANG_THUE = 0;

	public static final int NO = 1;

	public static final int KHONG_NO = 2;

	@EmbeddedId
	private ChiTietPhieuThueId ma;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("maPhieuThue")
	@JoinColumn(name = "ma_phieu_thue")
	private PhieuThue phieuThue;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("maBangDia")
	@JoinColumn(name = "ma_bang_dia")
	private BangDia bangDia;

	@Column(name = "ngay_tra")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayTra;

	@Column(name = "tinh_trang")
	private int tinhTrang;

	public ChiTietPhieuThue() {
		super();
	}

	public ChiTietPhieuThue(PhieuThue phieuThue, BangDia bangDia, int tinhTrang) {
		super();
		this.phieuThue = phieuThue;
		this.bangDia = bangDia;
		this.tinhTrang = tinhTrang;
		this.ma = new ChiTietPhieuThueId(phieuThue.getMa(), bangDia.getMa());
	}

	public ChiTietPhieuThueId getMa() {
		return ma;
	}

	public void setMa(ChiTietPhieuThueId ma) {
		this.ma = ma;
	}

	public PhieuThue getPhieuThue() {
		return phieuThue;
	}

	public void setPhieuThue(PhieuThue phieuThue) {
		this.phieuThue = phieuThue;
	}

	public BangDia getBangDia() {
		return bangDia;
	}

	public void setBangDia(BangDia bangDia) {
		this.bangDia = bangDia;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuThue [ma=" + ma + ", phieuThue=" + phieuThue + ", bangDia=" + bangDia + ", ngayTra="
				+ ngayTra + ", tinhTrang=" + tinhTrang + "]";
	}
}
