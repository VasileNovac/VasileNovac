package ro.digitalnation.NovacVasile;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet; 
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableAutoConfiguration
public class VMGController {
	public boolean erori = false ;
	public String xmodul = " " ;
	public String tcnp = " ";
	public String tnume = " " ;
	public String tprenume = " " ;
	public boolean nrView = true ;
	public boolean xcmdExt = false ;
	public static Connection xconn = null ;
    public static Statement stmt = null; 
	private String zmodul, ycnp, zcnp, ynrd, znrd, sql, insUpd ;
    private int xx ;
    public String xcnpRL, xcnp, xnrDispozitie ;
    private String xnumeRL, xprenumeRL, xadresaLoc, xadresaStr, xadresaNr, xadresaBl, xadresaSc, xadresaEt, xadresaAp, xadresaSector, xadresaJud, xadresaCodP, xprimarie ;
    private int xnrPersoaneMajore, xnrCopii, xvenitTotalUltimaLuna ;
    private String xnume, xprenume, xstareCivila, xactId, xserieActId, xnrActId, xdataExpActId, xdataNastere, xcetatenie, xsituatieScolara, xsituatieProfesionala, xcuDizabilitati, xbeneficiatAlteDreptSociale, xcategDreptSociale ;
	private String xgradRudaRL, xsex ;
	private String xdataDispozitie, xcategDispozitie, xdataIntrareVigoare ;

	
	@GetMapping("/rap")
	public String rap(Model model) {

		switch (zmodul) {
			case "rl" :
				model.addAttribute("familie", familieRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul", "rl") ;
				break ;
			case "mp":
				model.addAttribute("masculinParinte", masculinParinteRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul", "mp") ;
				break ;
			case "fp" :
				model.addAttribute("femininParinte", femininParinteRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul","fp") ;
				break ;
			case "copil" :
				model.addAttribute("copil", copilRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul","copil") ;
				break ;
			case "mf" :
				model.addAttribute("altaPersMajora", altaPersMajoraRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul","mf") ;
				break ;
			case "dp" :
				model.addAttribute("dispozitiePrimar", dispozitiePrimarRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul","dp") ;
				break ;
		}
		return "rap";
	}

	@Autowired
	private FamilieRepository familieRepository ;
			
	@GetMapping("/rl")
	public void rl(Model model) {
		zmodul = "rl" ;
		model.addAttribute("ymodul", "rl") ;
		model.addAttribute("nrView", nrView) ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("xcmdExt", xcmdExt) ;
		Familie familie = new Familie() ;
		familie.setCnpRL(ycnp) ;
		if (xcmdExt) {
			familie.setNumeRL(xnumeRL) ;
			familie.setPrenumeRL(xprenumeRL) ;
			familie.setAdresaLoc(xadresaLoc) ;
			familie.setAdresaStr(xadresaStr) ;
			familie.setAdresaNr(xadresaNr) ;
			familie.setAdresaBl(xadresaBl) ;
			familie.setAdresaSc(xadresaSc) ;
			familie.setAdresaEt(xadresaEt) ;
			familie.setAdresaAp(xadresaAp) ;
			familie.setAdresaSector(xadresaSector) ;
			familie.setAdresaJud(xadresaJud) ;
			familie.setAdresaCodP(xadresaCodP) ;
			familie.setPrimarie(xprimarie) ;
			familie.setNrPersoaneMajore(xnrPersoaneMajore) ;
			familie.setNrCopii(xnrCopii) ;
		}
		model.addAttribute("familie", familie) ;
	}

	@PostMapping("/errFamilie")
	public String errFamilie(@ModelAttribute Familie familie, Model model) {
		zmodul = "rl" ;
		model.addAttribute("ymodul", "rl") ;
		nrView = false ;
		erori = false ;
		ycnp = familie.getCnpRL();
		if(! MembriFamilie.verifCNP(familie.getCnpRL())) {
			xmodul = "rl" ;
			ycnp = "" ;
			erori = true ;
		}
		if(!erori) {
			ycnp = familie.getCnpRL();
			xconn = NovacVasileApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM familie") ;
				if ( rs1.next() ) {
					xx = rs1.getInt(1) ;
				}
				if ( xx != 0) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM familie WHERE cnpRL = ?" ;
					PreparedStatement sqlStatement = xconn.prepareStatement(sql);
					sqlStatement.setString(1, ycnp);
					ResultSet rs = sqlStatement.executeQuery();
					if ( rs.next()) {
						zcnp = rs.getString("cnpRL");
					}
					if ( ycnp.equals(zcnp) ) {	
//			exista cnpRL - se va afisa comanda tip buton-submit MODIFICARE
						xcmdExt = true ;
						familie.setCnpRL(rs.getString("cnpRL")) ;
//						xcnpRL = familie.getCnpRL();
						familie.setNumeRL(rs.getString("numeRL")) ;
						xnumeRL = familie.getNumeRL();
						familie.setPrenumeRL(rs.getString("prenumeRL")) ;
						xprenumeRL = familie.getPrenumeRL();
						familie.setAdresaLoc(rs.getString("adresaLoc")) ;
						xadresaLoc = familie.getAdresaLoc();
						familie.setAdresaStr(rs.getString("adresaStr")) ;
						xadresaStr = familie.getAdresaStr() ;
						familie.setAdresaNr(rs.getString("adresaNr")) ;
						xadresaNr = familie.getAdresaNr();
						familie.setAdresaBl(rs.getString("adresaBl")) ;
						xadresaBl = familie.getAdresaBl() ;
						familie.setAdresaSc(rs.getString("adresaSc")) ;
						xadresaSc = familie.getAdresaSc();
						familie.setAdresaEt(rs.getString("adresaEt")) ;
						xadresaEt = familie.getAdresaEt();
						familie.setAdresaAp(rs.getString("adresaAp")) ;
						xadresaAp = familie.getAdresaAp();
						familie.setAdresaSector(rs.getString("adresaSector")) ;
						xadresaSector = familie.getAdresaSector() ;
						familie.setAdresaJud(rs.getString("adresaJud")) ;
						xadresaJud = familie.getAdresaJud();
						familie.setAdresaCodP(rs.getString("adresaCodP")) ;
						xadresaCodP = familie.getAdresaCodP();
						familie.setPrimarie(rs.getString("primarie")) ;
						xprimarie = familie.getPrimarie();
						familie.setNrPersoaneMajore(rs.getInt("nrPersoaneMajore")) ;
						xnrPersoaneMajore = familie.getNrPersoaneMajore() ;
						familie.setNrCopii(rs.getInt("nrCopii")) ;
						xnrCopii = familie.getNrCopii();
					}
				} else {
//			 nu exista cnpRL - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//	Handle errors for JDBC 
				se.printStackTrace(); 
			}
		}
		return "redirect:rl" ;
	}
	
	@PostMapping("/addFamilie")
	public String addFamilie(@ModelAttribute Familie familie, Model model) {
		zmodul = "rl" ;
		erori = false ;
		nrView = true ;
		ycnp = "";
		if(! MembriFamilie.verifCNP(familie.getCnpRL())) {
			xmodul = "rl" ;
			erori = true ;
			return "redirect:rl" ;
		}
		familieRepository.save(familie) ;
		tcnp = familie.getCnpRL() ;
		tnume = familie.getNumeRL() ;
		tprenume = familie.getPrenumeRL() ;
		
		xconn = NovacVasileApplication.conn ;
		try {
			if (xcmdExt) {
				insUpd = "UPDATE familie SET numeRL=?, prenumeRL=?, adresaLoc=?, adresaStr=?, adresaNr=?, adresaBl=?, adresaSc=?, adresaEt=?, adresaAp=?, adresaSector=?, adresaJud=?, adresaCodP=?, primarie=?, nrPersoaneMajore=?, nrCopii=? WHERE cnpRL=? " ;
			} else {
				insUpd = "INSERT INTO familie (numeRL, prenumeRL, adresaLoc, adresaStr, adresaNr, adresaBl, adresaSc, adresaEt, adresaAp, adresaSector, adresaJud, adresaCodP, primarie, nrPersoaneMajore, nrCopii, cnpRL) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
			}
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, familie.getNumeRL());
				iS.setString(2, familie.getPrenumeRL()) ;
				iS.setString(3, familie.getAdresaLoc()) ;
				iS.setString(4, familie.getAdresaStr()) ;
				iS.setString(5, familie.getAdresaNr()) ;
				iS.setString(6, familie.getAdresaBl()) ;
				iS.setString(7, familie.getAdresaSc()) ;
				iS.setString(8, familie.getAdresaEt()) ;
				iS.setString(9, familie.getAdresaAp()) ;
				iS.setString(10, familie.getAdresaSector()) ;
				iS.setString(11, familie.getAdresaJud()) ;
				iS.setString(12, familie.getAdresaCodP()) ;
				iS.setString(13, familie.getPrimarie()) ;
				iS.setInt(14, familie.getNrPersoaneMajore()) ;
				iS.setInt(15, familie.getNrCopii()) ;
				iS.setString(16, familie.getCnpRL());
				iS.executeUpdate();
				iS.close();
				xcmdExt = false ;
	//	Execute a query 
			stmt = xconn.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM familie"); 
	//	Extract data from result set 
			while(rs.next()) { 
	//	Retrieve by column name 
				String cnpRL = rs.getString("cnpRL"); 
				String numeRL = rs.getString("numeRL"); 
				String prenumeRL = rs.getString("prenumeRL");
				String adresaLoc = rs.getString("adresaLoc") ;
				String adresaStr = rs.getString("adresaStr") ;
				String adresaNr = rs.getString("adresaNr");
				String adresaBl = rs.getString("adresaBl") ;
				String adresaSc = rs.getString("adresaSc") ;
				String adresaEt = rs.getString("adresaEt") ;
				String adresaAp = rs.getString("adresaAp") ;
				String adresaSector = rs.getString("adresaSector");
				String adresaJud = rs.getString("adresaJud");
				String adresaCodP = rs.getString("adresaCodP") ;
				String primarie = rs.getString("primarie") ;
				String nrPersoaneMajore  = rs.getString("nrPersoaneMajore") ;
				String nrCopii = rs.getString("nrCopii") ;
	//	Display values 
				System.out.println(cnpRL+", "+numeRL+", "+prenumeRL+ ", "+adresaLoc+", "+adresaStr+", "+adresaNr+", "+adresaBl+", "+adresaSc+", "+adresaEt+", "+adresaAp+", "+adresaSector+", "+adresaJud+", "+adresaCodP+", "+primarie+", "+nrPersoaneMajore+", "+nrCopii);
			}
		} catch(SQLException se) { 
	//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:rap";
//		return "redirect:rl" ;
	}

	@Autowired
	private MasculinParinteRepository masculinParinteRepository ;

	@GetMapping("/mp")
	public void mp(Model model) {
		zmodul = "mp" ;
		model.addAttribute("ymodul", "mp") ;
		model.addAttribute("nrView", nrView) ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("xcmdExt", xcmdExt) ;
		model.addAttribute("tcnp", tcnp) ;
		model.addAttribute("tnume", tnume) ;
		model.addAttribute("tprenume", tprenume) ;
		MasculinParinte masculinParinte = new MasculinParinte() ;
		masculinParinte.setCnp(ycnp) ;
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> scList = MembriFamilie.listSC();
		model.addAttribute("scList", scList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> spList = MembriFamilie.listSP() ;
		model.addAttribute("spList", spList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		if (xcmdExt) {
			masculinParinte.setNume(xnume) ;
			masculinParinte.setPrenume(xprenume) ;
			masculinParinte.setActId(xactId) ;
			masculinParinte.setSerieActId(xserieActId) ;
			masculinParinte.setNrActId(xnrActId) ;
			masculinParinte.setDataExpActId(xdataExpActId) ;
			masculinParinte.setStareCivila(xstareCivila) ;
			masculinParinte.setDataNastere(xdataNastere) ;
			masculinParinte.setCetatenie(xcetatenie) ;
			masculinParinte.setSituatieScolara(xsituatieScolara) ;
			masculinParinte.setSituatieProfesionala(xsituatieProfesionala) ;
			masculinParinte.setVenitTotalUltimaLuna(xvenitTotalUltimaLuna) ;
			masculinParinte.setCuDizabilitati(xcuDizabilitati) ;
			masculinParinte.setBeneficiatAlteDreptSociale(xbeneficiatAlteDreptSociale) ;
			masculinParinte.setCategDreptSociale(xcategDreptSociale) ;
		}
		model.addAttribute("masculinParinte", masculinParinte) ;
	}

	@PostMapping("/errMasculinParinte")
	public String errMasculinParinte ( @ModelAttribute MasculinParinte masculinParinte, Model model) {
		zmodul = "mp" ;
		model.addAttribute("ymodul", "mp") ;
		nrView = false ;
		erori = false ;
		ycnp = masculinParinte.getCnp();
		if (! MembriFamilie.verifCNP(masculinParinte.getCnp())) {
			xmodul = "mp" ;
			ycnp = "" ;
			erori = true ;
		}
		if(!erori) {
			ycnp = masculinParinte.getCnp();
			xconn = NovacVasileApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM masculinParinte") ;
				if ( rs1.next()) {
					xx = rs1.getInt(1) ;
				}
				if ( xx != 0) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM masculinParinte WHERE cnp = ?" ;
					PreparedStatement sqlStatement = xconn.prepareStatement(sql);
					sqlStatement.setString(1, ycnp);
					ResultSet rs = sqlStatement.executeQuery();
					if ( rs.next()) {
						zcnp = rs.getString("cnp");
					}
					if ( ycnp.equals(zcnp) ) {	
//			exista cnpRL - se va afisa comanda tip buton-submit MODIFICARE
						xcmdExt = true ;
						masculinParinte.setCnp(rs.getString("cnp")) ;
//						xcnp = masculinParinte.getCnp();
						masculinParinte.setNume(rs.getString("nume")) ;
						xnume = masculinParinte.getNume();
						masculinParinte.setPrenume(rs.getString("prenume")) ;
						xprenume = masculinParinte.getPrenume();
						masculinParinte.setActId(rs.getString("actId")) ;
						xactId = masculinParinte.getActId();
						masculinParinte.setSerieActId(rs.getString("serieActId")) ;
						xserieActId = masculinParinte.getSerieActId() ;
						masculinParinte.setNrActId(rs.getString("nrActId")) ;
						xnrActId = masculinParinte.getNrActId();
						masculinParinte.setDataExpActId(rs.getString("dataExpActId")) ;
						xdataExpActId = masculinParinte.getDataExpActId() ;
						masculinParinte.setStareCivila(rs.getString("stareCivila")) ;
						xstareCivila = masculinParinte.getStareCivila();
						masculinParinte.setDataNastere(rs.getString("dataNastere")) ;
						xdataNastere = masculinParinte.getDataNastere();
						masculinParinte.setCetatenie(rs.getString("cetatenie")) ;
						xcetatenie = masculinParinte.getCetatenie();
						masculinParinte.setSituatieScolara(rs.getString("situatieScolara")) ;
						xsituatieScolara = masculinParinte.getSituatieScolara() ;
						masculinParinte.setSituatieProfesionala(rs.getString("situatieProfesionala")) ;
						xsituatieProfesionala = masculinParinte.getSituatieProfesionala();
						masculinParinte.setVenitTotalUltimaLuna(rs.getInt("venitTotalUltimaLuna")) ;
						xvenitTotalUltimaLuna = masculinParinte.getVenitTotalUltimaLuna();
						masculinParinte.setCuDizabilitati(rs.getString("cuDizabilitati")) ;
						xcuDizabilitati = masculinParinte.getCuDizabilitati();
						masculinParinte.setBeneficiatAlteDreptSociale(rs.getString("beneficiatAlteDreptSociale")) ;
						xbeneficiatAlteDreptSociale = masculinParinte.getBeneficiatAlteDreptSociale() ;
						masculinParinte.setCategDreptSociale(rs.getString("categDreptSociale")) ;
						xcategDreptSociale = masculinParinte.getCategDreptSociale();
					}
				} else {
//			 nu exista cnpRL - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//	Handle errors for JDBC 
				se.printStackTrace(); 
			}
		}
		return "redirect:mp" ;
	}

	@PostMapping("/addMasculinParinte")
	public String addMasculinParinte(@ModelAttribute MasculinParinte masculinParinte, Model model) {
		zmodul = "mp" ;
		erori = false ;
		nrView = true ;
		ycnp = "" ;
		if (! MembriFamilie.verifCNP(masculinParinte.getCnp())) {
			xmodul = "mp" ;
			erori = true ;
			return "redirect:mp" ;
		}
		masculinParinte.setSex("M") ;
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> scList = MembriFamilie.listSC();
		model.addAttribute("scList", scList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> spList = MembriFamilie.listSP() ;
		model.addAttribute("spList", spList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		masculinParinteRepository.save(masculinParinte) ;

		xconn = NovacVasileApplication.conn ;
		try {
			if (xcmdExt) {
				insUpd = "UPDATE masculinParinte SET nume=?, prenume=?, actId=?, serieActId=?, nrActId=?, dataExpActId=?, stareCivila=?, dataNastere=?, cetatenie=?, situatieScolara=?, situatieProfesionala=?, venitTotalUltimaLuna=?, cuDizabilitati=?, beneficiatAlteDreptSociale=?, categDreptSociale=?, sex=?, cnprl=? WHERE cnp=? " ;
			} else {
				insUpd = "INSERT INTO masculinParinte (nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex, cnprl, cnp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			}
				PreparedStatement iS = xconn.prepareStatement(insUpd);
				iS.setString(1, masculinParinte.getNume());
				iS.setString(2, masculinParinte.getPrenume()) ;
				iS.setString(3, masculinParinte.getActId()) ;
				iS.setString(4, masculinParinte.getSerieActId()) ;
				iS.setString(5, masculinParinte.getNrActId()) ;
				iS.setString(6, masculinParinte.getDataExpActId()) ;
				iS.setString(7, masculinParinte.getStareCivila()) ;
				iS.setString(8, masculinParinte.getDataNastere()) ;
				iS.setString(9,masculinParinte.getCetatenie()) ;
				iS.setString(10, masculinParinte.getSituatieScolara()) ;
				iS.setString(11, masculinParinte.getSituatieProfesionala()) ;
				iS.setInt(12, masculinParinte.getVenitTotalUltimaLuna()) ;
				iS.setString(13, masculinParinte.getCuDizabilitati()) ;
				iS.setString(14, masculinParinte.getBeneficiatAlteDreptSociale()) ;
				iS.setString(15, masculinParinte.getCategDreptSociale()) ;
				iS.setString(16, masculinParinte.getSex()) ;
				iS.setString(17, tcnp) ;
				iS.setString(18, masculinParinte.getCnp());
				iS.executeUpdate();
				iS.close();
				xcmdExt = false ;
//	Execute a query
				stmt = xconn.createStatement(); 
				ResultSet rs = stmt.executeQuery("SELECT * FROM masculinParinte"); 
//	Extract data from result set 
				while(rs.next()) { 
//	Retrieve by column name  
					String cnp = rs.getString("cnp"); 
					String nume = rs.getString("nume"); 
					String prenume = rs.getString("prenume");
					String actId = rs.getString("actId") ;
					String serieActId = rs.getString("serieActId") ;
					String nrActId = rs.getString("nrActId");
					String dataExpActId = rs.getString("dataExpActId") ;
					String stareCivila = rs.getString("stareCivila") ;
					String dataNastere = rs.getString("dataNastere") ;
					String cetatenie = rs.getString("cetatenie") ;
					String situatieScolara = rs.getString("situatieScolara") ;
					String situatieProfesionala = rs.getString("situatieProfesionala") ;
					String venitTotalUltimaLuna = rs.getString("venitTotalUltimaLuna") ;
					String cuDizabilitati = rs.getString("cuDizabilitati") ;
					String beneficiatAlteDreptSociale = rs.getString("beneficiatAlteDreptSociale") ;
					String categDreptSociale = rs.getString("categDreptSociale");
					String sex = rs.getString("sex") ;
					String cnprl = rs.getString("cnprl") ;
//	Display values 
					System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+stareCivila+", "+dataNastere+", "+cetatenie+", "+situatieScolara+", "+situatieProfesionala+", "+venitTotalUltimaLuna+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+sex+", "+cnprl);
				}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}

		return "redirect:rap" ;
//		return "mp" ;
	}
	
	@Autowired
	private FemininParinteRepository femininParinteRepository ;

	@GetMapping("/fp")
	public void fp(Model model) {
		zmodul = "fp" ;
		model.addAttribute("ymodul", "fp") ;
		model.addAttribute("nrView", nrView) ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("xcmdExt", xcmdExt) ;
		model.addAttribute("tcnp", tcnp) ;
		model.addAttribute("tnume", tnume) ;
		model.addAttribute("tprenume", tprenume) ;
		FemininParinte femininParinte = new FemininParinte() ;
		femininParinte.setCnp(ycnp) ;
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> scList = MembriFamilie.listSC();
		model.addAttribute("scList", scList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> spList = MembriFamilie.listSP() ;
		model.addAttribute("spList", spList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		if (xcmdExt) {
			femininParinte.setNume(xnume) ;
			femininParinte.setPrenume(xprenume) ;
			femininParinte.setActId(xactId) ;
			femininParinte.setSerieActId(xserieActId) ;
			femininParinte.setNrActId(xnrActId) ;
			femininParinte.setDataExpActId(xdataExpActId) ;
			femininParinte.setStareCivila(xstareCivila) ;
			femininParinte.setDataNastere(xdataNastere) ;
			femininParinte.setCetatenie(xcetatenie) ;
			femininParinte.setSituatieScolara(xsituatieScolara) ;
			femininParinte.setSituatieProfesionala(xsituatieProfesionala) ;
			femininParinte.setVenitTotalUltimaLuna(xvenitTotalUltimaLuna) ;
			femininParinte.setCuDizabilitati(xcuDizabilitati) ;
			femininParinte.setBeneficiatAlteDreptSociale(xbeneficiatAlteDreptSociale) ;
			femininParinte.setCategDreptSociale(xcategDreptSociale) ;
		}
		model.addAttribute("femininParinte", femininParinte) ;
	}

	@PostMapping("/errFemininParinte")
	public String errFemininParinte ( @ModelAttribute FemininParinte femininParinte, Model model) {
		zmodul = "fp" ;
		model.addAttribute("ymodul", "fp") ;
		nrView = false ;
		erori = false ;
		ycnp = femininParinte.getCnp();
		if (! MembriFamilie.verifCNP(femininParinte.getCnp())) {
			xmodul = "fp" ;
			ycnp = "" ;
			erori = true ;
		}
		if(!erori) {
			ycnp = femininParinte.getCnp();
			xconn = NovacVasileApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM femininParinte") ;
				if ( rs1.next()) {
					xx = rs1.getInt(1) ;
				}
				if ( xx != 0) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM femininParinte WHERE cnp = ?" ;
					PreparedStatement sqlStatement = xconn.prepareStatement(sql);
					sqlStatement.setString(1, ycnp);
					ResultSet rs = sqlStatement.executeQuery();
					if ( rs.next()) {
						zcnp = rs.getString("cnp");
					}
					if ( ycnp.equals(zcnp) ) {	
//			exista cnpRL - se va afisa comanda tip buton-submit MODIFICARE
						xcmdExt = true ;
						femininParinte.setCnp(rs.getString("cnp")) ;
//						xcnp = femininParinte.getCnp();
						femininParinte.setNume(rs.getString("nume")) ;
						xnume = femininParinte.getNume();
						femininParinte.setPrenume(rs.getString("prenume")) ;
						xprenume = femininParinte.getPrenume();
						femininParinte.setActId(rs.getString("actId")) ;
						xactId = femininParinte.getActId();
						femininParinte.setSerieActId(rs.getString("serieActId")) ;
						xserieActId = femininParinte.getSerieActId() ;
						femininParinte.setNrActId(rs.getString("nrActId")) ;
						xnrActId = femininParinte.getNrActId();
						femininParinte.setDataExpActId(rs.getString("dataExpActId")) ;
						xdataExpActId = femininParinte.getDataExpActId() ;
						femininParinte.setStareCivila(rs.getString("stareCivila")) ;
						xstareCivila = femininParinte.getStareCivila();
						femininParinte.setDataNastere(rs.getString("dataNastere")) ;
						xdataNastere = femininParinte.getDataNastere();
						femininParinte.setCetatenie(rs.getString("cetatenie")) ;
						xcetatenie = femininParinte.getCetatenie();
						femininParinte.setSituatieScolara(rs.getString("situatieScolara")) ;
						xsituatieScolara = femininParinte.getSituatieScolara() ;
						femininParinte.setSituatieProfesionala(rs.getString("situatieProfesionala")) ;
						xsituatieProfesionala = femininParinte.getSituatieProfesionala();
						femininParinte.setVenitTotalUltimaLuna(rs.getInt("venitTotalUltimaLuna")) ;
						xvenitTotalUltimaLuna = femininParinte.getVenitTotalUltimaLuna();
						femininParinte.setCuDizabilitati(rs.getString("cuDizabilitati")) ;
						xcuDizabilitati = femininParinte.getCuDizabilitati();
						femininParinte.setBeneficiatAlteDreptSociale(rs.getString("beneficiatAlteDreptSociale")) ;
						xbeneficiatAlteDreptSociale = femininParinte.getBeneficiatAlteDreptSociale() ;
						femininParinte.setCategDreptSociale(rs.getString("categDreptSociale")) ;
						xcategDreptSociale = femininParinte.getCategDreptSociale();
					}
				} else {
//			 nu exista cnpRL - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//	Handle errors for JDBC 
				se.printStackTrace(); 
			}
		}
		return "redirect:fp" ;
	}

	@PostMapping("/addFemininParinte")
	public String addFemininParinte(@ModelAttribute FemininParinte femininParinte, Model model) {
		zmodul = "fp" ;
		erori = false ;
		nrView = true ;
		ycnp = "" ;
		if (! MembriFamilie.verifCNP(femininParinte.getCnp())) {
			xmodul = "fp" ;
			erori = true ;
			return "redirect:fp" ;
		}
		femininParinte.setSex("F") ;
		model.addAttribute("femininParinte", femininParinte) ;
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> scList = MembriFamilie.listSC();
		model.addAttribute("scList", scList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> spList = MembriFamilie.listSP() ;
		model.addAttribute("spList", spList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		femininParinteRepository.save(femininParinte) ;
		
		xconn = NovacVasileApplication.conn ;
		try {
			if (xcmdExt) {
				insUpd = "UPDATE femininParinte SET nume=?, prenume=?, actId=?, serieActId=?, nrActId=?, dataExpActId=?, stareCivila=?, dataNastere=?, cetatenie=?, situatieScolara=?, situatieProfesionala=?, venitTotalUltimaLuna=?, cuDizabilitati=?, beneficiatAlteDreptSociale=?, categDreptSociale=?, sex=?, cnprl=? WHERE cnp=? " ;
			} else {
				insUpd = "INSERT INTO femininParinte (nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex, cnprl, cnp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			}
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, femininParinte.getNume());
			iS.setString(2, femininParinte.getPrenume()) ;
			iS.setString(3, femininParinte.getActId()) ;
			iS.setString(4, femininParinte.getSerieActId()) ;
			iS.setString(5, femininParinte.getNrActId()) ;
			iS.setString(6, femininParinte.getDataExpActId()) ;
			iS.setString(7, femininParinte.getStareCivila()) ;
			iS.setString(8, femininParinte.getDataNastere()) ;
			iS.setString(9, femininParinte.getCetatenie()) ;
			iS.setString(10, femininParinte.getSituatieScolara()) ;
			iS.setString(11, femininParinte.getSituatieProfesionala()) ;
			iS.setInt(12, femininParinte.getVenitTotalUltimaLuna()) ;
			iS.setString(13, femininParinte.getCuDizabilitati()) ;
			iS.setString(14, femininParinte.getBeneficiatAlteDreptSociale()) ;
			iS.setString(15, femininParinte.getCategDreptSociale()) ;
			iS.setString(16, femininParinte.getSex()) ;
			iS.setString(17, tcnp) ;
			iS.setString(18, femininParinte.getCnp());
			iS.executeUpdate();
			iS.close();
			xcmdExt = false ;
//	Execute a query 
			stmt = xconn.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM femininParinte"); 
//	Extract data from result set 
			while(rs.next()) { 
//	Retrieve by column name  
				String cnp = rs.getString("cnp"); 
				String nume = rs.getString("nume"); 
				String prenume = rs.getString("prenume");
				String actId = rs.getString("actId") ;
				String serieActId = rs.getString("serieActId") ;
				String nrActId = rs.getString("nrActId");
				String dataExpActId = rs.getString("dataExpActId") ;
				String stareCivila = rs.getString("stareCivila") ;
				String dataNastere = rs.getString("dataNastere") ;
				String cetatenie = rs.getString("cetatenie") ;
				String situatieScolara = rs.getString("situatieScolara") ;
				String situatieProfesionala = rs.getString("situatieProfesionala") ;
				String venitTotalUltimaLuna = rs.getString("venitTotalUltimaLuna") ;
				String cuDizabilitati = rs.getString("cuDizabilitati") ;
				String beneficiatAlteDreptSociale = rs.getString("beneficiatAlteDreptSociale") ;
				String categDreptSociale = rs.getString("categDreptSociale");
				String sex = rs.getString("sex") ;
				String cnprl = rs.getString("cnprl") ;
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+stareCivila+", "+dataNastere+", "+cetatenie+", "+situatieScolara+", "+situatieProfesionala+", "+venitTotalUltimaLuna+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+sex+", "+cnprl);
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:rap" ;
//		return "fp" ;
	}
	
	@Autowired
	private CopilRepository copilRepository ;

	@GetMapping("/copil")
	public void copil(Model model) {
		zmodul = "copil" ;
		model.addAttribute("ymodul", "copil") ;
		model.addAttribute("nrView", nrView) ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("xcmdExt", xcmdExt) ;
		model.addAttribute("tcnp", tcnp) ;
		model.addAttribute("tnume", tnume) ;
		model.addAttribute("tprenume", tprenume) ;
		Copil copil = new Copil() ;
		copil.setCnp(ycnp) ;
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		ArrayList<String> grList = MembriFamilie.listGR() ;
		model.addAttribute("grList", grList) ;
		HashMap<String, String> sxList = MembriFamilie.listSX();
		model.addAttribute("sxList", sxList) ;
		if (xcmdExt) {
			copil.setNume(xnume) ;
			copil.setPrenume(xprenume) ;
			copil.setActId(xactId) ;
			copil.setSerieActId(xserieActId) ;
			copil.setNrActId(xnrActId) ;
			copil.setDataExpActId(xdataExpActId) ;
			copil.setDataNastere(xdataNastere) ;
			copil.setSituatieScolara(xsituatieScolara) ;
			copil.setCuDizabilitati(xcuDizabilitati) ;
			copil.setBeneficiatAlteDreptSociale(xbeneficiatAlteDreptSociale) ;
			copil.setCategDreptSociale(xcategDreptSociale) ;
			copil.setGradRudaRL(xgradRudaRL) ;
			copil.setSex(xsex) ;
		}
		model.addAttribute("copil", copil) ;
	}

	@PostMapping("/errCopil")
	public String errCopil(@ModelAttribute Copil copil, Model model) {
		zmodul = "copil" ;
		model.addAttribute("ymodul", "copil") ;
		nrView = false ;
		erori = false ;
		ycnp = copil.getCnp();
		if(! MembriFamilie.verifCNP(copil.getCnp())) {
			xmodul = "copil" ;
			ycnp = "" ;
			erori = true ;
		}
		if(!erori) {
			ycnp = copil.getCnp();
			xconn = NovacVasileApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM copil") ;
				if ( rs1.next()) {
					xx = rs1.getInt(1) ;
				}
				if ( xx != 0) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM copil WHERE cnp = ?" ;
					PreparedStatement sqlStatement = xconn.prepareStatement(sql);
					sqlStatement.setString(1, ycnp);
					ResultSet rs = sqlStatement.executeQuery();
					if ( rs.next()) {
						zcnp = rs.getString("cnp");
					}
					if ( ycnp.equals(zcnp) ) {	
//			exista cnpRL - se va afisa comanda tip buton-submit MODIFICARE
						xcmdExt = true ;
						copil.setCnp(rs.getString("cnp")) ;
//						xcnp = copil.getCnp();
						copil.setNume(rs.getString("nume")) ;
						xnume = copil.getNume();
						copil.setPrenume(rs.getString("prenume")) ;
						xprenume = copil.getPrenume();
						copil.setActId(rs.getString("actId")) ;
						xactId = copil.getActId();
						copil.setSerieActId(rs.getString("serieActId")) ;
						xserieActId = copil.getSerieActId() ;
						copil.setNrActId(rs.getString("nrActId")) ;
						xnrActId = copil.getNrActId();
						copil.setDataExpActId(rs.getString("dataExpActId")) ;
						xdataExpActId = copil.getDataExpActId() ;
						copil.setDataNastere(rs.getString("dataNastere")) ;
						xdataNastere = copil.getDataNastere();
						copil.setSituatieScolara(rs.getString("situatieScolara")) ;
						xsituatieScolara = copil.getSituatieScolara() ;
						copil.setCuDizabilitati(rs.getString("cuDizabilitati")) ;
						xcuDizabilitati = copil.getCuDizabilitati();
						copil.setBeneficiatAlteDreptSociale(rs.getString("beneficiatAlteDreptSociale")) ;
						xbeneficiatAlteDreptSociale = copil.getBeneficiatAlteDreptSociale() ;
						copil.setCategDreptSociale(rs.getString("categDreptSociale")) ;
						xcategDreptSociale = copil.getCategDreptSociale();
						copil.setGradRudaRL(rs.getString("gradRudaRL")) ;
						xgradRudaRL = copil.getGradRudaRL();
						copil.setSex(rs.getString("sex")) ;
						xsex = copil.getSex();
					}
				} else {
//			 nu exista cnpRL - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//	Handle errors for JDBC 
				se.printStackTrace(); 
			}
		}
		return "redirect:copil" ;
	}

	@PostMapping("/addCopil")
	public String addCopil(@ModelAttribute Copil copil, Model model) {
		zmodul = "copil" ;
		erori = false ;
		nrView = true ;
		ycnp = "" ;
		if(! MembriFamilie.verifCNP(copil.getCnp())) {
			xmodul = "copil" ;
			erori = true ;
			return "redirect:copil" ;
		}
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		ArrayList<String> grList = MembriFamilie.listGR() ;
		model.addAttribute("grList", grList) ;
		HashMap<String, String> sxList = MembriFamilie.listSX();
		model.addAttribute("sxList", sxList) ;
		copilRepository.save(copil) ;

        xconn = NovacVasileApplication.conn ;
		try {
			if (xcmdExt) {
				insUpd = "UPDATE copil SET nume=?, prenume=?, actId=?, serieActId=?, nrActId=?, dataExpActId=?, dataNastere=?, situatieScolara=?, cuDizabilitati=?, beneficiatAlteDreptSociale=?, categDreptSociale=?, gradRudaRL=?, sex=?, cnprl=? WHERE cnp=? " ;
			} else {
				insUpd = "INSERT INTO copil (nume, prenume, actId, serieActId, nrActId, dataExpActId, dataNastere, situatieScolara, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, gradRudaRL, sex, cnprl, cnp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			}
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, copil.getNume());
			iS.setString(2, copil.getPrenume()) ;
			iS.setString(3, copil.getActId()) ;
			iS.setString(4, copil.getSerieActId()) ;
			iS.setString(5, copil.getNrActId()) ;
			iS.setString(6, copil.getDataExpActId()) ;
			iS.setString(7, copil.getDataNastere()) ;
			iS.setString(8, copil.getSituatieScolara()) ;
			iS.setString(9, copil.getCuDizabilitati()) ;
			iS.setString(10, copil.getBeneficiatAlteDreptSociale()) ;
			iS.setString(11, copil.getCategDreptSociale()) ;
			iS.setString(12, copil.getGradRudaRL()) ;
			iS.setString(13, copil.getSex()) ;
			iS.setString(14, tcnp) ;
			iS.setString(15, copil.getCnp());
			iS.executeUpdate();
			iS.close();
			xcmdExt = false ;
//	Execute a query 
			stmt = xconn.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM copil"); 
//	Extract data from result set 
			while(rs.next()) { 
//	Retrieve by column name  
				String cnp = rs.getString("cnp"); 
				String nume = rs.getString("nume"); 
				String prenume = rs.getString("prenume");
				String actId = rs.getString("actId") ;
				String serieActId = rs.getString("serieActId") ;
				String nrActId = rs.getString("nrActId");
				String dataExpActId = rs.getString("dataExpActId") ;
				String dataNastere = rs.getString("dataNastere") ;
				String situatieScolara = rs.getString("situatieScolara") ;
				String cuDizabilitati = rs.getString("cuDizabilitati") ;
				String beneficiatAlteDreptSociale = rs.getString("beneficiatAlteDreptSociale") ;
				String categDreptSociale = rs.getString("categDreptSociale");
				String gradRudaRL = rs.getString("gradRudaRL");
				String sex = rs.getString("sex") ;
				String cnprl = rs.getString("cnprl") ;
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+dataNastere+", "+situatieScolara+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+gradRudaRL+", "+sex+", "+cnprl);
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:rap" ;
//		return "copil" ;
	}
	

	@Autowired
	private AltaPersMajoraRepository altaPersMajoraRepository ;

	@GetMapping("/mf")
	public void mf(Model model) {
		zmodul = "mf" ;
		model.addAttribute("ymodul", "mf") ;
		model.addAttribute("nrView", nrView) ;
		model.addAttribute("erori", erori) ;
		model.addAttribute("xcmdExt", xcmdExt) ;
		model.addAttribute("tcnp", tcnp) ;
		model.addAttribute("tnume", tnume) ;
		model.addAttribute("tprenume", tprenume) ;
		AltaPersMajora altaPersMajora = new AltaPersMajora() ;
		altaPersMajora.setCnp(ycnp) ;
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> scList = MembriFamilie.listSC();
		model.addAttribute("scList", scList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> spList = MembriFamilie.listSP() ;
		model.addAttribute("spList", spList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		HashMap<String, String> sxList = MembriFamilie.listSX();
		model.addAttribute("sxList", sxList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		if (xcmdExt) {
			altaPersMajora.setNume(xnume) ;
			altaPersMajora.setPrenume(xprenume) ;
			altaPersMajora.setActId(xactId) ;
			altaPersMajora.setSerieActId(xserieActId) ;
			altaPersMajora.setNrActId(xnrActId) ;
			altaPersMajora.setDataExpActId(xdataExpActId) ;
			altaPersMajora.setStareCivila(xstareCivila) ;
			altaPersMajora.setDataNastere(xdataNastere) ;
			altaPersMajora.setCetatenie(xcetatenie) ;
			altaPersMajora.setSituatieScolara(xsituatieScolara) ;
			altaPersMajora.setSituatieProfesionala(xsituatieProfesionala) ;
			altaPersMajora.setVenitTotalUltimaLuna(xvenitTotalUltimaLuna) ;
			altaPersMajora.setCuDizabilitati(xcuDizabilitati) ;
			altaPersMajora.setBeneficiatAlteDreptSociale(xbeneficiatAlteDreptSociale) ;
			altaPersMajora.setCategDreptSociale(xcategDreptSociale) ;
			altaPersMajora.setSex(xsex) ;
		}
		model.addAttribute("altaPersMajora", altaPersMajora) ;
	}

	@PostMapping("/errAltaPersMajora")
	public String errAltaPersMajora ( @ModelAttribute AltaPersMajora altaPersMajora, Model model) {
		zmodul = "mf" ;
		model.addAttribute("ymodul", "mf") ;
		nrView = false ;
		erori = false ;
		ycnp = altaPersMajora.getCnp() ;
		if (! MembriFamilie.verifCNP(altaPersMajora.getCnp())) {
			xmodul = "mf" ;
			ycnp = "" ;
			erori = true ;
		}
		if(!erori) {
			ycnp = altaPersMajora.getCnp();
			xconn = NovacVasileApplication.conn ;
			try {
				stmt = xconn.createStatement();
				ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM altaPersMajora") ;
				if ( rs1.next()) {
					xx = rs1.getInt(1) ;
				}
				if ( xx != 0) {
					stmt = xconn.createStatement();
					sql = "SELECT * FROM altaPersMajora WHERE cnp = ?" ;
					PreparedStatement sqlStatement = xconn.prepareStatement(sql);
					sqlStatement.setString(1, ycnp);
					ResultSet rs = sqlStatement.executeQuery();
					if ( rs.next()) {
						zcnp = rs.getString("cnp");
					}
					if ( ycnp.equals(zcnp) ) {	
//			exista cnpRL - se va afisa comanda tip buton-submit MODIFICARE
						xcmdExt = true ;
						altaPersMajora.setCnp(rs.getString("cnp")) ;
//						xcnp = altaPersMajora.getCnp();
						altaPersMajora.setNume(rs.getString("nume")) ;
						xnume = altaPersMajora.getNume();
						altaPersMajora.setPrenume(rs.getString("prenume")) ;
						xprenume = altaPersMajora.getPrenume();
						altaPersMajora.setActId(rs.getString("actId")) ;
						xactId = altaPersMajora.getActId();
						altaPersMajora.setSerieActId(rs.getString("serieActId")) ;
						xserieActId = altaPersMajora.getSerieActId() ;
						altaPersMajora.setNrActId(rs.getString("nrActId")) ;
						xnrActId = altaPersMajora.getNrActId();
						altaPersMajora.setDataExpActId(rs.getString("dataExpActId")) ;
						xdataExpActId = altaPersMajora.getDataExpActId() ;
						altaPersMajora.setStareCivila(rs.getString("stareCivila")) ;
						xstareCivila = altaPersMajora.getStareCivila();
						altaPersMajora.setDataNastere(rs.getString("dataNastere")) ;
						xdataNastere = altaPersMajora.getDataNastere();
						altaPersMajora.setCetatenie(rs.getString("cetatenie")) ;
						xcetatenie = altaPersMajora.getCetatenie();
						altaPersMajora.setSituatieScolara(rs.getString("situatieScolara")) ;
						xsituatieScolara = altaPersMajora.getSituatieScolara() ;
						altaPersMajora.setSituatieProfesionala(rs.getString("situatieProfesionala")) ;
						xsituatieProfesionala = altaPersMajora.getSituatieProfesionala();
						altaPersMajora.setVenitTotalUltimaLuna(rs.getInt("venitTotalUltimaLuna")) ;
						xvenitTotalUltimaLuna = altaPersMajora.getVenitTotalUltimaLuna();
						altaPersMajora.setCuDizabilitati(rs.getString("cuDizabilitati")) ;
						xcuDizabilitati = altaPersMajora.getCuDizabilitati();
						altaPersMajora.setBeneficiatAlteDreptSociale(rs.getString("beneficiatAlteDreptSociale")) ;
						xbeneficiatAlteDreptSociale = altaPersMajora.getBeneficiatAlteDreptSociale() ;
						altaPersMajora.setCategDreptSociale(rs.getString("categDreptSociale")) ;
						xcategDreptSociale = altaPersMajora.getCategDreptSociale();
						altaPersMajora.setSex(rs.getString("sex")) ;
						xsex = altaPersMajora.getSex();
					}
				} else {
//			 nu exista cnpRL - se va afisa comanda tip buton-submit ADAUGARE
					xcmdExt = false ;
				}
			} catch(SQLException se) { 
//	Handle errors for JDBC 
				se.printStackTrace(); 
			}
		}
		return "redirect:mf" ;
	}

	@PostMapping("/addAltaPersMajora")
	public String addAltaPersMajora(@ModelAttribute AltaPersMajora altaPersMajora, Model model) {
		zmodul = "mf" ;
		erori = false ;
		nrView = true ;
		ycnp = "" ;
		if (! MembriFamilie.verifCNP(altaPersMajora.getCnp())) {
			xmodul = "mf" ;
			erori = true ;
			return "redirect:mf" ;
		}
		HashMap<String, String> aiList = MembriFamilie.listAI();
		model.addAttribute("aiList", aiList) ;
		ArrayList<String> scList = MembriFamilie.listSC();
		model.addAttribute("scList", scList) ;
		ArrayList<String> ssList = MembriFamilie.listSS() ;
		model.addAttribute("ssList", ssList) ;
		ArrayList<String> spList = MembriFamilie.listSP() ;
		model.addAttribute("spList", spList) ;
		ArrayList<String> cdsList = MembriFamilie.listCDS() ;
		model.addAttribute("cdsList", cdsList) ;
		HashMap<String, String> sxList = MembriFamilie.listSX();
		model.addAttribute("sxList", sxList) ;
		ArrayList<String> dnList = MembriFamilie.listDN() ;
		model.addAttribute("dnList", dnList) ;
		altaPersMajoraRepository.save(altaPersMajora) ;

		xconn = NovacVasileApplication.conn ;
		try {
			if (xcmdExt) {
				insUpd = "UPDATE altaPersMajora SET nume=?, prenume=?, actId=?, serieActId=?, nrActId=?, dataExpActId=?, stareCivila=?, dataNastere=?, cetatenie=?, situatieScolara=?, situatieProfesionala=?, venitTotalUltimaLuna=?, cuDizabilitati=?, beneficiatAlteDreptSociale=?, categDreptSociale=?, sex=?, cnprl=? WHERE cnp=? " ;
			} else {
				insUpd = "INSERT INTO altaPersMajora (nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex, cnprl, cnp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			}
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, altaPersMajora.getNume());
			iS.setString(2, altaPersMajora.getPrenume()) ;
			iS.setString(3, altaPersMajora.getActId()) ;
			iS.setString(4, altaPersMajora.getSerieActId()) ;
			iS.setString(5, altaPersMajora.getNrActId()) ;
			iS.setString(6, altaPersMajora.getDataExpActId()) ;
			iS.setString(7, altaPersMajora.getStareCivila()) ;
			iS.setString(8, altaPersMajora.getDataNastere()) ;
			iS.setString(9, altaPersMajora.getCetatenie()) ;
			iS.setString(10, altaPersMajora.getSituatieScolara()) ;
			iS.setString(11, altaPersMajora.getSituatieProfesionala()) ;
			iS.setInt(12, altaPersMajora.getVenitTotalUltimaLuna()) ;
			iS.setString(13, altaPersMajora.getCuDizabilitati()) ;
			iS.setString(14, altaPersMajora.getBeneficiatAlteDreptSociale()) ;
			iS.setString(15, altaPersMajora.getCategDreptSociale()) ;
			iS.setString(16, altaPersMajora.getSex()) ;
			iS.setString(17, tcnp) ;
			iS.setString(18, altaPersMajora.getCnp());
			iS.executeUpdate();
			iS.close();
			xcmdExt = false ;
//	Execute a query 
			stmt = xconn.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM altaPersMajora"); 
//	Extract data from result set 
			while(rs.next()) { 
//	Retrieve by column name  
				String cnp = rs.getString("cnp"); 
				String nume = rs.getString("nume"); 
				String prenume = rs.getString("prenume");
				String actId = rs.getString("actId") ;
				String serieActId = rs.getString("serieActId") ;
				String nrActId = rs.getString("nrActId");
				String dataExpActId = rs.getString("dataExpActId") ;
				String stareCivila = rs.getString("stareCivila") ;
				String dataNastere = rs.getString("dataNastere") ;
				String cetatenie = rs.getString("cetatenie") ;
				String situatieScolara = rs.getString("situatieScolara") ;
				String situatieProfesionala = rs.getString("situatieProfesionala") ;
				String venitTotalUltimaLuna = rs.getString("venitTotalUltimaLuna") ;
				String cuDizabilitati = rs.getString("cuDizabilitati") ;
				String beneficiatAlteDreptSociale = rs.getString("beneficiatAlteDreptSociale") ;
				String categDreptSociale = rs.getString("categDreptSociale");
				String sex = rs.getString("sex") ;
				String cnprl = rs.getString("cnprl") ;
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+stareCivila+", "+dataNastere+", "+cetatenie+", "+situatieScolara+", "+situatieProfesionala+", "+venitTotalUltimaLuna+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+sex+", "+cnprl);
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}

		return "redirect:rap" ;
//		return "mf" ;
	}

	@Autowired
	private DispozitiePrimarRepository dispozitiePrimarRepository ;

	@GetMapping("/dp")
	public void dp(Model model) {
		zmodul = "dp" ;
		model.addAttribute("ymodul", "dp") ;
		model.addAttribute("nrView", nrView) ;
		model.addAttribute("xcmdExt", xcmdExt) ;
		model.addAttribute("tcnp", tcnp) ;
		model.addAttribute("tnume", tnume) ;
		model.addAttribute("tprenume", tprenume) ;
		DispozitiePrimar dispozitiePrimar = new DispozitiePrimar() ;
		dispozitiePrimar.setNrDispozitie(ynrd) ;
		HashMap<String, String> cdList = MembriFamilie.listCD();
		model.addAttribute("cdList", cdList) ;
		if (xcmdExt) {
			dispozitiePrimar.setDataDispozitie(xdataDispozitie) ;
			dispozitiePrimar.setCategDispozitie(xcategDispozitie) ;
			dispozitiePrimar.setDataIntrareVigoare(xdataIntrareVigoare) ;
		}
		model.addAttribute("dispozitiePrimar", dispozitiePrimar) ;
	}

	@PostMapping("/errDispozitiePrimar")
	public String errFamilie(@ModelAttribute DispozitiePrimar dispozitiePrimar, Model model) {
		zmodul = "dp" ;
		model.addAttribute("ymodul", "dp") ;
		nrView = false ;
		ynrd = dispozitiePrimar.getNrDispozitie();
		xconn = NovacVasileApplication.conn ;
		try {
			stmt = xconn.createStatement();
			ResultSet rs1 = stmt.executeQuery("SELECT count(*) FROM dispozitieprimar") ;
			if ( rs1.next() ) {
				xx = rs1.getInt(1) ;
			}
			if ( xx != 0) {
				stmt = xconn.createStatement();
				sql = "SELECT * FROM dispozitieprimar WHERE nrDispozitie = ?" ;
				PreparedStatement sqlStatement = xconn.prepareStatement(sql);
				sqlStatement.setString(1, ynrd);
				ResultSet rs = sqlStatement.executeQuery();
				if ( rs.next()) {
					znrd = rs.getString("nrDispozitie");
				}
				if ( ynrd.equals(znrd) ) {	
//			exista nr dispozitie - se va afisa comanda tip buton-submit MODIFICARE
					xcmdExt = true ;
					dispozitiePrimar.setNrDispozitie(rs.getString("nrDispozitie")) ;
//					xnrDispozitie = dispozitiePrimar.getNrDispozitie();
					dispozitiePrimar.setDataDispozitie(rs.getString("dataDispozitie")) ;
					xdataDispozitie = dispozitiePrimar.getDataDispozitie();
					dispozitiePrimar.setCategDispozitie(rs.getString("categDispozitie")) ;
					xcategDispozitie = dispozitiePrimar.getCategDispozitie();
					dispozitiePrimar.setDataIntrareVigoare(rs.getString("dataIntrareVigoare")) ;
					xdataIntrareVigoare = dispozitiePrimar.getDataIntrareVigoare();
				}
			} else {
//		nu exista nr dispozitie - se va afisa comanda tip buton-submit ADAUGARE
				xcmdExt = false ;
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:dp" ;
	}

	@PostMapping("/addDispozitiePrimar")
	public String addDispozitiePrimar(@ModelAttribute DispozitiePrimar dispozitiePrimar, Model model) {
		zmodul = "dp" ;
//		erori = false ;
		nrView = true ;
		ynrd = "" ;
		HashMap<String, String> cdList = MembriFamilie.listCD();
		model.addAttribute("cdList", cdList) ;
		dispozitiePrimarRepository.save(dispozitiePrimar) ;

		xconn = NovacVasileApplication.conn ;
		try {
			if (xcmdExt) {
				insUpd = "UPDATE dispozitieprimar SET dataDispozitie=?, categDispozitie=?, dataIntrareVigoare=?, cnprl=? WHERE nrDispozitie=? " ;
			} else {
				insUpd = "INSERT INTO dispozitieprimar (dataDispozitie, categDispozitie, dataIntrareVigoare, cnprl, nrDispozitie) values (?, ?, ?, ?)" ;
			}
			PreparedStatement iS = xconn.prepareStatement(insUpd);
			iS.setString(1, dispozitiePrimar.getDataDispozitie());
			iS.setString(2, dispozitiePrimar.getCategDispozitie()) ;
			iS.setString(3, dispozitiePrimar.getDataIntrareVigoare()) ;
			iS.setString(4, tcnp) ;
			iS.setString(5, dispozitiePrimar.getNrDispozitie());
			iS.executeUpdate();
			iS.close();
//	Execute a query 
			stmt = xconn.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT * FROM dispozitieprimar"); 
//	Extract data from result set 
			while(rs.next()) { 
//	Retrieve by column name 
				String nrDispozitie = rs.getString("nrDispozitie"); 
				String dataDispozitie = rs.getString("dataDispozitie"); 
				String categDispozitie = rs.getString("categDispozitie");
				String dataIntrareVigoare = rs.getString("dataIntrareVigoare") ;
				String cnprl = rs.getString("cnprl") ;
//	Display values 
				System.out.println(nrDispozitie+", "+dataDispozitie+", "+categDispozitie+ ", "+dataIntrareVigoare+", "+cnprl);
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:rap" ;
//		return "dp" ;
	}
}
