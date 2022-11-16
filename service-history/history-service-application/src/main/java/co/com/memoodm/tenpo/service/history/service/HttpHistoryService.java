package co.com.memoodm.tenpo.service.history.service;

import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import co.com.memoodm.tenpo.service.history.dto.HttpHistory;
import co.com.memoodm.tenpo.service.history.entity.HttpEventEntity;
import co.com.memoodm.tenpo.service.history.exceptions.ExceedTopLimitResultsPerPageException;
import co.com.memoodm.tenpo.service.history.mapper.HttpHistoryMapper;
import co.com.memoodm.tenpo.service.history.repository.HttpHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HttpHistoryService
{

    @Value("${application.setup.limits.elementsByPageLimit}")
    protected Integer elementsByPageLimit = 100;

    private HttpHistoryRepository httpHistoryRepository;

    @Autowired
    public HttpHistoryService(HttpHistoryRepository httpHistoryRepository)
    {
        this.httpHistoryRepository = httpHistoryRepository;
    }

    public HttpHistory getAllPaginated(Integer itemsPerPage, Integer page ) throws ExceedTopLimitResultsPerPageException
    {
        if(itemsPerPage > elementsByPageLimit)
        {
            throw new ExceedTopLimitResultsPerPageException("Limit: "+elementsByPageLimit);
        }
        Pageable paginatedSearch = PageRequest.of( page, itemsPerPage );
        Page result = httpHistoryRepository.findAll(paginatedSearch);
        return HttpHistoryMapper.paginatedToDto( result );
    }

    public synchronized void saveOrUpdate(HttpEvent dto)
    {
        HttpEventEntity entity = HttpHistoryMapper.dtoToEntity(dto);
        if(httpHistoryRepository.existsById(dto.getId()))
        {
            HttpEventEntity entityFromDB = httpHistoryRepository.findById( entity.getId() ).get();
            entityFromDB.updateNullValues( entity );
            httpHistoryRepository.save( entityFromDB );
        }
        else
        {
            httpHistoryRepository.save( entity );
        }
    }
}
