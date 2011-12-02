package mconta.core.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RECORD")
public class Record {
	
	@Id
	@GeneratedValue
	@Column(name = "REC_ID", nullable = false)
	private Long id;
	
	@Column(name = "REC_TITLE", length = 100)
	private String title;
	
	@Column(name = "REC_YEAR")
	private int year;
	
	@Column(name = "REC_PRICE")
	private double price;
  
	public Record() {
	}
	  
	public Record(Long id, String title, int year, double price) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.price = price;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
}
