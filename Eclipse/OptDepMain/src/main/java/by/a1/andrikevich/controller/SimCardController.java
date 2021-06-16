package by.a1.andrikevich.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
import by.a1.andrikevich.util.JspPageUtilities;
import by.a1.andrikevich.util.WebotaChecker;
import by.a1.andrikevich.util.WixChecker;

@Controller
@RequestMapping("/dtgroup")
@PropertySource("classpath:config.properties")
public class SimCardController {


	private final SimCardService simCardService;

	@Value("${webota.url}")
	String webotaUrl;

	@Autowired
	public SimCardController(SimCardService simCardService) {
		this.simCardService = simCardService;
	}


	@GetMapping("/selectedSim")
	public String retrieveSimCardInfo(@RequestParam("msisdn") String msisdn, Model model) {
		SimCard theSimCard = null;

		if (JspPageUtilities.isMsisdnCorrect(msisdn)) {
			theSimCard = simCardService.findByMsIsdn(msisdn);
		} else if (!JspPageUtilities.isIccidCorrect(msisdn)) {
			model.addAttribute("error", "error");
			return "sim-chooser";
		}
		if (theSimCard == null) {
			throw new RuntimeException("SimcARD id not found - by SimCardId " + msisdn);
		}

		WixChecker wixChecker = new WixChecker(msisdn);
		String infoFromWix = "";

		WebotaChecker webotaChecker = new WebotaChecker();
		String infoFromWebota = "";
		infoFromWix = new String(wixChecker.infoFromWix().getBytes(), StandardCharsets.UTF_8);
		infoFromWebota = new String(webotaChecker.retriveDataFromWebota(webotaUrl, msisdn).toString().getBytes(),StandardCharsets.UTF_8);
		model.addAttribute("info", infoFromWix);
		model.addAttribute("weBotaInfo", infoFromWebota);

//		} else if (!JspPageUtilities.isMsisdnCorrect(msisdn) && iccid !="") {
//			theSimCard = simCardService.findByIccid(iccid);
//				}else {
//					model.addAttribute("error", "error");
//					return "sim-chooser";
//				}
//		if (theSimCard == null) {
//			throw new RuntimeException("SimcARD id not found - by SimCardId " + msisdn);
//		}

		model.addAttribute("simCard", theSimCard);

		return "simcard-info";
	}

	@GetMapping("/simCardSearcher")
	public String simCardSearcher() {
		return ("redirect:/dtgroup/selectedSims?searchParam");
	}

	@GetMapping("/showFormForUpdateSimCard")
	public String updateSimCardInfo(@RequestParam("msisdn") String theMsisdn, Model theModel) {
		SimCard simCard = simCardService.findByMsIsdn(theMsisdn);
		theModel.addAttribute("simCard", simCard);
		return "simcard-update-form";

	}


	@PostMapping("/updatedSimCard")
	public String showUpdatedStu(@ModelAttribute("simCard") SimCard theSimCard, Model theModel) {
		simCardService.save(theSimCard);
		theModel.addAttribute("simCard", theSimCard);
		return "simcard-update-form";

	}

	@GetMapping("/selectedSims")
	public String retrieveSimCards(@RequestParam("searchParam") String param, Model theModel) {
		List<SimCard> simCards = simCardService.findAllByParam(param);
		if(simCards == null){
			theModel.addAttribute("error" ,"error");
			return "sim-chooser";
		}
		theModel.addAttribute("simCards",simCards);
		return "finded-sim-cards";
	}

}