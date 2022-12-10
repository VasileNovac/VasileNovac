package ro.digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Copil extends MembriFamilie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	private String gradRudaRL ;
	private String sex ;

//	gradRudaRL: copil natural; copil adoptat; copil in plasament familial; copil in tutela; copil in curatela; copil incredintat spre adoptie;

	public Copil() { }
	
	public Copil (String cnp, String nume, String prenume, String actId, String serieActId, String nrActId, String dataNastere, String situatieScolara, String cuDizabilitati, String beneficiatAlteDreptSociale, String categDreptSociale, String gradRudaRL, String sex) {
		super(cnp, nume, prenume, actId, serieActId, nrActId, dataNastere, situatieScolara, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale) ;
		this.gradRudaRL = gradRudaRL ;
		this.sex = sex ;
	}

	public String getGradRudaRL() {
		return gradRudaRL;
	}
	public void setGradRudaRL(String gradRudaRL) {
		this.gradRudaRL = gradRudaRL;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}

