package org.delivery.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolInterface;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSseConnection {

    private final String uniqueKey;

    private final SseEmitter sseEmitter;

    private final ConnectionPoolInterface<String, UserSseConnection> connectionPoolInterface;

    private final ObjectMapper objectMapper;

    private UserSseConnection(
            String uniqueKey,
            ConnectionPoolInterface<String, UserSseConnection> connectionPoolInterface,
            ObjectMapper objectMapper
    ){
        //key초기화
        this.uniqueKey = uniqueKey;

        //sse초기화
        this.sseEmitter = new SseEmitter(1000L * 60);

        //callBack초기화
        this.connectionPoolInterface = connectionPoolInterface;

        //object mapper초기화
        this.objectMapper = objectMapper;

        //oncompletion
        this.sseEmitter.onCompletion(()->{
            this.connectionPoolInterface.onCompletionCallback(this);
        });

        //on timeout
        this.sseEmitter.onTimeout(()->{
            this.sseEmitter.complete();
        });

        this.sendMessage("onopen", "connect");

    }

    public static UserSseConnection connect(
            String uniqueKey,
            ConnectionPoolInterface<String, UserSseConnection> connectionPoolInterface,
            ObjectMapper objectMapper){
        return new UserSseConnection(uniqueKey, connectionPoolInterface, objectMapper);
    }

    public void sendMessage(String eventName, Object data)  {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .name(eventName)
                    .data(json);

            this.sseEmitter.send(event);
        }catch (IOException e){
            this.sseEmitter.completeWithError(e);
        }

    }
    public void sendMessage(Object data){
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .data(json);
            this.sseEmitter.send(event);
        }catch (IOException e){
            this.sseEmitter.completeWithError(e);
        }
    }
}
