package co.com.memoodm.tenpo.service.history.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpHistory
{

    private List<HttpEvent> results;

    private Integer page;
    private Integer lastPage;
    private Integer totalPages;

}
