package org.delivery.api.config.rabbitmq;


/*@Configuration
public class RabbitmqConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("delivery.exchange");
    }

    @Bean
    public Queue queue(){
        return new Queue("delivery.queue");
    }

    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }

    //end queue 설정
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ){
        var rabbitTemplete = new RabbitTemplate(connectionFactory);
        rabbitTemplete.setMessageConverter(messageConverter);
        return rabbitTemplete;
    }

    @Bean
    public  MessageConverter messageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}*/
