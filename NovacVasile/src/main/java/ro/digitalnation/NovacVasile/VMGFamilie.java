package ro.digitalnation.NovacVasile;

import java.util.ArrayList;

public class VMGFamilie {

	private final double ISR = 525.5 ;
	private double cuantumAjutorSocial ;
	private Familie familie ;
	private MasculinParinte masculinParinte ;
	private FemininParinte femininParinte ;
	private Copil copil ;
	private ArrayList<Copil> copii ;
	private AltaPersMajora altaPersMajora ;
	private ArrayList<AltaPersMajora> altePersMajore;
	private DispozitiePrimar dispozitiePrimar ;

	public VMGFamilie() {}
	
	public VMGFamilie ( Object familie, Object masculinParinte, Object femininParinte, ArrayList<Copil> copii, ArrayList<AltaPersMajora> altePersMajore, Object dispozitiePrimar, double cuantumAjutorSocial) {
		this.familie = new Familie() ;
		this.masculinParinte = new MasculinParinte() ;
		this.femininParinte = new FemininParinte() ;
		this.altePersMajore = new ArrayList<AltaPersMajora>() ;
		this.copii = new ArrayList<Copil>() ;
		this.dispozitiePrimar = new DispozitiePrimar() ;
		this.cuantumAjutorSocial = cuantumAjutorSocial ;
	}
	
	public void calculCuantum() {
		familie = new Familie() ;
		cuantumAjutorSocial = ISR * (familie.getNrCopii() + familie.getNrPersoaneMajore());
		
		// 1 - 0,283; 2 - 0,510; 3 - 0,714; 4 - 0,884; 5 - 1,054 ; >5 pt fiecare persoana - 0,073; ISR = 525,5 
	}

	public double getCuantumAjutorSocial() {
		return cuantumAjutorSocial;
	}
	public void setCuantumAjutorSocial(double cuantumAjutorSocial) {
		this.cuantumAjutorSocial = cuantumAjutorSocial;
	}

	public Familie getFamilie() {
		return familie;
	}
	public void setFamilie(Familie familie) {
		this.familie = familie;
	}

	public MasculinParinte getMasculinParinte() {
		return masculinParinte;
	}
	public void setMasculinParinte(MasculinParinte masculinParinte) {
		this.masculinParinte = masculinParinte;
	}

	public FemininParinte getFemininParinte() {
		return femininParinte;
	}
	public void setFemininParinte(FemininParinte femininParinte) {
		this.femininParinte = femininParinte;
	}

	public Copil getCopil() {
		return copil;
	}
	public void setCopil(Copil copil) {
		this.copil = copil;
	}

	public ArrayList<Copil> getCopii() {
		return copii;
	}
	public void setCopii(ArrayList<Copil> copii) {
		this.copii = copii;
	}

	public AltaPersMajora getAltaPersMajora() {
		return altaPersMajora;
	}
	public void setAltaPersMajora(AltaPersMajora altaPersMajora) {
		this.altaPersMajora = altaPersMajora;
	}

	public ArrayList<AltaPersMajora> getAltePersMajore() {
		return altePersMajore;
	}
	public void setAltePersMajore(ArrayList<AltaPersMajora> altePersMajore) {
		this.altePersMajore = altePersMajore;
	}

	public DispozitiePrimar getDispozitiePrimar() {
		return dispozitiePrimar;
	}
	public void setDispozitiePrimar(DispozitiePrimar dispozitiePrimar) {
		this.dispozitiePrimar = dispozitiePrimar;
	}

}
