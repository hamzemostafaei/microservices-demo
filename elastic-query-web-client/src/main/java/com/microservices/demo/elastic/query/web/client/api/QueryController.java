package com.microservices.demo.elastic.query.web.client.api;

import com.microservices.demo.elastic.query.web.client.model.ElasticQueryWebClientRequestModel;
import com.microservices.demo.elastic.query.web.client.model.ElasticQueryWebClientResponseModel;
import com.microservices.demo.elastic.query.web.client.service.IElasticQueryWebClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QueryController {

    private final IElasticQueryWebClient elasticQueryWebClient;

    @GetMapping("")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("authentication", authentication);
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("favicon.ico")
    public ResponseEntity<Void> returnNoFavicon() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("elasticQueryWebClientRequestModel", ElasticQueryWebClientRequestModel.builder().build());
        model.addAttribute("authentication", authentication);
        return "home";
    }

    @PostMapping("/query-by-text")
    public String queryByText(@Valid ElasticQueryWebClientRequestModel requestModel, Model model) {

        log.info("Querying with text {}", requestModel.getText());

        List<ElasticQueryWebClientResponseModel> responseModels = elasticQueryWebClient.getDataByText(requestModel);

        model.addAttribute("elasticQueryWebClientResponseModels", responseModels);
        model.addAttribute("searchText", requestModel.getText());
        model.addAttribute("elasticQueryWebClientRequestModel",
                ElasticQueryWebClientRequestModel.builder().build());
        return "home";
    }

}
