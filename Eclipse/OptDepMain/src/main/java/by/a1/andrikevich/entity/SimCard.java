package by.a1.andrikevich.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="simcard")
public class SimCard {
	//id, iccid, msisdn, device, description_1, description_2, additional_info, stu_id
	
	
	
	
	@Id
	@Column(name="id")
	private int id;
	
	public SimCard(String iccid, String msisdn, String device, String description1, String description2,
			String additionalInfo) {
		this.iccid = iccid;
		this.msisdn = msisdn;
		this.device = device;
		this.description1 = description1;
		this.description2 = description2;
		this.additionalInfo = additionalInfo;
	}

	
	
	public SimCard() {
	}



	@Column(name="iccid")
	private String iccid;
	
	@Column(name="msisdn")
	private String msisdn;
	
	@Column(name="device")
	private String device;
	
	@Column(name="description_1")
	private String description1;
	
	@Column(name="description_2")
	private String description2;
	
	@Column(name="additional_info")
	private String additionalInfo;
	
//	@ManyToOne (cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
//			    fetch = FetchType.LAZY)
//	@JoinColumn(name="stu_id")
//	private Stu stu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

//	public Stu getStu() {
//		return stu;
//	}
//
//	public void setStu(Stu stu) {
//		this.stu = stu;
//	}

	@Override
	public String toString() {
		return "SimCard [id=" + id + ", iccid=" + iccid + ", msisdn=" + msisdn + ", device=" + device
				+ ", description1=" + description1 + ", description2=" + description2 + ", additionalInfo="
				+ additionalInfo  + "]";
	}
	
	
	
}
