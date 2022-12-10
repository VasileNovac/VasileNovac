package ro.digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DispozitiePrimar {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String nrDispozitie, dataDispozitie, categDispozitie, dataIntrareVigoare ;

/*
 	categDispozitie: A - acordare drept ; S - suspendare drept; I - incetare drept; R - respingere drept; RA - reluare acordare drept; 
*/

	public DispozitiePrimar () {}
	
	public DispozitiePrimar (String nrDispozitie, String dataDispozitie, String categDispozitie, String dataIntrareVigoare) {
		this.nrDispozitie = nrDispozitie ;
		this.dataDispozitie = dataDispozitie ;
		this.categDispozitie = categDispozitie ;
		this.dataIntrareVigoare = dataIntrareVigoare ;
	}

	
	public String getNrDispozitie() {
		return nrDispozitie;
	}
	public void setNrDispozitie(String nrDispozitie) {
		this.nrDispozitie = nrDispozitie;
	}

	public String getDataDispozitie() {
		return dataDispozitie;
	}
	public void setDataDispozitie(String dataDispozitie) {
		this.dataDispozitie = dataDispozitie;
	}

	public String getCategDispozitie() {
		return categDispozitie;
	}
	public void setCategDispozitie(String categDispozitie) {
		this.categDispozitie = categDispozitie;
	}

	public String getDataIntrareVigoare() {
		return dataIntrareVigoare;
	}
	public void setDataIntrareVigoare(String dataIntrareVigoare) {
		this.dataIntrareVigoare = dataIntrareVigoare;
	}
}
