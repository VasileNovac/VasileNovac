package ro.digitalnation.NovacVasile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Familie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String cnpRL, numeRL, prenumeRL ;
	private String adresaLoc, adresaStr, adresaNr, adresaBl, adresaSc, adresaEt, adresaAp, adresaSector, adresaJud, adresaCodP ;
	private String primarie ;
	private int nrPersoaneMajore, nrCopii ;

	public Familie() {	}

	public Familie (String cnpRL, String numeRL, String prenumeRL, String adresaLoc) {
		this.cnpRL = cnpRL ;
		this.numeRL = numeRL ;
		this.prenumeRL = prenumeRL ;
		this.adresaLoc = adresaLoc ;
	}

	public Familie (String cnpRL, String numeRL, String prenumeRL, String adresaLoc, String adresaStr, String adresaNr, String adresaBl, String adresaSc, String adresaEt, String adresaAp, String adresaSector,String adresaJud, String adresaCodP, String primarie, int nrPersoaneMajore, int nrCopii) {
		this.cnpRL = cnpRL ;
		this.numeRL = numeRL ;
		this.prenumeRL = prenumeRL ;
		this.adresaLoc = adresaLoc ;
		this.adresaStr = adresaStr ;
		this.adresaNr = adresaNr ;
		this.adresaBl = adresaBl ;
		this.adresaSc = adresaSc ;
		this.adresaEt = adresaEt ;
		this.adresaAp = adresaAp ;
		this.adresaSector = adresaSector ;
		this.adresaJud = adresaJud ;
		this.adresaCodP = adresaCodP ;
		this.primarie = primarie ;
		this.nrPersoaneMajore = nrPersoaneMajore ;
		this.nrCopii = nrCopii ;
	}
	
	public String getCnpRL() {
		return cnpRL;
	}
	public void setCnpRL(String cnpRL) {
		this.cnpRL = cnpRL;
	}

	public String getNumeRL() {
		return numeRL ;
	}
	public void setNumeRL(String numeRL) {
		this.numeRL = numeRL ;
	}

	public String getPrenumeRL() {
		return prenumeRL ;
	}
	public void setPrenumeRL(String prenumeRL) {
		this.prenumeRL = prenumeRL ;
	}

	public String getAdresaLoc() {
		return adresaLoc;
	}
	public void setAdresaLoc(String adresaLoc) {
		this.adresaLoc = adresaLoc;
	}

	public String getAdresaStr() {
		return adresaStr;
	}
	public void setAdresaStr(String adresaStr) {
		this.adresaStr = adresaStr;
	}

	public String getAdresaNr() {
		return adresaNr;
	}
	public void setAdresaNr(String adresaNr) {
		this.adresaNr = adresaNr;
	}

	public String getAdresaBl() {
		return adresaBl;
	}
	public void setAdresaBl(String adresaBl) {
		this.adresaBl = adresaBl;
	}

	public String getAdresaSc() {
		return adresaSc;
	}
	public void setAdresaSc(String adresaSc) {
		this.adresaSc = adresaSc;
	}

	public String getAdresaEt() {
		return adresaEt;
	}
	public void setAdresaEt(String adresaEt) {
		this.adresaEt = adresaEt;
	}

	public String getAdresaAp() {
		return adresaAp;
	}
	public void setAdresaAp(String adresaAp) {
		this.adresaAp = adresaAp;
	}

	public String getAdresaSector() {
		return adresaSector;
	}
	public void setAdresaSector(String adresaSector) {
		this.adresaSector = adresaSector;
	}

	public String getAdresaJud() {
		return adresaJud;
	}
	public void setAdresaJud(String adresaJud) {
		this.adresaJud = adresaJud;
	}

	public String getAdresaCodP() {
		return adresaCodP;
	}
	public void setAdresaCodP(String adresaCodP) {
		this.adresaCodP = adresaCodP;
	}

	public String getPrimarie() {
		return primarie;
	}
	public void setPrimarie(String primarie) {
		this.primarie = primarie;
	}

	public int getNrPersoaneMajore() {
		return nrPersoaneMajore;
	}
	public void setNrPersoaneMajore(int nrPersoaneMajore) {
		this.nrPersoaneMajore = nrPersoaneMajore;
	}

	public int getNrCopii() {
		return nrCopii;
	}
	public void setNrCopii(int nrCopii) {
		this.nrCopii = nrCopii;
	}
	
	@Override
	public String toString() {
		return String.format("RL [cnpRL='%d', numeRL='%s', prenumeRL='%s']", cnpRL, numeRL, prenumeRL) ;
	}

}
