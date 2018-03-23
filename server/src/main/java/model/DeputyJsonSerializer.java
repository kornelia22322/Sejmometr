package model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class DeputyJsonSerializer extends JsonSerializer<Deputy> {

    @Override
    public void serialize(Deputy deputy, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", deputy.getName());
        jsonGenerator.writeStringField("id", Integer.toString(deputy.getId()));
        jsonGenerator.writeEndObject();
    }
}