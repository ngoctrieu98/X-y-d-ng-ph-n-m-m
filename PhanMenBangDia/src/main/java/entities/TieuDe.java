package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tieude")
public class TieuDe implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int PHIM = 0;

	public static final int GAME = 1;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ma;

	private String ten;

	private int loai;

	public TieuDe() {
		super();
	}
	
	public int getLoai() {
		return loai;
	}

	public void setLoai(int loai) {
		this.loai = loai;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	
	public TieuDe(String ten, int loai) {
		super();
		this.ten = ten;
		this.loai = loai;
	}

	@Override
	public String toString() {
		return "TieuDe [ma=" + ma + ", ten=" + ten + ", loai=" + loai + "]";
	}

	
}
