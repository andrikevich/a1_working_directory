package by.a1.andrikevich.stu.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="stu")
public class Stu {
    //(`stu_id`,`email`,`invent_num`,`city`,`msisdn`,`imei`,`serial_num`,`adress`,`responsible_person`,`phone`,
    //`serv_cell`,`alias`)
    @Id
    @Column (name = "stu_id")
    private int id;

    @Column (name="email")
    private String email;

    @Column (name="invent_num")
    private String inventNum;

    @Column (name="city")
    private String city;

    @Column (name="msisdn")
    private String msisdn;

    @Column (name="imei")
    private String imei;

    @Column (name="serial_num")
    private String serialNum;

    @Column (name="adress")
    private String adress;

    @Column (name="responsible_person")
    private String responsiblePerson;

    @Column (name="phone")
    private String phone;

    @Column (name="serv_cell")
    private String servCell;

    @Column (name="alias")
    private String alias;


//	@OneToMany(fetch=FetchType.LAZY,
//			   mappedBy="stu",
//			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//						 CascadeType.DETACH, CascadeType.REFRESH})
//	private List <SimCard>  simCards;

    public int getId() {
        return id;
    }

    public void setId(int stuId) {
        this.id = stuId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInventNum() {
        return inventNum;
    }

    public void setInventNum(String inventNum) {
        this.inventNum = inventNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServCell() {
        return servCell;
    }

    public void setServCell(String servCell) {
        this.servCell = servCell;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


//	public List<SimCard> getSimCards() {
//		return simCards;
//	}
//
//	public void setSimCards(List<SimCard> simCards) {
//		this.simCards = simCards;
//	}

    @Override
    public String toString() {
        return "Stu [stuId=" + id + ", email=" + email + ", inventNum=" + inventNum + ", city=" + city + ", msisdn="
                + msisdn + ", imei=" + imei + ", serialNum=" + serialNum + ", adress=" + adress + ", responsiblePerson="
                + responsiblePerson + ", phone=" + phone + ", servCell=" + servCell + ", alias=" + alias + "]";
    }






}

