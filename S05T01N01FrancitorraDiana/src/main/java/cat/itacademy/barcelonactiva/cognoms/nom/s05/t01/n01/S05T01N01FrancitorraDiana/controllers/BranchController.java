package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services.BranchService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping("/getAll")
    public String getAllBranches(Model model){
        model.addAttribute("BranchList", branchService.getAll());
        return "index";
    }

}
