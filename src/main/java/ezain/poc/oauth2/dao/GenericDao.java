package ezain.poc.oauth2.dao;

import java.util.List;
import java.util.Map;

/**
 * Generic interface for objectify
 * <p/>
 * Created on 30/12/13
 */
public interface GenericDao<T> {

    public List<T> getEntities();

    public List<T> getEntities(Map<String, Object> keyValues);

    public List<T> getEntities(Map<String, Object> keyValues, int limit);

    public List<T> getEntities(Map<String, Object> keyValues, String sortParam);

    public List<T> getEntities(Map<String, Object> keyValues, String sortParam, int limit);

    public T getEntity(String column, String value);

    public T getEntity(Map<String, Object> keyValues);

    public T getEntityById(String id);

    public void insertEntity(T object);

    public void insertEntities(List<T> objects);

    public void deleteEntity(Long id);

    public void deleteEntity(String id);

    public void deleteEntities(List<T> entities);

}
