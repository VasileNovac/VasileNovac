package ro.digitalnation.NovacVasile;

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
	public boolean erori=false ;
	public String xmodul = " " ;
	public String zmodul ;
		
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
		return "redirect:rap" ;
//		return "copil" ;
	}

	@Autowired
	private FamilieRepository familieRepository ;
			
	@GetMapping("/rl")
	public void rl(Model model) {
		zmodul = "rl" ;
		model.addAttribute("ymodul", "rl") ;
		if (xmodul == "rl") {
			model.addAttribute("erori", erori) ;
		}
		model.addAttribute("familie", new Familie()) ;
	}

	@PostMapping("/addFamilie")
	public String addFamilie(@ModelAttribute Familie familie, Model model) {
		zmodul = "rl" ;
		model.addAttribute("ymodul", "rl") ;
		erori = false ;
		if(! MembriFamilie.verifCNP(familie.getCnpRL())) {
			xmodul = "rl" ;
			erori = true ;
			return "redirect:rl";
		}
		model.addAttribute("familie", familie) ;
		familieRepository.save(familie) ;
		return "redirect:rap";
//		return "rl" ;
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
		return "redirect:rap" ;
//		return "dp" ;
	}
}
