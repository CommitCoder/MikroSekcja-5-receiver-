package com.kuba.receiver;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ReceiverController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/message")
    public String receiveMessage(){
        Object message = rabbitTemplate.receiveAndConvert("kurs");

        if(message!=null){
            return "We have received a message" + message.toString();
        }else{
            return "There is no message to read";
        }


    }

    @RabbitListener(queues = "kurs")
    public void listenerMessage(String message){
        System.out.println(message);
    }





}
