package fr.diderot.cofly.customserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser arg0,
            DeserializationContext arg1) throws IOException {
        System.out.println("arg0 = " + arg0.getText());
        return LocalDateTime.parse(arg0.getText(), DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm"));
    }

}
