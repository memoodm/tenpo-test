package co.com.memoodm.tenpo.service.history.service;

import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import co.com.memoodm.tenpo.service.history.dto.HttpHistory;
import co.com.memoodm.tenpo.service.history.entity.HttpEventEntity;
import co.com.memoodm.tenpo.service.history.exceptions.ExceedTopLimitResultsPerPageException;
import co.com.memoodm.tenpo.service.history.repository.HttpHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class HttpHistoryServiceTest
{

    @Mock
    HttpHistoryRepository httpHistoryRepository;

    private HttpHistoryService httpHistoryService;

    @BeforeEach
    public void setup()
    {
        this.httpHistoryService = new HttpHistoryService(httpHistoryRepository);
        this.httpHistoryService.elementsByPageLimit = 100;

        List<HttpEventEntity> entities = List.of(
                HttpEventEntity.builder().id("id1").build(),
                HttpEventEntity.builder().id("id2").build(),
                HttpEventEntity.builder().id("id3").build()
        );
        PageImpl<HttpEventEntity> page = new PageImpl(entities);
        Mockito.when(httpHistoryRepository.findAll( any(Pageable.class) ))
                .thenReturn(page);

        Mockito.when(httpHistoryRepository.existsById( "id1" ))
                .thenReturn(false);

        Mockito.when(httpHistoryRepository.existsById( "id2" ))
                .thenReturn(true);

        Mockito.when(httpHistoryRepository.findById( "id2" ))
                .thenReturn(Optional.ofNullable(HttpEventEntity.builder().id("id2").build()));
    }

    @Test
    public void shouldGetAllPaginated()
    {
        HttpHistory history = this.httpHistoryService.getAllPaginated(10,0);
        assertTrue( history.getResults().size() == 3 );
    }

    @Test
    public void shouldFailExceedLimitValues()
    {
        Exception exception = assertThrows(ExceedTopLimitResultsPerPageException.class, () -> {
            HttpHistory history = this.httpHistoryService.getAllPaginated(99999,0);
        });
        assertTrue( exception instanceof ExceedTopLimitResultsPerPageException );
    }

    @Test
    public void shouldCallSaveNewValue()
    {
        HttpEvent event = HttpEvent.builder().id("id1").build();
        this.httpHistoryService.saveOrUpdate(event);
        verify( httpHistoryRepository, times(1) ).save( any() );
    }

    @Test
    public void shouldCallUpdateValue()
    {
        HttpEvent event = HttpEvent.builder().id("id2").build();
        this.httpHistoryService.saveOrUpdate(event);
        verify( httpHistoryRepository, times(1) ).findById( any() );
        verify( httpHistoryRepository, times(1) ).save( any() );
    }

}
