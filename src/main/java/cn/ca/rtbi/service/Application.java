package cn.ca.rtbi.service;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	private static final String dateFormat = "yyyy-MM-dd";
//	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
//
//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//		return builder -> {
//			builder.simpleDateFormat(dateTimeFormat);
//			//builder.serializers(new OffsetDateTimeSerializer(OffsetDateTimeSerializer.INSTANCE));
//		};
//	}

}
