package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.services.impl.BranchServiceImpl;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n01.S05T01N01FrancitorraDiana.model.util.Countries;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("branch")
public class BranchController {
    @Autowired
    private BranchServiceImpl branchService;

    @GetMapping({"/getAll","/home",""})
    public String getAllBranches(Model model){
        model.addAttribute("BranchList", branchService.getAll());
        return "index";
    }

    @GetMapping("/form")
    public String showAddBranchForm(Model model){
        BranchDTO branchDTO = new BranchDTO();
        model.addAttribute("newBranch",branchDTO);

        List<String> countries = Countries.getAllCountries();
        model.addAttribute("countries", countries);

        return "form";
    }

    @PostMapping("/add")
    public String addBranch(@ModelAttribute("newBranch") BranchDTO branchDTO){
        branchService.create(branchDTO);
        return "redirect:/branch/getAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable(value="id") Integer id){
        branchService.delete(id);
        return "redirect:/branch/getAll";
    }

    @GetMapping("/update_form/{id}")
    public String showUpdateBranchForm(@PathVariable(value="id") Integer id, Model model){
        model.addAttribute("branchToUpdate", branchService.getOne(id));

        List<String> countries = Countries.getAllCountries();
        model.addAttribute("countries", countries);

        return "update_form";
    }

    @PostMapping("/update")
    public String updateBranch(@ModelAttribute("branchToUpdate") BranchDTO branchDTO){
        branchService.update(branchDTO);
        return "redirect:/branch/getAll";
    }


}
