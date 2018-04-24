package sophia.copier.dto;

public class TempOutputDto {

	private String book_nm_2;
	private String book_price_2;
	private String dttm;

	public String getBook_nm_2() {
		return book_nm_2;
	}

	public void setBook_nm_2(String book_nm_2) {
		this.book_nm_2 = book_nm_2;
	}

	public String getBook_price_2() {
		return book_price_2;
	}

	public void setBook_price_2(String book_price_2) {
		this.book_price_2 = book_price_2;
	}

	public String getDttm() {
		return dttm;
	}

	public void setDttm(String dttm) {
		this.dttm = dttm;
	}

	@Override
	public String toString() {
		return "TempOutputDto [book_nm_2=" + book_nm_2 + ", book_price_2=" + book_price_2 + ", dttm=" + dttm + "]";
	}

}
