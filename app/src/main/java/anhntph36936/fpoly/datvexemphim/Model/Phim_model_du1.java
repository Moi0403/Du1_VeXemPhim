package anhntph36936.fpoly.datvexemphim.Model;

public class Phim_model_du1 {
    private int maphim;
    private String tenphim;
    private String hinhanh;
    private String tenloai;
    private String thoigianchieu;
    private String ngaychieu;
    private int maxuatchieu;
    private int maloai;

    public Phim_model_du1(int maphim, String tenphim, String hinhanh, String tenloai, String thoigianchieu, String ngaychieu) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.hinhanh = hinhanh;
        this.tenloai = tenloai;
        this.thoigianchieu = thoigianchieu;
        this.ngaychieu = ngaychieu;
        this.maxuatchieu = maxuatchieu;
        this.maloai = maloai;
    }

    public int getMaphim() {
        return maphim;
    }

    public void setMaphim(int maphim) {
        this.maphim = maphim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getThoigianchieu() {
        return thoigianchieu;
    }

    public void setThoigianchieu(String thoigianchieu) {
        this.thoigianchieu = thoigianchieu;
    }

    public String getNgaychieu() {
        return ngaychieu;
    }

    public void setNgaychieu(String ngaychieu) {
        this.ngaychieu = ngaychieu;
    }

    public int getMaxuatchieu() {
        return maxuatchieu;
    }

    public void setMaxuatchieu(int maxuatchieu) {
        this.maxuatchieu = maxuatchieu;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
