package ro.digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MasculinParinte extends MembriFamilie{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	private String sex ;

	public MasculinParinte() { }
	
	public MasculinParinte ( String cnp, String nume, String prenume, String actId, String serieActId, String nrActId, String dataExpActId, String stareCivila, String dataNastere, String cetatenie, String situatieScolara, String situatieProfesionala, int venitTotalUltimaLuna, String cuDizabilitati, String beneficiatAlteDreptSociale, String categDreptSociale, String sex) {
		super(cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale) ;
		this.sex = "M" ;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}