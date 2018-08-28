
package com.leysoft.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import com.leysoft.exception.PersonException;
import com.leysoft.model.Person;
import com.leysoft.processor.DatabaseProcessor;
import com.leysoft.processor.MailProcessor;
import com.leysoft.processor.PersonProcessor;

@Component
public class RabbitMqCamelRoute extends RouteBuilder {

    @Value(
            value = "${route.from.rabbit-mq}")
    private String fromRabbitMq;

    @Value(
            value = "${route.to.db}")
    private String toDb;

    @Autowired
    private PersonProcessor personProcessor;

    @Autowired
    private DatabaseProcessor databaseProcessor;

    @Autowired
    private MailProcessor mailProcessor;

    @Override
    public void configure() throws Exception {
        DataFormat dataFormat = new JacksonDataFormat(Person.class);
        onException(PersonException.class).log(LoggingLevel.ERROR, "Error: PersonException ${body}")
                .process(mailProcessor);
        onException(MailException.class).log(LoggingLevel.ERROR, "Error: MailException ${body}");
        onException(Exception.class).log(LoggingLevel.ERROR, "Error: Exception ${body}");
        from(fromRabbitMq).transacted().log("body -> ${body}").unmarshal(dataFormat)
                .process(personProcessor).process(databaseProcessor).log("body -> ${body}")
                .to(toDb);
    }
}
