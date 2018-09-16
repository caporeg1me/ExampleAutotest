package helpers;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonConverter {

    public static void toFile(String file, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(file), object);
        System.out.println("json created!");
    }

    public static Object jsonToObject(String json, Class object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
        return mapper.readValue(json, object);
    }

    public static List jsonToListObject(String json, Class objClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, objClass));
        //return mapper.readValue(json, new TypeReference<List<Object>>(){});
    }

    public static List fileToListObject(InputStream jsonFile, Class objClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(jsonFile, mapper.getTypeFactory().constructCollectionType(List.class, objClass));
    }

    public static List fileToListObject(String jsonFile, Class objClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(new File(jsonFile), mapper.getTypeFactory().constructCollectionType(List.class, objClass));
    }

    public static Object fileToObject(String jsonFile, Class object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        //mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return mapper.readValue(new File(jsonFile), object);
    }
}
