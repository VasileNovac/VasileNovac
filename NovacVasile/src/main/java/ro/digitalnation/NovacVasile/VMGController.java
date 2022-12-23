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
	public String zmodul ;
	public String ycnp, zcnp ;
	public String sql ;
	public boolean nrView = true ;
	public static Connection xconn = null ;
    public static Connection conn = null; 
    public static Statement stmt = null; 

		
	@Autowired
	private MasculinParinteRepository masculinParinteRepository ;
	
	@GetMapping("/rap")
	public String rap(Model model) {

		switch (zmodul) {
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
			case "rl" :
				model.addAttribute("familie", familieRepository.findAll()) ;
				model.addAttribute("ymodul", "rap") ;
				model.addAttribute("zmodul", "rl") ;
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

	@GetMapping("/mp")
	public void mp(Model model) {
		zmodul = "mp" ;
		model.addAttribute("ymodul", "mp") ;
		if (xmodul == "mp") {
			model.addAttribute("erori", erori) ;
		}
		model.addAttribute("masculinParinte", new MasculinParinte()) ;
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
	}

	@PostMapping("/addMasculinParinte")
	public String addMasculinParinte(@ModelAttribute MasculinParinte masculinParinte, Model model) {
		zmodul = "mp" ;
		model.addAttribute("ymodul", "mp") ;
		erori = false ;
		if(! MembriFamilie.verifCNP(masculinParinte.getCnp())) {
			xmodul = "mp" ;
			erori = true ;
			return "redirect:mp";
		}
		masculinParinte.setSex("M") ;
		model.addAttribute("masculinParinte", masculinParinte) ;
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
			String insert = "INSERT into masculinParinte (cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			PreparedStatement insertStatement = xconn.prepareStatement(insert);
			insertStatement.setString(1, masculinParinte.getCnp());
			insertStatement.setString(2, masculinParinte.getNume());
			insertStatement.setString(3, masculinParinte.getPrenume()) ;
			insertStatement.setString(4, masculinParinte.getActId()) ;
			insertStatement.setString(5, masculinParinte.getSerieActId()) ;
			insertStatement.setString(6, masculinParinte.getNrActId()) ;
			insertStatement.setString(7, masculinParinte.getDataExpActId()) ;
			insertStatement.setString(8, masculinParinte.getStareCivila()) ;
			insertStatement.setString(9, masculinParinte.getDataNastere()) ;
			insertStatement.setString(10,masculinParinte.getCetatenie()) ;
			insertStatement.setString(11, masculinParinte.getSituatieScolara()) ;
			insertStatement.setString(12, masculinParinte.getSituatieProfesionala()) ;
			insertStatement.setInt(13, masculinParinte.getVenitTotalUltimaLuna()) ;
			insertStatement.setString(14, masculinParinte.getCuDizabilitati()) ;
			insertStatement.setString(15, masculinParinte.getBeneficiatAlteDreptSociale()) ;
			insertStatement.setString(16, masculinParinte.getCategDreptSociale()) ;
			insertStatement.setString(17, masculinParinte.getSex()) ;
			insertStatement.executeUpdate();
			insertStatement.close();
//	Execute a query 
			stmt = xconn.createStatement(); 
			sql = "SELECT cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex FROM masculinParinte"; 
			ResultSet rs = stmt.executeQuery(sql); 
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
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+stareCivila+", "+dataNastere+", "+cetatenie+", "+situatieScolara+", "+situatieProfesionala+", "+venitTotalUltimaLuna+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+sex);
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
		if (xmodul == "fp") {
			model.addAttribute("erori", erori) ;
 		}
		model.addAttribute("femininParinte", new FemininParinte()) ;
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
	}
	@PostMapping("/addFemininParinte")
	public String addFemininParinte(@ModelAttribute FemininParinte femininParinte, Model model) {
		zmodul = "fp" ;
		model.addAttribute("ymodul", "fp") ;
		erori = false ;
		if(! MembriFamilie.verifCNP(femininParinte.getCnp())) {
			xmodul = "fp" ;
			erori = true ;
			return "redirect:fp";
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
			String insert = "INSERT into femininParinte (cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			PreparedStatement insertStatement = xconn.prepareStatement(insert);
			insertStatement.setString(1, femininParinte.getCnp());
			insertStatement.setString(2, femininParinte.getNume());
			insertStatement.setString(3, femininParinte.getPrenume()) ;
			insertStatement.setString(4, femininParinte.getActId()) ;
			insertStatement.setString(5, femininParinte.getSerieActId()) ;
			insertStatement.setString(6, femininParinte.getNrActId()) ;
			insertStatement.setString(7, femininParinte.getDataExpActId()) ;
			insertStatement.setString(8, femininParinte.getStareCivila()) ;
			insertStatement.setString(9, femininParinte.getDataNastere()) ;
			insertStatement.setString(10, femininParinte.getCetatenie()) ;
			insertStatement.setString(11, femininParinte.getSituatieScolara()) ;
			insertStatement.setString(12, femininParinte.getSituatieProfesionala()) ;
			insertStatement.setInt(13, femininParinte.getVenitTotalUltimaLuna()) ;
			insertStatement.setString(14, femininParinte.getCuDizabilitati()) ;
			insertStatement.setString(15, femininParinte.getBeneficiatAlteDreptSociale()) ;
			insertStatement.setString(16, femininParinte.getCategDreptSociale()) ;
			insertStatement.setString(17, femininParinte.getSex()) ;
			insertStatement.executeUpdate();
			insertStatement.close();
//	Execute a query 
			stmt = xconn.createStatement(); 
			sql = "SELECT cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex FROM femininParinte"; 
			ResultSet rs = stmt.executeQuery(sql); 
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
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+stareCivila+", "+dataNastere+", "+cetatenie+", "+situatieScolara+", "+situatieProfesionala+", "+venitTotalUltimaLuna+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+sex);
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
		if (xmodul == "copil") {
			model.addAttribute("erori", erori) ;
		}
		model.addAttribute("copil", new Copil()) ;
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
	}
	@PostMapping("/addCopil")
	public String addCopil(@ModelAttribute Copil copil, Model model) {
		zmodul = "copil" ;
		model.addAttribute("ymodul", "copil") ;
		erori = false ;
		if(! MembriFamilie.verifCNP(copil.getCnp())) {
			xmodul = "copil" ;
			erori = true ;
			return "redirect:copil";
		}
		model.addAttribute("copil", copil) ;
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
			String insert = "INSERT into copil (cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, dataNastere, situatieScolara, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, gradRudaRL, sex) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			PreparedStatement insertStatement = xconn.prepareStatement(insert);
			insertStatement.setString(1, copil.getCnp());
			insertStatement.setString(2, copil.getNume());
			insertStatement.setString(3, copil.getPrenume()) ;
			insertStatement.setString(4, copil.getActId()) ;
			insertStatement.setString(5, copil.getSerieActId()) ;
			insertStatement.setString(6, copil.getNrActId()) ;
			insertStatement.setString(7, copil.getDataExpActId()) ;
			insertStatement.setString(8, copil.getDataNastere()) ;
			insertStatement.setString(9, copil.getSituatieScolara()) ;
			insertStatement.setString(10, copil.getCuDizabilitati()) ;
			insertStatement.setString(11, copil.getBeneficiatAlteDreptSociale()) ;
			insertStatement.setString(12, copil.getCategDreptSociale()) ;
			insertStatement.setString(13, copil.getGradRudaRL()) ;
			insertStatement.setString(14, copil.getSex()) ;
			insertStatement.executeUpdate();
			insertStatement.close();
//	Execute a query 
			stmt = xconn.createStatement(); 
			sql = "SELECT cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, dataNastere, situatieScolara, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, gradRudaRL, sex FROM copil"; 
			ResultSet rs = stmt.executeQuery(sql); 
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
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+dataNastere+", "+situatieScolara+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+gradRudaRL+", "+sex);
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:rap" ;
//		return "copil" ;
	}
	
	@Autowired
	private FamilieRepository familieRepository ;
			
	@GetMapping("/rl")
	public void rl(Model model) {
		zmodul = "rl" ;
		model.addAttribute("ymodul", "rl") ;
		model.addAttribute("nrView", nrView) ;
		if (xmodul == "rl") {
			model.addAttribute("erori", erori) ;
		}
		Familie familie = new Familie() ;
		familie.setCnpRL(ycnp) ;
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
			ycnp = " " ;
			erori = true ;
		}
