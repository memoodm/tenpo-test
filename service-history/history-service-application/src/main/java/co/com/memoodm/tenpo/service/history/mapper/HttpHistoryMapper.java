package co.com.memoodm.tenpo.service.history.mapper;

import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import co.com.memoodm.tenpo.service.history.dto.HttpHistory;
import co.com.memoodm.tenpo.service.history.entity.HttpEventEntity;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;

public class HttpHistoryMapper
{

    public static HttpHistory paginatedToDto(Page page)
    {
        return HttpHistory.builder()
                .page( page.getNumber() )
                .lastPage( page.getTotalPages() - 1 )
                .totalPages( page.getTotalPages() )
                .results( Lists.newArrayList( page.iterator() ) )
                .build();
    }

    public static HttpEvent entityToDto(HttpEventEntity entity)
    {
        return HttpEvent.builder()
                .id( entity.getSleuth() )
                .endpoint( entity.getEndpoint() )
                .httpMethod( entity.getHttpMethod() )
                .requestDate( entity.getRequestDate() )
                .responseDate( entity.getResponseDate() )
                .requestJson( entity.getRequest() )
                .responseJson( entity.getResponse() )
                .build();
    }

    public static HttpEventEntity dtoToEntity(HttpEvent dto)
    {
        return HttpEventEntity.builder()
                .id(dto.getId())
                .sleuth(dto.getSleuth())
                .application(dto.getApplication())
                .endpoint(dto.getEndpoint())
                .httpMethod(dto.getHttpMethod())
                .request(dto.getRequestJson())
                .response(dto.getResponseJson())
                .requestDate(dto.getRequestDate())
                .responseDate(dto.getResponseDate())
                .error(dto.getError())
                .build();
    }

}
