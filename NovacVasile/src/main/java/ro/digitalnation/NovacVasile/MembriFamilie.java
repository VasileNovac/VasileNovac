package ro.digitalnation.NovacVasile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class MembriFamilie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String  cnp, nume, prenume ;
	private String stareCivila, actId, serieActId, nrActId, dataExpActId, dataNastere, cetatenie, situatieScolara, situatieProfesionala, cuDizabilitati, beneficiatAlteDreptSociale ;
	private int venitTotalUltimaLuna ;
	private String categDreptSociale ;
	private static ArrayList<String> stareCivilaList ;
	private static ArrayList<String> situatieScolaraList ;
	private static ArrayList<String> situatieProfesionalaList ;
	private static ArrayList<String> categDreptSocialeList ;
	private static ArrayList<String> gradRudaRLList ;
	private static ArrayList<String> danuList ;
	public static HashMap<String, String> actIdList ;
	public static HashMap<String, String> sexList ;
	public static HashMap<String, String> categDispozitieList ;

	private static boolean ret1, ret2, ret3, ret4, ret5 ;

/*
  	actId: CN - certificat nastere; BI - buletin identitate; CI - carte identitate ; CIP - carte identitate provizorie; P - pasaport; 
  		   PST - permis de sedere temporara; DI - document identitate; PSTL - permis sedere pe termen lung; 
  		   CIN - certificat inregistrare; CR - carte rezidenta; 
	cetatenie: romana; UE + tara; Non-UE + tara;
	stareCivila: casatorit(a); necasatorit(a); uniune consensuala; vaduv(a); divortat(a); despartit(a) in fapt;
	situatieScolara: fara studii; studii generale; studii medii; studii superioare; 
	situatieScolara: prescolar; elev clasele 1 - 8; elev clasele 9 - 12;
	situatieProfesionala: salariat; pensionar; somer; student; independent; lucrator agricol; lucrator ocazional, elev; altele;
	categDreptSociale: alocatie stat; ajutor social; alocatie pentru sustinerea familiei; ajutor pentru incalzirea locuintei cu: energie termica, gaze naturale, energie electrica, lemne, carbuni;
*/
	public MembriFamilie() { }

//constructor pentru MasculinParinte, FemininParinte, AltaPersMajora
	
	public MembriFamilie (String cnp, String nume, String prenume, String actId, String serieActId, String nrActId, String dataExpActId, String stareCivila, String dataNastere, String cetatenie, String situatieScolara, String situatieProfesionala, int venitTotalUltimaLuna, String cuDizabilitati, String beneficiatAlteDreptSociale, String categDreptSociale ) {
		this.cnp = cnp ;
		this.nume = nume ;
		this.prenume = prenume ;
		this.actId = actId ;
		this.serieActId = serieActId ;
		this.nrActId = nrActId ;
		this.dataExpActId = dataExpActId ;
		this.stareCivila = stareCivila ;
		this.dataNastere = dataNastere ;
		this.cetatenie = cetatenie ;
		this.situatieScolara = situatieScolara ;
		this.situatieProfesionala = situatieProfesionala ;
		this.venitTotalUltimaLuna = venitTotalUltimaLuna ;
		this.cuDizabilitati = cuDizabilitati ;
		this.beneficiatAlteDreptSociale = beneficiatAlteDreptSociale ;
		this.categDreptSociale = categDreptSociale ;
	}

//constructor pentru Copil
	
	public MembriFamilie ( String cnp, String nume, String prenume, String actId, String serieActId, String nrActId, String dataNastere, String situatieScolara, String cuDizabilitati, String beneficiatAlteDreptSociale, String categDreptSociale ) {
		this.cnp = cnp ;
		this.nume = nume ;
		this.prenume = prenume ;
		this.actId = actId ;
		this.serieActId = serieActId ;
		this.nrActId = nrActId ;
		this.dataNastere = dataNastere ;
		this.situatieScolara = situatieScolara ;
		this.cuDizabilitati = cuDizabilitati ;
		this.beneficiatAlteDreptSociale = beneficiatAlteDreptSociale ;
		this.categDreptSociale = categDreptSociale ;
	}

//constructor pentru verificare CNP
	
	public MembriFamilie (String cnp) {
		this.cnp = cnp ;
	}

