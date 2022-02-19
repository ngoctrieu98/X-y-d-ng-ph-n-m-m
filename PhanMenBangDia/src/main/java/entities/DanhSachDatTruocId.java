package entities;

import java.io.Serializable;

import javax.persistence.*;


@Embeddable
public class DanhSachDatTruocId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int maTieuDe;
	
	private int maKhachHang;

	public DanhSachDatTruocId() {
		super();
	}

	public DanhSachDatTruocId(int maTieuDe, int maKhachHang) {
		super();
		this.maTieuDe = maTieuDe;
		this.maKhachHang = maKhachHang;
	}

	public int getMaTieuDe() {
		return maTieuDe;
	}

	public void setMaTieuDe(int maTieuDe) {
		this.maTieuDe = maTieuDe;
	}

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maKhachHang;
		result = prime * result + maTieuDe;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DanhSachDatTruocId other = (DanhSachDatTruocId) obj;
		if (maKhachHang != other.maKhachHang)
			return false;
		if (maTieuDe != other.maTieuDe)
			return false;
		return true;
	}	
}
