package neo4j.services;

import neo4j.repositories.DatasetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class DatasetService {

    @Autowired DatasetRepository datasetRepository;

    private Map<String, Object> toAlcFormat(Iterator<Map<String, Object>> result) {
        List<Map<String,Object>> nodes = new ArrayList<Map<String, Object>>();
        List<Map<String,Object>> rels = new ArrayList<Map<String, Object>>();
        int i = 1;
        int target = 0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map6("id", i, "title",row.get("dataset"),"label", "dataset", "cluster", "1", "value", 2, "group", "dataset"));
            target = i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> author = map5("title", 
                		name,"label", "user", "cluster", "2", "value", 1, "group", "user");
                int source = 0;
                for (int j = 0; j < nodes.size(); j++) {
                	if (nodes.get(j).get("title").equals(name)) {
                		source = (int) nodes.get(j).get("id");
                		break;
                	} 
                }
                if (source == 0) {
                	author.put("id", i);
                    source = i;
                    i++;
                    nodes.add(author);
                }

                rels.add(map3("from", source, "to", target, "title", "USE"));
            }
        }
        return map("nodes", nodes, "edges", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String,Object>(2);
        result.put(key1,value1);
        result.put(key2,value2);
        return result;
    }
    
    private Map<String, Object> map3(String key1, Object value1, String key2, Object value2, 
    		String key3, Object value3) {
        Map<String, Object> result = new HashMap<String,Object>(3);
        result.put(key1,value1);
        result.put(key2,value2);
        result.put(key3, value3);
        return result;
    }

    private Map<String, Object> map5(String key1, Object value1, String key2, Object value2, 
    		String key3, Object value3, String key4, Object value4, String key5, Object value5) {
        Map<String, Object> result = new HashMap<String,Object>(5);
        result.put(key1,value1);
        result.put(key2,value2);
        result.put(key3, value3);
        result.put(key4, value4);
        result.put(key5, value5);
        return result;
    }
    
    private Map<String, Object> map6(String key1, Object value1, String key2, Object value2, 
    		String key3, Object value3, String key4, Object value4, String key5, Object value5,
    		String key6, Object value6) {
        Map<String, Object> result = new HashMap<String,Object>(6);
        result.put(key1,value1);
        result.put(key2,value2);
        result.put(key3, value3);
        result.put(key4, value4);
        result.put(key5, value5);
        result.put(key6, value6);
        return result;
    }
    
    public Map<String, Object> graphAlc(int limit) {
        Iterator<Map<String, Object>> result = datasetRepository.graph(limit).iterator();
        return toAlcFormat(result);
    }
}

