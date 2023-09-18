package com.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalFormatSerializer extends JsonSerializer<Object> {
   private static final BigDecimal TEMP = BigDecimal.valueOf(1000000L);

   public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      if (value != null) {
         BigDecimal bigDecimal = new BigDecimal(value.toString());
         gen.writeNumber(bigDecimal.divide(TEMP, 2, RoundingMode.DOWN));
      }

   }
}
