package ro.digitalnation.NovacVasile;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableAutoConfiguration
public class VMGController {
	
	@Autowired
	private MasculinParinteRepository masculinParinteRepository ;
	
	@GetMapping("/mp")
	public void mp(Model model) {
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
	public String addMasculinParinte(Model model) {
		MasculinParinte masculinParinte = new MasculinParinte() ;
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
		return "mp" ;
	}
	
	@Autowired
	private FemininParinteRepository femininParinteRepository ;
	
	@GetMapping("/fp")
	public void fp(Model model) {
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
	public String addFemininParinte(Model model) {
		FemininParinte femininParinte = new FemininParinte() ;
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
		return "fp" ;
	}
	
	@Autowired
	private CopilRepository copilRepository ;
	
	@GetMapping("/copil")
	public void copil(Model model) {
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
	public String addCopil(Model model) {
		Copil copil = new Copil() ;
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
		return "copil" ;
	}

	@Autowired
	private FamilieRepository familieRepository ;
	
	@GetMapping("/rl")
	public void rl(Model model) {
		model.addAttribute("familie", new Familie()) ;
	}
	@PostMapping("/addFamilie")
	public String addFamilie(Model model) {
		Familie familie = new Familie() ;
		model.addAttribute("familie", familie) ;
		familieRepository.save(familie) ;
		return "rl" ;
	}

	@Autowired
	private AltaPersMajoraRepository altaPersMajoraRepository ;

	@GetMapping("/mf")
	public void mf(Model model) {
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
	public String addAltaPersMajora(Model model) {
		AltaPersMajora altaPersMajora = new AltaPersMajora() ;
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
		return "mf" ;
	}

	@Autowired
	private DispozitiePrimarRepository dispozitiePrimarRepository ;

	@GetMapping("/dp")
	public void dp(Model model) {
		model.addAttribute("dispozitiePrimar", new DispozitiePrimar()) ;
		HashMap<String, String> cdList = MembriFamilie.listCD();
		model.addAttribute("cdList", cdList) ;
	}
	@PostMapping("/addDispozitiePrimar")
	public String addDispozitiePrimar(Model model) {
		DispozitiePrimar dispozitiePrimar = new DispozitiePrimar() ;
		dispozitiePrimarRepository.save(dispozitiePrimar) ;
		model.addAttribute("dispozitiePrimar", dispozitiePrimar) ;
		HashMap<String, String> cdList = MembriFamilie.listCD();
		model.addAttribute("cdList", cdList) ;
		return "dp" ;
	}
}
