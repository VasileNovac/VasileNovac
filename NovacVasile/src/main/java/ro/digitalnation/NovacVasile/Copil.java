package ro.digitalnation.NovacVasile;

import jakarta.persistence.Entity;

@Entity
public class Copil extends MembriFamilie {
	
	private String gradRudaRL ;
	private String sex ;

//	gradRudaRL: copil natural; copil adoptat; copil in plasament familial; copil in tutela; copil in curatela; copil incredintat spre adoptie;

	public Copil() { }
	
	public Copil (String cnp, String nume, String prenume, String actId, String serieActId, String nrActId, String dataExpActId, String dataNastere, String situatieScolara, String cuDizabilitati, String beneficiatAlteDreptSociale, String categDreptSociale, String gradRudaRL, String sex) {
		super(cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, dataNastere, situatieScolara, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale) ;
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

	@Override
	public String toString() {
		return super.toString() ;
	}

}

