package com.example.bitlab.sprintTaskTwo.controller;


import com.example.bitlab.sprintTaskTwo.model.ApplicationRequest;
import com.example.bitlab.sprintTaskTwo.repository.ApplicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicationRequestController {
    private final ApplicationRequestRepository applicationRequestRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findAll();
        model.addAttribute("list",applicationRequestList);
        return "index";
    }

    @GetMapping(value = "add-request")
    public String addRequestPage(){
        return "add-request";
    }

    @PostMapping(value = "/add-request")
    public String addRequest(ApplicationRequest applicationRequest){
        applicationRequest.setHandled(false);
        applicationRequestRepository.save(applicationRequest);
        return "redirect:/";
    }

    @GetMapping(value = "/new-applications")
    public String newRequests(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findApplicationRequestByHandledIsFalse();
        model.addAttribute("newRequests", applicationRequestList);
        return "new-requests";
    }

    @GetMapping(value = "/processed-applications")
    public String processedApplications(Model model){
        List<ApplicationRequest> applicationRequestList = applicationRequestRepository.findApplicationRequestByHandledIsTrue();
        model.addAttribute("processedApplications", applicationRequestList);
        return "processed-applications";
    }

    @GetMapping(value = "/details/{appId}")
    public String applicationDetails(@PathVariable(name = "appId") Long id, Model model){
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);
        model.addAttribute("app",applicationRequest);
        return "details";
    }

    @PostMapping(value = "/save-app")
    public String saveApp(ApplicationRequest applicationRequest){
        applicationRequest.setHandled(true);
        applicationRequestRepository.save(applicationRequest);
        return "redirect:/details/" + applicationRequest.getId();
    }

    @PostMapping(value = "/delete-app")
    public String deleteApp(@RequestParam(name = "id") Long id){
        applicationRequestRepository.deleteById(id);
        return "redirect:/";
    }
}
