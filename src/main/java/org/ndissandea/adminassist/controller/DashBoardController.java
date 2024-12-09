package org.ndissandea.adminassist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashBoardController {

        @GetMapping("/dashboard")
        public String showDashboard() {
            return "dashboard";
        }

    @GetMapping("/dashboard_assistant")
    public String showAssistantDashboard() {
        return "dashboard_assistant";
    }
    }

