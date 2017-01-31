package ee.ttu.spring.rest.api;

import ee.ttu.spring.rest.engine.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test() {
        return testService.getMessage();
    }
}
