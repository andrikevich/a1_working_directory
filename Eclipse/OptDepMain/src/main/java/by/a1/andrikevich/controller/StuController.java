package by.a1.andrikevich.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.a1.andrikevich.entity.Stu;
import by.a1.andrikevich.service.StuService;

@Controller
@RequestMapping("/dtgroup")
public class StuController {
	
	private StuService stuService;
	
	@Autowired
	public StuController(StuService stuService) {
		this.stuService = stuService;
	}

	@GetMapping("/stuChooser")
	public String mainStuPage (){
		return "stu-chooser";
	}
	
	@GetMapping("/selectedStu")
	public String selectedStu (@RequestParam("id") String stuId,@RequestParam("city") String city, Model model) {
		List <Stu> stuList = new ArrayList<Stu>();
		if ((stuId != "" && city =="") || (stuId != "" && city != "")) {
			int tmpId = Integer.parseInt(stuId);
			Stu theStu = stuService.findById(tmpId);
			stuList.add(theStu);
			if (theStu == null) {
				throw new RuntimeException("Stu id not found - by stuId " + stuId);
			}
		} else if (stuId == "" && city !="") {
			stuList = stuService.findByCity(city);
		}
		model.addAttribute("stuList", stuList);
		return "selected-stu";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId,
									Model theModel) {
		
		Stu theStu = stuService.findById(theId);	
		theModel.addAttribute("stu", theStu);
		return "stuForUpdate";
	}
	
	// TODO: Change to @PostMapping and change in form of page "stuForUpdate" (incorrect reflection of Russian symbols, something with encoding MethodPost)
	@GetMapping(path ="/updatedStu")
	public String showUpdatedStu(@ModelAttribute ("stu") Stu theStu, Model theModel) {
		stuService.save(theStu);
		theModel.addAttribute("stu", theStu);
		return "stuForUpdate";
	}
}
