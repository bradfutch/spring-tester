package com.bfutchpivotal.springtester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesterController {

    @Value("${mount.dir}")
    private String mountDir;

    @GetMapping("/index")
    public String getIndex() {
        String indexVal = System.getenv( "CF_INSTANCE_INDEX" );
        return "indexVal : " + indexVal;
    }

    @GetMapping("/files")
    public List<String> getFiles() {

        List<String> results = new ArrayList<String>();

        try (Stream<Path> files = Files.list(Paths.get(mountDir)) ) {

            results = files.filter(Files::isRegularFile)
                .map(x -> x.toString()).collect(Collectors.toList());
    
            //result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return results;
        }
    }
}
