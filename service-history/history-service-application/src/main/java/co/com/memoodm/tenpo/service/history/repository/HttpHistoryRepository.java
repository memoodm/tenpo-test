package co.com.memoodm.tenpo.service.history.repository;

import co.com.memoodm.tenpo.service.history.entity.HttpEventEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HttpHistoryRepository extends PagingAndSortingRepository<HttpEventEntity, String>
{
}
