package com.jannchie.biliob.controller;

import com.jannchie.biliob.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Get the global information of bilibili.
 *
 * @author jannchie
 */
@RestController
public class SiteController {

    private final SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/site")
    public ResponseEntity<?> listOnline(@RequestParam(defaultValue = "1") Integer days) {
        return siteService.listOnline(days);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/api/site/count")
    public Map getBiliOBCount() {
        return siteService.getBiliOBCounter();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/site/video/count")
    public Map getVideoCount() {
        return siteService.getVideoCount();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/site/author/count")
    public Map getAuthorCount() {
        return siteService.getAuthorCount();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/site/user/count")
    public Map getUserCount() {
        return siteService.getUserCount();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/site/alert")
    public Map getAlert() {
        return siteService.getAlert();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/site/alert")
    public ResponseEntity<?> postAlert() {
        return siteService.postAlert();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/site/sponsor")
    public ResponseEntity<?> listSponsor(@RequestParam(value = "sort", defaultValue = "0") Integer sort,
                                         @RequestParam(value = "p", defaultValue = "1") Integer page,
                                         @RequestParam(value = "ps", defaultValue = "20") Long pageSize

    ) {
        return ResponseEntity.ok(siteService.listSponsor(page, pageSize, sort));
    }

}
