package blnk.witaxi.exceptions;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Produces(MediaType.APPLICATION_JSON)
public record BlnkError(String error) {
}
