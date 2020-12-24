package by.a1.andrikevich.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.a1.andrikevich.entity.SimCard;
import by.a1.andrikevich.service.SimCardService;
import by.a1.andrikevich.util.WebotaChecker;
import by.a1.andrikevich.util.WixChecker;

@Controller
@RequestMapping("/dtgroup")
@PropertySource("classpath:config.properties")
public class SimCardController {
	
	
	private SimCardService simCardService;
	
	@Value("${webota.url}")
	String webotaUrl;
	
	@Autowired
	public SimCardController(SimCardService simCardService) {
		this.simCardService = simCardService;
	}
	@GetMapping ("/selectedSim")
	public String retrieveSimCardInfo(@RequestParam("msisdn") String msisdn,@RequestParam("iccid") String iccid, Model model) {
		SimCard theSimCard = null;
		
		if ((msisdn != "" && iccid =="") || (msisdn != "" && iccid != "")) {
			 theSimCard = simCardService.findByMsIsdn(msisdn);
			 
			 WixChecker wixChecker = new WixChecker(msisdn);
			 String infoFromWix = "";
			 
			 WebotaChecker webotaChecker = new WebotaChecker();
			 String infoFromWebota = "";
			try {
				infoFromWix = new String (wixChecker.infoFromWix().getBytes(),"UTF-8");
				infoFromWebota = new String (webotaChecker.retriveDataFromWebota(webotaUrl, msisdn).toString().getBytes(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			 model.addAttribute("info",infoFromWix);
			 model.addAttribute("weBotaInfo",infoFromWebota);
			if (theSimCard == null) {
				throw new RuntimeException("SimcARD id not found - by SimCardId " + msisdn);
			}
		} else if (msisdn == "" && iccid !="") {
			theSimCard = simCardService.findByIccid(iccid);
		}
		
		model.addAttribute("simCard", theSimCard);
		
		return "simcard-info";
	}
	@GetMapping ("/simCardSearcher")
	public String simCardSearcher() {
		return "sim-chooser";
	}
	
	@GetMapping("/showFormForUpdateSimCard")
	public String updateSimCardInfo(@RequestParam("msisdn") String theMsisdn,	Model theModel) {
		SimCard simCard = simCardService.findByMsIsdn(theMsisdn);
		theModel.addAttribute("simCard",simCard);
		return "simcard-update-form";
		
	}
	
	
	@PostMapping("/updatedSimCard")
	public String showUpdatedStu(@ModelAttribute ("simCard") SimCard theSimCard, Model theModel) {
		simCardService.save(theSimCard);
		theModel.addAttribute("simCard", theSimCard);
		return "simcard-update-form";
	}
}
