package com.roadassistant.backend.controller;

import com.roadassistant.backend.config.JwtUtil;
import com.roadassistant.backend.dto.AgentLoginRequest;
import com.roadassistant.backend.dto.AgentRegisterRequest;
import com.roadassistant.backend.model.Agent;
import com.roadassistant.backend.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin("*")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerAgent(@RequestBody AgentRegisterRequest request) {
        return agentService.registerAgent(request);
    }

    @PostMapping("/login")
    public Object loginAgent(@RequestBody AgentLoginRequest request) {
        Agent agent = agentService.loginAgent(request);

        if (agent == null) {
            return "Invalid agent email or password";
        }

        String token = jwtUtil.generateToken(agent.getEmail(), "AGENT", agent.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("id", agent.getId());
        response.put("name", agent.getName());
        response.put("email", agent.getEmail());
        response.put("role", "AGENT");
        response.put("token", token);

        return response;
    }

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }
}