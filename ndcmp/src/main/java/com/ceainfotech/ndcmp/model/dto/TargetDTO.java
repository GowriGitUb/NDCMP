/**
 * 
 */
package com.ceainfotech.ndcmp.model.dto;


/**
 * @author Samy
 * 
 */
public class TargetDTO {

	private Integer id;
	private String message;
	private String status;

	/**
	 * 
	 */
	public TargetDTO() {
	}

	/**
	 * @param id
	 * @param message
	 */
	public TargetDTO(Integer id, String message, String status) {
		this.id = id;
		this.message = message;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TargetDTO [id=" + id + ", message=" + message + ", status=" + status + "]";
	}

}
