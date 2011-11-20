package mconta.web.shared;

import java.io.Serializable;

import mconta.core.persistence.Record;

@SuppressWarnings("serial")
public class RecordDTO implements Serializable {
		
	private Long id;
	private String title;
	private int year;
	private double price;
	
	public RecordDTO() {
	}
	
	public RecordDTO(Long id) {
		this.id = id;
	}
	
	public Record toBean() {
		return new Record(new Long(10), "lalala", 10, 20);		
	}
	
	public RecordDTO(Long id, String title, int year, double price) {
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
