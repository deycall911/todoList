package hello;

import hello.Entity.ToDoEntity;
import hello.Repository.ToDoEntityRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RestCallsControllerTest {
    private RestCallsController restCallsController = new RestCallsController();
    private ToDoEntity jobEntity = new ToDoEntity();
    private ToDoEntityRepository toDoEntityRepository;

    private List<ToDoEntity> allEntities;
    private List<ToDoEntity> doneList;
    private List<ToDoEntity> notDoneList;

    @Before
    public void setUp() throws Exception {
        jobEntity.setId(0);
        jobEntity.setDone(false);
        jobEntity.setContent("content");

        toDoEntityRepository = mock(ToDoEntityRepository.class);
        restCallsController.toDoEntityRepository = toDoEntityRepository;
        when(toDoEntityRepository.save(any(ToDoEntity.class))).thenReturn(jobEntity);
        when(toDoEntityRepository.findById(0)).thenReturn(jobEntity);

        ToDoEntity doneEntity = new ToDoEntity();
        doneEntity.setDone(true);
        doneEntity.setId(9);
        doneEntity.setContent("this is done");
        doneList = Lists.newArrayList(doneEntity);
        when(toDoEntityRepository.findByDone(true)).thenReturn(doneList);

        ToDoEntity notDoneEntity =  new ToDoEntity();
        doneEntity.setContent("not done");
        doneEntity.setDone(false);
        doneEntity.setId(1);
        notDoneList = Lists.newArrayList(notDoneEntity);
        when(toDoEntityRepository.findByDone(false)).thenReturn(notDoneList);

        allEntities = Lists.newArrayList(notDoneEntity, doneEntity);
        when(toDoEntityRepository.findAll()).thenReturn(allEntities);

    }

    @Test
    public void insert() throws Exception {
        assertEquals(restCallsController.insert("content"), jobEntity);
    }

    @Test
    public void delete() throws Exception {
        int rnd = new Random().nextInt();
        restCallsController.delete(rnd);
        verify(toDoEntityRepository, times(1)).delete(rnd);
    }

    @Test
    public void requestList() throws Exception {
        assertEquals(restCallsController.requestList(null), allEntities);
        assertEquals(restCallsController.requestList(true), doneList);
        assertEquals(restCallsController.requestList(false), notDoneList);
    }

    @Test
    public void markAsDone() throws Exception {
        assertEquals(restCallsController.markAsDone(0, false), jobEntity);
        assertNull(restCallsController.markAsDone(1, false));
    }

}