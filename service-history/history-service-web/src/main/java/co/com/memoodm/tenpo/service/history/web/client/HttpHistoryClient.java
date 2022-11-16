package co.com.memoodm.tenpo.service.history.web.client;

import co.com.memoodm.tenpo.service.history.dto.HttpHistory;
import co.com.memoodm.tenpo.service.history.web.endpoint.HistoryServiceEndpoints;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = HistoryServiceEndpoints.ID_HISTORY_SERVICE, path= HistoryServiceEndpoints.PATH_HTTP_HISTORIES)
@LoadBalancerClient(name = HistoryServiceEndpoints.ID_HISTORY_SERVICE)
public interface HttpHistoryClient
{
    @GetMapping()
    ResponseEntity<HttpHistory> getAllPaginated(@RequestParam(name = "itemsPerPage") Integer itemsPerPage, @RequestParam(name = "page") Integer page);

}