// in curs de implementare
//		if(!erori) {
//			xconn = NovacVasileApplication.conn ;
//			try {
//				sql = "select cnpRL from familie" ;
//				ResultSet rs = stmt.executeQuery(sql); 
//				String zcnp = rs.getString("cnpRL");
//				if ( ycnp == zcnp ) {
					// exista cnpRL - se vor afisa comenzile MODIFICARE si STERGERE
					// nu exista cnpRL - se va afisa comanda ADAUGARE
//				}
//			} catch(SQLException se) { 
//	Handle errors for JDBC 
//				se.printStackTrace(); 
//			}
//		}

		return "redirect:rl" ;
	}
	
	@PostMapping("/addFamilie")
	public String addFamilie(@ModelAttribute Familie familie, Model model) {
		zmodul = "rl" ;
		erori = false ;
		nrView = true ;
		ycnp = " ";
		familieRepository.save(familie) ;
		
		xconn = NovacVasileApplication.conn ;
		try {
			String insert = "INSERT into familie (cnpRL, numeRL, prenumeRL, adresaLoc, adresaStr, adresaNr, adresaBl, adresaSc, adresaEt, adresaAp, adresaSector, adresaJud, adresaCodP, primarie, nrPersoaneMajore, nrCopii) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
			PreparedStatement insertStatement = xconn.prepareStatement(insert);
			insertStatement.setString(1, familie.getCnpRL());
			insertStatement.setString(2, familie.getNumeRL());
			insertStatement.setString(3, familie.getPrenumeRL()) ;
			insertStatement.setString(4, familie.getAdresaLoc()) ;
			insertStatement.setString(5, familie.getAdresaStr()) ;
			insertStatement.setString(6, familie.getAdresaNr()) ;
			insertStatement.setString(7, familie.getAdresaBl()) ;
			insertStatement.setString(8, familie.getAdresaSc()) ;
			insertStatement.setString(9, familie.getAdresaEt()) ;
			insertStatement.setString(10, familie.getAdresaAp()) ;
			insertStatement.setString(11, familie.getAdresaSector()) ;
			insertStatement.setString(12, familie.getAdresaJud()) ;
			insertStatement.setString(13, familie.getAdresaCodP()) ;
			insertStatement.setString(14, familie.getPrimarie()) ;
			insertStatement.setInt(15, familie.getNrPersoaneMajore()) ;
			insertStatement.setInt(16, familie.getNrCopii()) ;
			insertStatement.executeUpdate();
			insertStatement.close();
	//	Execute a query 
			stmt = xconn.createStatement(); 
			sql = "SELECT cnpRL, numeRL, prenumeRL, adresaLoc, adresaStr, adresaNr, adresaBl, adresaSc, adresaEt, adresaAp, adresaSector, adresaJud, adresaCodP, primarie, nrPersoaneMajore, nrCopii FROM familie"; 
			ResultSet rs = stmt.executeQuery(sql); 
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
	private AltaPersMajoraRepository altaPersMajoraRepository ;

	@GetMapping("/mf")
	public void mf(Model model) {
		zmodul = "mf" ;
		model.addAttribute("ymodul", "mf") ;
		if (xmodul == "mf") {
			model.addAttribute("erori", erori) ;
		}
		model.addAttribute("altaPersMajora", new AltaPersMajora()) ;
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
	}
	@PostMapping("/addAltaPersMajora")
	public String addAltaPersMajora(@ModelAttribute AltaPersMajora altaPersMajora, Model model) {
		zmodul = "mf" ;
		model.addAttribute("ymodul", "mf") ;
		erori = false ;
		if(! MembriFamilie.verifCNP(altaPersMajora.getCnp())) {
			xmodul = "mf" ;
			erori = true ;
			return "redirect:mf";
		}
		model.addAttribute("altaPersMajora", altaPersMajora) ;
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
			String insert = "INSERT into altaPersMajora (cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ;
			PreparedStatement insertStatement = xconn.prepareStatement(insert);
			insertStatement.setString(1, altaPersMajora.getCnp());
			insertStatement.setString(2, altaPersMajora.getNume());
			insertStatement.setString(3, altaPersMajora.getPrenume()) ;
			insertStatement.setString(4, altaPersMajora.getActId()) ;
			insertStatement.setString(5, altaPersMajora.getSerieActId()) ;
			insertStatement.setString(6, altaPersMajora.getNrActId()) ;
			insertStatement.setString(7, altaPersMajora.getDataExpActId()) ;
			insertStatement.setString(8, altaPersMajora.getStareCivila()) ;
			insertStatement.setString(9, altaPersMajora.getDataNastere()) ;
			insertStatement.setString(10, altaPersMajora.getCetatenie()) ;
			insertStatement.setString(11, altaPersMajora.getSituatieScolara()) ;
			insertStatement.setString(12, altaPersMajora.getSituatieProfesionala()) ;
			insertStatement.setInt(13, altaPersMajora.getVenitTotalUltimaLuna()) ;
			insertStatement.setString(14, altaPersMajora.getCuDizabilitati()) ;
			insertStatement.setString(15, altaPersMajora.getBeneficiatAlteDreptSociale()) ;
			insertStatement.setString(16, altaPersMajora.getCategDreptSociale()) ;
			insertStatement.setString(17, altaPersMajora.getSex()) ;
			insertStatement.executeUpdate();
			insertStatement.close();
//	Execute a query 
			stmt = xconn.createStatement(); 
			sql = "SELECT cnp, nume, prenume, actId, serieActId, nrActId, dataExpActId, stareCivila, dataNastere, cetatenie, situatieScolara, situatieProfesionala, venitTotalUltimaLuna, cuDizabilitati, beneficiatAlteDreptSociale, categDreptSociale, sex FROM altaPersMajora"; 
			ResultSet rs = stmt.executeQuery(sql); 
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
//	Display values 
				System.out.println(cnp+", "+nume+", "+prenume+ ", "+actId+", "+serieActId+", "+nrActId+", "+dataExpActId+", "+stareCivila+", "+dataNastere+", "+cetatenie+", "+situatieScolara+", "+situatieProfesionala+", "+venitTotalUltimaLuna+", "+cuDizabilitati+", "+beneficiatAlteDreptSociale+", "+categDreptSociale+", "+sex);
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
		model.addAttribute("dispozitiePrimar", new DispozitiePrimar()) ;
		HashMap<String, String> cdList = MembriFamilie.listCD();
		model.addAttribute("cdList", cdList) ;
	}
	@PostMapping("/addDispozitiePrimar")
	public String addDispozitiePrimar(@ModelAttribute DispozitiePrimar dispozitiePrimar, Model model) {
		zmodul = "dp" ;
		model.addAttribute("ymodul", "dp") ;
		model.addAttribute("dispozitiePrimar", dispozitiePrimar) ;
		HashMap<String, String> cdList = MembriFamilie.listCD();
		model.addAttribute("cdList", cdList) ;
		dispozitiePrimarRepository.save(dispozitiePrimar) ;
        xconn = NovacVasileApplication.conn ;
		try {
			String insert = "INSERT into dispozitieprimar (id, nrDispozitie, dataDispozitie, categDispozitie, dataIntrareVigoare) values (?, ?, ?, ?, ?)" ;
			PreparedStatement insertStatement = xconn.prepareStatement(insert);
			insertStatement.setLong(1, dispozitiePrimar.getId());
			insertStatement.setString(2, dispozitiePrimar.getNrDispozitie());
			insertStatement.setString(3, dispozitiePrimar.getDataDispozitie());
			insertStatement.setString(4, dispozitiePrimar.getCategDispozitie()) ;
			insertStatement.setString(5, dispozitiePrimar.getDataIntrareVigoare()) ;
			insertStatement.executeUpdate();
			insertStatement.close();
//	Execute a query 
			stmt = xconn.createStatement(); 
			sql = "SELECT id, nrDispozitie, dataDispozitie, categDispozitie, dataIntrareVigoare FROM dispozitieprimar"; 
			ResultSet rs = stmt.executeQuery(sql); 
//	Extract data from result set 
			while(rs.next()) { 
//	Retrieve by column name 
				Long id  = rs.getLong("id"); 
				String nrDispozitie = rs.getString("nrDispozitie"); 
				String dataDispozitie = rs.getString("dataDispozitie"); 
				String categDispozitie = rs.getString("categDispozitie");
				String dataIntrareVigoare = rs.getString("dataIntrareVigoare") ;
//	Display values 
				System.out.println(id+", "+nrDispozitie+", "+dataDispozitie+", "+categDispozitie+ ", "+dataIntrareVigoare);
			}
		} catch(SQLException se) { 
//	Handle errors for JDBC 
			se.printStackTrace(); 
		}
		return "redirect:rap" ;
//		return "dp" ;
	}
}