//metoda verificare CNP
	public static boolean verifCNP(String xcnp ) { 
		// verificare lungime cnp
		ret1 = false ;
		if ( xcnp.length() == 13 ) {
			ret1 = true ;
		}
		
		// verificare ca toate caracterele cnp sunt numerice
		ret2 = true ;
		for ( int j = 0 ; j < xcnp.length() ; j++) {
			if (Pattern.matches("[0-9]", Character.toString(xcnp.charAt(j))) && ret2 == true ) { }
			else {
				ret2 = false ;
			}
		}

		// verificare ca luna cnp este cuprinsa intre 1 si 12 
		ret3 = false ;
		if (Integer.valueOf(Character.toString(xcnp.charAt(3)) + Character.toString(xcnp.charAt(4))) < 13 && Integer.valueOf(Character.toString(xcnp.charAt(3)) + Character.toString(xcnp.charAt(4))) > 0 ) {
			ret3 = true ;
		}
		
		// verificare ca ziua cnp este cuprinsa intre 1 si 31
		ret4 = false ;
		if (Integer.valueOf(Character.toString(xcnp.charAt(5)) + Character.toString(xcnp.charAt(6))) < 32 && Integer.valueOf(Character.toString(xcnp.charAt(5)) + Character.toString(xcnp.charAt(6))) > 0 ) {
			ret4 = true ;
		}

		// verificare ca cifra de control cnp este corecta
		int restCNP = ( 2*Integer.valueOf(Character.toString(xcnp.charAt(0))) + 
						7*Integer.valueOf(Character.toString(xcnp.charAt(1))) + 
						9*Integer.valueOf(Character.toString(xcnp.charAt(2))) + 
						  Integer.valueOf(Character.toString(xcnp.charAt(3))) + 
						4*Integer.valueOf(Character.toString(xcnp.charAt(4))) + 
						6*Integer.valueOf(Character.toString(xcnp.charAt(5))) + 
						3*Integer.valueOf(Character.toString(xcnp.charAt(6))) + 
						5*Integer.valueOf(Character.toString(xcnp.charAt(7))) + 
						8*Integer.valueOf(Character.toString(xcnp.charAt(8))) + 
						2*Integer.valueOf(Character.toString(xcnp.charAt(9))) + 
						7*Integer.valueOf(Character.toString(xcnp.charAt(10))) + 
						9*Integer.valueOf(Character.toString(xcnp.charAt(11)))) % 11 ;
		if (restCNP == 10) {
			restCNP = 1 ;
		}
		ret5 = false ;
		if (restCNP == Integer.valueOf(Character.toString(xcnp.charAt(12)))) {
			ret5 = true ;
		}

		return ret1 && ret2 && ret3 && ret4 && ret5 ;
	}
	
	public static ArrayList<String> listSC() {
		stareCivilaList = new ArrayList<String>() ;
		stareCivilaList.add("casatorit(a)") ;
		stareCivilaList.add("necasatorit(a)");
		stareCivilaList.add("uniune consensuala");
		stareCivilaList.add("vaduv(a)") ;
		stareCivilaList.add("divortat(a)");
		stareCivilaList.add("despartit(a) in fapt") ;
		return stareCivilaList ;
	}

	public static ArrayList<String> listDN() {
		danuList = new ArrayList<String>() ;
		danuList.add("NU") ;
		danuList.add("DA") ;
		return danuList ;
	}

	public static ArrayList<String> listSS() {
		situatieScolaraList = new ArrayList<String>() ;
		situatieScolaraList.add("fara studii") ;
		situatieScolaraList.add("studii generale") ;
		situatieScolaraList.add("studii medii");
		situatieScolaraList.add("studii superioare");
		return situatieScolaraList ;
	}

	public static ArrayList<String> listSP() {
		situatieProfesionalaList = new ArrayList<String>() ;
		situatieProfesionalaList.add("salariat") ;
		situatieProfesionalaList.add("pensionar");
		situatieProfesionalaList.add("somer");
		situatieProfesionalaList.add("student");
		situatieProfesionalaList.add("independent");
		situatieProfesionalaList.add("lucrator agricol");
		situatieProfesionalaList.add("lucrator ocazional");
		situatieProfesionalaList.add("elev");
		situatieProfesionalaList.add("altele");
		return situatieProfesionalaList ;
	}
	
	public static ArrayList<String> listCDS() {
		categDreptSocialeList = new ArrayList<String>();
		categDreptSocialeList.add(" ") ;
		categDreptSocialeList.add("alocatie stat");
		categDreptSocialeList.add("ajutor social");
		categDreptSocialeList.add("alocatie pentru sustinerea familiei");
		categDreptSocialeList.add("ajutor pentru incalzire locuintei cu: energie termica, gaze naturale, energie electrica, lemne, carbuni");
		return categDreptSocialeList ;
	}

	public static ArrayList<String> listGR() {
		gradRudaRLList = new ArrayList<String>();
		gradRudaRLList.add("copil natural");
		gradRudaRLList.add("copil adoptat");
		gradRudaRLList.add("copil in plasament familial");
		gradRudaRLList.add("copil in tutela");
		gradRudaRLList.add("copil in curatela");
		gradRudaRLList.add("copil incredintat spre adoptie");
		return gradRudaRLList ;
	}
	
	public static HashMap<String, String> listAI() {
		actIdList = new HashMap<String, String>() ;
		actIdList.put("CN", "Certificat nastere") ;
		actIdList.put("BI", "Buletin identitate");
		actIdList.put("CI", "Carte identitate") ;
		actIdList.put("CIP", "Carte identitate provizorie");
		actIdList.put("P", "Pasaport");
		actIdList.put("PST", "Permis de sedere temporara");
		actIdList.put("DI", "Document identitate");
		actIdList.put("PSTL", "Permis sedere pe termen lung");
		actIdList.put("CIN", "Certificat inregistrare");
		actIdList.put("CR", "Carte rezidenta");
		return actIdList ;
	}
	
	public static HashMap<String, String> listSX() {
		sexList = new HashMap<String, String>() ;
		sexList.put("F", "feminin");
		sexList.put("M", "masculin");
		return sexList ;
	}
	
	public static HashMap<String, String> listCD() {
		categDispozitieList = new HashMap<String, String>();
		categDispozitieList.put("A", "acordare drept") ;
		categDispozitieList.put("S", "suspendare drept");
		categDispozitieList.put("I", "incetare drept");
		categDispozitieList.put("R", "respingere drept");
		categDispozitieList.put("RA", "reluare acordare drept");
		return categDispozitieList ;
	}
	
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getStareCivila() {
		return stareCivila;
	}
	public void setStareCivila(String stareCivila) {
		this.stareCivila = stareCivila;
	}

	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getSerieActId() {
		return serieActId;
	}
	public void setSerieActId(String serieActId) {
		this.serieActId = serieActId;
	}

	public String getNrActId() {
		return nrActId;
	}
	public void setNrActId(String nrActId) {
		this.nrActId = nrActId;
	}

	public String getDataExpActId() {
		return dataExpActId;
	}
	public void setDataExpActId(String dataExpActId) {
		this.dataExpActId = dataExpActId;
	}

	public String getDataNastere() {
		return dataNastere;
	}
	public void setDataNastere(String dataNastere) {
		this.dataNastere = dataNastere;
	}

	public String getCetatenie() {
		return cetatenie;
	}
	public void setCetatenie(String cetatenie) {
		this.cetatenie = cetatenie;
	}

	public String getSituatieScolara() {
		return situatieScolara;
	}
	public void setSituatieScolara(String situatieScolara) {
		this.situatieScolara = situatieScolara;
	}

	public String getSituatieProfesionala() {
		return situatieProfesionala;
	}
	public void setSituatieProfesionala(String situatieProfesionala) {
		this.situatieProfesionala = situatieProfesionala;
	}

	public int getVenitTotalUltimaLuna() {
		return venitTotalUltimaLuna;
	}
	public void setVenitTotalUltimaLuna(int venitTotalUltimaLuna) {
		this.venitTotalUltimaLuna = venitTotalUltimaLuna;
	}

	public String getCuDizabilitati() {
		return cuDizabilitati;
	}
	public void setCuDizabilitati(String cuDizabilitati) {
		this.cuDizabilitati = cuDizabilitati;
	}

	public String getBeneficiatAlteDreptSociale() {
		return beneficiatAlteDreptSociale;
	}
	public void setBeneficiatAlteDreptSociale(String beneficiatAlteDreptSociale) {
		this.beneficiatAlteDreptSociale = beneficiatAlteDreptSociale;
	}

	public String getCategDreptSociale() {
		return categDreptSociale;
	}
	public void setCategDreptSociale(String categDreptSociale) {
		this.categDreptSociale = categDreptSociale;
	}
	
	@Override
	public String toString() {
		return String.format("MF [cnp='%d', nume='%s', prenume='%s']", cnp, nume, prenume) ;
	}

}
