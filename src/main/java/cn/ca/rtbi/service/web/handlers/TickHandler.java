package cn.ca.rtbi.service.web.handlers;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalTime;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Component
public class TickHandler {

    private final Sinks.Many<Long> trigger = Sinks.many().multicast().directBestEffort();

    public Mono<ServerResponse> tick(ServerRequest request) {
        trigger.emitNext(0L, FAIL_FAST);
        return ServerResponse.noContent().build();
    }

    public Mono<ServerResponse> subscribeTick(ServerRequest request){

        Flux<ServerSentEvent<String>> sse = trigger.asFlux().map(sequence -> ServerSentEvent.<String>builder()
                .id(String.valueOf(sequence))
                .event("periodic-event")
                .data("SSE - " + LocalTime.now().toString())
                .build());

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(BodyInserters.fromServerSentEvents(sse));
    }

}
