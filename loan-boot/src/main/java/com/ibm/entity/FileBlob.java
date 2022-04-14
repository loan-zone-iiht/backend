package com.ibm.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Class {FileBlob} is the entity holding the blob of the file data uploaded to the server.
 * 
 *  
 * @JsonIdentityInfo handles JSON references, and stops them becoming infinitely
 *                   nested objects. No need for JsonBackReference and
 *                   JsonManagedReference anymore.
 * 
 * @author Saswata Dutta
 */

@Entity
@Table(name = "loan_files_boot")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // json infy
public class FileBlob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "file_type")
	private String fileType;
	@Lob
	@Column(name = "data_blob")
	private byte[] dataBlob;
	
	public FileBlob() {
	}

	public FileBlob(Customer customer, String fileName, String fileType, byte[] dataBlob) {
		this.customer = customer;
		this.fileName = fileName;
		this.fileType = fileType;
		this.dataBlob = dataBlob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getDataBlob() {
		return dataBlob;
	}

	public void setDataBlob(byte[] dataBlob) {
		this.dataBlob = dataBlob;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", dataBlob="
				+ Arrays.toString(dataBlob) + "]";
	}

	
	
	
	
	
}
