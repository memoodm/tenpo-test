package co.com.memoodm.tenpo.service.history.controller;

import co.com.memoodm.tenpo.service.history.dto.HttpHistory;
import co.com.memoodm.tenpo.service.history.service.HttpHistoryService;
import co.com.memoodm.tenpo.service.history.web.client.HttpHistoryClient;
import co.com.memoodm.tenpo.service.history.web.endpoint.HistoryServiceEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HistoryServiceEndpoints.PATH_HTTP_HISTORIES)
public class HttpHistoryController implements HttpHistoryClient
{

    private HttpHistoryService httpHistoryService;

    @Autowired
    public HttpHistoryController(HttpHistoryService httpHistoryService)
    {
        this.httpHistoryService = httpHistoryService;
    }

    @Override
    public ResponseEntity<HttpHistory> getAllPaginated(Integer itemsPerPage, Integer page) {
        return new ResponseEntity<HttpHistory>(
                this.httpHistoryService.getAllPaginated( itemsPerPage, page ),
                HttpStatus.OK
        );
    }

}
