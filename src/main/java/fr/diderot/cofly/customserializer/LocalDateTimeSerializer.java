package fr.diderot.cofly.customserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime arg0, JsonGenerator arg1, 
            SerializerProvider arg2) throws IOException {
        arg1.writeString(arg0.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm")));
    }

}
