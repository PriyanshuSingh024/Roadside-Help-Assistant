
package com.roadassistant.backend.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.roadassistant.backend.dto.AgentLoginRequest;
import com.roadassistant.backend.dto.AgentRegisterRequest;
import com.roadassistant.backend.model.Agent;
import com.roadassistant.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AgentRepository agentRepository;

    public String registerAgent(AgentRegisterRequest request) {
        Optional<Agent> existingAgent = agentRepository.findByEmail(request.getEmail());

        if (existingAgent.isPresent()) {
            return "Agent email already exists";
        }

        Agent agent = new Agent();
        agent.setName(request.getName());
        agent.setEmail(request.getEmail());
        agent.setPassword(encoder.encode(request.getPassword()));
        agent.setPhone(request.getPhone());
        agent.setStatus("ACTIVE");
        agent.setRole("AGENT");

        agentRepository.save(agent);
        return "Agent registered successfully";
    }

    public Agent loginAgent(AgentLoginRequest request) {
        Optional<Agent> agentOptional = agentRepository.findByEmail(request.getEmail());

        if (agentOptional.isPresent()) {
            Agent agent = agentOptional.get();

            if (encoder.matches(request.getPassword(), agent.getPassword())) {
    return agent;
}
        }

        return null;
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }
}