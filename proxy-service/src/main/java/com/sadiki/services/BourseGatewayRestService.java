package com.sadiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created by X-MART on 6/28/2017.
 */
@RestController
public class BourseGatewayRestService {
    private RestTemplate restTemplate=new RestTemplate();

    @RequestMapping("/names")
    public Collection<Societe> listSociete() {
        ParameterizedTypeReference<Resources<Societe>> responseType =
                new ParameterizedTypeReference<Resources<Societe>>() {};
        return restTemplate.exchange(
                "http://societe-service/societes",
                HttpMethod.GET,
                null, responseType).getBody().getContent();
    }
}

class Societe {
    private Long id;
    private String nomSociete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }
}