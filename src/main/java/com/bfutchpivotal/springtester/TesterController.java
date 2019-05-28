package com.bfutchpivotal.springtester;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesterController {

    @GetMapping("/index")
    public String getIndex() {
        String indexVal = System.getenv( "CF_INSTANCE_INDEX" );
        return "indexVal : " + indexVal;
    }
}
