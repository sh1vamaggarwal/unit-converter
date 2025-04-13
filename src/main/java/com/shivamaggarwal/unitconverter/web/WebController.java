package com.shivamaggarwal.unitconverter.web;

import com.shivamaggarwal.unitconverter.service.UnitConverterService;
import com.shivamaggarwal.unitconverter.web.forms.UnitConverterForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class WebController {

    UnitConverterService unitConverterService;

    public WebController(UnitConverterService unitConverterService) {
        this.unitConverterService = unitConverterService;
    }

    @GetMapping("/")
    public String lengthForm(Model model) {
        model.addAttribute("unitConverterForm", new UnitConverterForm());
        return "length";
    }

    @PostMapping("/")
    public String postLength(@ModelAttribute @Valid UnitConverterForm unitConverterForm, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "length";
        }
        double result = unitConverterService.convertLength(unitConverterForm.getValue(),
                unitConverterForm.getFromUnit(), unitConverterForm.getToUnit());
        model.addAttribute("redirectUrl", "/");
        model.addAttribute("result", result);
        model.addAttribute("unitConverterForm", unitConverterForm);
        model.addAttribute("isRedirectedFromLength", true);
        return "result";
    }

    @GetMapping("/weight")
    public String weightForm(Model model) {
        model.addAttribute("unitConverterForm", new UnitConverterForm());
        return "weight";
    }

    @PostMapping("/weight")
    public String postWeight(@ModelAttribute @Valid UnitConverterForm unitConverterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "weight";
        }
        double result = unitConverterService.convertWeight(unitConverterForm.getValue(),
                unitConverterForm.getFromUnit(), unitConverterForm.getToUnit());
        model.addAttribute("redirectUrl", "/weight");
        model.addAttribute("result", String.format("%.2f",result));
        model.addAttribute("unitConverterForm", unitConverterForm);
        model.addAttribute("isRedirectedFromWeight", true);
        return "result";
    }

    @GetMapping("/temp")
    public String tempForm(Model model) {
        model.addAttribute("unitConverterForm", new UnitConverterForm());
        return "temp";
    }

    @PostMapping("/temp")
    public String postTemp(@ModelAttribute @Valid UnitConverterForm unitConverterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "temp";
        }
        double result = unitConverterService.convertTemperature(unitConverterForm.getValue(),
                unitConverterForm.getFromUnit(), unitConverterForm.getToUnit());
        model.addAttribute("redirectUrl", "/temp");
        model.addAttribute("result", String.format("%.2f",result));
        model.addAttribute("unitConverterForm", unitConverterForm);
        model.addAttribute("isRedirectedFromTemp", true);
        return "result";
    }
}
