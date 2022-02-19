package entities;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "khachhang")
public class KhachHang implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ma;
	private String sdt;
	private String ten;
	@Column(name = "phi_tre_han")
	private int phiTreHan;
	@Column(name = "dia_chi")
	private String diaChi;

	public KhachHang(String sdt, String ten, int phiTreHan, String diaChi) {
		super();
		this.sdt = sdt;
		this.ten = ten;
		this.phiTreHan = phiTreHan;
		this.diaChi = diaChi;
	}

	public KhachHang() {
		super();
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getPhiTreHan() {
		return phiTreHan;
	}

	public void setPhiTreHan(int phiTreHan) {
		this.phiTreHan = phiTreHan;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "KhachHang [ma=" + ma + ", sdt=" + sdt + ", ten=" + ten + ", phiTreHan=" + phiTreHan + ", diaChi="
				+ diaChi + "]";
	}

}
