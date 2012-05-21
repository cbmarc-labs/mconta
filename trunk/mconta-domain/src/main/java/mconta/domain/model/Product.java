/**
 * 
 */
package mconta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

/**
 * Product class
 * 
 * @author Marc
 */
@Entity
@Table(name = "PRODUCT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRO_ID", nullable = false)
	protected Long pro_id;

	@Column(name = "PRO_NAME", nullable = false)
	@Length(min=4, max=50)
	protected String pro_name;

	@Column(name = "PRO_DESCRIPTION")
	@Length(min=4, max=100)
	protected String pro_description;

	@Column(name = "PRO_IMAGE")
	@Length(max=100)
	protected String pro_image;

	public Product() {
	}

	public Product(String pro_name, String pro_description) {
		this.pro_name = pro_name;
		this.pro_description = pro_description;
	}
	
	/**
	 * @return the pro_id
	 */
	public Long getPro_id() {
		return pro_id;
	}

	/**
	 * @param pro_id the pro_id to set
	 */
	public void setPro_id(Long pro_id) {
		this.pro_id = pro_id;
	}

	/**
	 * @return the pro_name
	 */
	public String getPro_name() {
		return pro_name;
	}

	/**
	 * @param pro_name the pro_name to set
	 */
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	/**
	 * @return the pro_description
	 */
	public String getPro_description() {
		return pro_description;
	}

	/**
	 * @param pro_description the pro_description to set
	 */
	public void setPro_description(String pro_description) {
		this.pro_description = pro_description;
	}

	/**
	 * @return the pro_image
	 */
	public String getPro_image() {
		return pro_image;
	}

	/**
	 * @param pro_image the pro_image to set
	 */
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}

	/**
	 * deHibernate() 
	 */
	public void deHibernate() {
	}
	
	/**
	 * toString()
	 */
	public String toString() { 
		StringBuilder build = new StringBuilder();
		
		return build.append("id: ").append(pro_id)
				.append("name: ").append(pro_name).toString();
	}

}
