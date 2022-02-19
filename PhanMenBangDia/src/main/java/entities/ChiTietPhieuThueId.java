package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ChiTietPhieuThueId implements Serializable {

	private static final long serialVersionUID = 1L;

	private int maPhieuThue;

	private int maBangDia;

	public ChiTietPhieuThueId() {
		super();
	}

	public ChiTietPhieuThueId(int maPhieuThue, int maBangDia) {
		super();
		this.maPhieuThue = maPhieuThue;
		this.maBangDia = maBangDia;
	}

	public int getMaPhieuThue() {
		return maPhieuThue;
	}

	public void setMaPhieuThue(int maPhieuThue) {
		this.maPhieuThue = maPhieuThue;
	}

	public int getMaBangDia() {
		return maBangDia;
	}

	public void setMaBangDia(int maBangDia) {
		this.maBangDia = maBangDia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maBangDia;
		result = prime * result + maPhieuThue;
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
		ChiTietPhieuThueId other = (ChiTietPhieuThueId) obj;
		if (maBangDia != other.maBangDia)
			return false;
		if (maPhieuThue != other.maPhieuThue)
			return false;
		return true;
	}
}
