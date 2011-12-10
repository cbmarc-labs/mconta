package mconta.core.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "RECORD")
public class Record implements Model {
	
	@Id
	@GeneratedValue
	@Column(name = "REC_ID", nullable = false)
	private Long rec_id;
	
	@Column(name = "REC_TITLE", length = 100)
	private String rec_title;
	
	@Column(name = "REC_YEAR")
	private int rec_year;
	
	@Column(name = "REC_PRICE")
	private double rec_price;
  
	public Record() {
	}

	/**
	 * @return the rec_id
	 */
	public Long getRec_id() {
		return rec_id;
	}

	/**
	 * @param rec_id the rec_id to set
	 */
	public void setRec_id(Long rec_id) {
		this.rec_id = rec_id;
	}

	/**
	 * @return the rec_title
	 */
	public String getRec_title() {
		return rec_title;
	}

	/**
	 * @param rec_title the rec_title to set
	 */
	public void setRec_title(String rec_title) {
		this.rec_title = rec_title;
	}

	/**
	 * @return the rec_year
	 */
	public int getRec_year() {
		return rec_year;
	}

	/**
	 * @param rec_year the rec_year to set
	 */
	public void setRec_year(int rec_year) {
		this.rec_year = rec_year;
	}

	/**
	 * @return the rec_price
	 */
	public double getRec_price() {
		return rec_price;
	}

	/**
	 * @param rec_price the rec_price to set
	 */
	public void setRec_price(double rec_price) {
		this.rec_price = rec_price;
	}
	
}
